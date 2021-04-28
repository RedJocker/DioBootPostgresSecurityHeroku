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
        
    
        
            