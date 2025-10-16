# Veloso-OAuth2-Integration-with-GitHub-Google-IT342-SIA-G01

OAuth2 Integration with GitHub and Google
A Spring Boot application that integrates OAuth2 authentication using Google and GitHub.
The project demonstrates secure login, user data management, and connection to a MySQL Workbench database.

## How to Run the App

Prerequisites

-Java 17 or higher
-Maven 3.6+
-MySQL Workbench
-Git
-Node.js (for frontend, if applicable)

Steps to Run

1. Clone the repository
  -git clone <repository-url>
  -cd Veloso-OAuth2-Integration-with-GitHub-Google-IT342-SIA-G01
  
2. Configure OAuth2 Providers
  -Copy your credentials into application.yml or application.properties

3. Set up the Database
  -Setup MySQl Workbench

4. Run the Backend
  -Click DemoApplication.java and click the run button from the Intellij IDE

5. Run the Frontend
  -Navigate to your frontend folder (if applicable)

## Start the local server:
  -npm start
  -Open your browser and go to: http://localhost:8080

6. Login
  -Click “Login with Google” or “Login with GitHub”

## OAuth2 Providers Configured
Google OAuth2

1. Go to Google Cloud Console
2. Create OAuth2 credentials:

  -Application type: Web application
  -Authorized redirect URIs: http://localhost:8080/login/oauth2/code/google

Add to application.yml:
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: YOUR_GOOGLE_CLIENT_ID
            client-secret: YOUR_GOOGLE_CLIENT_SECRET
            redirect-uri: "{baseUrl}/login/oauth2/code/google"
            scope:
              - profile
              - email

GitHub OAuth2

1. Go to GitHub Developer Settings
2. Create a new OAuth App:
  -Authorization callback URL: http://localhost:8080/login/oauth2/code/github

Add to application.yml:
spring:
  security:
    oauth2:
      client:
        registration:
          github:
            client-id: YOUR_GITHUB_CLIENT_ID
            client-secret: YOUR_GITHUB_CLIENT_SECRET
            redirect-uri: "{baseUrl}/login/oauth2/code/github"
            scope:
              - user:email

## Database Setup

This project uses MySQL Workbench as the database.

First: USE oauth2db;
Second: SHOW TABLES;
Third: SELECT * FROM users;

Configure MySQL in application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/oauth2db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
    username: root
    password: 12345678
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL8Dialect
