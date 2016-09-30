Spring mvc application for pension system
# Requirements

* Java 1.7
    * please set JAVA_HOME environment variable 
* Maven
    *maven version 3.0.3 or higher
*Internet connection
    *This is important for maven to download dependecies from maven centrall in initial project set up
# Database Mysql database
   1.Create database named  `db_accomodation`
   2.To set db password open file named application.yml.(path from project root = src/main/resources/application.yml)

# How to run
  *Import the sql file provided named init.sql (path= project root directory).
  *From a linux terminal or windows command prompt, navigate to project root directory of the ectracted zip file and
   run mvn spring-boot:run
  *From the browser view the project using this link http://localhost:8080

## Packaging
  1. `mvn package`
