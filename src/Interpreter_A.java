/**
 * This is the interpreter to execute internal AT from the silo.
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interpreter_A {
    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    private int numRows;
    private int numCols;
    private Silo_A[][] arrayOfSilos;
    private List<Integer> inputValues;
    private List<List<Integer>> inputValuesList;
    private int inputRow[];
    private int inputColumn[];
    private int outputFromSiloX;
    private int outputFromSiloY;

    /**
     * This class handles the execution of internal AST. The value of the parameter tokenizer will be
     * passed in the TestMain or the GUI part.
     * @param aLineInstruction
     */
    public Interpreter_A(String aLineInstruction){
        // read from the command line when the program starts

        // call tokenizer and parser to work with in the GUI after interpreter is being called
        Tokenizer_J tokenizer = new Tokenizer_J(aLineInstruction);
        Parser_J parser = new Parser_J(tokenizer);
        parser.parse();
    }
    public Silo_A[][] getArrayOfSilos() {
        return arrayOfSilos;
    }

    public void setArrayOfSilos(Silo_A[][] arrayOfSilos) {
        this.arrayOfSilos = arrayOfSilos;
    }

    public void addSiloToArray(Silo_A siloA, int row, int column){
        this.arrayOfSilos[row][column] = siloA;
    }

    public void initialStartFromCmd(){
        Scanner scanner = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();

        // Read in game instructions
        String line;
        while (true) {
            line = scanner.nextLine();
            if (line.equals("")) {
                break;
            }
            inputs.add(line);
        }

        // Set row and col values
        line = inputs.remove(0);
        String[] gridValues = line.split("\\s+");
        this.numRows = Integer.parseInt(gridValues[0]);
        this.numCols = Integer.parseInt(gridValues[1]);
        this.arrayOfSilos = new Silo_A[numRows][numCols];

        // only prints the statement before the input values
        int inputCounter = 0;
        int outputCounter = 0;
        for (int i=0; i<inputs.size(); i++){
            if (inputs.get(i).equals("INPUT")){
                inputCounter++;
                break;
            }
            else if (inputs.get(i).equals("OUTPUT")){
                outputCounter++;
                break;
            }
        }
        System.out.println("Input count is: " + inputCounter);

        // find input and output values in inputs
        // TODO: Handle if the number of input (inputCounter) is more than 1.
        this.inputValues = new ArrayList<>();
        int outputIndex = inputs.indexOf("OUTPUT");
        for (int j=0; j<inputCounter;j++) {
            int endIndex = inputs.indexOf("END");
            if (inputs.contains("INPUT")) {
                int inputIndex = inputs.indexOf("INPUT");
                // add the input row and input column to the header variable
                String inputRowAndColumn = inputs.remove(inputIndex + 1);
                String[] inputRowAndColumnSplit = inputRowAndColumn.split("\\s+");
                for (int i = 0; i < inputRowAndColumnSplit.length; i++) {
                    if (inputRowAndColumnSplit[i].equals("-")) {
                        String concatRow = inputRowAndColumnSplit[i] + inputRowAndColumnSplit[i + 1];
                        // concatRow is the input row
                        this.inputRow = new int[inputCounter];
                        this.inputRow[j] = Integer.parseInt(concatRow);
                        System.out.println(inputRow[j]);
                        i++;
                    } else {
                        // otherwise get the input Column
                        this.inputColumn = new int[inputCounter];
                        this.inputColumn[j] = Integer.parseInt(inputRowAndColumnSplit[i]);
                        System.out.println(inputColumn[j]);
                    }
                }
                // add all the input values for input into inputValues
                for (int i = inputIndex + 1; i < outputIndex - 2; i++) {
                    this.inputValues.add(Integer.parseInt(inputs.get(i)));
                    System.out.println("Input Value: " + this.inputValues);
                }
            }
        }
        if (inputs.contains("OUTPUT")){
            String outputRowAndColumn = inputs.remove(outputIndex);
            String[] outputRowAndColumnSplit = outputRowAndColumn.split("\\s+");
            outputFromSiloX = Integer.parseInt(outputRowAndColumnSplit[0]);
            outputFromSiloY = Integer.parseInt(outputRowAndColumnSplit[1]);
        }


        // Load instructions into silos
        List<String> currSiloInstruction = new ArrayList<>();
        Silo_A currSilo = new Silo_A();
        int siloCounterX = 0;
        int siloCounterY = 0;
        for (String instruction : inputs) {
            if (instruction.equals("INPUT")){
                System.out.println("break time");
                break;
            }
            if (instruction.equals("END")) {
                currSilo.setListOfInstructions(currSiloInstruction);
                arrayOfSilos[siloCounterX][siloCounterY] = currSilo;

                currSilo = new Silo_A();
                currSiloInstruction = new ArrayList<>();
                if (siloCounterY + 1 == numCols){
                    siloCounterX++;
                    siloCounterY = 0;
                }
                else {
                    siloCounterY++;
                }
            }
            else {
                currSiloInstruction.add(instruction);
            }
            System.out.println("x:" + siloCounterX + " y:" + siloCounterY);
        }
        // Add last silo
        currSilo.setListOfInstructions(currSiloInstruction);
        //arrayOfSilos[siloCounterX][siloCounterY] = currSilo;

        for (int i=0; i<numRows; i++){
            for (int j=0; j<numCols; j++){

                for (int k=0; k<arrayOfSilos[i][j].getListOfInstructions().size();k++) {
                    System.out.println("inp: " + arrayOfSilos[i][j].getListOfInstructions().get(k));
                }
            }
        }
    }
}

