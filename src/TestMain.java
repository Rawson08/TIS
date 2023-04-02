import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestMain {
private static int numRows;
private static int numCols;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> gameLines = new ArrayList<>();

        String line;
        while (true) {
            line = scanner.nextLine();
            if (line.equals("")) {
                break;
            }
            gameLines.add(line);
        }

        //set row and col values
        line = gameLines.remove(0);
        String[] gridValues = line.split("\\s+");
        numRows = Integer.parseInt(gridValues[0]);
        numCols = Integer.parseInt(gridValues[1]);

        while(gameLines.size() > 0){
            Tokenizer_J tokenizer = new Tokenizer_J(gameLines.remove(0));
            Parser_J parser = new Parser_J(tokenizer);
            parser.parse();
        }

    }
}
