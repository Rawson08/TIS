import java.util.ArrayList;
import java.util.List;

/**
 * This class is where we index every silo through two-dimensional array as we discussed in our weekly meeting.
 *
 */
public class Silo_A {
//    private int numOfSilos;
    private int acc;
    private int bak;
    private int[][] arrayOfSilos;
//    private Port_A[][] ports;
    //added list of instructions
    private List<Instruction> instructions = new ArrayList<>();

    public Silo_A(int numRow, int numColumn) {
        this.acc = acc;
        this.bak = bak;
        this.instructions = new ArrayList<>();
//        this.numOfSilos = numOfSilos;
        this.arrayOfSilos = new int[numRow][numColumn];
//        this.ports = new Port_A[numOfSilos][numOfSilos];
//        int indexCounter = 0;
//
//        // Initialize ports for each silo
//        for (int i = 0; i < numOfSilos; i++) {
//            for (int j = 0; j < numOfSilos; j++) {
//                // TODO: SET THE VALUES FOR ALL FOURS PORTS FOR EACH SILO
//                int up = i > 0 ? arrayOfSilos[i-1][j] : null;
//                int down = i < numOfSilos-1 ? arrayOfSilos[i+1][j] : 0;
//                int left = j > 0 ? arrayOfSilos[i][j-1] : 0;
//                int right = j < numOfSilos-1 ? arrayOfSilos[i][j+1] : 0;
//
//                // Create and store new Port_A object
//                ports[i][j] = new Port_A(indexCounter, up, down, right, left);
//                indexCounter++;
//            }
//        }
    }

//    public int getNumOfSilos() {
//        return numOfSilos;
//    }

    public int getSiloAtIndex(int i, int j) {
        return arrayOfSilos[i][j];
    }

    public int[][] getArrayOfSilos() {
        return arrayOfSilos;
    }

    public void setSiloIndex(int i, int j, int value) {
        this.arrayOfSilos[i][j] = value;
    }

//    public Port_A getPortAtIndex(int i, int j) {
//        return ports[i][j];
//    }
//
//    public void setPortAtIndex(int i, int j, Port_A port) {
//        ports[i][j] = port;
//    }
}
