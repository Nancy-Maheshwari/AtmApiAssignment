To create user account: 

Post :  localhost:8080/api/v1/user

To GetBalance of user

Get : localhost:8080/api/v1/user/account/12345678/ifsc/OBC1234

To deposit in user account : 

Put : localhost:8080/api/v1/user/account/{accountNumber}/ifsc/{ifscCode}/Deposit/{deposit}


To withdraw from user account 

Put : localhost:8080/api/v1/user/account/{accountNumber}/ifsc/{ifscCode}/Withdrawl/{withdrawl}/atmPin/{atmPin}


Points need to be remember:

1. I am using h2 database which is a in memory cache and everytime we run the project we need to create user account again(Run project again) and it will not store any previous data
2. For running the project:
  
   Command: mvn spring-boot:run

