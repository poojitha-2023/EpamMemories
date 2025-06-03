public class BuilderPattern {
    class Employee {
        private final String name;
        private final int employeeId;
        private final String department;
        private final double baseSalary;
        private final double bonus;

        private Employee(EmployeeBuilder builder) {
            this.name = builder.name;
            this.employeeId = builder.employeeId;
            this.department = builder.department;
            this.baseSalary = builder.baseSalary;
            this.bonus = builder.bonus;
        }

        public String getName() {
            return name;
        }

        public int getEmployeeId() {
            return employeeId;
        }

        public String getDepartment() {
            return department;
        }

        public double getBaseSalary() {
            return baseSalary;
        }

        public double getBonus() {
            return bonus;
        }

        public double calculateSalary() {
            return baseSalary + bonus;
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

            public EmployeeBuilder setName(String name) {
                if (name == null || name.trim().isEmpty()) {
                    throw new IllegalArgumentException("Invalid name.");
                }
                this.name = name;
                return this;
            }

            public EmployeeBuilder setEmployeeId(int id) {
                if (id <= 0) {
                    throw new IllegalArgumentException("Invalid Employee ID.");
                }
                this.employeeId = id;
                return this;
            }

            public EmployeeBuilder setDepartment(String department) {
                if (department == null || department.trim().isEmpty()) {
                    throw new IllegalArgumentException("Invalid department.");
                }
                this.department = department;
                return this;
            }

            public EmployeeBuilder setBaseSalary(double baseSalary) {
                if (baseSalary < 0 || baseSalary > 1_000_000) {
                    throw new IllegalArgumentException("Invalid base salary.");
                }
                this.baseSalary = baseSalary;
                return this;
            }

            public EmployeeBuilder setBonus(double bonus) {
                if (bonus < 0 || bonus > 500_000) {
                    throw new IllegalArgumentException("Invalid bonus.");
                }
                this.bonus = bonus;
                return this;
            }

            public Employee build() {
                return new Employee(this);
            }
        }
    }

    class Director {
        public Employee createFullTimeEmployee() {
            return new Employee.EmployeeBuilder()
                    .setEmployeeId(1)
                    .setName("Poojitha")
                    .setDepartment("HR")
                    .setBaseSalary(50000)
                    .setBonus(10000)
                    .build();
        }

        public Employee createPartTimeEmployee() {
            return new Employee.EmployeeBuilder()
                    .setEmployeeId(2)
                    .setName("Jharna")
                    .setDepartment("IT")
                    .setBaseSalary(30000)
                    .setBonus(5000)
                    .build();
        }
    }

    class Mainj {
        public static void main(String[] args) {
            Director director = new Director();

            Employee fullTimeEmp = director.createFullTimeEmployee();
            Employee partTimeEmp = director.createPartTimeEmployee();

            fullTimeEmp.displayInformation();
            System.out.println("-----------------------------");
            partTimeEmp.displayInformation();
        }
    }

}
