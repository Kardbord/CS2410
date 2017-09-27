package control;

import model.*;

import java.util.ArrayList;

/**
 * Kvarfordt-Tanner-Assn7
 * Created on 11/11/2016
 *
 * This class is the control aspect for the greater project of Polyabstraheritism.
 * This class includes a container to store objects of type Simpleton - more specifically
 * objects of HourlyWorker, ContractWorker, and Hobbit.
 *
 * Methods allow for either creation of new instances of these classes and inserting these objects into the container,
 * as well as the retrieval of information about each object regardless of explicit type.
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */

public class Control {
    /**
     * ArrayList to keep track of personnel.
     * Holds Simpletons and any subclasses - noteable HourlyWorker, ContractWorker, and Hobbit
     */
    private ArrayList<Simpleton> personnel;

    /**
     * Constructor to initialize the local ArrayList<Simpleton> personnel
     */
    public Control() {
        personnel = new ArrayList<>();
    }

    /**
     * Creates a new Hobbit object and inserts it to the end of the personnel ArrayList
     *
     * @param name    name for the Hobbit
     * @param math    math that the Hobbit can do
     * @param say     what the Hobbit says
     * @param carrots how many carrots the Hobbit has
     */
    public void makeNewHobbit(String name, String math, String say, String carrots) {
        personnel.add(new Hobbit(name, math, say, carrots));
    }

    /**
     * Creates a new ContractWorker object and inserts it to the end of the personnel ArrayList
     *
     * @param name           name of the ContractWorker
     * @param math           math that the ContractWorker can do
     * @param say            what the ContractWorker says
     * @param payPerContract pay per contract completed for the ContractWorker
     * @param contracts      contracts completed by the ContractWorker
     * @param IQ             IQ of the ContractWorker
     */
    public void makeNewContractWorker(String name, String math, String say, String payPerContract, String contracts, String IQ) {
        personnel.add(new ContractWorker(name, math, say, payPerContract, contracts, IQ));
    }

    /**
     * Creates a new HourlyWorker and inserts it to the end of the personnel ArrayList
     *
     * @param name       name of the HourlyWorker
     * @param math       math that the HourlyWorker can do
     * @param say        what the HourlyWorker says
     * @param hourlyWage hourly wage of the HourlyWorker
     * @param hours      hours worked by the HourlyWorker
     * @param IQ         IQ of the HourlyWorker
     */
    public void makeNewHourlyWorker(String name, String math, String say, String hourlyWage, String hours, String IQ) {
        personnel.add(new HourlyWorker(name, math, say, hourlyWage, hours, IQ));
    }

    /**
     * Cycles through the personnel ArrayList and returns the math done by each type of person
     *
     * @return a String with the name, PersonType, and math done by each person; each person separated with a new line
     */
    public String getMaths() {
        String maths = "";
        for (Simpleton s : personnel) {
            maths += s.getName() + ", ";
            if (s instanceof PersonType) {
                maths += ((PersonType) s).getPersonType() + ": " + s.doMath();
            }
            maths += "\n";
        }
        return maths;
    }

    /**
     * Cycles through the personnel ArrayList and returns the income of the person if applicable
     *
     * @return a String with the name, PersonType, and income of each applicable person;
     * each person separated with a new line
     */
    public String getIncomes() {
        String incomes = "";
        for (Simpleton s : personnel) {
            if (s instanceof Smarty) {
                incomes += s.getName() + ", " + ((Smarty) s).getPersonType() + ": " + ((Smarty) s).getIncome() + "\n";
            }
        }
        return incomes;
    }

    /**
     * Cycles through the personnel ArrayList and returns the hours worked by each person if applicable
     *
     * @return a String with the name, PersonType, and hours worked by each applicable person;
     * each person separated with a new line
     */
    public String getHours() {
        String hours = "";
        for (Simpleton s : personnel) {
            if (s instanceof HourlyWorker) {
                hours += s.getName() + ", " + ((HourlyWorker) s).getPersonType()
                        + ": worked " + ((HourlyWorker) s).getHoursWorked() + " hours\n";
            }
        }
        return hours;
    }

    /**
     * Cycles through the personnel ArrayList and returns the IQ of each person if applicable
     *
     * @return a String with the name, PersonType, and IQ of each applicable person;
     * each person separated with a new line
     */
    public String getIQs() {
        String IQs = "";
        for (Simpleton s : personnel) {
            if (s instanceof Smarty) {
                IQs += s.getName() + ", " + ((Smarty) s).getPersonType() + ": has an IQ of "
                        + ((Smarty) s).getIQ() + "\n";
            }
        }
        return IQs;
    }

    /**
     * Cycles through the personnel ArrayList and returns what each person has to say
     *
     * @return a String with the name, PersonType, and what each person has to say; each person separated by a new line
     */
    public String getSayList() {
        String says = "";
        for (Simpleton s : personnel) {
            if (s instanceof PersonType)
                says += s.getName() + ", " + ((PersonType) s).getPersonType() + ": says " + s.saySomethingSmart() + "\n";
        }
        return says;
    }

    /**
     * Cycles through the personnel ArrayList and returns the carrots picked by each person if applicable
     *
     * @return a String with the name, PersonType, and how many carrots were picked of each applicable person;
     * each person separated by a new line
     */
    public String getCarrots() {
        String carrots = "";
        for (Simpleton s : personnel) {
            if (s instanceof Hobbit) {
                carrots += s.getName() + ", " + ((Hobbit) s).getPersonType() + ": picked "
                        + ((Hobbit) s).getCarrotsPicked() + " carrots\n";
            }
        }
        return carrots;
    }

    /**
     * Cycles through the personnel ArrayList and returns the contracts completed by each person if applicable
     *
     * @return a String with the name, PersonType, and how many contracts were completed by each applicable person;
     * each person separated by a new line
     */
    public String getContracts() {
        String contracts = "";
        for (Simpleton s : personnel) {
            if (s instanceof ContractWorker) {
                contracts += s.getName() + ", " + ((ContractWorker) s).getPersonType() + ": completed "
                        + ((ContractWorker) s).getContractsCompleted() + " contracts\n";
            }
        }
        return contracts;
    }

}
