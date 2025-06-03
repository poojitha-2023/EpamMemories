public class ObserverPattern {
    import java.util.*;
    interface SalaryObserver {
        void onSalaryChange(Employee employee);
    }

    interface Employee {
        double calculateSalary();
        void displayInformation();
        void addObserver(SalaryObserver observer);
        void removeObserver(SalaryObserver observer);
        void notifyObservers();
    }

    class BaseEmployee implements Employee {
        private final String name;
        private final int employeeId;
        private final String department;
        private double baseSalary;
        private double bonus;
        private final List<SalaryObserver> observers = new ArrayList<>();

        public BaseEmployee(String name, int employeeId, String department, double baseSalary, double bonus) {
            if (baseSalary < 0 || bonus < 0) {
                throw new IllegalArgumentException("Base salary and bonus must be non-negative.");
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

        public void setBaseSalary(double baseSalary) {
            if (baseSalary < 0) {
                throw new IllegalArgumentException("Base salary must be non-negative.");
            }
            this.baseSalary = baseSalary;
            notifyObservers();
        }

        public void setBonus(double bonus) {
            if (bonus < 0) {
                throw new IllegalArgumentException("Bonus must be non-negative.");
            }
            this.bonus = bonus;
            notifyObservers();
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

        @Override
        public void addObserver(SalaryObserver observer) {
            observers.add(observer);
        }

        @Override
        public void removeObserver(SalaryObserver observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers() {
            for (SalaryObserver observer : observers) {
                observer.onSalaryChange(this);
            }
        }
    }

    class HRDepartment implements SalaryObserver {
        @Override
        public void onSalaryChange(Employee employee) {
            System.out.println("HR Department notified: Salary changed for employee.");
            employee.displayInformation();
        }
    }

    abstract class EmployeeDecorator implements Employee {
        protected Employee decoratedEmployee;

        public EmployeeDecorator(Employee decoratedEmployee) {
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

        @Override
        public void addObserver(SalaryObserver observer) {
            decoratedEmployee.addObserver(observer);
        }

        @Override
        public void removeObserver(SalaryObserver observer) {
            decoratedEmployee.removeObserver(observer);
        }

        @Override
        public void notifyObservers() {
            decoratedEmployee.notifyObservers();
        }
    }

    class BonusDecorator extends EmployeeDecorator {
        private final double additionalBonus;

        public BonusDecorator(Employee decoratedEmployee, double additionalBonus) {
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

    public class SalaryEnhancementDemo {
        public static void main(String[] args) {
            Employee employee = new BaseEmployee("Alice", 101, "Engineering", 60000, 5000);
            SalaryObserver hrObserver = new HRDepartment();
            employee.addObserver(hrObserver);
            System.out.println("Original Employee Salary:");
            employee.displayInformation();
            ((BaseEmployee) employee).setBaseSalary(65000);
            ((BaseEmployee) employee).setBonus(6000);
            Employee enhancedEmployee = new BonusDecorator(employee, 3000);
            System.out.println("\nEnhanced Employee Salary:");
            enhancedEmployee.displayInformation();
        }
    }

}
