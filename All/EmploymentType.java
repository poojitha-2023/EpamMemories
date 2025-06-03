abstract class EmploymentType {
    private int employeeId;
    private String name;
    public EmploymentType(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public String getName() {
        return name;
    }

    public void displayInformation() {
        System.out.println("Employee ID: " + employeeId + ", Name: " + name);
    }
    public abstract int calculateWorkHours();
}

interface Payable {
    double calculatePay();
}

class Contractor extends EmploymentType implements Payable {
    private double hourlyRate;
    private int hoursWorked;

    public Contractor(int employeeId, String name, double hourlyRate, int hoursWorked) {
        super(employeeId, name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public int calculateWorkHours() {
        return hoursWorked;
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }
}

class PermanentEmployee extends EmploymentType implements Payable {
    private double monthlySalary;

    public PermanentEmployee(int employeeId, String name, double monthlySalary) {
        super(employeeId, name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public int calculateWorkHours() {
        return 160;
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

class Demoo{
    public static void main(String[] args) {
        Contractor contractor = new Contractor(1, "Shravya", 50, 120);
        PermanentEmployee permanentEmployee = new PermanentEmployee(2, "Poojitha", 5000);
        System.out.println("Contractor Details:");
        contractor.displayInformation();
        System.out.println("Work Hours: " + contractor.calculateWorkHours());
        System.out.println("Pay: " + contractor.calculatePay());
        System.out.println();
        System.out.println("Permanent Employee Details:");
        permanentEmployee.displayInformation();
        System.out.println("Work Hours: " + permanentEmployee.calculateWorkHours());
        System.out.println("Pay: " + permanentEmployee.calculatePay());
    }
}
