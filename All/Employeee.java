import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.*;
public class Employeee{
    private String name,department;
    private int employeeId;
    private double baseSalary;
    private double bonus;
    Employeee(int employeeId,String name,String department,double baseSalary,double bonus){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Error in setting name.");
        }
        if (department == null || department.trim().isEmpty() ) {
            throw new IllegalArgumentException("Error in setting department.");
        }
        if (baseSalary<0 || baseSalary>1000000) {
            throw new IllegalArgumentException("Invalid baseSalary");
        }
        if (bonus<0 || bonus>100000) {
            throw new IllegalArgumentException("Invalid bonus");
        }
        this.employeeId = employeeId;
        this.name=name;
        this.department=department;
        this.baseSalary=baseSalary;
        this.bonus = bonus;
    }
    public int getEmployeeId(){
        return employeeId;
    }
    public void setEmployeeId(int id){
        this.employeeId = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name ){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Error in setting name.");
        }
        this.name =name;
    }
    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        if (department == null || department.trim().isEmpty() ) {
            throw new IllegalArgumentException("Error in setting department.");
        }
        this.department= department;
    }
    public double getBaseSalary(){
        return  baseSalary;
    }
    public void setBaseSalary(double baseSalary){
        if (baseSalary<0) {
            throw new IllegalArgumentException("Invalid baseSalary");
        }
        this. baseSalary =  baseSalary;
    }
    public void setBonus(double bonus){
        if (bonus<0 || bonus>100000) {
            throw new IllegalArgumentException("Invalid bonus");
        }
        this.bonus =bonus;
    }
    public double getBonus(){
        return bonus;
    }

    public void displayInformation(){
        System.out.println("Employee ID : "+employeeId);
        System.out.println("Employee name : "+name);
        System.out.println("Employee department : "+department);
        Employee e = new Employee(employeeId,name,department,baseSalary,bonus);
        SalaryCalculator c = new SalaryCalculator(e);
        System.out.println("Employee overall Salary : "+c.calculateSalary());
    }

}
//single responsibility
class SalaryCalculator{
    private Employeee e;
    SalaryCalculator(Employee e){
        this.e = e;
    }
    public double calculateSalary(){
        return (e.getBaseSalary()+e.getBonus());
    }
}
class Main{
    public static void main(String[] args) {
        ArrayList<Employeee> employees = new ArrayList<Employeee>();
        employees.add(new Employeee(1,"Poojitha","IT",2400000,2000));
        employees.add(new Employeee(2,"Keerthana","IT",900000,2000));
        employees.add(new Employeee(3,"Shravya","IT",900000,2000));
        employees.add(new Employeee(4,"Vasundhara","IT",1000000,2000));
        employees.add(new Employeee(5,"Jharna","IT",1100000,2000));
        if(employees.size()!=0) {
            for (Employeee e : employees) {
                e.displayInformation();
            }
        }
        else {
            System.out.println("Empty list");
        }
    }
}
