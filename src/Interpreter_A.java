/**
 * This is the interpreter to execute internal AT from the silo.
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interpreter_A {
    private static int numRows;
    private static int numCols;
    public static int acc = 0;
    public static int bak = 0;

    public static void main(String[] args) {
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
        numRows = Integer.parseInt(gridValues[0]);
        numCols = Integer.parseInt(gridValues[1]);

        // Load instructions into silos
        List<List<String>> silos = new ArrayList<>();
        List<String> currentSilo = new ArrayList<>();
        for (String instruction : inputs) {
            if (instruction.equals("END")) {
                silos.add(currentSilo);
                currentSilo = new ArrayList<>();
            } else {
                currentSilo.add(instruction);
            }
        }
        silos.add(currentSilo); // Add last silo

        // Set the ports for each silo
        // TODO: Instead of making a silo for input and output values, use them to make the table.
        int siloCounter = 0;
        for (List<String> silo : silos) {
            Silo_A siloForPorts = new Silo_A(numRows, numCols);
            System.out.println("Silo Number: " + siloCounter);
            for (String instruction : silo) {
                Tokenizer_J tokenizer = new Tokenizer_J(instruction);
                Parser_J parser = new Parser_J(tokenizer);
                parser.parse();
                System.out.println("Instruction: " + instruction);
            }
            siloCounter++;
        }
    }
}

