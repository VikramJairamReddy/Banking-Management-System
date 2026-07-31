# Banking Management System

A Java Swing banking application built using MVC architecture and OOP principles.  
The system allows employees to manage bank accounts, perform transactions, and 
manage users through graphical interface.

## Features

### Login & User Management
- Secure employee login system
- Supports multiple roles:
  - Admin
  - Manager
  - Employee
- Role-based access control
- Different permissions for different users
- Current user tracking
- Secure logout

### Employee Management
- Add new employees
- Search employees by:
  - Employee ID
  - Name
  - Username
- View employee details
- Update employee role and email
- Remove employees
- Unique employee ID generation

### Account Management
- Create Savings and Checking accounts
- Search accounts by:
  - Account number
  - Account holder name
- View account details
- Remove accounts based on permissions
- Generate unique account numbers
- Input validation and error handling

### Transaction Management
- Deposit money
- Withdraw money
- Transfer money between accounts
- Generate unique transaction IDs
- Store transaction history
- View transaction details

### Transaction Search & Filtering
- Search transactions by:
  - Account number
  - Transaction ID
  - Date
- Filter by transaction type
- Sort transactions:
  - Newest first
  - Oldest first
- Reset search and filter options

### Dashboard
- Displays logged-in user information
- Shows user role
- Displays total accounts
- Displays today's transactions
- Enables or disables features based on permissions

## Technologies

- Java
- Java Swing
- MVC Architecture
- Object-Oriented Programming (OOP)
- MySQL
- JDBC
- Maven
- Java Collections Framework
- Exception Handling
- Git & GitHub

## OOP Concepts Used

- Abstraction
- Encapsulation
- Inheritance
- Polymorphism

## Project Structure

```text
src/
├── main/
│    └── java/
│        ├── Controller/
│        │   ├── BankController.java
│        │   ├── CreateAccountController.java
│        │   ├── CreateEmployeeController.java
│        │   ├── DashboardController.java
│        │   ├── EmployeeManagementController.java
│        │   ├── FindAccountController.java
│        │   ├── LoginController.java
│        │   ├── ManageAccountController.java
│        │   ├── PermissionManager.java
│        │   ├── TransactionController.java
│        │   ├── TransactionHistoryController.java
│        │   ├── UpdateEmployeeController.java
│        │   └── UserManagementController.java
│        │
│        ├── Model/
│        │   ├── Account.java
│        │   ├── Bank.java
│        │   ├── CheckingAccount.java
│        │   ├── CurrentUser.java
│        │   ├── Employee.java
│        │   ├── LoginModel.java
│        │   ├── Role.java
│        │   ├── SavingsAccount.java
│        │   ├── Transaction.java
│        │   └── Validate.java
│        │
│        ├── View/
│        │   ├── AccountResultFrame.java
│        │   ├── CreateAccountFrame.java
│        │   ├── CreateEmployeeFrame.java
│        │   ├── DashboardFrame.java
│        │   ├── EmployeeManagementFrame.java
│        │   ├── FindAccountFrame.java
│        │   ├── LoginFrame.java
│        │   ├── ManageAccountFrame.java
│        │   ├── TransactionFrame.java
│        │   ├── TransactionHistoryFrame.java
│        │   ├── UpdateEmployeeFrame.java
│        │   └── UserManagementFrame.java
│        │
│        ├── Database/
│        │   └── DatabaseConnection.java
│        ├── DAO/
│        │   └── EmployeeDAO.java
│        │
│        └── Main.java
│
├── pom.xml
├── README.md
└── .gitignore
```
## Access Permissions

### Admin
- Full access to banking operations
- Manage employees
- Manage accounts
- View transaction history
- Perform all transactions

### Manager
- Create and search accounts
- Perform banking transactions
- View transaction history
- Manage allowed account operations

### Employee
- Create accounts
- Search accounts
- Perform daily banking operations
- Restricted from administrative and transaction features

## Database
The application uses MySQL for permanent data storage.

Tables:
- Employees
- Customers
- Accounts

## Future Enhancements
- Database - MySQL
- Account update
- Interest calculation for savings accounts

## AI Usage

AI tools were used as development assistant throughout this project. They helped with learning unfamiliar concepts, reviewing implementation approaches, debugging issues, and improving documentation.

AI was primarily used for:
- Understanding database design and JDBC integration.
- Discussing software architecture decisions.
- Reviewing code quality and identifying improvements.
- Assisting with debugging and documentation.

All system design decisions, implementation, testing, and final integration were completed and verified by me.

## Author

Ganta Vikram Jairam Reddy