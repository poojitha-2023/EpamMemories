public class StrategyPattern {
    interface SalaryCalculator {
        double calculateSalary(Employee employee);
    }
    class FullTimeSalaryCalculator implements SalaryCalculator {
        @Override
        public double calculateSalary(Employee employee) {
            return employee.getBaseSalary() + employee.getBonus();
        }
    }

    class PartTimeSalaryCalculator implements SalaryCalculator {
        private final int hoursWorked;
        private final double hourlyRate;

        public PartTimeSalaryCalculator(int hoursWorked, double hourlyRate) {
            if(hoursWorked < 0 || hoursWorked >= 18){
                throw new IllegalArgumentException("Hours worked must be non-negative.");
            }
            if( hourlyRate< 0 ||  hourlyRate>=200000){
                throw new IllegalArgumentException("Rate must be non-negative.");
            }
            this.hoursWorked = hoursWorked;
            this.hourlyRate = hourlyRate;
        }

        @Override
        public double calculateSalary(Employee employee) {
            return hoursWorked * hourlyRate;
        }
    }
    class Employee {
        private final String name;
        private final int employeeId;
        private final String department;
        private final double baseSalary;
        private final double bonus;
        private final SalaryCalculator salaryCalculator;

        private Employee(EmployeeBuilder builder) {
            this.name = builder.name;
            this.employeeId = builder.employeeId;
            this.department = builder.department;
            this.baseSalary = builder.baseSalary;
            this.bonus = builder.bonus;
            this.salaryCalculator = builder.salaryCalculator;
        }

        public String getName() { return name; }
        public int getEmployeeId() { return employeeId; }
        public String getDepartment() { return department; }
        public double getBaseSalary() { return baseSalary; }
        public double getBonus() { return bonus; }
        public double calculateSalary() {
            return salaryCalculator.calculateSalary(this);
        }

        public void displayInformation() {
            System.out.println("Employee ID: " + employeeId);
            System.out.println("Name: " + name);
            System.out.println("Department: " + department);
            System.out.println("Base Salary: " + baseSalary);
            System.out.println("Bonus: " + bonus);
            System.out.println("Total Salary: " + calculateSalary());
        }
        public static class EmployeeBuilder {
            private String name;
            private int employeeId;
            private String department;
            private double baseSalary;
            private double bonus;
            private SalaryCalculator salaryCalculator;

            public EmployeeBuilder setName(String name) {
                this.name = name;
                return this;
            }
            public EmployeeBuilder setEmployeeId(int employeeId) {
                this.employeeId = employeeId;
                return this;
            }
            public EmployeeBuilder setDepartment(String department) {
                this.department = department;
                return this;
            }
            public EmployeeBuilder setBaseSalary(double baseSalary) {
                this.baseSalary = baseSalary;
                return this;
            }
            public EmployeeBuilder setBonus(double bonus) {
                this.bonus = bonus;
                return this;
            }
            public EmployeeBuilder setSalaryCalculator(SalaryCalculator salaryCalculator) {
                this.salaryCalculator = salaryCalculator;
                return this;
            }
            public Employee build() {
                return new Employee(this);
            }
        }
    }
    public class SalaryCalculationDemo {
        public static void main(String[] args) {
            Employee fullTimeEmployee = new Employee.EmployeeBuilder()
                    .setName("Poojitha")
                    .setEmployeeId(1)
                    .setDepartment("Engineering")
                    .setBaseSalary(90000)
                    .setBonus(5000)
                    .setSalaryCalculator(new FullTimeSalaryCalculator())
                    .build();
            fullTimeEmployee.displayInformation();
            try{
                Employee partTimeEmployee = new Employee.EmployeeBuilder()
                        .setName("Bob")
                        .setEmployeeId(102)
                        .setDepartment("Support")
                        .setBaseSalary(0)
                        .setBonus(0)
                        .setSalaryCalculator(new PartTimeSalaryCalculator(160, 20))
                        .build();
                partTimeEmployee.displayInformation();
            }
            catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}
