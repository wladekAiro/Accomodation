Spring mvc application for pension system
# Requirements

* Java 1.7 of higher
    * please set JAVA_HOME environment variable 
* Maven
    *maven version 3.0.3 or higher
*Internet connection
    *This is important for maven to download dependecies from maven centrall in initial project set up
# Database Mysql database
   1.Create database named  `db_accomodation`
   2.To set db password open file named application.yml.(path from project root = src/main/resources/application.yml)

#Log In
  1.ADMIN
     username  'ochiwladek'
     password  'ochiwladek.'//Take note of the fullstop.

  2.STUDENT
    a) Account 1
        username 'teststudent'
        password 'pass'
    b)Account 2
        username 'mike'
        password 'pass'

 #Student sign up
   New students can sign up into the system using the sign up link.
   They complete their profiles before they can start booking.
# How to run
  *Import the sql file provided named init.sql (path= project root directory).
  *From a linux terminal or windows command prompt, navigate to project root directory of the extracted zip file and
   run mvn spring-boot:run
  *From the browser view the project using this link http://localhost:8080

## Packaging
  1. `mvn package`
