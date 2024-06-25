# Library Management System

## Project Description

The Library Management System is a Java-based desktop application developed using Swing for the GUI and MySQL for the database. It allows administrators to manage books, patrons, borrowing records, and fines. The system includes features for book management, patron records, borrowing system, fine calculation, search functionality, and report generation.

## Features

1. **Book Management**: Store and manage book information, including title, author, genre, and availability status.
2. **Patron Records**: Manage patron details, such as name, contact information, and borrowing history.
3. **Borrowing System**: Allow patrons to borrow and return books, with an automatic update of book availability.
4. **Fine Calculation**: Calculate and manage fines for overdue books.
5. **Search Functionality**: Provide search options to find books and patrons.
6. **Reports**: Generate reports on book availability, borrowing history, and fines.
7. **Admin Login and Registration**: Secure the system with admin login and registration.

## Tech Stack

- **Programming Language**: Java
- **GUI**: Swing
- **Database**: MySQL
- **IDE**: Eclipse
- **Concepts**: Object-Oriented Programming (OOP)

## Prerequisites

- Java Development Kit (JDK) installed.
- Eclipse IDE installed.
- MySQL server installed and running.
- MySQL Workbench or any other SQL client to manage the database.

## Setup Instructions

### Database Setup

1. Open MySQL Workbench or your preferred SQL client.
2. Create a new database called `library_db`.
3. Run the following SQL script to create the necessary tables:

```sql
CREATE DATABASE library_db;

USE library_db;

CREATE TABLE admins (
    adminID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE books (
    bookID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    available BOOLEAN DEFAULT TRUE
);

CREATE TABLE patrons (
    patronID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contactInfo VARCHAR(100) NOT NULL
);

CREATE TABLE borrowing_records (
    recordID INT AUTO_INCREMENT PRIMARY KEY,
    bookID INT NOT NULL,
    patronID INT NOT NULL,
    borrowDate DATE NOT NULL,
    returnDate DATE,
    returned BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (bookID) REFERENCES books(bookID),
    FOREIGN KEY (patronID) REFERENCES patrons(patronID)
);

CREATE TABLE fines (
    fineID INT AUTO_INCREMENT PRIMARY KEY,
    recordID INT NOT NULL,
    amount DOUBLE NOT NULL,
    paid BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (recordID) REFERENCES borrowing_records(recordID)
);
