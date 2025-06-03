public class PartTimeEmployee extends Employee{
    private int hoursWorked;
    private double rate;
    PartTimeEmployee(int employeeId,String name,String department,double baseSalary,int hoursWorked,double rate){
        super(employeeId,name,department,baseSalary);
        this.hoursWorked = hoursWorked;
        this.rate = rate;
    }
    public int getHoursWorked (){
        return hoursWorked ;
    }
    public void setHoursWorked (int hoursWorked ){
        if(hoursWorked < 0 || hoursWorked > 2000){
            throw new IllegalArgumentException("Error in Hours Worked.");
        }
        this.hoursWorked  = hoursWorked ;
    }
    public double getRate (){
        return rate ;
    }
    public void setRate (double rate ){
        if(rate < 0 || rate>10000){
            throw new IllegalArgumentException("Error in Rate.");
        }
        this.rate = rate ;
    }
    @Override
    public void displayInformation(){
        super.displayInformation();
        System.out.println("Employee Hours Worked : "+hoursWorked) ;
        System.out.println("Employee Hourly Rate : "+rate) ;
    }
    @Override
    public double salaryCalculation(){
        return hoursWorked*rate;
    }
}
