/**
 * this class represents a parser for the silo language
 */
public class Parser_J {
    private final Tokenizer_J tokenizer;

    /**
     * simple constructor takes a tokenizer
     * @param tokenizer is the tokenizer to use
     */
    public Parser_J(Tokenizer_J tokenizer) {
        this.tokenizer = tokenizer;
    }

    /**
     * starts the parsing process and returns the root node of the AST
     * @return the root node of the AST
     */
    public String parse() {
        return parseStatement();
    }

    /**
     * matches a statement according to the grammar rule:
     * statement -> MOVE expression expression
     *           | SWAP
     *           | SAVE expression
     *           | ADD expression
     *           | SUB expression
     *           | NEGATE
     *           | JUMP expression
     *           | JEZ expression
     *           | JNZ expression
     *           | JGZ expression
     *           | JLZ expression
     *           | JRO expression
     * @return a statement node representing the matched statement
     */
    private String parseStatement() {
        // TODO: implement the parsing logic for the statement rule

        // Get the current token type and value
        tokenizer.nextToken();
        Tokenizer_J.TokenType tokenType = tokenizer.getTokenType();
        String tokenValue = tokenizer.getTokenValue();

        // Use a series of conditional statements to handle each type of token
        switch (tokenType) {
            case NOOP:
                // Handle NOOP token
                break;
            case MOVE:
                // Handle MOVE token
                break;
            case SWAP:
                // Handle SWAP token
                break;
            case SAVE:
                // Handle SAVE token
                break;
            case ADD:
                // Handle ADD token
                break;
            case SUB:
                // Handle SUB token
                break;
            case NEGATE:
                // Handle NEGATE token
                break;
            case JUMP:
                // Handle JUMP token
                break;
            case JEZ:
                // Handle JEZ token
                break;
            case JNZ:
                // Handle JNZ token
                break;
            case JGZ:
                // Handle JGZ token
                break;
            case JLZ:
                // Handle JLZ token
                break;
            case JRO:
                // Handle JRO token
                break;
            case REGISTER:
                // Handle REGISTER token
                break;
            case NUMBER:
                // Handle NUMBER token
                break;
            case COMMA:
                // Handle COMMA token
                break;
            case ERROR:
                // Handle ERROR token
                break;
            case END_OF_FILE:
                // Handle END_OF_FILE token
                break;
        }

        // Get the next token and continue parsing
        tokenizer.nextToken();
        return tokenValue;
    }

}