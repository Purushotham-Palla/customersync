
Add this one dependencies 
<dependency>

    <groupId>org.apache.tomcat</groupId>

    <artifactId>tomcat-catalina</artifactId>

    <version>9.0.88</version>

</dependency>

# Customer Sync Application

This project is a CRUD (Create, Read, Update, Delete) application for 
managing customer data. The system utilizes a MySQL database, with the backend 
developed using JSP/Servlet and the frontend built with HTML, CSS. The application 
includes secure JWT-based authentication and integrates with a remote API for 
synchronizing customer data. 

## Database Schema

### Users Table

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login_id VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
```

### CustomerDetails Table

```sql
CREATE TABLE customerdetails (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    street VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    uuid VARCHAR(250)
);
```

## Project Phases

### Phase 1: Setting Up the Backend Environment

- **Java Dynamic Web Project**: Create a new Java Dynamic Web Project and convert it to a Maven project. 
- **Dependencies**: Add the necessary dependencies in the `pom.xml` file, including:
  - `org.json:json` for JSON parsing
  - `org.apache.httpcomponents:httpclient` for HTTP client functionality
  - `javax.xml.bind:jaxb-api` for JAXB API
  - `io.jsonwebtoken:jjwt` for JWT authentication
  - `mysql:mysql-connector-java` for MySQL connectivity

### Phase 2: API Specifications

- **Create Customer**: Implement API for adding new customer records to the database.
- **Update Customer**: Implement API for updating existing customer information.
- **Get Customer List**: Implement API for retrieving a paginated, sorted, and searchable list of customers.
- **Get Customer by ID**: Implement API for fetching details of a specific customer by ID.
- **Delete Customer**: Implement API for removing customer records from the database.

### Phase 3: JWT Authentication Implementation

- Implement JWT-based authentication to secure the application, ensuring only authorized users can access and modify customer data.

### Phase 4: Data Synchronization Feature

- **Integration with Remote API**: Develop functionality to sync customer data with a remote API.
- **Sync Button Implementation**: Add a sync button in the user interface to trigger the synchronization process.
- **Data Update Logic**: Implement logic to update existing customer data or add new records based on the information received from the remote API.

## Project Links

- **GitHub Repository**: [Purushotham-Palla/customersync](https://github.com/Purushotham-Palla/customersync)
- **Project Video Demonstration**: [Watch the video](https://drive.google.com/file/d/1FMM8hlaWRSFCO4ZJ8yMFvEcw8IkZVXf_/view?usp=drivesdk)

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/Purushotham-Palla/customersync.git
   ```

2. **Set Up the MySQL Database**:
   - Create the necessary database and tables using the provided SQL scripts (`users.sql`, `customerdetails.sql`).

3. **Import the Project**:
   - Import the project into your IDE as a Java Dynamic Web Project.

4. **Deploy the Application**:
   - Deploy the project on a servlet container like Apache Tomcat.

5. **Access the Application**:
   - Access the application via `http://localhost:8080/customersync`.

## Tech Stack

- **Backend**: JSP/Servlet
- **Frontend**: HTML, CSS
- **Database**: MySQL
- **Authentication**: JWT (JSON Web Tokens)

## Contact

For any questions or feedback, feel free to reach out:

- **Author**: Purushotham Palla
- **GitHub**: [Purushotham-Palla](https://github.com/Purushotham-Palla/)
- **Email**: ppurushotham2001@gmail.com
