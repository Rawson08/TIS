import java.util.Scanner;

public class Parser_A {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a command: ");
        String command = scanner.nextLine();
        parseCommand(command);
    }

    public static void parseCommand(String command) {
        String[] tokens = command.split(" ");
        String operation = tokens[0];
        int arg1 = Integer.parseInt(tokens[1]);
        int arg2 = Integer.parseInt(tokens[2]);

        switch (operation) {
            case "add":
                int sum = arg1 + arg2;
                System.out.println("Result: " + sum);
                break;
            case "subtract":
                int difference = arg1 - arg2;
                System.out.println("Result: " + difference);
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }
}
