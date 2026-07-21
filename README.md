# Banking Management System

A Java Swing application using MVC architecture and OOP principles. The system allows users to manage bank accounts, perform transactions, and track account activity through a graphical user interface built with Java Swing.

## Features

- Secure user login
- Dashboard with banking statistics
- Create Savings and Checking accounts
- Deposit and withdraw funds
- Transfer money between accounts
- Search accounts by name or account number
- View complete transaction history
- Unique account number generation
- Unique transaction ID generation
- Input validation and exception handling

## Technologies

- Java
- Java Swing
- MVC Architecture
- Object-Oriented Programming (OOP)
- Java Collections Framework
- Git & GitHub

## OOP Concepts

- Abstraction
- Encapsulation
- Inheritance
- Polymorphism

## Project Structure

```text
src/
├── Controller/
│   ├── BankController.java
│   ├── CreateAccountController.java
│   ├── DashboardController.java
│   ├── LoginController.java
│   ├── SearchAccountController.java
│   ├── TransactionController.java
│   └── TransactionHistoryController.java
│
├── Model/
│   ├── Account.java
│   ├── Bank.java
│   ├── CheckingAccount.java
│   ├── LoginModel.java
│   ├── SavingsAccount.java
│   ├── Transaction.java
│   └── Validate.java
│
├── View/
│   ├── CreateAccountFrame.java
│   ├── DashboardFrame.java
│   ├── LoginFrame.java
│   ├── SearchAccountFrame.java
│   ├── TransactionFrame.java
│   └── TransactionHistoryFrame.java
│
└── Main.java
```

## Future Enhancements

- Remove account function
- Database - MySQL
- Transaction search and filters
- Account editing
- Interest calculation automation
- Role-based access control (Admin, Employee, Manager)

## Author
Ganta Vikram Jairam Reddy