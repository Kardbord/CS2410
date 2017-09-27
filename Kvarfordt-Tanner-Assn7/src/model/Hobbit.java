package model;

/**
 * Kvarfordt-Tanner-Assn7
 * Created on 11/11/2016
 *
 * Hobbits know how to do addition, know a lot about gardening, and have carrots that they picked.
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */
public class Hobbit implements Simpleton, PersonType {

    /**
     * The name of the Hobbit
     */
    private String name;

    /**
     * Addition that the Hobbit knows how to do
     */
    private String math;

    /**
     * What the Hobbit has to say about gardening
     */
    private String say;

    /**
     * Carrots picked by the Hobbit
     */
    private String carrots;

    /**
     * Hobbit constructor - initializes all member variables
     * @param name name of the new Hobbit
     * @param math addition done by the new Hobbit
     * @param say what the new Hobbit has to say about gardening
     * @param carrots how many carrots the Hobbit has picked
     */
    public Hobbit(String name, String math, String say, String carrots){
        this.name = name;
        this.math = math;
        this.say = say;
        this.carrots = carrots;
    }

    /**
     * @return the name of the Hobbit
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the addition done by the Hobbit
     */
    @Override
    public String doMath() {
        return math;
    }

    /**
     * @return what the Hobbit has to say about gardening
     */
    @Override
    public String saySomethingSmart() {
        return say;
    }

    /**
     * @return the type of worker (Hobbit)
     */
    @Override
    public String getPersonType() {
        return "Hobbit";
    }

    /**
     * @return carrots picked by the Hobbit
     */
    public String getCarrotsPicked() {
        return carrots;
    }
}
