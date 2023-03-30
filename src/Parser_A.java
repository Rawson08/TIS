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
            case "MOVE":
                // AFTER this command I am thinking of add the ports so that whatever the port user is asking to take the
                // value from we can retrieve that value. I am thinking of making a separate class which holds up, down,
                // right, and left port values for all ports
                int sum = arg1 + arg2;
                System.out.println("Result: " + sum);
                break;
            case "SWAP":
                // add the function of swap command
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }
}
