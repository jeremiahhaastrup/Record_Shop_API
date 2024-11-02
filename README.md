# Record Shop API

## Overview

The Record Shop API is a RESTful web service built using Spring Boot, PostgreSQL, and deployed on AWS Elastic Beanstalk. It allows users to manage records, inventory, and customer data, providing CRUD (Create, Read, Update, Delete) operations for various entities in the record shop.

## Features

- Secure CRUD operations for managing record shop data.
- User data management with industry-standard security practices.
- Cloud-hosted PostgreSQL database for persistent storage.
- Dockerised for consistent environments across development and production.
- Deployed on AWS Elastic Beanstalk for scalable cloud infrastructure.
- Unit testing for the Model-View-Controller (MVC) layers to validate HTTP requests and ensure application stability.

## Technologies Used

- **Java**: Programming language used for API implementation
- **Spring Boot**: Framework for building the API.
- **PostgreSQL**: Cloud-hosted relational database.
- **AWS Elastic Beanstalk**: Platform-as-a-Service (PaaS) for deployment.
- **Docker**: Containerisation for consistent development and deployment environments.
- **Cloudinary**: Image storage and management service.
- **JUnit**: Testing framework used for unit testing.
- **Git**: Version control system.

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/jeremiahhaastrup/Record_Shop_API.git
   ```

2. **Navigate to the project directory**:

   ```bash
   cd Record_Shop_API
   ```

3. **Build the project**:

   Ensure you have [Maven](https://maven.apache.org/) installed. Run the following command to build the project:

   ```bash
   mvn package
   ```

4. **Run the application using Docker**:

   If Docker is installed, follow these steps:

   1. **Check Docker Images**:

      Before building, check if a previous Docker image exists using the following command:

      ```bash
      docker images
      ```

   2. **Build the Docker Image**:

      Run the following command to build the Docker image from the `Dockerfile` in the project directory:

      ```bash
      docker build -t record_shop_api:<VERSION> .
      ```

   3. **Run the Docker Container**:

      Once the image is successfully built, run the container using the following command. The `-p` flag maps the container's port 8080 to your local machine's port 8080:

      ```bash
      docker run -p 8080:8080 record_shop_api:<VERSION>
      ```

      After running this command, the API will be accessible at `http://localhost:8080`.

---

## Configuration

The application requires configuration for PostgreSQL and other settings. You can use environment variables to manage these configurations, avoiding hardcoding sensitive information.

### Environment Variables

Create a `.env` file in the root of your project to store configuration variables like database credentials:

```bash
# .env
DATABASE_HOST=localhost
DATABASE_PORT=5432
DATABASE_NAME=recordshop
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password
```

Alternatively, configure environment-specific properties files (e.g., `application-dev.properties`, `application-prod.properties`) to suit your environment.

### Default Application Properties

The `application.properties` file uses placeholders for database connections:

```properties
# Database configuration
spring.datasource.url=jdbc:postgresql://${DATABASE_HOST}:${DATABASE_PORT}/${DATABASE_NAME}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

# JPA configuration
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database=postgresql
```

## Usage

Once the application is running, you can interact with the API using Postman or any other API client. The base URL for the API is `http://localhost:8080`.

### Swagger API Documentation

Swagger UI is enabled for interactive API documentation and testing. You can access it by navigating to:

```
http://localhost:8080/swagger-ui.html
```

### Example Endpoints

- **GET /api/v1/albums**: Retrieve all records in the shop.
- **GET /api/v1/albums/{id}**: Retrieve a specific record by ID.
- **POST /api/v1/albums**: Add a new record to the shop.
- **PUT /api/v1/albums/{id}**: Update an existing record by ID.
- **DELETE /api/v1/albums/{id}**: Delete a record by ID.

For example, to fetch all records:

```bash
GET http://localhost:8080/api/v1/albums
```

## Testing

Unit tests are provided to ensure the API performs as expected. You can run the tests with Maven:

```bash
mvn test
```

These tests cover the MVC architecture and validate HTTP request/response handling.

## Project Structure

- **`src/main/java/com/example/RecordShop`**: Contains the main business logic for the API.
  - `AlbumController.java`: Handles incoming HTTP requests related to albums.
  - `ArtistController.java`: Handles incoming HTTP requests related to artists.
  - `AlbumService.java`: Interface for the album operations.
  - `ArtistService.java`: Interface for the artist operations.
  - `AlbumServiceImpl.java`: Contains the business logic for album operations.
  - `ArtistServiceImpl.java`: Contains the business logic for artist operations.
  - `AlbumRepository.java`: Manages database interactions with PostgreSQL.
  - `ArtistRepository.java`: Manages database interactions with PostgreSQL.
  - `Album.java`: Entity representing an album in the shop.
  - `Artist.java`: Entity representing an artist in the shop.
  - `Genre.java`: Enum class representing album genres in the shop.

- **`src/test/java/com/example/RecordShop`**: Contains the unit tests for the project.
  - `AlbumControllerTest.java`: Tests for the `AlbumController`.
  - `AlbumServiceImplTest.java`: Tests for the `AlbumServiceImpl`.
  - `ArtistControllerTest.java`: Tests for the `ArtistController`.
  - `ArtistServiceImplTest.java`: Tests for the `ArtistServiceImpl`.

- **`Dockerfile`**: Defines the Docker setup for running the API.

## Deployment

The Record Shop API is deployed on AWS Elastic Beanstalk. To deploy your own version:

1. Package the application into a `.jar` file using Maven:

   ```bash
   mvnw package
   ```

2. Use the AWS Elastic Beanstalk CLI or AWS Management Console to upload and deploy the `.jar` file.

3. AWS Elastic Beanstalk will automatically manage the server infrastructure, including load balancing and scaling.

## Contributing

If you'd like to contribute to this project, please fork the repository and create a pull request with your changes. Make sure to include tests for any new functionality or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
