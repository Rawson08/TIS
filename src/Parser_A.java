import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser_A {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String word = scanner.next();
            parseWord(word);
        }
        scanner.close();
    }

    public static String parseWord(String rawTextToClassify) {
        switch (rawTextToClassify) {
            case "MOVE":
                // call move interpreter
                break;
            case "SWAP":
                // add the function of swap command
                break;
            default:
                break;
        }
        return rawTextToClassify;
    }
}
