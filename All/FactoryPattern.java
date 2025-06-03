public class FactoryPattern {

    public abstract class Employee {
        private String name, department;
        private int employeeId;
        private double baseSalary;

        public Employee(int employeeId, String name, String department, double baseSalary) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid name.");
            }
            if (department == null || department.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid department.");
            }
            if (baseSalary < 0 || baseSalary > 1_000_000) {
                throw new IllegalArgumentException("Invalid base salary.");
            }
            this.employeeId = employeeId;
            this.name = name;
            this.department = department;
            this.baseSalary = baseSalary;
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
            System.out.println("Name: " + name);
            System.out.println("Department: " + department);
        }

        public abstract double calculateSalary();
        public abstract double calculateBonus();
    }

    class FullTimeEmployee extends Employee {
        private double annualBonus;

        public FullTimeEmployee(int employeeId, String name, String department, double baseSalary, double annualBonus) {
            super(employeeId, name, department, baseSalary);
            if (annualBonus < 0 || annualBonus > 500_000) {
                throw new IllegalArgumentException("Invalid annual bonus.");
            }
            this.annualBonus = annualBonus;
        }

        @Override
        public double calculateSalary() {
            return getBaseSalary() + calculateBonus();
        }

        @Override
        public double calculateBonus() {
            return annualBonus * 0.8;
        }

        @Override
        public void displayInformation() {
            super.displayInformation();
            System.out.println("Salary: " + calculateSalary());
        }
    }
    class PartTimeEmployee extends Employee {
        private int hours;
        private double rate;

        public PartTimeEmployee(int employeeId, String name, String department, double baseSalary, int hours, double rate) {
            super(employeeId, name, department, baseSalary);
            if (hours < 0 || hours > 12) {
                throw new IllegalArgumentException("Invalid hours.");
            }
            if (rate < 0 || rate > 1_000_000) {
                throw new IllegalArgumentException("Invalid rate.");
            }
            this.hours = hours;
            this.rate = rate;
        }

        @Override
        public double calculateBonus() {
            return hours * rate;
        }

        @Override
        public double calculateSalary() {
            return getBaseSalary() + calculateBonus();
        }

        @Override
        public void displayInformation() {
            super.displayInformation();
            System.out.println("Salary: " + calculateSalary());
        }
    }

    class EmployeeFactory {
        public static Employee createEmployee(String type, int employeeId, String name, String department, double baseSalary, double additionalValue) {
            if (type.equalsIgnoreCase("fulltime")) {
                return new FullTimeEmployee(employeeId, name, department, baseSalary, additionalValue);
            } else if (type.equalsIgnoreCase("parttime")) {
                return new PartTimeEmployee(employeeId, name, department, baseSalary, (int) additionalValue, baseSalary * 0.05);
            } else {
                throw new IllegalArgumentException("Invalid employee type.");
            }
        }
    }

    class Main {
        public static void main(String[] args) {
            Employee fullTimeEmp = EmployeeFactory.createEmployee("fulltime", 1, "Poojitha", "HR", 50000, 10000);
            Employee partTimeEmp = EmployeeFactory.createEmployee("parttime", 2, "Jharna", "IT", 40000, 8);

            fullTimeEmp.displayInformation();
            System.out.println("-----------------------------");
            partTimeEmp.displayInformation();
        }
    }

}
