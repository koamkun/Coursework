# 📊 Employee Payroll Management System

**Student:**Sultanov Timur  
**Course:** Eeair24  


---

## 📘 Description

The **Employee Payroll Management System** is a Java-based application that allows users to manage payroll records for employees. The system handles the creation, reading, updating, and deletion of employee records, and calculates net salaries based on base pay, tax rates, and deductions. Data is saved in a CSV file to allow persistence across sessions.

---

## 🎯 Objectives

- Create a payroll management tool using Java.
- Support basic CRUD operations.
- Calculate employee net salary including taxes and deductions.
- Implement file persistence using CSV files.
- Practice console-based user interaction.
- Use OOP principles to organize code.
- Enable structured and reusable code.
- Test the application with different user inputs.
- Practice Java collections and file I/O.
- Document the entire system for academic evaluation.

---

## ✅ Project Requirement List

1. Load employee data from a CSV file.
2. Save employee data to a CSV file.
3. Automatically assign unique IDs to employees.
4. Create new employee records.
5. Read/display all employee records with net pay calculation.
6. Update existing employee records.
7. Delete employee records by ID.
8. Handle invalid inputs gracefully.
9. Calculate net pay using base salary, tax rate, and deductions.
10. Structure code using OOP principles (Employee class, PayrollSystem controller).
11. Console-based interface with options for user input.
12. Ensure data consistency when saving/loading from files.

---

## 📚 Documentation

### 🔢 Algorithms

- **Net Salary Calculation**  
  ```netPay = baseSalary - (baseSalary * taxRate) - deductions```

### 🧱 Data Structures

- `HashMap<Integer, Employee>` — for fast access and update of employee records by ID.
- `ArrayList<String>` (temporary, for reading file lines).
- `Scanner` — for console input and file reading.

### 🧩 Functions/Modules

- `Employee` class: holds employee attributes and logic.
- `PayrollSystem` class: manages employee operations and file I/O.
- `loadFromCSV()`, `saveToCSV()` — for persistence.
- `createEmployee()`, `updateEmployee()`, `deleteEmployee()`, etc.

### 🧗 Challenges

- Managing unique IDs across sessions.
- Ensuring CSV format is robust and parseable.
- Input validation to avoid exceptions.
- Updating employee data without GUI.

---

## 🧪 Test Cases and Outputs

### ✅ Sample Input 1:
- Name: Alice Johnson  
- Base Salary: 5000  
- Tax Rate: 0.2  
- Deductions: 200

### ✅ Expected Output:
ID: 1 Name: Alice Johnson Base Salary: $5000.0 Tax Withheld: $1000.0 Deductions: $200.0 Net Pay: $3800.0
