import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestMain {
private static int numRows;
private static int numCols;
public static int acc = 0;
public static int bak = 0;
private int inputRow;
private int inputColumn;
private int outputRow;
private int outputColumn;
private List<Integer> inputValues;

    public static void main(String[] args) {
        Interpreter_A interpreterA = new Interpreter_A("something");
        interpreterA.initialStartFromCmd();
//        // creating an instance of TestMain to get non-static values
//        TestMain testMain = new TestMain();
//
//        Scanner scanner = new Scanner(System.in);
//        List<String> inputs = new ArrayList<>();
//
//
//        // Read in game instructions
//        String line;
//        while (true) {
//            line = scanner.nextLine();
//            if (line.equals("")) {
//                break;
//            }
//            inputs.add(line);
//        }
//
//        // Set row and col values
//        line = inputs.remove(0);
//        String[] gridValues = line.split("\\s+");
//        numRows = Integer.parseInt(gridValues[0]);
//        numCols = Integer.parseInt(gridValues[1]);
//
//        // only prints the statement before the input values
//        int inputCounter = 0;
//        for (int i=0; i<inputs.size(); i++){
//            if (inputs.equals("INPUT")){
//                inputCounter++;
//                break;
//            }
//            System.out.println("v: " + inputs.get(i) );
//        }
//
//        // find input and output values in inputs
//        testMain.inputValues = new ArrayList<>();
//        int outputIndex = inputs.indexOf("OUTPUT");
//        if (inputs.contains("INPUT")){
//            int inputIndex = inputs.indexOf("INPUT");
//            // add the input row and input column to the header variable
//            String inputRowAndColumn = inputs.remove(inputIndex+1);
//            String[] inputRowAndColumnSplit = inputRowAndColumn.split("\\s+");
//            for (int i=0; i<inputRowAndColumnSplit.length; i++){
//                if (inputRowAndColumnSplit[i].equals("-")){
//                    String concatRow = inputRowAndColumnSplit[i] + inputRowAndColumnSplit[i+1];
//                    testMain.inputRow = Integer.parseInt(concatRow);
////                    System.out.println(testMain.inputRow);
//                    i++;
//                }
//                else {
//                    testMain.inputColumn = Integer.parseInt(inputRowAndColumnSplit[i]);
////                    System.out.println(testMain.outputRow);
//                }
//            }
//            // add all the input values for input into inputValues
//            for (int i=inputIndex+1; i<outputIndex-2; i++){
//                testMain.inputValues.add(Integer.parseInt(inputs.get(i)));
//                System.out.println("Input Value: " + testMain.inputValues);
//            }
//        }
//        if (inputs.contains("OUTPUT")){
//                String outputRowAndColumn = inputs.remove(outputIndex);
//                String[] outputRowAndColumnSplit = outputRowAndColumn.split("\\s+");
//                testMain.outputRow = Integer.parseInt(outputRowAndColumnSplit[0]);
//                testMain.outputColumn = Integer.parseInt(outputRowAndColumnSplit[1]);
//        }
//
//
//        // Load instructions into silos
//        List<List<String>> silos = new ArrayList<>();
//        List<String> currentSilo = new ArrayList<>();
//        for (String instruction : inputs) {
//            if (instruction.equals("END")) {
//                silos.add(currentSilo);
//                currentSilo = new ArrayList<>();
//            }
//                currentSilo.add(instruction);
//        }
//        silos.add(currentSilo); // Add last silo
//
//        // Set the ports for each silo
//        // TODO: Instead of making a silo for input and output values, use them to make the table.
//        int siloCounter = 0;
//        for (List<String> silo : silos) {
//            System.out.println("Silo Number: " + siloCounter);
//            Interpreter_A interpreterA;
//            // here instruction passes the whole line of instruction
//            for (String instruction : silo) {
//                interpreterA = new Interpreter_A(instruction);
//                System.out.println("" + instruction);
//            }
//            siloCounter++;
//        }
    }
}