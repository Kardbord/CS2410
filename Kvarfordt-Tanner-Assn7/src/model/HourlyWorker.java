package model;

/**
 * Kvarfordt-Tanner-Assn7
 * Created on 11/11/2016
 *
 * HourlyWorkers are paid based on an hourly wage, know a lot about animals, and can do multiplication
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */
public class HourlyWorker extends Smarty {

    /**
     * Name of the HourlyWorker
     */
    private String name;

    /**
     * Multiplication that the HourlyWorker knows how to do
     */
    private String math;

    /**
     * What the HourlyWorker has to say about animals
     */
    private String say;

    /**
     * Wage made per hour by the HourlyWorker
     */
    private String wage;

    /**
     * Hours worked by the HourlyWorker
     */
    private String hours;

    /**
     * HourlyWorker constructor; initializes all member variables
     * @param name name of the HourlyWorker
     * @param math multiplication that the HourlyWorker knows how to do
     * @param say what the HourlyWorker has to say about animals
     * @param wage hourly wage of the HourlyWorker
     * @param hours hours worked by the HourlyWorker
     * @param IQ IQ of the HourlyWorker
     */
    public HourlyWorker(String name, String math, String say, String wage, String hours, String IQ){
        super(IQ);
        this.name = name;
        this.math = math;
        this.say = say;
        this.wage = wage;
        this.hours = hours;
    }

    /**
     * @return the name of the HourlyWorker
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the multiplication the HourlyWorker knows how to do
     */
    @Override
    public String doMath() {
        return math;
    }

    /**
     * @return what the HourlyWorker has to say about animals
     */
    @Override
    public String saySomethingSmart() {
        return say;
    }

    /**
     * @return the type of worker (HourlyWorker)
     */
    @Override
    public String getPersonType() {
        return "HourlyWorker";
    }

    /**
     * Calculates the total income for the HourlyWorker based on hours worked and wage
     * @return the total income for the HourlyWorker
     */
    public String getIncome() {
        Double income = Double.parseDouble(wage) * Double.parseDouble(hours);
        return "$" + String.format("%.2f", income);
    }

    /**
     * @return hours worked by the HourlyWorker
     */
    public String getHoursWorked(){
        return hours;
    }
}
