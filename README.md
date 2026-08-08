🚗 AutoServe VSM
Vehicle Service Management System
Full-Stack Web Application with Microservice Integration
React • Spring Boot • MySQL • Spring Security • Razorpay • Microservices
📌 Project Overview
AutoServe VSM is a full-stack Vehicle Service Management System designed to digitize and simplify the complete vehicle servicing lifecycle. The system connects Customers, Mechanics and Administrators through a centralized platform.
Customers can manage vehicles, book appointments, track service progress, view job cards, make online payments and download paid invoices. Mechanics can manage assigned service work and maintain detailed job cards. Administrators can manage customers, mechanics and service operations.
✨ Features
👤 Customer
• Registration and Login
• JWT Authentication
• Profile Management
• Change Password
• Vehicle Management
• Appointment Booking
• Appointment Tracking
• Appointment Search
• Service History
• Job Card Viewing
• Inspection Notes Viewing
• Work Done Viewing
• Mechanic Remarks Viewing
• Invoice Viewing
• Online Payment
• Paid Invoice PDF Download
• Vehicle Maintenance Tips
🔧 Mechanic
• Mechanic Login
• Mechanic Dashboard
• Assigned Appointments
• Job Card Management
• Service Status Updates
• Inspection Notes
• Work Done
• Mechanic Remarks
• Spare Parts
• Labor Cost
• Mechanic Profile
• Change Password
🛡️ Administrator
• Admin Login
• Admin Dashboard
• Customer Management
• Mechanic Management
• Vehicle Management
• Appointment Management
• Payment Monitoring
• Admin Profile
• System Monitoring
🔐 Authentication & Authorization
AutoServe uses Spring Security with JWT-based authentication.
Role Access
CUSTOMER Customer dashboard and service features
🚗 Vehicle Management
Customers can add and manage their vehicles.
• Vehicle Brand
• Vehicle Model
• Vehicle Number
• Vehicle Type
• Manufacturing Year
📅 Appointment Management
Customers can create service appointments for registered vehicles.
Customer
↓
Select Vehicle
↓
Enter Problem / Service Details
↓
Create Appointment
↓
Appointment Processing
↓
Mechanic Service Work
🔧 Job Card Management
• Inspection Notes
• Work Done
• Mechanic Remarks
• Spare Parts
• Labor Cost
• Estimated Cost
• Final Cost
• Job Status
Appointment
↓
Mechanic Service
↓
Job Card
├── Inspection Notes
├── Work Done
├── Mechanic Remarks
├── Spare Parts
└── Labor Cost
↓
Final Cost → Invoice → Payment
💳 Payments & Razorpay
AutoServe integrates Razorpay for online payments.
Invoice Generated
↓
Payment Pending
↓
Razorpay Checkout
↓
Payment Successful
↓
Payment Confirmed
📄 Invoice PDF Protection
Invoice PDF downloads are restricted until payment has been completed.
Payment Pending → PDF Download ❌ BLOCKED
Payment Completed → PDF Download ✅ ALLOWED
👤 Profile Management
• Customer: view/update profile and change password
• Mechanic: view/update profile and change password
• Admin: view admin profile and manage account information
💡 Vehicle Tips Microservice
AutoServe includes an independently running Vehicle Tips Microservice built using Spring Boot. It provides maintenance tips according to the selected vehicle type.
Supported Vehicle Types
🚗 CAR
🏍️ BIKE
🚚 TRUCK
Microservice Architecture
React Frontend :3000
│ REST API
▼
AutoServe Backend :8080
│ HTTP Request
▼
Vehicle Tips Microservice :8081
Vehicle Tips Flow
Customer Dashboard → Select Vehicle → Vehicle Type
↓
React Frontend → AutoServe Backend :8080
↓
VehicleTipsClient → Vehicle Tips Service :8081
↓
Vehicle-specific Tips → Customer Dashboard
🏗️ System Architecture
┌─────────────────┐
│ CUSTOMER │
└────────┬────────┘
↓
┌────────────────────────┐
│ REACT FRONTEND │
│ Port 3000 │
└───────────┬────────────┘
│ REST APIs
↓
┌────────────────────────────────┐
│ AUTOSERVE BACKEND │
│ Spring Boot :8080 │
└──────────────┬─────────┬───────┘
│ │ HTTP
│ ↓
│ ┌────────────────────┐
│ │ VEHICLE TIPS │
│ │ MICROSERVICE :8081 │
│ └────────────────────┘
↓
┌──────────────┐
│ MySQL │
└──────────────┘
↓
┌──────────────┐
│ RAZORPAY │
└──────────────┘
🛠️ Technology Stack
Frontend
Technology Purpose
React User Interface
Vite Development & Build Tool
React Router Routing
Axios API Communication
Bootstrap UI Design
Bootstrap Icons Icons
React Toastify Notifications
Backend
Technology Purpose
Java 21 Programming Language
Spring Boot 4.1.0 Backend Framework
Spring Web MVC REST APIs
Spring Security Security
JWT Authentication
Spring Data JPA Data Access
Hibernate ORM
Maven Build & Dependency Management
MySQL Database
Lombok Boilerplate Reduction
Microservice
Technology Purpose
Java 21 Programming Language
Spring Boot 4.1.0 Microservice Framework
Spring Web REST APIs
Maven Build & Dependency Management
Payment
Technology Purpose
Razorpay Online Payments
📂 Project Structure
AutoServe-VSM/
├── Backend/
│ ├── src/main/java/com/project/autoserve/
│ │ ├── client/ config/ controller/ dto/
│ │ ├── entity/ enums/ exception/ repository/
│ │ ├── security/ service/ util/
│ ├── src/main/resources/
│ └── pom.xml
├── Frontend/
│ ├── src/
│ │ ├── api/ components/ context/
│ │ ├── pages/admin/ pages/customer/ pages/mechanic/
│ │ ├── services/ utils/
│ ├── package.json
│ └── vite.config.js
├── VehicleTipsService/
│ ├── src/main/java/
│ ├── src/main/resources/
│ └── pom.xml
├── README.md
└── .gitignore
⚙️ Prerequisites
• Java 21
• Maven
• Node.js
• npm
• MySQL
• Git
• Eclipse / Spring Tool Suite
• Visual Studio Code
• MySQL Workbench
• Postman
🚀 Installation & Setup

1. Clone the Repository
   git clone <repository-url>
   cd AutoServe-VSM
2. Configure MySQL
   CREATE DATABASE autoserve;
   Configure Backend/src/main/resources/application.properties:
   spring.datasource.url=jdbc:mysql://localhost:3306/autoserve
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>
   ⚠️ Never commit real database credentials to GitHub.
3. Start the Backend
   Run AutoServeVsmApplication. Backend: http://localhost:8080
4. Start the Vehicle Tips Microservice
   Run VehicleTipsServiceApplication. Microservice: http://localhost:8081
5. Start the Frontend
   cd Frontend
   npm install
   npm run dev
   Frontend: http://localhost:3000
   ▶️ Running the Complete Application
6. MySQL
   ↓
7. AutoServe Backend :8080
   ↓
8. Vehicle Tips Microservice :8081
   ↓
9. React Frontend :3000
   🔑 Demo Credentials
   These accounts are intended for local development and academic/project demonstration only.
   Role Email Password
   ADMIN admin@autoserve.com Admin@123
   CUSTOMER aditya@gmail.com Password@123
   MECHANIC amit@gmail.com Amit@123
   The Login page also provides Quick Demo Login Autofill buttons. The Admin account is currently configured as a hard-coded/demo account.
   🔌 Main API Modules
   Authentication
   POST /api/auth/login
   POST /api/auth/register
   Customer Profile
   GET /api/customer/profile
   PUT /api/customer/profile
   Vehicles
   GET /api/vehicles
   POST /api/vehicles
   PUT /api/vehicles/{id}
   DELETE /api/vehicles/{id}
   Appointments
   GET /api/appointments
   POST /api/appointments
   Invoices
   POST /api/invoices/generate/{jobId}
   GET /api/invoices/{invoiceId}
   GET /api/invoices/jobcard/{jobId}
   GET /api/invoices
   GET /api/invoices/{invoiceId}/pdf
   Vehicle Tips
   GET /api/vehicle-tips/{vehicleType}
   Microservice: GET /api/tips/{vehicleType}
   🔄 Complete Customer Service Flow
   Register / Login
   ↓
   Dashboard
   ↓
   Add Vehicle
   ↓
   Book Appointment
   ↓
   Mechanic Service
   ↓
   Job Card
   ↓
   Inspection / Work Done / Remarks
   ↓
   Final Cost
   ↓
   Invoice
   ↓
   Payment
   ↓
   Paid Invoice PDF
   🧪 Testing
   • Browser
   • Postman
   • Frontend UI
   • Spring Boot application logs
   Frontend: http://localhost:3000
   Backend: http://localhost:8080
   Vehicle Tips Service: http://localhost:8081
   🔒 Security
   • JWT Authentication
   • Spring Security
   • Role-Based Authorization
   • Protected Frontend Routes
   • Protected Backend APIs
   • Payment Verification
   • Protected Invoice PDF Downloads
   Sensitive values such as database passwords, JWT secrets and Razorpay secrets should be stored outside the repository for production use.
   🌱 Git Workflow
   main
   │
   ├── feature/authentication
   ├── feature/profile-management
   ├── feature/payments
   ├── feature/vehicle-tips
   └── feature/ui-polish
   git status
   git add .
   git commit -m "feat: description"
   git push
   📈 Current Project Status
   Authentication
   ✅ Customer Login
   ✅ Mechanic Login
   ✅ Admin Login
   ✅ JWT Authentication
   ✅ Role-Based Authorization
   Customer
   ✅ Customer Dashboard
   ✅ Vehicle Management
   ✅ Appointment Booking
   ✅ Appointment History
   ✅ Appointment Search
   ✅ Job Card Viewing
   ✅ Service History
   ✅ Invoice Viewing
   ✅ Payment
   ✅ Profile Management
   ✅ Change Password
   ✅ Vehicle Maintenance Tips
   Mechanic
   ✅ Mechanic Dashboard
   ✅ Assigned Appointments
   ✅ Job Card Management
   ✅ Inspection Notes
   ✅ Work Done
   ✅ Mechanic Remarks
   ✅ Spare Parts
   ✅ Labor Cost
   ✅ Profile Management
   ✅ Change Password
   Admin
   ✅ Admin Dashboard
   ✅ Customer Management
   ✅ Mechanic Management
   ✅ Vehicle Management
   ✅ Appointment Management
   ✅ Profile Management
   Payments & Invoices
   ✅ Invoice Generation
   ✅ Razorpay Integration
   ✅ Payment Status
   ✅ Invoice PDF Generation
   ✅ PDF Download Restriction Before Payment
   UI
   ✅ Responsive Dashboard
   ✅ Role-based Navigation
   ✅ Confirmation Modals
   ✅ Loading States
   ✅ Toast Notifications
   ✅ Vehicle Tips UI
   Microservice
   ✅ Vehicle Tips Microservice
   ✅ CAR Tips
   ✅ BIKE Tips
   ✅ TRUCK Tips
   ✅ Backend-to-Microservice Communication
   ✅ Frontend Integration
   🚀 Future Enhancements
   • Email notifications
   • SMS notifications
   • Customer service ratings
   • Automated service reminders
   • Advanced admin analytics
   • Multiple service center support
   • Advanced mechanic assignment
   • Service history analytics
   • Docker containerization
   • Cloud deployment
   • Swagger/OpenAPI documentation
   • Production-ready secret management
   🏆 Project Highlights
   Full-Stack Development
   React
   ↓
   Spring Boot REST API
   ↓
   MySQL
   Secure Authentication
   React
   ↓
   JWT
   ↓
   Spring Security
   ↓
   Role-Based APIs
   Online Payment
   Customer
   ↓
   Razorpay
   ↓
   Payment Verification
   ↓
   Invoice PDF Access
   Microservice Integration
   React Frontend
   ↓
   AutoServe Backend
   ↓
   Vehicle Tips Microservice
   📊 Project Architecture Summary
   Component Technology Port
   Frontend React + Vite 3000
   Main Backend Spring Boot 8080
   Vehicle Tips Spring Boot Microservice 8081
   Database MySQL 3306
   Payment Razorpay External
   👨‍💻 Project Details
   Project AutoServe – Vehicle Service Management System
   Type Full-Stack Web Application with Microservice Integration
   Frontend React
   Backend Spring Boot
   Database MySQL
   Authentication JWT + Spring Security
   Payment Gateway Razorpay
   Microservice Vehicle Tips Service
   📄 License
   This project was developed for educational and academic purposes.

🚗 AutoServe VSM
Simplifying Vehicle Service Management
React • Spring Boot • MySQL • Spring Security • Razorpay • Microservices
