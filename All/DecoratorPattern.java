public class DecoratorPattern {
    package parking_space;
    interface Employees {
        double calculateSalary();
        void displayInformation();
    }
    public class SalaryDecorator {}
    class BaseEmployee implements Employees {
        private final String name;
        private final int employeeId;
        private final String department;
        private final double baseSalary;
        private final double bonus;

        public BaseEmployee(String name, int employeeId, String department, double baseSalary, double bonus) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid name.");
            }
            if (department == null || department.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid department.");
            }
            if (baseSalary < 0 || baseSalary > 1_000_000) {
                throw new IllegalArgumentException("Invalid base salary.");
            }
            this.name = name;
            this.employeeId = employeeId;
            this.department = department;
            this.baseSalary = baseSalary;
            this.bonus = bonus;
        }

        @Override
        public double calculateSalary() {
            return baseSalary + bonus;
        }

        @Override
        public void displayInformation() {
            System.out.println("Employee ID: " + employeeId);
            System.out.println("Name: " + name);
            System.out.println("Department: " + department);
            System.out.println("Base Salary: " + baseSalary);
            System.out.println("Bonus: " + bonus);
            System.out.println("Total Salary: " + calculateSalary());
        }
    }

    abstract class EmployeeDecorator implements Employees {
        protected Employees decoratedEmployee;

        public EmployeeDecorator(Employees decoratedEmployee) {
            this.decoratedEmployee = decoratedEmployee;
        }

        @Override
        public double calculateSalary() {
            return decoratedEmployee.calculateSalary();
        }

        @Override
        public void displayInformation() {
            decoratedEmployee.displayInformation();
        }
    }
    class BonusDecorator extends EmployeeDecorator {
        private final double additionalBonus;

        public BonusDecorator(Employees decoratedEmployee, double additionalBonus) {
            super(decoratedEmployee);
            if (additionalBonus < 0) {
                throw new IllegalArgumentException("Additional bonus must be non-negative.");
            }
            this.additionalBonus = additionalBonus;
        }

        @Override
        public double calculateSalary() {
            return super.calculateSalary() + additionalBonus;
        }

        @Override
        public void displayInformation() {
            super.displayInformation();
            System.out.println("Additional Bonus: " + additionalBonus);
            System.out.println("Enhanced Total Salary: " + calculateSalary());
        }
    }
    class Main{
        public static void main(String[] args) {
            Employees employee = new BaseEmployee("Poojitha", 1, "Engineering", 60000, 5000);
            System.out.println("Original Employee Salary:");
            employee.displayInformation();

            Employees enhancedEmployee = new BonusDecorator(employee, 3000);
            System.out.println("\nEnhanced Employee Salary:");
            enhancedEmployee.displayInformation();
        }
    }
}
