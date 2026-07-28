# 🚗 AutoServe-VSM

A full-stack **Vehicle Service Management System** that streamlines the complete vehicle servicing workflow. Customers can register, manage their vehicles, book service appointments, track repair progress, view job cards, invoices, and make payments through a secure web application.

---

## 📖 Project Overview

AutoServe-VSM is designed to digitize the vehicle servicing process by connecting customers, mechanics, and administrators on a single platform. The application follows a secure role-based architecture using JWT authentication and RESTful APIs built with Spring Boot.

---

## ✨ Features

### 👤 Customer
- User Registration & Login
- Manage Multiple Vehicles
- Book Service Appointments
- Track Appointment Status
- View Job Cards
- View Invoices
- Make Payments
- View Service History

### 🔧 Mechanic
- Secure Login
- View Assigned Appointments
- Accept/Reject Service Requests
- Update Service Status
- Generate Job Cards
- Add Repair Details

### 🛠️ Administrator
- Manage Customers
- Manage Mechanics
- Monitor Vehicles
- View Appointments
- View Payments
- System Dashboard

---

# 🛠️ Tech Stack

## Backend

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA (Hibernate)
- MySQL
- Maven

## Frontend

- React.js
- React Router
- Axios
- Bootstrap
- CSS

## Tools

- Eclipse IDE
- VS Code
- MySQL Workbench
- Postman
- Git
- GitHub

---

# 📂 Project Structure

```text
AutoServe-VSM
│
├── Backend
│   ├── src
│   ├── pom.xml
│   └── ...
│
├── Frontend
│   ├── src
│   ├── public
│   ├── package.json
│   └── ...
│
├── README.md
└── .gitignore
```

---

# 🔐 Authentication

- JWT Authentication
- Role-Based Authorization
- Customer
- Mechanic
- Administrator

---

# 🗄️ Database Entities

- User
- Customer
- Mechanic
- Vehicle
- Appointment
- JobCard
- Invoice
- Payment

---

# 🚀 Installation

## Backend

```bash
cd Backend
```

Configure your MySQL database in:

```
src/main/resources/application.properties
```

Run:

```bash
mvn spring-boot:run
```

---

## Frontend

```bash
cd Frontend
npm install
npm run dev
```

---

# 📡 API Testing

Use **Postman** to test the REST APIs.

---

# 📸 Screenshots

Screenshots will be added after project completion.

---

# 🔮 Future Enhancements

- Email Notifications
- Online Payment Gateway
- Service Reminders
- Vehicle Maintenance History
- Admin Dashboard Analytics
- Spare Parts Inventory
- Service Reports

---

# 👨‍💻 Developed By

**Aditya Molwane**

CDAC PG-DAC Final Project