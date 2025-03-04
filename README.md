# Smart Contact Manager  

A secure and efficient **Contact Management System** built with **Spring Boot, JPA, Spring Security, MySQL, and Thymeleaf**. This application provides **user authentication**, ensuring data security and seamless user interaction.  

## Features  
- ✅ **User Authentication** – Secure login and registration using Spring Security.  
- ✅ **Contact Management** – Add, edit, and delete contacts with an intuitive interface.  
- ✅ **Role-Based Access** – Restrict access based on user roles.  
- ✅ **Responsive UI** – Built using Thymeleaf and Bootstrap.  
- ✅ **Secure Data Handling** – Uses JPA and MySQL for efficient database management.  

## Tech Stack  
- **Backend**: Spring Boot, Spring Security, Spring Data JPA  
- **Frontend**: Thymeleaf, Bootstrap  
- **Database**: MySQL  
- **Security**: Role-based authentication & password encryption  

## Installation  

### 1. Clone the Repository  
```
git clone https://github.com/chandramohan0/SmartContactManager/
cd SmartContactManager
```

### 2. Configure Database
Update the **application.properties** file with your MySQL credentials:
```
server.port= 8080

#Database Configuration
spring.datasource.url= jdbc:mysql://localhost:3306/smartcontactmanager
spring.datasource.username=root
spring.datasource.password=root@123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update

spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

### 3. Run the Application
```
mvn spring-boot:run
```

### 4. Access the Application
Open **http://localhost:8080/** in your browser.
