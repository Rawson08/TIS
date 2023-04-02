/**
 * this class produces tokens based on a given input String
 * for all the basic commands of the silo language
 */
public class Tokenizer_J {
    private final String input;
    private int pos;
    private static String tokenValue;
    private TokenType tokenType;

    /**
     * these enums represent the token type
     */
    public enum TokenType {
        NOOP, MOVE, SWAP, SAVE, ADD, SUB, NEGATE, JUMP, JEZ, JNZ, JGZ, JLZ, JRO, REGISTER, NUMBER, COMMA, END_OF_FILE, ERROR
    }

    /**
     * simple constructor starts the tokenizer
     * @param input is the String input to tokenize
     */
    public Tokenizer_J(String input) {
        this.input = input;
        pos = 0;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    /**
     * looks for the next token and sets tokenType and tokenValue if found
     * @return true if not EOF
     */
    public boolean nextToken() {
        if (pos >= input.length()) {
            tokenType = TokenType.END_OF_FILE;
            tokenValue = "";
            return false;
        }
        //skips white spaces
        char c = input.charAt(pos);
        if (Character.isWhitespace(c)) {
            pos++;
            return nextToken();
        }
        //currently allows commas like the game does
        if (c == ',') {
            pos++;
            tokenType = TokenType.COMMA;
            tokenValue = ",";
            return true;
        }
        //determines if the character is a number or a negative sign
        if (Character.isDigit(c) || c == '-') {
            int start = pos;
            pos++;
            while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                pos++;
            }
            tokenType = TokenType.NUMBER;
            tokenValue = input.substring(start, pos);
            System.out.println(tokenValue);
            return true;
        }

        if (Character.isAlphabetic(c)) {
            int start = pos;
            pos++;
            while (pos < input.length() && Character.isAlphabetic(input.charAt(pos))) {
                pos++;
            }
            String word = input.substring(start, pos);
            switch (word.toUpperCase()) {
                case "NOOP":
                    tokenType = TokenType.NOOP;
                    break;
                case "MOVE":
                    tokenType = TokenType.MOVE;
                    break;
                case "SWAP":
                    tokenType = TokenType.SWAP;
                    break;
                case "SAVE":
                    tokenType = TokenType.SAVE;
                    break;
                case "ADD":
                    tokenType = TokenType.ADD;
                    break;
                case "SUB":
                    tokenType = TokenType.SUB;
                    break;
                case "NEGATE":
                    tokenType = TokenType.NEGATE;
                    break;
                case "JUMP":
                    tokenType = TokenType.JUMP;
                    break;
                case "JEZ":
                    tokenType = TokenType.JEZ;
                    break;
                case "JNZ":
                    tokenType = TokenType.JNZ;
                    break;
                case "JGZ":
                    tokenType = TokenType.JGZ;
                    break;
                case "JLZ":
                    tokenType = TokenType.JLZ;
                    break;
                case "JRO":
                    tokenType = TokenType.JRO;
                    break;
                //register cases
                case "ACC":
                case "BAK":
                    tokenType = TokenType.REGISTER;
                    break;
                //if unrecognized word pattern
                default:
                    tokenType = TokenType.ERROR;
                    break;
            }
            tokenValue = word;
            System.out.println(word);
            return true;
        }
//        //catches unhandled errors
//        tokenType = TokenType.ERROR;
//        tokenValue = Character.toString(c);
//        pos++;
        return true;
    }

}

