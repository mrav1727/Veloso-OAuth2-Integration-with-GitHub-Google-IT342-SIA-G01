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


## System Architecture
The system follows a layered MVC architecture with OAuth2 security integration:

┌──────────────────────────────────────────────────────┐
│                    Frontend (View)                   │
│   REACTJS / HTML         (login, dashboard)          │
└──────────────────────────────────────────────────────┘
                     │
                     ▼
┌──────────────────────────────────────────────────────┐
│                Controller Layer                      │
│  Handles user requests and OAuth2 callbacks          │
│  (LoginController, DashboardController)              │
└──────────────────────────────────────────────────────┘
                     │
                     ▼
┌──────────────────────────────────────────────────────┐
│                Service Layer                         │
│  Business logic, OAuth2 user handling                │
│  (CustomOAuth2UserService)                           │
└──────────────────────────────────────────────────────┘
                     │
                     ▼
┌──────────────────────────────────────────────────────┐
│                Repository Layer                      │
│  Database access using JPA Repositories              │
│  (UserRepository, AuthProviderRepository)            │
└──────────────────────────────────────────────────────┘
                     │
                     ▼
┌──────────────────────────────────────────────────────┐
│                Data Layer                            │
│  MySQL database storing user and provider data       │
└──────────────────────────────────────────────────────┘


## Project Structure


frontend/ # React Frontend
│ ├── src/
│ │ ├── components/ # Reusable UI Components
│ │ ├── pages/ # Login & Profile Pages
│ │ ├── App.js # Root Component
│ │ └── index.js # Entry Point
│ ├── package.json
│ └── README.md

spring-oauth2-demo/ # Spring Boot Backend
src/
└── main/
    ├── java/com/example/demo/
    │   ├── config/
    │   │   └── SecurityConfig.java             # Spring Security + OAuth2 setup
    │   ├── controller/
    │   │   ├── DashboardController.java        # Manages user dashboard after login
    │   │   ├── ErrorController.java            # Handles authentication and access errors
    │   │   ├── HomeController.java             # Displays home and landing pages
    │   │   ├── LoginController.java            # Handles OAuth2 login requests
    │   │   └── ProfileController.java          # Manages user profile view/update
    │   ├── model/
    │   │   ├── AuthProvider.java               # Model for supported OAuth providers
    │   │   └── User.java                       # Entity for authenticated user info
    │   ├── repository/
    │   │   ├── AuthProviderRepository.java     # Data access for OAuth provider info
    │   │   └── UserRepository.java             # Data access for user records
    │   ├── service/
    │   │   └── CustomOAuth2UserService.java
    │   └── DemoApplication.java                 # Main entry point
    └── resources/
        ├── static/                              # CSS, JS, images
        ├── templates/                           # Thymeleaf templates
        ├── application.properties
        └── application.yml

## Features

-OAuth2 login with Google and GitHub
-Secure user session and profile management
-Integrated with MySQL Workbench
-Organized layered architecture (MVC + OAuth2)
-Custom user service for OAuth2 user handling
-Spring Security configuration for authentication and authorization
-Responsive UI using Thymeleaf templates and REACTJS
