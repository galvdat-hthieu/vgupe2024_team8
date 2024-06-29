# Programing Exercise Report: Pharmacy Management System
# Team 8


## Table of Contents
1. [Introduction](#item-one)
2. [Problem Description](#item-two)
3. [Customer Requirement](#item-three)
4. [Framework](#item-four)
5. [Technologies Used](#item-five)
6. [Design](#item-six)
7. [User Guide](#item-seven)
8. [Task Division](#item-seven)

<a id="item-one"></a>
## 1. Introduction
This Maven project focuses on an innovative application for managing a pharmaceutical store. The application can record the maintenance of medicines, drugs, and supplies from suppliers. It also handles the administration of employee records, provides separate usernames and passwords for each employee, and features a built-in messaging system. Additionally, it can generate invoices, bills, receipts, and other related documents, while ensuring the effective management of drugs and consumables within the pharmacy unit.

<a id="item-two"></a>
## 2. Problem Description
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
## 4. Code Modules
This report outlines the design and implementation of a Pharmacy Management System using Hibernate, Postgre SQL, and the MVC pattern. Hibernate has available operations to connect, store, and modify data in the database because this is an open source and automatically generates SQL queries based on the entity mappings. In addition, Hibernate supports multiple database dialects which means we can switch databases with minimal configuration changes.
### System Architecture
- Hibernate:  Used for ORM to map Java objects to database tables.
- PostgreSQL JDBC Driver: Allows Java to connect to PostgreSQL databases.
- model/: Contains entity classes representing the database tables.
- repository/: Contains repository classes for database operations.
- service/: Contains service classes that implement business logic.
- view/: Contains Swing form classes for the GUI.
- util/: Contains utility classes.
- hibernate.cfg.xml: Configuration file for Hibernate. Important for database connection details and mappings.

<a id="item-five"></a>
## 5. Technologies Used
   - Hibernate: ORM framework for mapping Java objects to database tables.
   - PostgreSQL: Relational database management system for storing employee data.
   - Swing: GUI toolkit for the Java programming language to create the user interface.
   - Java: Programming language used for the development of the application.

Thanks to Java’s diverse libraries, it has helped us a lot in development. We utilized the following
libraries:



<a id="item-six"></a>
## 6. Design

<a id="item-seven"></a>
## 7. User Guide
### Employee Management:
![Screenshot 2024-06-29 161245](https://github.com/galvdat-hthieu/vgupe2024_team8/assets/130123521/403b158f-83bc-40c4-9b51-00275941f9a6)


<a id="item-seven"></a>
## 8. Task Division
