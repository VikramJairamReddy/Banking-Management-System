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
├── Controller/
│   ├── BankController.java
│   ├── CreateAccountController.java
│   ├── CreateEmployeeController.java
│   ├── DashboardController.java
│   ├── EmployeeManagementController.java
│   ├── FindAccountController.java
│   ├── LoginController.java
│   ├── ManageAccountController.java
│   ├── PermissionManager.java
│   ├── TransactionController.java
│   ├── TransactionHistoryController.java
│   ├── UpdateEmployeeController.java
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
│   ├── CreateEmployeeFrame.java
│   ├── DashboardFrame.java
│   ├── EmployeeManagementFrame.java
│   ├── FindAccountFrame.java
│   ├── LoginFrame.java
│   ├── ManageAccountFrame.java
│   ├── TransactionFrame.java
│   ├── TransactionHistoryFrame.java
│   ├── UpdateEmployeeFrame.java
│   └── UserManagementFrame.java
│
├── .gitignore
├── Main.java
└── README.md
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

## Future Enhancements
- Satabase - MySQL
- Account update
- Interest calculation for savings accounts

## Author

Ganta Vikram Jairam Reddy