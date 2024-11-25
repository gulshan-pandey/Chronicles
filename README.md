# Chronicles

Chronicles is a comprehensive and secure journal application backend designed to enable users to effortlessly store, manage, and analyze their personal journal entries. Built with state-of-the-art technologies and a strong emphasis on modularity, Chronicles ensures seamless integration, enhanced security, and a host of insightful features, providing users with an unparalleled journaling experience.

---

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
  - [Secure Authentication](#secure-authentication)
  - [Weekly Insights](#weekly-insights)
  - [Data Storage and Management](#data-storage-and-management)
  - [Advanced Caching](#advanced-caching)
  - [Enhanced Logging](#enhanced-logging)
  - [Asynchronous Messaging](#asynchronous-messaging)
  - [Cron Jobs](#cron-jobs)
  - [API Documentation](#api-documentation)
  - [Admin Functionality](#admin-functionality)
  - [Inspirational Features](#inspirational-features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## Introduction

Chronicles serves as a robust backend solution for journaling applications, emphasizing security, scalability, and user engagement. Whether you're an individual looking to maintain a personal diary or a developer aiming to integrate a journaling feature into your application, Chronicles provides the necessary tools and infrastructure to meet your needs.

---

## Features

Chronicles is packed with a variety of features that ensure a secure, efficient, and user-friendly experience.

### Secure Authentication

- **Spring Security**: Utilizes Spring Security to provide a comprehensive authentication and authorization framework, ensuring that user data remains protected.
- **BCrypt Encryption**: Implements BCrypt hashing for password encryption, adding an extra layer of security against potential breaches.

### Weekly Insights

- **AI Analysis**: Integrates artificial intelligence to analyze user journal entries from the past week, offering insights such as mood trends and activity patterns.
- **Email Notifications**: Automatically sends insightful summaries to users every Sunday, helping them reflect on their week.

### Data Storage and Management

- **NoSQL Cloud Storage**: Employs MongoDB Cloud for flexible and scalable data storage, accommodating a vast amount of journal entries without compromising performance.

### Advanced Caching

- **Redis Integration**: Leverages Redis for caching frequently accessed data, significantly reducing database load and enhancing application responsiveness.

### Enhanced Logging

- **Comprehensive Logging**: Implements detailed logging mechanisms to facilitate easier debugging, monitoring, and maintenance of the application.

### Asynchronous Messaging

- **Apache Kafka**: Uses Apache Kafka to manage asynchronous communication between different components, ensuring reliable and efficient data processing.

### Cron Jobs

- **Automated Scheduling**: Sets up cron jobs to handle scheduled tasks such as sending weekly emails and performing routine maintenance, ensuring the application runs smoothly without manual intervention.

### API Documentation

- **Swagger Integration**: Incorporates Swagger to provide interactive and easily navigable API documentation, making it simpler for developers to understand and utilize the available endpoints.

### Admin Functionality

- **Role-Based Access**: Adds admin roles that allow designated users to manage and maintain the application, including user management and oversight of journal entries.

### Inspirational Features

- **Weather & Quotes API**: Integrates external APIs to fetch and display current weather information and inspirational quotes, enhancing the overall user experience and engagement.

---

## Technologies Used

Chronicles is built using a suite of modern and reliable technologies, ensuring robustness, scalability, and security.

- **Spring Boot**: A versatile Java framework for building scalable and maintainable applications with ease.
- **MongoDB Cloud**: A NoSQL database solution that offers flexibility and scalability for storing extensive journal entries.
- **Spring Security**: Provides a robust framework for handling authentication and authorization, safeguarding user data.
- **Redis**: An in-memory data structure store used for caching to improve application performance.
- **Apache Kafka**: A distributed streaming platform that manages asynchronous messaging between different parts of the application.
- **BCrypt Encryption**: A secure hashing algorithm for encrypting user passwords, ensuring data protection.
- **Swagger**: A powerful tool for generating interactive API documentation, simplifying the development process.
- **AI Integration**: Utilizes artificial intelligence to analyze user data, offering insightful feedback and enhancing user engagement.
- **JUnit & Mockito**: Employed for rigorous unit and integration testing, ensuring code reliability and quality.

---

## Getting Started

Follow these instructions to set up and run Chronicles on your local machine.

### Prerequisites

Ensure you have the following software and accounts set up before proceeding:

- **Java 17 or Higher**: Download and install from [Oracle's official site](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- **MongoDB Cloud Account or Local Instance**: Set up via [MongoDB](https://www.mongodb.com/try/download/community).
- **Redis Server**: Install Redis following the [official documentation](https://redis.io/download).
- **Kafka Server**: Install Kafka following the [official documentation](https://kafka.apache.org/documentation/).
- **Maven**: Install Apache Maven from [here](https://maven.apache.org/install.html).
- **Git**: Ensure Git is installed, available at [Git Downloads](https://git-scm.com/downloads).

### Installation

1. **Clone the Repository**

   Begin by cloning the Chronicles repository from GitHub:

   ```bash
   git clone https://github.com/gulshan-pandey/Chronicles.git
   cd chronicles
   ```

2. **Configure Environment Variables**

   Create a `.env` file in the root directory of the project and populate it with the necessary environment variables, including database connection strings, API keys, and other configurations.

3. **Install Dependencies**

   Use Maven to install all required dependencies:

   ```bash
   mvn install
   ```

4. **Set Up Databases**

   - **MongoDB**: If using MongoDB Cloud, ensure your account is active and your connection string is correctly configured in the `.env` file. For a local MongoDB instance, start the MongoDB server:

     ```bash
     mongod
     ```

   - **Redis**: Start the Redis server. For a local setup, execute:

     ```bash
     redis-server
     ```

5. **Run the Application**

   Launch the Spring Boot application using Maven:

   ```bash
   mvn spring-boot:run
   ```

6. **Accessing API Documentation**

   Once the application is running, navigate to the Swagger UI to explore and test the APIs:

   ```
   http://localhost:8080/swagger-ui.html
   ```

7. **Running Tests**

   Execute the test suite to verify that everything is functioning as expected:

   ```bash
   mvn test
   ```

---

## Usage

Chronicles offers a range of APIs to manage journal entries, handle user authentication, and provide insightful analytics. Here's a brief overview of the available endpoints:

### Authentication

- **Register a New User**
  - **Endpoint**: `POST /api/public/create-user`
  - **Description**: Registers a new user with the required credentials.
  - **Request Body**:
    ```json
    {
      "username": "johndoe",
      "password": "securePassword123"
    }
    ```
  - **Response**: Confirmation of successful registration.

- **User Login**
  - **Endpoint**: `POST /api/`
  - **Description**: Authenticates a user and provides a JWT token for subsequent requests.
  - **Request Body**:
    ```json
    {
      "username": "johndoe",
      "password": "securePassword123"
    }
    ```
  - **Response**: JWT token for authenticated access.

### Journal Entries

- **Retrieve All Journal Entries**
  - **Endpoint**: `GET /api/journals`
  - **Description**: Fetches all journal entries associated with the authenticated user.
  - **Response**: List of journal entries.

- **Create a New Journal Entry**
  - **Endpoint**: `POST /api/journals`
  - **Description**: Allows the user to create a new journal entry.
  - **Request Body**:
    ```json
    {
      "title": "A Day in the Life",
      "content": "Today was a productive day...",
      "mood": "Happy"
    }
    ```
  - **Response**: Confirmation of entry creation.

- **Update an Existing Journal Entry**
  - **Endpoint**: `PUT /api/journals/{id}`
  - **Description**: Updates the details of an existing journal entry identified by its ID.
  - **Request Body**:
    ```json
    {
      "title": "A More Productive Day",
      "content": "I achieved all my goals today...",
      "mood": "Joyful"
    }
    ```
  - **Response**: Confirmation of entry update.

- **Delete a Journal Entry**
  - **Endpoint**: `DELETE /api/journals/{id}`
  - **Description**: Deletes a journal entry identified by its ID.
  - **Response**: Confirmation of entry deletion.

### Insights

- **Retrieve Weekly Insights**
  - **Description**: Provides analytical insights based on the user's journal entries from the past week, such as mood trends and activity summaries.
  - **Response**: Detailed insights report will be emailed on the repective regestered email ids.

For a comprehensive understanding of all available endpoints and their usage, refer to the [Swagger API Documentation](http://localhost:8080/swagger-ui.html).

---

## Contributing

Contributions are highly appreciated! If you'd like to contribute to the development of Chronicles, please follow the guidelines below:

1. **Fork the Repository**

   Click on the "Fork" button at the top-right corner of the repository page to create a personal copy of the project.

2. **Clone Your Fork**

   ```bash
   git clone https://github.com/gulshan-pandey/chronicles.git
   cd chronicles
   ```

3. **Create a New Branch**

   Create a feature branch to work on your contributions:

   ```bash
   git checkout -b feature/YourFeatureName
   ```

4. **Make Your Changes**

   Implement your desired features, improvements, or bug fixes.

5. **Commit Your Changes**

   ```bash
   git commit -m "Add detailed description of your changes"
   ```

6. **Push to Your Fork**

   ```bash
   git push origin feature/YourFeatureName
   ```

7. **Create a Pull Request**

   Navigate to the original Chronicles repository and create a pull request from your fork, detailing the changes you've made.


---

## License

This project is licensed under the [MIT License](LICENSE). You are free to use, modify, and distribute this software as per the terms of the license.

---

## Contact

For any questions, suggestions, or support, please reach out to us at [gulshanpandey7210@gmail.com](mailto:gulshanpandey7210@gmail.com) or visit our [GitHub Issues](https://github.com/gulshan-pandey/Chronicles/) page.

---

**Happy Journaling with Chronicles!**
