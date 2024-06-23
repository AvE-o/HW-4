# Simple Spring DB HW

## Description
This project is a simple database interaction with Spring Boot. 
It supports operations such as adding books and authors, retrieving book and author details, and managing associations between books and authors.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Running Tests](#running-tests)

## Installation
Follow these steps to set up your project locally. You need to have Java and Maven installed.

```console
git clone https://github.com/yourusername/library-management-system.git
cd library-management-system
mvn install
```

## Usage
To run this project please use the following Maven command

```console
mvn spring-boot:run
```

## Api-documentation
Below are the endpoints available in this Library Management System:

```console

Books
Get all books: GET /api/books
Add a book: POST /api/books - Requires a JSON body with book details.

Authors
Get all authors: GET /api/authors
Add an author: POST /api/authors - Requires a JSON body with author details.
Get an author by ID: GET /api/authors/{id}

```

## Running-tests
Execute the following command to run the unit tests:

```console
mvn test
```
## 
