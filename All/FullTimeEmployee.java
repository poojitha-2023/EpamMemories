public class FullTimeEmployee extends Employee{
    private double annualBonus ;
    FullTimeEmployee(int employeeId,String name,String department,double baseSalary,double annualBonus){
        super(employeeId,name,department,baseSalary);
        this.annualBonus = annualBonus;
    }
    public double getAnnualBonus(){
        return annualBonus;
    }
    public void setAnnualBonus(double bonus){
        if(bonus < 0  || bonus > 500_000){
            throw new IllegalArgumentException("Error in Annual bonus.");
        }
        this.annualBonus = bonus;
    }
    @Override
    public void displayInformation(){
        super.displayInformation();
        System.out.println("Employee Annual Bonus : "+annualBonus);
    }
    @Override
    public double salaryCalculation(){
        return getBaseSalary() + annualBonus;
    }

}
