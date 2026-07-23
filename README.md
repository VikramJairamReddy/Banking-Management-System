# Banking Management System

A Java Swing application using MVC architecture and OOP principles. The system allows users to manage bank accounts, perform transactions, and track account activity through a graphical user interface built with Java Swing.

## Features

### Authentication & User Management
- Secure user login system
- Support for multiple user roles:
  - Admin
  - Manager
  - Employee
- Permission based feature access
- Current user management
- Secure logout functionality

### Account Management
- Create Savings and Checking accounts
- Search accounts by account number or account holder name
- View account details
- Remove accounts based on user permissions
- Unique account number generation
- Input validation and exception handling

### Transaction Management
- Deposit funds
- Withdraw funds
- Transfer money between accounts
- Unique transaction ID generation
- Maintain transaction history
- View detailed transaction information through double-click

### Transaction Search & Filtering
- Search transactions by:
  - Account number
  - Transaction ID
  - Date
- Filter transactions by transaction type
- Sort transactions by:
  - Newest first
  - Oldest first
- Reset search and filter options

### Dashboard
- User-friendly banking dashboard
- Displays total number of accounts
- Displays today's transaction count
- Shows logged-in username and role
- Enables and disables features based on user permissions

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
│   ├── ManageAccountController.java
│   ├── PermissionManager.java
│   ├── TransactionController.java
│   └── TransactionHistoryController.java
│
├── Model/
│   ├── Account.java
│   ├── Bank.java
│   ├── CheckingAccount.java
│   ├── CurrentUser.java
│   ├── LoginModel.java
│   ├── Role.java
│   ├── SavingsAccount.java
│   ├── Transaction.java
│   └── Validate.java
│
├── View/
│   ├── AccountResultFrame.java
│   ├── CreateAccountFrame.java
│   ├── DashboardFrame.java
│   ├── FindAccountFrame.java
│   ├── LoginFrame.java
│   ├── ManageAccountFrame.java
│   ├── TransactionFrame.java
│   └── TransactionHistoryFrame.java
│
├── .gitignore
├── Main.java
└── README.md
```
## Current Access Permissions

Admin:
- Full access to all banking operations
- Manage accounts
- View transaction history
- Perform all transactions

Manager:
- Create and search accounts
- Perform banking transactions
- View transaction history
- Manage allowed account operations

Employee:
- Create accounts
- Search accounts
- Perform allowed daily banking operations
- Restricted from administrative features and transaction history

## Future Enhancements

- Database - MySQL
- Account editing
- Interest calculation

## Author
Ganta Vikram Jairam Reddy