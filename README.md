📚 Student Course Management System
🧾 Overview
This project is a Student Course Management System designed to handle the relationship between students, their registered courses, and attendance in course sections. The system is backed by a MySQL database designed using MySQL Workbench, and a Java application that connects to the database and performs full CRUD operations, as well as complex queries that span multiple entities.

🏗️ Project Structure
✅ Database Design: Created using MySQL Workbench, the database includes tables for:

Students

Courses

Enrollments (student-course registrations)

Sections

Attendance

☕ Java Integration:

The database is connected to a Java application using JDBC.

Each database table is mapped to a corresponding Java class (Entity).

CRUD operations (Create, Read, Update, Delete) are fully supported on all entities.

🧠 Complex Queries Support:

The application can execute and handle complex SQL queries that involve joins across multiple tables (e.g., student attendance in a course, listing students per section, etc.).

Any query executed in the Java layer directly reflects in the actual MySQL database.

🎨 Simple GUI Interface:

A basic user interface allows interaction with the system.

The GUI supports displaying data, inserting/updating entries, and triggering complex queries.

The interface is not overly fancy but achieves its goal in terms of functionality and clarity.

⚙️ Features
Register new students and courses

Assign students to courses

Track attendance for each student in specific course sections

View course-wise and student-wise reports

Execute advanced queries combining data from multiple tables

🔌 Technologies Used
Java SE (Core + JDBC)

MySQL Database

MySQL Workbench (for DB schema design)

Swing / JavaFX (for GUI — depending on your actual implementation)

🔄 Live Interaction
Any operation done through the Java program (insert/update/delete/query) is directly applied to the live database, ensuring real-time consistency and up-to-date results.

📁 Getting Started
Clone the repository.

Import the .sql file (if provided) into MySQL Workbench.

Update DB credentials in the Java application (DB URL, username, password).

Compile and run the Java application.

Use the GUI or terminal to interact with the system.

✍️ Authors
[Your Name Here]

Ain Shams University — Faculty of Science
