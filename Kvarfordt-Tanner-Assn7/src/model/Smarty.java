package model;

/**
 * Kvarfordt-Tanner-Assn7
 * Created on 11/11/2016
 *
 * Abstract class - abstract method getIncome() returns income of an extending class object
 *
 * Smarty objects have an IQ
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */
public abstract class Smarty implements Simpleton, PersonType {

    /**
     * IQ of the Smarty
     */
    private String IQ;

    /**
     * Smarty constructor - initializes IQ
     *
     * @param i IQ of the new Smarty
     */
    protected Smarty(String i) {
        IQ = i;
    }

    /**
     * @return the IQ of the Smarty
     */
    public String getIQ() {
        return IQ;
    }

    /**
     * Abstract Method
     *
     * @return the calculated income of an extending class object
     */
    public abstract String getIncome();
}
