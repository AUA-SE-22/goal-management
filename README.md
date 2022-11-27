# Backend application for goal-management-ui with SSO Keycloak

## Endpoints:
1. Get All Employees: 		http://localhost:8081/users/employees
2. Get All Employers: 		http://localhost:8081/users/employers


3. Get All EmployeeGoals:  	http://localhost:8081/employee/goals
4. Post Create goal:        http://localhost:8081/employee/goals || body :
                                   {	"name" : "Not null", 
                                        "detail" : "Not null",   
										"employerId": "Not null", 
								   }
5. Post Edit goal:         	http://localhost:8081/employee/goals/id || body:
                                      { "name" : "Not null", 
                                        "detail" : "Not null", 
                                      }
											
																					
6. Get All EmployerGoals:  	http://localhost:8081/employer/goals
7. Post Employer update status: http://localhost:8081/employer/goals/id || body {"status": "ACCEPTED,PENDING, REJECTED"}

8. Get goal by goalId: http://localhost:8081/goals/id

