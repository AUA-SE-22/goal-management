# Backend application for goal-management-ui with SSO Keycloak

## Endpoints:
1. Get All Employees: 		http://localhost:8081/users/employees
2. Get All Employers: 		http://localhost:8081/users/employees


3. Get All EmployeeGoals:  	http://localhost:8081/employee/goals
4. Post Create goal:        http://localhost:8081/employee/goals || body :
                                   {	"name" : "Not null", 
                                        "detail" : "Not null", 
										"employeeId": "Not null",  
										"creationDate": "Not null",  
										"status": "Not null",  
										"employerId": "Not null", 
										"approveDate": "", 
										"rejectDate": ""
								   }
5. Post Update goal:         	http://localhost:8081/employee/goals/id || body:
                                      { "name" : "Not null", 
                                        "detail" : "Not null", 
                                      }
											
																					
6. Get All EmployerGoals:  	http://localhost:8081/employer/goals
7. Post Employer update status: http://localhost:8081/employer/goals/id || body {"status": "ACCEPTED,PENDING, REJECTED"}

