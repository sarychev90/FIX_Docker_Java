# FIX DOCKER JAVA SPRING BOOT

This is a simple dockerized project to implement a Java Spring Boot client and server communication through a FIX protocol using quickfixj libs. For more details please use link: https://www.quickfixj.org/usermanual/2.3.0/usage/application.html

## What you need to run the application
1. Clone project from GitHub
2. Starting Docker on your machine
3. Run the command line from the parent folder of the project
4. Run the following command:

	docker-compose up

Note: for sending data you may use API of fix client controller. A Swagger-based OpenAPI interface is available in the project. After deploying the project, the user can get acquainted in detail with the structure of the REST services methods at the link: http://localhost:8081/swagger-ui.html