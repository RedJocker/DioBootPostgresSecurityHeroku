- initial commit
    - initialized a project with spring initializr
        - spring web
        - spring boot dev tools
    
    
- fix live reload
    - created a HelloController
    - live reload was not working
    - fixing live reload
        - enabled 'build project automatically'
            - preferences -> build, execution, deployment -> compiler
        - edited registry
            - look for registry on the search tool bar
            - edit compiler.automake.allow.when.app.running
        - installed plugin for live reload on firefox
        - activated plugin on the page
    - live reload working  


- defined ports
    - application.properties
        - server.port 8080
    - CloudParkingApplicationTests
        - webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT


- heroku initial configuration
    - created system.properties
        - java.runtime.version=11
    - link heroku app 
        - https://dio-boot-web-postgress-securit.herokuapp.com/hello
    

- parking resource
    - created model.Parking
    - created controller.Parking.Controller
        - 1 endpoint
            - GET listAll()
                - hardcoded return for testing purposes
                - https://dio-boot-web-postgress-securit.herokuapp.com/parking
    

- parking service
    - created service.ParkingService
    - removed mock data from controller to service
    - added constructor on model Parking


- parking dto
    - created ParkingDTO
    - chosen a different implementation to convert dto to entity from tutors implementation
        - instead of adding a dependency and creating a class mapper I created conversion 
          methods on the ParkingDTO class
        - dependency implemented by tutor was org.modelmapper modelmapper 2.3.5
    - filtered null values from response
        - @JsonInclude(JsonInclude.Include.NON_NULL)


- endpoint findById
    - GET findById(String id)
        - ParkingService.findById
        - https://dio-boot-web-postgress-securit.herokuapp.com/parking/{id}
    - minor change on ParkingService.findAll
        - changed to return stream instead of list
  
  
- endpoint create
    - POST create
        - ParkingService.create
        - created ParkingPostRequestDTO
        - renamed ParkingDTO to ParkingResponseDTO
        - https://dio-boot-web-postgress-securit.herokuapp.com/parking
    - transferred creation logic to ParkingPostRequestDTO
        - id and entryDate attributed during conversion from ParkingRequestDTO to Parking
    

- inclusion of swagger
    - SwaggerConfig
    - updated HelloController
        - @ApiIgnore
    - updated ParkingController
        - @Api(tags = "Parking Controller")
        - @ApiOperation("end point description")
    - https://dio-boot-web-postgress-securit.herokuapp.com/swagger-ui.html
    

- id not found exception treatment
    - created IdNotFoundException extends RunTimeException
    - treated exception 
        - with @ResponseStatus(HttpStatus.NOT_FOUND) on IdNotFoundException
    - updated ParkingController to throw exception if not found
        - using Optional.orElseThrow
    - application.properties
        - server.error.include-message=always
    
    
- renaming of classes and methods based on "ubiquitous language" concept and creation of DELETE endpoint
    
    - DELETE checkout()
        - use case: car checking out of the parking
        - returns all the information of the car, including exit time and bill to be paid
    - CarCheckoutDto
        - use case: models the information about checking out cars
            - marks exit time on construction
            - calculates bill in function of entryDate and exitDate
                - calculateBill()
    
    - renaming
        - ParkingController -> ParkingSupervisor
            - use case: used by park supervisors to monitor parked cars
        - ParkingPostRequestDTO -> IngressingCarDto
            - use case: models a car coming in and going to be parked
                - toParking()
        - ParkingResponseDTO -> CarDetailsDto
            - use case: models information to be delivered about parked cars


- --fix ParkingSupervisor, improved logic for billing
    - didn't test before committing :(
        - don't do it
    - fixed ParkingSupervisor DELETE endpoint
    - fixed incorrect paths
    - excluded unnecessary fields on CarDetailsDto
    - improved logic for billing
        - static final variables
            - FIXED_VALUE
            - HOURLY_RATE
            - MAX_MINUTES_EXCEPTION


- little improvements
    - removed unnecessary fields on Parking entity
        - exitDate and bill makes sense only when checking out
        - dtos that depended on Parking constructor changed accordingly
    - improved mock data initialization readability on ParkingService
    - improved POST ParkingService.carEntry readability
    

- little improvements 2
    - changed entity field from private to public final
        - if the field is final I don't have to worry with encapsulation
            - set and get method not needed anymore
    - other classes that depended on Parking getters and setters changed accordingly
    

- little improvements 3
    - renamed ParkingService.create to ParkingService.registerCar
    

- created PUT updateCarInfo
    - use case: fix incorrect registration info
        - entryTime not updatable
    - created FixCarInfoDto
    - created CarInfoInconsistencyPreventionException
    - created ParkingService.updateInfo()
    

- a little bit of testing
    - inclusion of dependency restAssured
    - creation of ParkingSupervisorIntegrationTest
        - listAllCars_whenGetThenStatusOK()
        - carEntry_whenValidBodyThenStatusCreated()
    

- adding postgres
    - dependency included
        - spring-boot-starter-data-jpa
        - postgresql
    - included properties for connection on application.properties
    - written docker-compose.yml to run local db on a container
    - docker compose up
    - application connects successfully
    

- persisting data with postgres
    - inclusions on application.properties
        - spring.jpa.show-sql=true
        - spring.jpa.hibernate.dll-auto=create-drop
    - updates on entity Parking
        - @Entity
        - Jpa needs defaultConstructor
        - Jpa needs non final fields
            - Dtos had to be updated to use getters
    - created interface ParkingRepository
        - extends JpaRepository
    - updated ParkingService
        - substituted mockData hash map for repository
        - updated methods to use repository
    - seems to be working ok
    - heroku database addon installed
     


- security dependency added
    - spring-boot-starter-security
    - spring-security-test
    
        
    


      
    

  
    
        
    
        
            