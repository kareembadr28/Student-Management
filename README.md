# 🎓 Student Course Management System

This project is a **Student Course Management System** designed to manage students, the courses they are enrolled in, and their attendance in course sections. It combines a well-structured **MySQL database** (designed using **MySQL Workbench**) with a **Java** application that performs full functionality via a clean connection using JDBC.

---

## 🧱 System Description

- 🗄️ The database was built using **MySQL Workbench** and contains the following main tables:
  - `Students`
  - `Courses`
  - `studentcourse` (student-course registrations)
  - `Sections`
  - `Attendance`

- ☕ The Java application:
  - Connects to the database using **JDBC**.
  - Maps each table into a corresponding **Java class** (Entity).
  - Supports full **CRUD operations**: Create, Read, Update, and Delete on all entities.
  - Can perform **complex SQL queries** that join multiple tables and classes together.
  - Executes queries in Java that directly reflect and update the **original live database**.

- 🖥️ A simple and functional **Graphical User Interface (GUI)** was developed to:
  - Interact with the system easily.
  - Display and manage student-course relationships.
  - Track and manage student attendance per section.
  - Execute queries and instantly visualize results.

---

## 🚀 Features

- Register and manage student information.
- Add, edit, and delete course details.
- Link students to their enrolled courses.
- Track attendance by course section.
- View course-wise or student-wise attendance reports.
- Run advanced custom queries across the system.

---

## 🛠️ Technologies Used

- **Java SE** with **JDBC**
- **MySQL Workbench** for DB design
- **MySQL Server**
- **Swing / JavaFX** for GUI (depending on your implementation)

---

## 🧾 Notes

> All operations done through the Java application reflect directly on the live MySQL database, ensuring real-time accuracy and consistency.

---

## 📌 Author

Made with ❤️ by `kareem badr`  
Ain Shams University – Faculty of Science  
