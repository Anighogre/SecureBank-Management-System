# 🏦 SecureBank – Online Banking Management System

SecureBank is a secure and user-friendly online banking application built using **Java, Servlets, JSP, JDBC, and Oracle Database**.  
It enables users to manage their bank accounts, transfer funds, view transaction history, and perform essential banking operations within a safe and structured environment.

---

## 🚀 Features

- 🔐 **User Authentication**
  - Secure login and session handling
  - Input validation & restricted access

- 💳 **Account Management**
  - View account balance
  - Manage user profile & account details

- 🔄 **Fund Transfer**
  - Transfer money between accounts
  - Backend validation & secure workflow

- 📜 **Transaction History**
  - Track all debits/credits
  - Chronological transaction records

- 🗄️ **Database Integration**
  - Oracle DB tables for users, accounts, loans & transactions
  - JDBC-based CRUD operations

- 🎨 **Responsive Frontend**
  - HTML, CSS, JavaScript, Bootstrap
  - Clean and user-friendly interface

---

## 🏗️ Project Architecture

SecureBank is built following the **MVC Architecture**:


### Tech Stack
- **Backend:** Java, Servlets, JSP, JDBC  
- **Frontend:** HTML, CSS, Bootstrap, JavaScript  
- **Database:** Oracle  
- **Server:** Apache Tomcat  
- **Tools:** Eclipse / IntelliJ, SQL Plus, Git  

---

## 📁 Folder Structure

SecureBank/
├── src/
│ ├── controller/ # Servlets
│ ├── dao/ # Database logic
│ ├── model/ # Beans
│ └── service/ # Business logic
├── WebContent/
│ ├── pages/ # JSP pages
│ ├── css/ # Styles
│ └── js/ # Scripts
├── database/ # SQL tables
├── README.md
└── pom.xml or .project


---

## ⚙️ How to Run the Project

1. Install **Apache Tomcat** (8 or above)  
2. Install **Oracle Database**  
3. Create required tables (users, accounts, transactions, loans)  
4. Configure DB credentials in your `DBConnection.java` file  
5. Import project into **Eclipse / IntelliJ**  
6. Run on Tomcat Server  
7. Open in browser:

