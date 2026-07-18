# Banking Management System

A Java Swing application using MVC architecture and OOP principles. The system allows users to manage bank accounts, perform transactions, and track account activity through a graphical user interface built with Java Swing.

## Features

- Secure employee login
- Dashboard with banking statistics
- Create Savings and Checking accounts
- Deposit and withdraw funds
- Transfer money between accounts
- Search transactions by account number, transaction ID, and date
- Filter transactions by transaction type
- Sort transactions by newest or oldest
- Reset transaction search and filter options
- View detailed transaction information through double-click
- Search accounts by name or account number
- Unique account number generation
- Unique transaction ID generation
- Input validation and exception handling
- Single bank instance for maintaining application data

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
│   ├── FindAccountController.java
│   ├── LoginController.java
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
    ├── AccountResultFrame.java
│   ├── CreateAccountFrame.java
│   ├── DashboardFrame.java
│   ├── FindAccountFrame.java
│   ├── LoginFrame.java
│   ├── TransactionFrame.java
│   └── TransactionHistoryFrame.java
│
└── Main.java
```

## Future Enhancements

- Remove account function
- Database - MySQL
- Account editing
- Interest calculation
- Role-based access control (Admin, Employee, Manager)

## Author
Ganta Vikram Jairam Reddy