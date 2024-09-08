# API for Product Management And CSV File Management
The Coding Interview for Graduates project at Prospecta entails the development of secure APIs using JWT authentication and role-based access control, in conjunction with the Fake Store API. The project encompasses the creation of two primary APIs: one for retrieving product details based on category and another for adding new product entries.

Additionally, the project includes a theoretical component involving the development of a program that processes a CSV file containing both values and formulas. This program is expected to compute the results of the formulas and produce a corresponding CSV output.

The technical stack for this project includes Java with Spring Boot for API development, JWT for authentication, and role-based access control for endpoint security. The implementation emphasizes comprehensive error handling and validation mechanisms to effectively manage formulas, cell references, and non-integer values.


## Getting Started

### Prerequisites

- Java 17
- Maven
- MySQL
- Postman (for API testing)
- Swagger
- STS

**Create and Configure MySQL Database:**

    - Create a new MySQL database.
    - Update the `src/main/resources/application.properties` file with your database credentials:

  # properties
      spring.datasource.url=jdbc:mysql://localhost:3306/product
      spring.datasource.username=root
      spring.datasource.password=admin
      spring.jpa.hibernate.ddl-auto=update
      
## Images

## Screenshots

### All Api Image

![logo](https://github.com/raushanraj901/Prospecta_Assignment/blob/main/Screenshot%20(2).png)

## How to Test
#### Open Browser And paste this URL `http://localhost:8888/swagger-ui/index.html` 

**OR**

1. **Sign Up:**

    Use Postman to send a `POST` request to `http://localhost:8888/api/register-user` with the required body parameters.

2. **Log In:**

    Send a `POST` request to `http://localhost:8888/api/auth/login` to receive the JWT token.

3. **Get Products:**

    Send a `GET` request to `http://localhost:8888/api/products/category/{category}` with the appropriate category and jwt token in headers.

4. **Add Product:**

    Send a `POST` request to `http://localhost:8888/api/products/` with the product details in the request body and jwt token in headers.

   
## Security and Reliability

To ensure the security and reliability of the APIs:

- **Authentication:** Use JWT tokens for secure access.
- **Data Validation:** Validate all input data to prevent invalid or harmful data from being processed.
- **Error Handling:** Implement proper error handling to provide meaningful error messages.
- **Logging:** Use logging to track application behavior and debug issues.
- **Rate Limiting:** Consider implementing rate limiting to prevent abuse of the APIs.


## API Endpoints

### Authentication

#### 1. Sign Up

- **Endpoint:** `POST http://localhost:8888/api/register-user`
- **Description:** Registers a new user.
- **Request Body:**

    ```json
    {
      "email": "user@email.com",
      "password": "password",
      "name": "name",
      "phone": "1234567890"
    }
    ```

#### 2. Log In

- **Endpoint:** `POST http://localhost:8888/api/auth/login`
- **Description:** Authenticates a user and returns a JWT token.
- **Request Body:**

    ```json
    {
      "email": "user@email.com",
      "password": "password"
    }
    ```
- **Response:**

    ```json
    {
      "token": "token",
      "expiresIn": "milli_second"
    }
    ```

### Product Management

#### 1. Get Products by Category

- **Endpoint:** `GET http://localhost:8888/api/products/category/{category}`
- **Description:** Retrieves a list of products based on the specified category.
- **Parameters:**
  - `category`: The category of the products (e.g., `jewelery`).
- **Response:**

    ```json
    [
      {
        "id": 1,
        "title": "Product Title",
        "price": 29.99,
        "description": "Product Description",
        "category": "jewelery",
        "image": "http://example.com/image.jpg",
        "rating": {
          "rate": 4.5,
          "count": 120
        }
      }
    ]
    ```

#### 2. Add New Product

- **Endpoint:** `POST http://localhost:8888/api/products/`
- **Description:** Adds a new product to the store.
- **Request Body:**

    ```json
      {
        "title": "Product Title",
        "price": 29.99,
        "description": "Product Description",
        "category": "jewelery",
        "image": "http://example.com/image.jpg"
      }
    ```

- **Response:**

    ```json
      {
        "id": 21,
        "title": "Product Title",
        "price": 29.99,
        "description": "Product Description",
        "category": "jewelery",
        "image": "http://example.com/image.jpg",
    
      }
    ```
