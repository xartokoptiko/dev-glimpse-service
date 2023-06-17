# DevGlimpse Backend Microservice Documentation

This documentation provides an overview of the backend microservice for DevGlimpse, a web application developed using Quarkus with Kotlin. DevGlimpse is a web app that generates AI-generated hot development topics and articles based on those topics. It also provides coding tips and features a "Get a Hand" section, which allows users to post coding problems and seek assistance from others or an AI.

## Table of Contents

1. [Overview](#overview)
2. [API Endpoints](#api-endpoints)
    - [Topics](#topics)
    - [Articles](#articles)
    - [Coding Tips- Not Available yet](#coding-tips)
    - [Get a Hand-Not Available yet](#get-a-hand)
3. [Database Schema](#database-schema)
4. [Development](#development)
5. [Testing](#testing)

## Overview

The backend microservice for DevGlimpse is developed using Quarkus with Kotlin and is responsible for handling the core functionalities of the web application. It interacts with a database to store and retrieve data related to hot development topics, AI-generated articles, coding tips, and the "Get a Hand" feature. To run the microservice 
```
./mvnw compile quarkus:dev
```

## API Endpoints

The backend microservice provides the following API endpoints:

### Topics

- `GET /topics`: Retrieves a list of hot development topics.
- `GET /topics/{id}`: Retrieves detailed information about a specific topic.
- `POST /topics`: Creates a new topic.
- `PUT /topics/{id}`: Updates an existing topic.
- `DELETE /topics/{id}`: Deletes a topic.

### Articles

- `GET /articles`: Retrieves a list of AI-generated development articles.
- `GET /articles/{id}`: Retrieves detailed information about a specific article.
- `POST /articles`: Creates a new article.
- `PUT /articles/{id}`: Updates an existing article.
- `DELETE /articles/{id}`: Deletes an article.

### Coding Tips(Not yet developed)

- `GET /coding-tips`: Retrieves a list of coding tips.
- `GET /coding-tips/{id}`: Retrieves detailed information about a specific coding tip.
- `POST /coding-tips`: Creates a new coding tip.
- `PUT /coding-tips/{id}`: Updates an existing coding tip.
- `DELETE /coding-tips/{id}`: Deletes a coding tip.

### Get a Hand(Not yet developed)

- `GET /get-a-hand/questions`: Retrieves a list of coding questions posted by users.
- `GET /get-a-hand/questions/{id}`: Retrieves detailed information about a specific coding question.
- `POST /get-a-hand/questions`: Creates a new coding question.
- `PUT /get-a-hand/questions/{id}`: Updates an existing coding question.
- `DELETE /get-a-hand/questions/{id}`: Deletes a coding question.

## Configuration

The backend microservice can be configured through environment variables. The configuration options include database connection details, API keys, and logging configuration. Please refer to the configuration documentation for more information on available configuration options.

## Development

To contribute to the development of the DevGlimpse backend microservice, follow these guidelines:

1. Set up the development environment, including the necessary tools, IDE, and dependencies.
2. Clone the project repository from the Git repository.
3. Familiarize yourself with the codebase and project structure.
4. Make changes or add new features following the project's coding conventions and best practices.
5. Test your changes locally and ensure they pass all relevant tests.
6. Submit a pull request to the project repository with a detailed description of the changes made.

## Testing

The DevGlimpse backend microservice includes a comprehensive suite of tests to ensure code reliability and functionality. The tests cover various aspects of the microservice, including API endpoints, data persistence, and integration with external services. To run the tests, execute the provided testing script or use your preferred testing framework.

Refer to the testing documentation for detailed instructions on running tests and understanding the test coverage.

