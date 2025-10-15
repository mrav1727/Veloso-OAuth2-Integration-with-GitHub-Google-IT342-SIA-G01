# Veloso-OAuth2-Integration-with-GitHub-Google-IT342-SIA-G01

Key Components

OAuth2 Authentication Flow: Handles Google and GitHub OAuth2 login and callback authentication.

User Profile Management: Displays authenticated user information such as name, email, and profile picture.

Session Management: Maintains user sessions and authentication state using Spring Security.

Frontend Integration: Connects React frontend with the Spring Boot backend API for authentication requests.

Database Integration: Persists user account and session data across authenticated logins.


Veloso-OAuth2-Integration-with-GitHub-Google-IT342-SIA-G01/
│
├── spring-oauth2-demo/                 # Backend (Spring Boot)
│   └── src/
│       └── main/
│           ├── java/com/example/oauth2login/
│           │   ├── controller/
│           │   │   └── AuthController.java          # Handles OAuth2 login & redirection
│           │   ├── model/
│           │   │   └── User.java                    # User entity model
│           │   ├── service/
│           │   │   └── UserService.java             # Handles user logic
│           │   ├── config/
│           │   │   └── SecurityConfig.java          # Spring Security configuration
│           │   └── Oauth2LoginApplication.java      # Main Spring Boot class
│           │
│           └── resources/
│               ├── application.yml                  # OAuth2 provider credentials (Google, GitHub)
│               ├── templates/                       # Optional Thymeleaf templates
│               │   ├── home.html
│               │   └── profile.html
│               └── static/                          # Static resources (CSS, JS, images)
│
├── frontend/                            # Frontend (React)
│   ├── src/                             # React components
│   │   ├── App.js                       # Main React component
│   │   ├── LoginButton.js               # OAuth2 login button
│   │   ├── Profile.js                   # Displays user info after login
│   │   └── api.js                       # Handles API calls to backend
│   ├── public/                          # Static assets
│   ├── package.json                     # React dependencies
│   └── README.md
│
└── README.md                            # Main project overview
