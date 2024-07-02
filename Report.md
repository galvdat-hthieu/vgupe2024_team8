# Programing Exercise Report: Pharmacy Management System
# Team 8


## Table of Contents
1. [Introduction](#item-one)
2. [Application Description](#item-two)
3. [Analysis](#item-three)
4. [Design](#item-four)
   1. [Use-case](#item-four-a)
   2. [Sequence Diagram](#item-four-b)
   3. [Database](#item-four-c)
6. [Framework](#item-five)
7. [User Guide](#item-six)
   1. [Installation](#item-six-a)
   2. [Getting Started](#item-six-b)
   3. [Role-base Functionalities](#item-six-c)
9. [Task Division](#item-seven)

<a id="item-one"></a>
## 1. Introduction
Welcome to the documentation for our Pharmacy Management System. This system is designed to streamline the operations of a pharmacy, ensuring efficient management of inventory, employees, and transactions.

<a id="item-two"></a>
## 2. Application Description
This application is built to effectively manage a pharmaceutical store. It helps the pharmacist to :
- Record maintenance of medicines/drugs and supplies sent by the supplier
- Administration management of employee records
- Provision of separate usernames and passwords for each employee
- Built-in messaging system for communication among users
- Generation of invoices, bills, receipts, and other related documents
- Management of drugs and consumables within the pharmacy unit

<a id="item-three"></a>
## 3. Analysis 
### Functional:   
   1. Record maintenance of medicines/drugs and supplies sent by the supplier
      - The system shall provide forms or fields to input details such as medicine name, batch number, expiration date, quantity, and supplier information.
      - The system shall allow user to create new records for each medicine or drug received from the supplier.
      - The system shall be able to update or edit existing records, including modifying quantities, expiration dates, or other relevant information.
      - The system shall support search functionality base on criteria such as medicine name, batch number, supplier information.
   2. Administration management of employee records
      - The system shall provide forms or fields to input personal details, employment contracts and role within the pharmacy, licensing and certification details.
      - The system shall define clear access levels and permissions for employees and administrators.
      - The system shall ensure that only authorized personnel can view or modify employee, medication, and consumable information.
      - The system shall support search functionality base on criteria such as employee names, numbers, role.
      - The system shall be able to update or edit existing records of the list.
   3. Provision of separate accounts with usernames and passwords for each employee
      - The system shall create account username base on employee ID.
      - When creating account, the password will be given to employee.
      - Employee can change password after accessing into the account.
      - The account contains employee ID, personal detail of employee, job title, role within the pharmacy.
      - Higher permission of the account will be granted base on role of the employee.
      - The account can be (optionally) deleted 1 years after employee leave the pharmarcy.
   4. Built-in messaging system for communication among users
      - Users should be able to view message history.
      - USers should be able to choose which employee will receive message.
      - Users should be able to message to many employee at once.
   5. Generation of invoices, bills, receipts, and other related documents
      - The system shall integrate with current databases and transaction records, and automatically print out document templates with accurate information.
      - The system shall display transaction items and calculate totals.
      - The system shall meet regulatory standards and compliance requirements, ensuring legal and industry-specific document compliance.
   6. Management of drugs and consumables within the pharmacy unit
      - The system shall keep track of the medication and consumables information frequently.
      - The system shall display the recorded information as commanded.
### Non-Functional:
   1. The system shall have an intuitive and user-friendly interface.

<a id="item-four"></a>
## 4. Design

<a id="item-four-a"></a>
### Use case diagram

#### Account
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/use_case/account_usecase.png" width="700" height="800">

#### Consumable
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/use_case/consumable_use%20case.png" width="700" height="800">

#### Employee
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/use_case/usecaseemployee.png" width="700" height="800">


#### Medicine
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/94373117/49a8d1f6-9918-4b7b-ad38-89f403ad7983" width="700" height="800">

#### Transaction
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/use_case/transaction_Use%20case.png" width="700" height="800">

#### Message
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/use_case/Message%20System.png" width="700" height="800">

<a id="item-four-b"></a>
### Sequence diagram 

#### Account
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/sequence_diagram/separate_account.png" width="700" height="800">

#### Item
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/sequence_diagram/Item_%20Management.png" width="700" height="800">

#### Employee
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/sequence_diagram/employeesequence.png" width="700" height="800">

#### Medicine and Consumable
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/sequence_diagram/Medicine-Consumable_Manager.png" width="700" height="800">

#### Transaction
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/sequence_diagram/transaction_sequence.png" width="700" height="800">

#### Message
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/sequence_diagram/MessagingSeq.png" width="700" height="800">

<a id="item-four-c"></a>
### Database 
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/94373117/6f74be52-8ea0-4339-b1b2-cd3731a9d75b">

#### Entities and Attributes

#### Employee
- **EID**: Employee ID (Primary Key)
- **EName**: Employee Name
- **Gender**: Gender of the Employee
- **Year of Birth**: Year of Birth of the Employee
- **Phone number**: Contact Number
- **Role**: Role in the organization

#### Admin
- **AID**: Admin ID (Primary Key)
- **AName**: Admin Name

#### Account
- **Username**: Account Username (Primary Key)
- **A**: Foreign Key linking to Admin
- **Access level**: Access level of the account
- **AStatus**: Account Status

#### Message
- **senderID**: ID of the sender (Foreign Key referencing Employee)
- **receiverID**: ID of the receiver (Foreign Key referencing Employee)
- **message content**: Content of the message
- **Time**: Timestamp of the message
- **MStatus**: Status of the message

#### Transaction
- **id**: Transaction ID (Primary Key)
- **transaction date**: Date of the transaction
- **total amount**: Total amount of the transaction

#### Transaction Item
- **id**: Transaction Item ID (Primary Key)
- **name**: Name of the transaction item
- **price**: Price of the item
- **quantity**: Quantity of the item
- **item type**: Type of item
- **transaction id**: Foreign Key linking to Transaction

#### Item
- **id**: Item ID (Primary Key)
- **name**: Name of the item
- **price**: Price of the item
- **type**: Type of item

#### Medicine
- **MName**: Medicine Name
- **Batch number**: Batch number of the medicine
- **Expiration date**: Expiration date of the medicine
- **Supplied date**: Date the medicine was supplied
- **Quantity**: Quantity of the medicine
- **Supplier**: Supplier of the medicine
- **price**: Price of the medicine

#### Consumable
- **CName**: Consumable Name
- **Supplied date**: Date the consumable was supplied
- **Quantity**: Quantity of the consumable
- **Supplier**: Supplier of the consumable
- **price**: Price of the consumable

#### Relationships
- **Employee** creates **Transaction** (1:N)
- **Employee** manages **Medicine** (M:N)
- **Employee** manages **Consumable** (M:N)
- **Transaction** contains **Transaction Item** (1:N)
- **Transaction Item** is part of **Item** (N:1)
- **Message** is sent by **Employee** (1:N)
- **Admin** creates **Account** (1:N)
- **Account** is owned by **Employee** (1:1)
- **Item** is a **Medicine** (1:1)
- **Item** is a **Consumable** (1:1)

This ERD provides a comprehensive view of how different entities within the pharmacy management system interact with each other, facilitating effective management and communication within the system.


<a id="item-five"></a>
## 5. System architecture
This section outlines the architecture of the Pharmacy Management System.
### Language
- Java : Core language for application development. Built on Java JDK 21
### Framework
- Hibernate : used for Object-Relational Mapping (ORM) to interact with databases. Provides some onjects for database operation and manages entities in the database.
### Library 
- PostgreSQL JDBC Driver: provide the JDBC driver for PostgreSQL database. Allow Java application to connect to databases
- Java Standard libray: Collections, Input/Output, Exception Handling (java.util, java.io, etc.)
- iText: for creating and designing PDF documents in Java. Use to generate bills, invoices of the transaction.
- Swing : used to build  the graphical user interface of the application 
### Tools
- Build Tool: Maven 
- Maven Jar Plugin : used to build JAR file.
- Maven Shade Plugin : used to packages all dependencies into a single JAR file.
### Architectural Pattern and Directory Structure
This application is built base on the Model-View-Control pattern:
#### Model
- model/: Contains entity classes representing the database tables.
- repository/: Contains repository classes for database operations.
- service/: Contains service classes that implement business logic.
#### View
- view/: Contains Swing form classes for the GUI including action listeners for handling user input
#### Controller
- Intergrated into the GUI form
#### Utilities
- util/: Contains utility classes.
#### Other resource
- hibernate.cfg.xml: Configuration file for Hibernate. Important for database connection details and mappings.
  
<a id="item-six"></a>
## 6. User Guide

<a id="item-six-a"></a>
### I. Installation 
#### System Requirements:
- Java JDK 21
- PostgreSQL database
#### Installation Instruction:
- Step 1: Download the application package.
- Step 2: Install Java JDK 21 or higher.
- Step 3: Set up PostgreSQL and import the provide sql file into the database.
- Step 4: Go to the config.properties file to specify the connection link to the database server as well as the username and password of that server.

<a id="item-six-b"></a>
### Getting Started
- Launching the Application: double click on Pharmacy_System.jar or run it from the command line using "java -jar Pharmacy_System.jar".
- Login Process: Enter the username and provided password in the package to log in as Admin.

<a id="item-six-c"></a>
### Role-Based Functionalities
### I. Admin Role
- Admin have full access to the application features.
##### 1. Main menu
- Overview of available features
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/b86dc19b-091d-499f-a49a-5b8d915bd607" width="400" height="400">

##### 2. Manage Employee
- GUI of the Employee Management
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/ff984017-9e0e-454b-999f-63e06c7638a8" width="500" height="400">

- To search employee, fill out at least 1 data into 1 of 3 fields at the top left of the GUI and click Search.
   
- To view employee information:  select an employee on the list -> click "View". It will display complete information.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/de7348f0-72af-4f4c-ae06-ccbb0c963a59" width="500" height="400">

- To add employee: click "Add" at the bottom left of the GUI, fill out the fields -> click "Ok" -> click "Refresh" to load the employees list again.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/3bfd3e48-5169-456b-9e00-97c591af0f1c" width="500" height="400">

- To edit an employee information : Choose an employee on the list -> click "Edit" at the bottom left -> Change information in the field -> click "Ok" 

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/0c6e9456-15a4-4613-a651-00c9118c6f9e" width="500" height="400">

- To delete an employee : Choose an employee on the list -> click "Delete" .

- To export employee list into .csv file : click export at the bottom right. The .csv file will be save on the current working directory.

##### 3. Manage Account 
- To create a new account: click "Create Account" -> input the username and the employee id associate with that account -> "Create". The password will be "123" by default
  
  <img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/64c3ecca-d86b-4d67-8550-e04ef7d5a5e7" width="400" height="400">
  
- To reset password of an account : click "Reset password" -> input the account username that need to be reset -> "Reset Password". The password will be reset to the default (123).
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/4d4589ea-b6a6-4c21-8258-d35f378bf3e3" width="400" height="400">

- To delete an account: click "Delete account" -> input the account username that need to be deleted -> "Delete Account"
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/b12bb9d0-0d39-418d-828a-56500a95f97c" width="400" height="400">

- To change the role : click "Change Role" -> enter the username, specify the new role -> "Change Role".
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/4f939cd5-f9e0-4e09-afcc-9c0443cf4613" width="400" height="500">


##### 4. Medicine
- GUI of Medicine Management
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/36ccba23-bf83-4d03-9d41-0cb2bfd9a823" width="700" height="600">

- To search medicine: fill out at least 1 data into 1 of 3 fields at the top left of the GUI and click Search.
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/6be1fdb6-f941-4fc9-b7db-ff55119c2362" width="700" height="600">

- To view medicine: choose a medicine on the list -> "view"
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/b52af887-3f29-4c54-a2ab-3582ddc3cbfe" width="700" height="600">

- To check for expired medicine : click "Out-of-date medicine" . It will list all the medicine that is expired
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/7ab473cf-f86b-4eef-a26c-bc3e7db6e858" width="700" height="600">
  
- To add medicine: click "Add" at the bottom left of the GUI, fill out the fields -> click "Ok" -> click "Refresh" to load the medicine list again.
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/5dc49c08-2d88-4f11-83d5-cc4c045257ad" width="700" height="600">

- To edit a medicine information : Choose a medicine on the list -> click "Edit" at the bottom left -> Change information in the field -> click "Ok"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/012a051b-91b5-43a9-bc35-25b25cd1fae0" width="700" height="600">
  
- To delete an medicine : Choose an medicine on the list -> click "Delete" .

- To export medicine list into .csv file : click export at the bottom right. The .csv file will be save on the current working directory.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/92cef99f-c7b7-470f-b34e-d39dbf4f55cc" width="1000" height="200">

##### 5. Consumable
- GUi of Consumable Management
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/cb708a7d-8f7b-4486-93e2-749b008db04a" width="700" height="600">

- To search consumable, fill out at least 1 data into 1 of 3 fields at the top left of the GUI and click Search.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/a3726c0c-959a-4a71-83da-ea1a70b0fa6a" width="700" height="600">

- To view consumable, choose a consumable on the list -> "view"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/a79076d6-aa19-4d41-ae23-5ee8cb017529" width="700" height="600">

- To add consumable: click "Add" at the bottom left of the GUI, fill out the fields -> click "Ok" -> click "Refresh" to load the consumable list again.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/721a0c27-17da-49e7-8910-fca6935fd03d" width="700" height="600">

- To edit a consumable information : Choose a consumable on the list -> click "Edit" at the bottom left -> Change information in the field -> click "Ok"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/a689ee14-ec01-40b8-8c3d-2ca9d2efd2d9" width="700" height="600">

- To delete an consumable : Choose an consumable on the list -> click "Delete" .


- To export consumable list into .csv file : click export at the bottom right. The .csv file will be save on the current working directory.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/b69e6a23-6e10-4c0b-875c-0b77b9d4a99e" width="1000" height="200">

##### 6. Internal chat
- GUi of internal chat

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/5b1c8976-4a45-459d-a541-1ecfa382ead7" width="400" height="400">

- To view the conversation with other employee: click on the box next to "Conversation with" text -> choose the employee.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/9de0bc13-2c85-4478-8cdf-c570b269ae9b" width="400" height="400">

- To send message: Click "Send Message" -> select the receiver -> "Next" -> Input message -> "Send Message"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/456f6c7c-8f34-4877-b83c-ff1cb28275fa" width="400" height="400">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/96dd6ec4-e1b2-40a6-893f-aded78894dff" width="400" height="400">

- To send message to many people : Click "Send Message" -> select multiple receiver -> "Next" -> Input message -> "Send Message"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/b35b86d9-7481-4905-bd34-096dda93db4f" width="400" height="400">

- To send message to all:  Click "Send Message" -> click "All" -> "Next" -> Input message -> "Send Message"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/e5c583ac-3c29-4aeb-b25b-c503a6e14fef" width="400" height="400">

##### 7. Transaction
- GUI of Transaction Management

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/e43b85ae-7aaf-4761-8c53-0452f254150b" width="400" height="400">

- To create new transacton : click "New Transaction" -> specify the name and quantity of the item -> click "Add". Multiple items can be added to the list. When you are done adding items, click "Save Transaction"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/ed74c86e-ff8f-41d4-aab0-7e80154a74aa" width="400" height="400">

- To view a transaction : choose a transaction on the list -> "View Transaction"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/70e88207-2bf4-4226-ad0a-d21918ed2a3d" width="400" height="400">

- To generate invoice of a transaction : choose a transaction on the list -> "View Transaction" -> "generate invoice". The invoice will be saved in the current working directory.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/22936152-1dc7-438d-a90e-c1f5d7d4cf05" width="400" height="400">

### II. User Account
##### 1. Main menu
- Overview of available features
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/493d7085-945d-4c16-85fc-682185abfa2b" width="500" height="500">

##### 2. Medicine
- GUI of Medicine Management
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/36ccba23-bf83-4d03-9d41-0cb2bfd9a823" width="700" height="600">

- To search medicine: fill out at least 1 data into 1 of 3 fields at the top left of the GUI and click Search. 
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/6be1fdb6-f941-4fc9-b7db-ff55119c2362" width="700" height="600">

- To view medicine: choose a medicine on the list -> "view" 
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/b52af887-3f29-4c54-a2ab-3582ddc3cbfe" width="700" height="600">

- To check for expired medicine : click "Out-of-date medicine" . It will list all the medicine that is expired
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/7ab473cf-f86b-4eef-a26c-bc3e7db6e858" width="700" height="600">

- To export medicine list into .csv file : click export at the bottom right. The .csv file will be save on the current working directory.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/92cef99f-c7b7-470f-b34e-d39dbf4f55cc" width="1000" height="200">

###### For authorized user
- To add medicine: click "Add" at the bottom left of the GUI, fill out the fields -> click "Ok" -> click "Refresh" to load the medicine list again. 
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/5dc49c08-2d88-4f11-83d5-cc4c045257ad" width="700" height="600">

- To edit a medicine information : Choose a medicine on the list -> click "Edit" at the bottom left -> Change information in the field -> click "Ok" 

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/012a051b-91b5-43a9-bc35-25b25cd1fae0" width="700" height="600">
  
- To delete an medicine : Choose an medicine on the list -> click "Delete" . (only user Authorized)



##### 3. Consumable
- GUi of Consumable Management
  
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/cb708a7d-8f7b-4486-93e2-749b008db04a" width="700" height="600">

- To search consumable, fill out at least 1 data into 1 of 3 fields at the top left of the GUI and click Search.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/a3726c0c-959a-4a71-83da-ea1a70b0fa6a" width="700" height="600">

- To view consumable, choose a consumable on the list -> "view"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/a79076d6-aa19-4d41-ae23-5ee8cb017529" width="700" height="600">

- To export consumable list into .csv file : click export at the bottom right. The .csv file will be save on the current working directory.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/b69e6a23-6e10-4c0b-875c-0b77b9d4a99e" width="1000" height="200">

###### For authorized user
- To add consumable: click "Add" at the bottom left of the GUI, fill out the fields -> click "Ok" -> click "Refresh" to load the consumable list again. (only user Authorized)

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/721a0c27-17da-49e7-8910-fca6935fd03d" width="700" height="600">

- To edit a consumable information : Choose a consumable on the list -> click "Edit" at the bottom left -> Change information in the field -> click "Ok" (only user Authorized)

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/a689ee14-ec01-40b8-8c3d-2ca9d2efd2d9" width="700" height="600">

- To delete an consumable : Choose an consumable on the list -> click "Delete" . (only user Authorized)


##### 4. Internal chat
- GUi of internal chat

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/5b1c8976-4a45-459d-a541-1ecfa382ead7" width="400" height="400">

- To view the conversation with other employee: click on the box next to "Conversation with" text -> choose the employee.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/9de0bc13-2c85-4478-8cdf-c570b269ae9b" width="400" height="400">

- To send message: Click "Send Message" -> select the receiver -> "Next" -> Input message -> "Send Message"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/456f6c7c-8f34-4877-b83c-ff1cb28275fa" width="400" height="400">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/96dd6ec4-e1b2-40a6-893f-aded78894dff" width="400" height="400">

- To send message to many people : Click "Send Message" -> select multiple receiver -> "Next" -> Input message -> "Send Message"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/b35b86d9-7481-4905-bd34-096dda93db4f" width="400" height="400">

- To send message to all:  Click "Send Message" -> click "All" -> "Next" -> Input message -> "Send Message"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/e5c583ac-3c29-4aeb-b25b-c503a6e14fef" width="400" height="400">

##### 5. Transaction
- GUI of Transaction Management

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/e43b85ae-7aaf-4761-8c53-0452f254150b" width="400" height="400">

- To create new transacton : click "New Transaction" -> specify the name and quantity of the item -> click "Add". Multiple items can be added to the list. When you are done adding items, click "Save Transaction"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/ed74c86e-ff8f-41d4-aab0-7e80154a74aa" width="400" height="400">

- To view a transaction : choose a transaction on the list -> "View Transaction"

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/70e88207-2bf4-4226-ad0a-d21918ed2a3d" width="400" height="400">

- To generate invoice of a transaction : choose a transaction on the list -> "View Transaction" -> "generate invoice". The invoice will be saved in the current working directory.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/68812852/22936152-1dc7-438d-a90e-c1f5d7d4cf05" width="400" height="400">



<a id="item-seven"></a>
## 7. Task Division
ID        |Name                  |Tasks                                                                    |Comments                                                   |
|:-------:|:--------------------:|-------------------------------------------------------------------------|-----------------------------------------------------------|
|10421034 |Phạm Phi Long         |<ul><li>Project manager</li><li>Full-stack developer</li></ul>           | <ul><li>Responsible</li><li>Enthusiastic</li></ul>        |
|10421087 |Thái Minh Kiên        |<ul><li>Back-end developer</li><li>Docker deployment </li></ul>          | <ul><li>Active</li><li>Understanding</li></ul>            |
|10421074 |Đào Thế Hiển          |<ul><li>Back-end developer</li><li>Tester</li><li>Report writer</li></ul>| <ul><li>High-level tool utilizing</li><li>Neat</li></ul>  |
|10421092 |Trương Quang Minh     |<ul><li>Back-end developer</li><li>Tester</li><li>Report writer</li></ul>| <ul><li>Entertaining</li><li>Hard-working</li></ul>       |
|10421121 |Nguyễn Đức Nhật Minh  |<ul><li>Full-stack developer</li><li>Designer</li></ul>                  | <ul><li>Decisive</li><li>Quick-witted</li></ul>           |
|10421031 |Đặng Hoàng Anh Khôi   |<ul><li>Back-end developer</li><li>Docker deployment </li></ul>          | <ul><li>Unique thinking</li><li>Persuasive</li></ul>      |
