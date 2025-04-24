import java.io.*;
import java.util.*;

class Employee {
    private int id;
    private String name;
    private double baseSalary;
    private double taxRate;
    private double deductions;

    public Employee(int id, String name, double baseSalary, double taxRate, double deductions) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
        this.taxRate = taxRate;
        this.deductions = deductions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void update(String name, double baseSalary, double taxRate, double deductions) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.taxRate = taxRate;
        this.deductions = deductions;
    }

    public double calculateNetPay() {
        double tax = baseSalary * taxRate;
        return baseSalary - tax - deductions;
    }

    public void printPaycheck() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Base Salary: $" + baseSalary);
        System.out.println("Tax Withheld: $" + (baseSalary * taxRate));
        System.out.println("Deductions: $" + deductions);
        System.out.println("Net Pay: $" + calculateNetPay());
        System.out.println("----------------------------------");
    }

    public String toCSV() {
        return id + "," + name + "," + baseSalary + "," + taxRate + "," + deductions;
    }

    public static Employee fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Employee(
                Integer.parseInt(parts[0]),
                parts[1],
                Double.parseDouble(parts[2]),
                Double.parseDouble(parts[3]),
                Double.parseDouble(parts[4])
        );
    }
}

public class Main {
    private Map<Integer, Employee> employeeMap = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private int nextId = 1;
    private final String FILE_NAME = "employees.csv";

    public void run() {
        loadFromCSV();

        while (true) {
            System.out.println("\n--- Payroll System Menu ---");
            System.out.println("1. Create Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Process Payroll");
            System.out.println("0. Save and Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> createEmployee();
                case 2 -> viewEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> processPayroll();
                case 0 -> {
                    saveToCSV();
                    System.out.println("Saved and exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void createEmployee() {
        System.out.print("Enter name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter base salary: ");
        double baseSalary = scanner.nextDouble();
        System.out.print("Enter tax rate (e.g., 0.2): ");
        double taxRate = scanner.nextDouble();
        System.out.print("Enter deductions: ");
        double deductions = scanner.nextDouble();

        Employee emp = new Employee(nextId++, name, baseSalary, taxRate, deductions);
        employeeMap.put(emp.getId(), emp);
        System.out.println("Employee created with ID: " + emp.getId());
    }

    private void viewEmployees() {
        if (employeeMap.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee emp : employeeMap.values()) {
            emp.printPaycheck();
        }
    }

    private void updateEmployee() {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        Employee emp = employeeMap.get(id);
        if (emp == null) {
            System.out.println("Employee not found.");
            return;
        }
        scanner.nextLine();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new base salary: ");
        double baseSalary = scanner.nextDouble();
        System.out.print("Enter new tax rate: ");
        double taxRate = scanner.nextDouble();
        System.out.print("Enter new deductions: ");
        double deductions = scanner.nextDouble();

        emp.update(name, baseSalary, taxRate, deductions);
        System.out.println("Employee updated.");
    }

    private void deleteEmployee() {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        if (employeeMap.remove(id) != null) {
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void processPayroll() {
        for (Employee emp : employeeMap.values()) {
            emp.printPaycheck();
        }
    }

    private void saveToCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Employee emp : employeeMap.values()) {
                writer.println(emp.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Failed to save to CSV: " + e.getMessage());
        }
    }

    private void loadFromCSV() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Employee emp = Employee.fromCSV(line);
                employeeMap.put(emp.getId(), emp);
                nextId = Math.max(nextId, emp.getId() + 1);
            }
        } catch (IOException e) {
            System.out.println("Failed to load from CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
