package model;

/**
 * Kvarfordt-Tanner-Assn7
 * Created on 11/11/2016
 *
 * ContractWorkers are paid based on a pay per contract completed,
 * know how to do division, and know a lot about astronomy
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */
public class ContractWorker extends Smarty {

    /**
     * Name of the ContractWorker
     */
    private String name;

    /**
     * Division that the ContractWorker knows how to do
     */
    private String math;

    /**
     * What the contract worker has to say about astronomy
     */
    private String say;

    /**
     * Pay per contract completed for the ContractWorker
     */
    private String payPerContract;

    /**
     * Number of completed by the ContractWorker
     */
    private String contractsCompleted;

    /**
     * Constructor for ContractWorker; initializes all member variables
     *
     * @param name   name of the new ContractWorker
     * @param math   math done by the new ContractWorker
     * @param say   what the new ContractWorker says
     * @param payPerContract pay per contract for the new ContractWorker
     * @param contractsCompleted   contracts completed by the new ContractWorker
     * @param IQ  IQ of the new ContractWorker
     */
    public ContractWorker(String name, String math, String say, String payPerContract,
                          String contractsCompleted, String IQ) {
        super(IQ);
        this.name = name;
        this.math = math;
        this.say = say;
        this.payPerContract = payPerContract;
        this.contractsCompleted = contractsCompleted;
    }

    /**
     * @return the name of the ContractWorker
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return division done by the ContractWorker
     */
    @Override
    public String doMath() {
        return math;
    }

    /**
     * @return what the ContractWorker says
     */
    @Override
    public String saySomethingSmart() {
        return say;
    }

    /**
     * @return the type of worker (ContractWorker)
     */
    @Override
    public String getPersonType() {
        return "ContractWorker";
    }

    /**
     * Calculates the income of the ContractWorker based on contractsCompleted and payPerContract
     * @return the calculated income of the ContractWorker
     */
    public String getIncome() {
        Double income = Double.parseDouble(payPerContract) * Double.parseDouble(contractsCompleted);
        return "$" + String.format("%.2f", income);
    }

    /**
     * @return contractsCompleted by the contract worker
     */
    public String getContractsCompleted() {
        return contractsCompleted;
    }
}
