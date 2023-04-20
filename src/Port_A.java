/**
 * Project 4: TIS-100 Game
 * Class: CS351L
 * Group Members: Joel Gloetzner, Roshan Subedi, and Aayush Kafle
 * Description: This is the implementation of the game TIS:100 in JavaFX.
 *
 * This is the port that holds four side values for every silo
 */
public class Port_A {
    private int upPortAccValue;
    private int downPortAccValue;
    private int rightPortAccValue;
    private int leftPortAccValue;

    public Port_A(){
        // I am thinking of making the right value of silo index 0 to the left value of silo index 1
        // this can be done where ever port class is called.
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
