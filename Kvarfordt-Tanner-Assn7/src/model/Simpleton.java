package model;

/**
 * Kvarfordt-Tanner-Assn7
 * Created on 11/11/2016
 *
 * Interface - contains methods for getting the name, say, and math done by implementing class objects
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */
public interface Simpleton {

    /**
     * @return name associated with the implementing class object
     */
    String getName();

    /**
     * @return math problem associated with the implementing class object
     */
    String doMath();

    /**
     * @return whatever the implementing class object has to say
     */
    String saySomethingSmart();
}
