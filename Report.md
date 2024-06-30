# Programing Exercise Report: Pharmacy Management System
# Team 8


## Table of Contents
1. [Introduction](#item-one)
2. [Application Description](#item-two)
3. [Analysis](#item-three)
4. [Design](#item-four)
5. [Framework](#item-five)
6. [User Guide](#item-six)
7. [User Guide](#item-seven)
8. [Task Division](#item-seven)

<a id="item-one"></a>
## 1. Introduction
This Maven project focuses on an innovative application for managing a pharmaceutical store. The application can record the maintenance of medicines, drugs, and supplies from suppliers. It also handles the administration of employee records, provides separate usernames and passwords for each employee, and features a built-in messaging system. Additionally, it can generate invoices, bills, receipts, and other related documents, while ensuring the effective management of drugs and consumables within the pharmacy unit.

<a id="item-two"></a>
## 2. Application Description
This project is about an application to effectively manage a pharmaceutical store. It helps the pharmacist to :
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
      - The system shall maintain an accurate inventory count by updating quantities of medicines or drugs received from suppliers.
      - The system shall support search functionality base on criteria such as medicine name, batch number, supplier information.
   2. Administration management of employee records
      - The system shall provide forms or fields to input personal details, employment contracts and role within the pharmacy, licensing and certification details.
      - The system shall create unique employee ID whenever there is a new employee input.
      - The system shall define clear access levels and permissions for employees and administrators.
      - The system shall ensure that only authorized personnel can view or modify employee and medication information.
      - The system shall support search functionality base on criteria such as employee names, numbers, role.
      - The system shall be able to update or edit existing records of the list.
   3. Provision of separate accounts with usernames and passwords for each employee
      - The system shall create account username base on employee ID, and a random password.
      - When creating account, the password will be given to employee.
      - Employee can change password after accessing into the account.
      - The account contains employee ID, personal detail of employee, job title, role within the pharmacy, training records, licensing and certification details.
      - Higher permission of the account will be granted base on role of the employee.
      - The account can be (optionally) deleted 1 years after employee leave the pharmarcy.
   4. Built-in messaging system for communication among users
      - The system should support message editing and deletion, allowing users to modify or remove sent messages within a specified timeframe.
      - Users should be able to view message history and search for specific messages.
      - Users should have the ability to create or participate in group conversations with multiple participants.
      - Group messaging features should include adding or removing members.
      - Users should have the option to export or back up their message history for safekeeping.
      - Users can forward received messages to other users or groups.
      - Users can add new contacts to their contact list.
   5. Generation of invoices, bills, receipts, and other related documents
      - The system shall integrate with current databases and transaction records, and automatically print out document templates with accurate information.
      - The system shall calculate totals, taxes, discounts, and other financial details from input data.
      - The system shall print out accurate payment details, transaction IDs, and payment status in documents, or legal documents...
      - The system shall meet regulatory standards and compliance requirements, ensuring legal and industry-specific document compliance.
   6. Management of drugs and consumables within the pharmacy unit
      - The system shall keep track of the medication and consumables information frequently.
      - The system shall alert when the quantities are below a certain threshold to ensure that the pharmacy never runs out of medications and consumables. The same goes for expiration monitoring.
      - The system shall display the recorded information as commanded.
### Non-Functional:
   1. The system shall have an intuitive and user-friendly interface.

<a id="item-four"></a>
## 4. Design

### Class diagram

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/class_diagram/account_class.png">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/class_diagram/consumable_class.png">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/class_diagram/employeeclass.png">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/class_diagram/medicine_class.png">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/class_diagram/transaction_class.png">

### Use case diagram
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/use_case/account_usecase.png">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/use_case/consumable_use%20case.png">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/use_case/medicine_usecase.png">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/use_case/transaction_Use%20case.png">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/use_case/usecaseemployee.png">


### Sequence diagram
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/sequence_diagram/Item_%20Management.png">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/sequence_diagram/Medicine-Consumable_Manager.png">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/sequence_diagram/employeesequence.png">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/sequence_diagram/transaction_sequence.png">
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/blob/main/sequence_diagram/separate_account.png">
<img src="">

### ER diagram
<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/92b574e4-9a1f-4416-9bdb-fb7de8bf2e91">
<img src="">

### Database

<a id="item-five"></a>
## 5. System architecture
This section outlines the architecture of the Pharmacy Management System.
### Framework
- Hibernate : used for Object-Relational Mapping (ORM) to interact with databases. Provides some onjects for database operation and manages entities in the database.
### Library 
- PostgreSQL JDBC Driver: provide the JDBC driver for PostgreSQL database. Allow Java application to connect to databases
- Java Standard libray: Collections, Input/Output, Exception Handling (java.util, java.io, etc.)
- iText: for creating and designing PDF documents in Java. Use to generate bills, invoices of the transaction.
- Swing : used to build  the graphical user interface of the application 
### Tools
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
### I. Admin Account
#### Employee Management:
This is our Main Menu, here you can click the "Manage Employee" button to enter the Employee Management fuction. But this functionality is only available to people with admin roles.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/b86dc19b-091d-499f-a49a-5b8d915bd607" width="400" height="400">

Here is the GUI of this function. It will have 3 blank boxes so you can enter information to search based on ID, name, position and then just press "Search".

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/ff984017-9e0e-454b-999f-63e06c7638a8" width="500" height="400">

You can view employee information more easily by clicking "View", it will display complete information.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/de7348f0-72af-4f4c-ae06-ccbb0c963a59" width="500" height="400">

Next, you can add employees by entering information or importing a csv file. After you have added it, you click on the "Refresh" button.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/3bfd3e48-5169-456b-9e00-97c591af0f1c" width="500" height="400">

Not only that, you can click on the box of the employee who needs to change. you have delete and edit functions.

<img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/0c6e9456-15a4-4613-a651-00c9118c6f9e" width="500" height="400">

Finally, you can export a csv file containing employee information.

### II. User Account
#### ....







<a id="item-seven"></a>
## 8. Task Division

## 9. Cách up hình lên report
<p>kéo hình từ thư mục trong máy vào thẳng report thì ví dụ nó sẽ ra cái này.
![editem](https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/0c6e9456-15a4-4613-a651-00c9118c6f9e) </p>

![editem](https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/0c6e9456-15a4-4613-a651-00c9118c6f9e) 

 sau đó copy cái https vào trong " " như thế này: img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/0c6e9456-15a4-4613-a651-00c9118c6f9e" width="400" height="400"
<p><img src="https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/0c6e9456-15a4-4613-a651-00c9118c6f9e" width="400" height="400"></p>
