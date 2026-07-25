# Banking Management System

A Java Swing application using MVC architecture and OOP principles. The system allows users to manage bank accounts, perform transactions, and track account activity through a graphical user interface built with Java Swing.

## Features

### Authentication & User Management
- Secure user login system
- Support for multiple user roles:
  - Admin
  - Manager
  - Employee
- Role-based access control
- Permission based feature access
- Current user management
- Secure logout functionality

### Account Management
- Create Savings and Checking accounts
- Live account search by:
  - Account number
  - Account holder name
- View detailed account information
- Remove accounts based on user permissions
- Unique account number generation
- Input validation and exception handling

### Transaction Management
- Deposit funds
- Withdraw funds
- Transfer money between accounts
- Unique transaction ID generation
- Maintain complete transaction history
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
- Dynamically enables and disables features based on user permissions

## Technologies

- Java
- Java Swing
- MVC Architecture
- Object-Oriented Programming (OOP)
- Java Collections Framework
- Exception Handling
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
│   ├── TransactionHistoryController.java
│   └── UserManagementController.java
│
├── Model/
│   ├── Account.java
│   ├── Bank.java
│   ├── CheckingAccount.java
│   ├── CurrentUser.java
│   ├── Employee.java
│   ├── EmployeeManager.java
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
│   ├── TransactionHistoryFrame.java
│   └── UserManagementFrame.java
│
├── .gitignore
├── Main.java
└── README.md