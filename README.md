# Enterprise E-Commerce Backend System

## Project Overview
A robust, terminal-based e-commerce management system built entirely in Java. This project serves as a comprehensive implementation of advanced Object-Oriented Programming (OOP) principles and software engineering design patterns, simulating a real-world backend infrastructure.

The system relies on a custom-built command processing engine that reads batch operations from text files, simulating database transactions, user management, and automated financial logic.

## System Architecture & Design
* **UML-Driven Development:** The software development lifecycle was initiated with comprehensive Use-Case and Activity diagrams to map out complex transactional flows (e.g., cart management, campaign generation).
* **Deep Class Hierarchy:** Utilizes abstract classes, deep inheritance, and polymorphism to model a highly scalable inventory system (Electronics, Cosmetics, Office Supplies) and multi-tiered user roles (Admin, Technician, Customer).

## Key Features
* **Custom Command Processor:** A centralized engine (`MyBazaar.java`) that parses and executes over 15 distinct operational commands (e.g., `ADDCUSTOMER`, `ADDTOCART`, `ORDER`, `CREATECAMPAIGN`) sequentially from a `.txt` file.
* **Dynamic Customer Loyalty Algorithm:** Automatically monitors a customer's `totalSpent` state. Triggers algorithmic tier upgrades (Classic -> Silver -> Golden) and dynamically applies appropriate discount percentages (10% or 15%) at checkout.
* **Authorization & Role Management:** Strict method-level access control where Technicians handle inventory queries and Admins manage campaigns and user registries.

## Tech Stack
* **Language:** Java
* **Concepts:** Object-Oriented Programming (OOP), File I/O, Data Structures, System Design
* **Design Tools:** UML (Use-Case & Activity Diagrams)

## How to Run
The system initializes by loading existing users and item catalogs, then processes a batch of commands. Ensure all three data files are in the root directory.

Compile the source code:
> javac Main.java

Execute the engine by passing the database files as arguments:
> java Main users.txt item.txt commands.txt