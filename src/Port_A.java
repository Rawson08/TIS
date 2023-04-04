/**
 * This is the port has hold four side values for every silo
 */
public class Port_A {
    private int upPortAccValue;
    private int downPortAccValue;
    private int rightPortAccValue;
    private int leftPortAccValue;

    public Port_A(int siloIndex, int upPortAccValue, int downPortAccValue, int rightPortAccValue, int leftPortAccValue){
        // I am thinking of making the right value of silo index 0 to the left value of silo index 1
        // this can be done whereever port class is called.
        this.upPortAccValue = upPortAccValue;
        this.downPortAccValue = downPortAccValue;
        this.rightPortAccValue = rightPortAccValue;
        this.leftPortAccValue = leftPortAccValue;
    }
}
