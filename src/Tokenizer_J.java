/**
 * this class produces tokens based on a given input String
 * for all the basic commands of the silo language
 */
public class Tokenizer_J {
    private final String input;
    private int pos;
    private String tokenValue;
    private TokenType tokenType;

    /**
     * these enums represent the token type
     */
    public enum TokenType {
        NOOP, MOVE, SWAP, SAVE, ADD, SUB, NEGATE, JUMP, JEZ, JNZ, JGZ, JLZ, JRO, REGISTER, NUMBER, COMMA, END_OF_FILE,
        PORT, ERROR, LABEL
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
            return true;
        }

        //if LABEL
        if(c == ':'){
            int start = pos;
            pos++;
            while(pos < input.length() && Character.isAlphabetic(input.charAt(pos))){
                pos++;
            }
            if(Character.valueOf(input.charAt(pos)) == ':') {
                tokenType = TokenType.LABEL;
                tokenValue = input.substring(start,pos + 1);
            }
            else{
                tokenType = TokenType.ERROR;
                tokenValue = input.substring(start,pos + 1);
            }
            System.out.println("type: " + tokenType + " value: " + tokenValue);
            pos++;
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
                case "RIGHT":
                case "LEFT":
                case "UP":
                case "DOWN":
                    tokenType = tokenType.PORT;
                    break;
                //if unrecognized word pattern
                default:
                    tokenType = TokenType.ERROR;
                    break;
            }
            tokenValue = word;
            return true;
        }
        return true;
    }

}

