//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

abstract class EmployeeContract {
    public abstract void defineWorkHours();
    public abstract void assignProject(String projectName);

    public void displayContractDetails() {
        System.out.println("Employee contract details as defined per role.");
    }
}

// DeveloperContract.java
class DeveloperContract extends EmployeeContract {
    private int dailyWorkHours;
    private String assignedProject;

    @Override
    public void defineWorkHours() {
        this.dailyWorkHours = 8;
        System.out.println("Developer work hours are defined as 8 hours/day.");
    }

    @Override
    public void assignProject(String project) {
        this.assignedProject = project;
        System.out.println("Developer assigned to project: " + project);
    }

    @Override
    public void displayContractDetails() {
        System.out.println("Developer Contract Details:");
        System.out.println("Daily Work Hours: " + dailyWorkHours);
        System.out.println("Assigned Project: " + (assignedProject != null ? assignedProject : "No project assigned."));
    }
}


// ManagerContract.java
class ManagerContract extends EmployeeContract {
    private int weeklyWorkHours;
    private String assignedDepartment;

    @Override
    public void defineWorkHours() {
        this.weeklyWorkHours = 40;
        System.out.println("Manager work hours are defined as 40 hours/week.");
    }

    @Override
    public void assignProject(String department) {
        this.assignedDepartment = department;
        System.out.println("Manager assigned to oversee department: " + department);
    }

    @Override
    public void displayContractDetails() {
        System.out.println("Manager Contract Details:");
        System.out.println("Weekly Work Hours: " + weeklyWorkHours);
        System.out.println("Assigned Department: " + (assignedDepartment != null ? assignedDepartment : "No department assigned."));
    }
}

class Demo {
    public static void main(String[] args) {
        // List of contracts
        ArrayList<EmployeeContract> contracts = new ArrayList<>();

        // Create specific contracts
        EmployeeContract devContract = new DeveloperContract();
        EmployeeContract mgrContract = new ManagerContract();

        // Define work hours and assign projects
        devContract.defineWorkHours();
        devContract.assignProject("Payroll System Development");

        mgrContract.defineWorkHours();
        mgrContract.assignProject("IT Department Oversight");

        // Add contracts to list
        contracts.add(devContract);
        contracts.add(mgrContract);

        // Display contract details
        for (EmployeeContract contract : contracts) {
            contract.displayContractDetails();
            System.out.println();
        }
    }
}
