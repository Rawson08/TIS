import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Project 4: TIS-100 Game
 * Class: CS351L
 * Group Members: Joel Gloetzner, Roshan Subedi, and Aayush Kafle
 * Description: This is the implementation of the game TIS:100 in JavaFX.
 *
 * This class is where we index every silo through two-dimensional array as we discussed in our weekly meeting.
 *
 */
public class Silo_A {
    private int acc;
    private int bak;
    private int siloInsCount = 0;
    private Port_A portA;
    // added list of instructions
    private ArrayList<Instruction> instructions;
    private List<String> listOfInstructions;
    private int instructionIndex = 0;

    public void setSiloInsCount(int siloInsCount) {
        this.siloInsCount = siloInsCount;
    }

    public Silo_A() {
        this.acc = acc;
        this.bak = bak;
        this.siloInsCount = siloInsCount;
        this.instructions = new ArrayList<>();
        this.listOfInstructions = new ArrayList<>();
        this.portA = new Port_A();
        this.instructionIndex = instructionIndex;
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
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
    public Port_A getPortA() {
        return portA;
    }

    public void setPortA(Port_A portA) {
        this.portA = portA;
    }

    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

    public void execute() {
        for (Instruction instruction : instructions) {
            instruction.execute();
        }
    }

    public int getInstructionIndex() {
        return instructionIndex;
    }

    public void setInstructionIndex(int instructionIndex) {
        this.instructionIndex = instructionIndex;
    }
}
