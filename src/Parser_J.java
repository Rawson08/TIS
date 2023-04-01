import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Parser_J {
    public static final Scanner scanner = new Scanner(System.in);
    static int numRows;
    static int numCols;
    static List<String> inputList = new LinkedList<String>();
    public Parser_J() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter your data:");
            numRows = scanner.nextInt();
            numCols = scanner.nextInt();
        while(!scanner.nextLine().equals("END")) {
            inputList.add(scanner.nextLine());
        }
    }
    public void printList(){
        for(int i = 0; i < inputList.size(); i++) {
            System.out.println(inputList.get(i));
        }
    }
}
