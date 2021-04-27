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
    

- parking service
    - created service.ParkingService
    - removed mock data from controller to service
    - added contructon on model Parking
    
            