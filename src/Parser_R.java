import java.util.*;

public class Parser_R {
    private int numRows;
    private int numCols;
    private List<List<String>> silos;
    private List<String> inputCoords;
    private List<String> inputValues;
    private List<String> outputCoords;

    public Parser_R(String input) {
        String[] lines = input.split("\n");
        int lineNum = 0;

        // Parse grid dimensions
        String[] dimensions = lines[lineNum++].split(" ");
        numRows = Integer.parseInt(dimensions[0]);
        numCols = Integer.parseInt(dimensions[1]);

        // Parse silo instructions
        silos = new ArrayList<List<String>>();
        List<String> currentSilo = new ArrayList<String>();
        for (; lineNum < lines.length; lineNum++) {
            String line = lines[lineNum];
            if (line.equals("END")) {
                silos.add(currentSilo);
                currentSilo = new ArrayList<String>();
            } else {
                currentSilo.add(line);
            }
        }

        // Parse input stream
        lineNum++;
        inputCoords = Arrays.asList(lines[lineNum++].split(" "));
        inputValues = Arrays.asList(lines[lineNum++].split(" "));

        // Parse output stream
        lineNum++;
        outputCoords = Arrays.asList(lines[lineNum++].split(" "));
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public List<List<String>> getSilos() {
        return silos;
    }

    public List<String> getInputCoords() {
        return inputCoords;
    }

    public List<String> getInputValues() {
        return inputValues;
    }

    public List<String> getOutputCoords() {
        return outputCoords;
    }
}
