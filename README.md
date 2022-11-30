# Backend application for goal-management-ui with SSO Keycloak
## Authentication:
## Token generation: POST { 
                            "url": "http://localhost:8181/auth/realms/goal-management/protocol/openid-connect/token",
                            "body": x-www-form-urlencoded
                                    {
                                        "client_id": "login-app",
                                        "client_secret":"mCGzxRMDNLEZKcqZ1i094BwkZKvgrOfF"
                                        "username":"hovhannesc| anim" # ROLE: employer|employee
                                        "password":"hovhannes_p| ani_p" # ROLE: employer|employee
                                        "grant_type":"password"
                                    }
                            }
## Endpoints:
### ROLE: {EMPLOYEE, EMPLOYER}
    - GET http://localhost:8081/users/employees
    - GET http://localhost:8081/users/employers
    - GET http://localhost:8081/users/employees/{id}
    - GET http://localhost:8081/users/employers/{id}
    - GET http://localhost:8081/goals/{id}
    

### ROLE: EMPLOYEE
    - GET http://localhost:8081/employee/goals
    - POST {
            url : "http://localhost:8081/employee/goals"
            body: {
                    "name": "",
                    "detail": "",
                    "employerId": "",
                    }
            }
    - PUT {
            url: http://localhost:8081/employee/goals/{id}
            body: {
                    "name": "",
                    "detail": ""
                    }
            }	
### ROLE: EMPLOYER
        - GET http://localhost:8081/employer/goals
        - PUT {
            url: http://localhost:8081/employer/goals/{id}
            body: {
                    "status": "ACCEPTED|REJECTED",
                    }
            }	

