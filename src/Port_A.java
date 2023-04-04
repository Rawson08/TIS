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
    public int getUpPortAccValue() {
        return upPortAccValue;
    }

    public void setUpPortAccValue(int upPortAccValue) {
        this.upPortAccValue = upPortAccValue;
    }

    public int getDownPortAccValue() {
        return downPortAccValue;
    }

    public void setDownPortAccValue(int downPortAccValue) {
        this.downPortAccValue = downPortAccValue;
    }

    public int getRightPortAccValue() {
        return rightPortAccValue;
    }

    public void setRightPortAccValue(int rightPortAccValue) {
        this.rightPortAccValue = rightPortAccValue;
    }

    public int getLeftPortAccValue() {
        return leftPortAccValue;
    }

    public void setLeftPortAccValue(int leftPortAccValue) {
        this.leftPortAccValue = leftPortAccValue;
    }

}
