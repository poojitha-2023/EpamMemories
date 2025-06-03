import java.util.*;
public class Employee {
    private String name;
    private String department;
    private int employeeId;
    private double baseSalary;
    private static HashSet<Integer> idCheck = new HashSet<>();
    public Employee(int employeeId, String name, String department, double baseSalary) {
        if (idCheck.contains(employeeId)) {
            throw new IllegalArgumentException("Error: Duplicate Employee ID.");
        }
        if (name == null || name.trim().isEmpty() || department == null || department.trim().isEmpty() || baseSalary < 0) {
            throw new IllegalArgumentException("Invalid employee details provided.");
        }
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.baseSalary = baseSalary;
        idCheck.add(employeeId);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void displayInformation() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + name);
        System.out.println("Employee Department: " + department);
        System.out.println("Employee Base Salary: " + baseSalary);
    }
}

class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Employee> employees = new ArrayList<>();
            employees.add(new Employee(1, "Poojitha", "HR", 1200000));
            employees.add(new Employee(2, "Shravya", "IT", 800000));
            System.out.println("Displaying current employee records:");
            for (Employee e : employees) {
                e.displayInformation();
            }
            System.out.println("\nRemoving Employee with ID 1...");
            boolean removed = false;
            Iterator<Employee> iterator = employees.iterator();
            while (iterator.hasNext()) {
                Employee emp = iterator.next();
                if (emp.getEmployeeId() == 1) {
                    iterator.remove();
                    removed = true;
                    System.out.println("Successful deletion.");
                    break;
                }
            }
            if (!removed) {
                System.out.println("Unsuccessful deletion: Employee ID not found.");
            }
            System.out.println("\nUpdated Employee Records:");
            for (Employee emp : employees) {
                emp.displayInformation();
            }
            System.out.println("Adding a new employee...");
            employees.add(new Employee(3, "Jharna", "Finance", 405050));
            System.out.println("\nFinal Employee Records:");
            for (Employee emp : employees) {
                emp.displayInformation();
            }
            Map<Integer, String> employeeBenefits = new HashMap<>();
            employeeBenefits.put(1, "Health Insurance");
            employeeBenefits.put(2, "Pension Plan");
            employeeBenefits.put(3, "Transport Allowance");

            System.out.println("Employee Benefits:");
            for (Map.Entry<Integer, String> entry : employeeBenefits.entrySet()) {
                System.out.println("Employee ID: " + entry.getKey() + ", Benefit: " + entry.getValue());
            }
            Queue<Employee> payrollQueue = new LinkedList<>(employees);
            System.out.println("\nProcessing Payroll:");
            while (!payrollQueue.isEmpty()) {
                Employee emp = payrollQueue.poll();
                System.out.println("Processing payroll for: " + emp.getName() + " (ID: " + emp.getEmployeeId() + ")");
            }
            Map<Integer, Double> salaryDeductions = new HashMap<>();
            salaryDeductions.put(1, 5000.0);
            salaryDeductions.put(2, 3000.0);
            salaryDeductions.put(3, 4000.0);

            System.out.println("\nSalary Deductions:");
            for (Employee emp : employees) {
                double deduction = salaryDeductions.getOrDefault(emp.getEmployeeId(), 0.0);
                double netSalary = emp.getBaseSalary() - deduction;
                System.out.println("Employee ID: " + emp.getEmployeeId() + ", Net Salary: " + netSalary);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
