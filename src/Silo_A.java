import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is where we index every silo through two-dimensional array as we discussed in our weekly meeting.
 *
 */
public class Silo_A {
    private int acc;
    private int bak;
    private Port_A portA;
    // added list of instructions
    private LinkedList<Instruction> instructions;
    private List<String> listOfInstructions;

    public Silo_A() {
        this.acc = acc;
        this.bak = bak;
        this.instructions = new LinkedList<>();
        this.listOfInstructions = new ArrayList<>();
        this.portA = new Port_A();
    }

    public LinkedList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(LinkedList<Instruction> instructions) {
        this.instructions = instructions;
    }


    public List<String> getListOfInstructions() {
        return listOfInstructions;
    }

    public void setListOfInstructions(List<String> listOfInstructions) {
        this.listOfInstructions = listOfInstructions;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public int getBak() {
        return bak;
    }

    public void setBak(int bak) {
        this.bak = bak;
    }

}