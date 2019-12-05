
Uplan library for simplifying validation process in microservice architecture.

Dependency :

1) Maven 
        
        <repositories>
          <repository>
            <id>Azazelovi_4up-uplan</id>
            <url>https://packagecloud.io/Azazelovi_4up/uplan/maven2</url>
            <releases>
              <enabled>true</enabled>
            </releases>
            <snapshots>
              <enabled>true</enabled>
            </snapshots>
          </repository>
        </repositories>
        
        <dependencies>
            <dependency>
                <groupId>com.uplan</groupId>
                <artifactId>uplan-validation-lib</artifactId>
                <version>0.0.1</version>
            </dependency>
        </dependencies>
        
        
2) Gradle 

        repositories {
            maven {
                url "https://packagecloud.io/Azazelovi_4up/uplan/maven2"
            }
        }
        
        dependencies {
        compile 'com.uplan:uplan-validation-lib:0.0.1'
        }


The easiest way to use library is to inherit error messaging abstractions:

                         - Let's say we have a simple request Dto -
                         
    public class UserDto {
        private String name;
        private String email;
        
        public UserDto(String name, String email) {
            this.name = name;
            this.email = email;
        }
        
        // getters and setters...
    }
____________________________________________________________________________________________________
                         - Extend DtoValidator and override validation logic -

    public class UserDtoValidator extends DtoValidator<UserDto> {
    
        @Override
        public void validate(UserDto targetDto, List<String> errorCodes) {
            if(targetDto.getName().equals("Invalid...") {
                errorCodes.add("user.validation.name.invalid");
            }
        }
    
    }
    
____________________________________________________________________________________________________
                                      - After this validate your dto -
    
    public UserService {
    
        private DtoValidator<UserDto> userDtoValidator;
    
        @Override
        public saveUser(UserDto entity) {
            try {
                userDtoValidator.validate(entity)
                // Here MessageContainException.class will be thrown when validation failed.
             } catch(MessageContainException ex) {
                // If you want to get validation errors...
                for(String errorCode: ex.getErrors()) {
                    log.error(errorCode)
                 }
             }
        }
    
    }
    
    
More complex way is to inherit entity validation abstraction in order to separate validation error codes
and maybe add some additional fields. Let's get a look...

____________________________________________________________________________________________________
                               -Create validation response dto object-
                               
    public class UserValidationFailDto extends EntityValidationFailDto {
    
        private List<String> nameErrorCodes;
        private List<String> emailErrorCodes;
        private String someMetadata;
        
        public UserValidationFailDto() {
            nameErrorCodes = new ArrayList<>();
            emailErrorCodes = new ArrayList<>();
        }
        
        @Override
        public boolean isContainErrorCodes() {
            return !nameErrorCodes.isEmpty() || !emailErrorCodes.isEmpty();
        }
        
        // getters and setters
    
    }

____________________________________________________________________________________________________
                        - Extend EntityMappedDtoValidator and override validation logic -

    public class EntityUserDtoValidator extends EntityMappedDtoValidator<UserDto,UserValidationFailDto> {
    
        @Override
        public UserValidationFailDto validateEntity(UserDto targetDto) {
            UserValidationFailDto userValidationFailDto = new UserValidationFailDto();
            if(targetDto.getName().equals("Invalid...")) {
                userValidationFailDto.getNameErrorCodes().add("user.validation.name.invalid");
            }
            
            if(targetDto.getEmail().equals("Invalid...")) {
                userValidationFailDto.getEmailErrorCodes().add("user.validation.email.invalid");
            }
            
            userValidationFailDto.setMetadata("Some value");
            return userValidationFailDto;
        }
    
    }
____________________________________________________________________________________________________
                                    - And validate dto -
        
    public UserService {
    
        private EntityMappedDtoValidator<UserDto,UserValidationFailDto> userDtoValidator;
    
        @Override
        public saveUser(UserDto entity) {
            try {
                userDtoValidator.validate(entity)
                // Here EntityMessageContainException.class will be thrown when validation failed.
             } catch(EntityMessageContainException ex) {
                // If you want to get validation errors...
                UserValidationFailDto validationFailDto = ex.getErrors();
             }
        }
    
    }
    
But honestly, this is not entirely useful yet. It is not correctly every time catch validation exception in out 
services, facades or controllers, so you may produce special bean for it`s handling.

    @Bean
    public ResponseEntityExceptionHandler validationExceptionHandler() {
        return new ValidationExceptionHandler();
        //or ValidationExceptionHandler(new ValidationExceptionMapperImpl(), HttpStatus.BAD_REQUEST);
    }
    
Here ValidationExceptionMapperImpl() it`s just simple mapper that converts dto objects and lists to ResponseEntity<>.

In such case when your validation classes find some incorrect data - they always will be able to return 
response in JSON format to their clients.

And what about clients? The way of implementing client is the following:
____________________________________________________________________________________________________
                                - Create same validation bean in you microservice client -
                                
    public class UserValidationFailDto extends EntityValidationFailDto {
    
        private List<String> nameErrorCodes;
        private List<String> emailErrorCodes;
        private String someMetadata;
        
        public UserValidationFailDto() {
            nameErrorCodes = new ArrayList<>();
            emailErrorCodes = new ArrayList<>();
        }
        
        @Override
        public boolean isContainErrorCodes() {
            return !nameErrorCodes.isEmpty() || !emailErrorCodes.isEmpty();
        }
        
        // getters and setters
    
    }

____________________________________________________________________________________________________
                    - Define client handler bean with this dto generic -

    @Bean
    public EntityValidationFailedResponseClientHandler<UserValidationFailDto> fallbackValidationHandler() {
        return new EntityValidationFailedResponseClientHandlerImpl<>();
    }

                    - Or if it`s just a list of strings define next handler - 

    @Bean
    public ListValidationFailedResponseClientHandler fallbackValidationHandler() {
        return new ListValidationFailedResponseClientHandlerImpl();
    }
    
____________________________________________________________________________________________________
      - Use tool to your taste to get an response content when some http error occure(I use hystrix) and
       don`t forget to check http error code that is indicates a validation error - 
       
       @Autowire
       private EntityValidationFailedResponseClientHandler<UserValidationFailDto> fallbackValidationHandler;
    
      private Long insertUserFallback(UserDto newUser, Throwable hystrixRuntimeException) {
        Throwable cause = hystrixRuntimeException.getCause();
        if (cause instanceof FeignException) {
          FeignException originalException = (FeignException) cause;
          if(originalException.status() == 400) {
            fallbackValidationHandler.throwIfValidationError(originalException.contentUTF8(), UserValidationFailDto.class);
          }
        }
        return null;
      }

In all cases if it is not validation error or this validation error not corresponds to UserValidationFailDto.class
mapping - nothing will thrown, so that you can continue processing 400 http response.

And finally, it will be very convenient if you announce ResponseEntityExceptionHandler.class
in the service client, so errors can pass through it without redefinition.
 
 
     @Bean
     public ResponseEntityExceptionHandler validationExceptionHandler() {
         return new ValidationExceptionHandler();
     }
     
Hope I have a little easier development for you, good luck

