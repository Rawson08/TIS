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
     * starts the parsing process
     * @return an interface object corresponding with the statement parsed
     */
    public Instruction parse() {
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
     * @return an interface object of the matched statement
     */
    private Instruction parseStatement() {
        // TODO: implement the parsing logic for the statement rule
        String tokenValue = null;
        while (tokenizer.nextToken()) {
            // Get the current token type and value
            Tokenizer_J.TokenType tokenType = tokenizer.getTokenType();
            tokenValue = tokenizer.getTokenValue();

            // Use a series of conditional statements to handle each type of token
            switch (tokenType) {
                case NOOP:
                    return new NoopInstruction();
                case MOVE:
                    tokenizer.nextToken();
                    // TODO: check for proper src and dst tokens
                    String src = tokenizer.getTokenValue();
                    tokenizer.nextToken();
                    String dst = tokenizer.getTokenValue();
                    return new MoveInstruction(src, dst);
                case SWAP:
                    return new SwapInstruction();
                case SAVE:
                    return new SaveInstruction();
                case ADD:
                    tokenizer.nextToken();
                    // TODO: check for proper follow-up token
                    String addString = tokenizer.getTokenValue();
                    return new AddInstruction(addString);
                case SUB:
                    tokenizer.nextToken();
                    // TODO: check for proper follow-up token
                    String subString = tokenizer.getTokenValue();
                    return new SubInstruction(subString);
                case NEGATE:
                    return new NegateInstruction();
                case JUMP:
                    tokenizer.nextToken();
                    String jumpString = tokenizer.getTokenValue();
                    return new JumpInstruction(jumpString);
                case JEZ:
                    tokenizer.nextToken();
                    String jezString = tokenizer.getTokenValue();
                    return new JezInstruction(jezString);
                case JNZ:
                    tokenizer.nextToken();
                    String jnzString = tokenizer.getTokenValue();
                    return new JnzInstruction(jnzString);
                case JGZ:
                    tokenizer.nextToken();
                    String jgzString = tokenizer.getTokenValue();
                    return new JgzInstruction(jgzString);
                case JLZ:
                    tokenizer.nextToken();
                    String jlzString = tokenizer.getTokenValue();
                    return new JlzInstruction(jlzString);
                case JRO:
                    tokenizer.nextToken();
                    int jro = Integer.parseInt(tokenizer.getTokenValue());
                    return new JroInstruction(jro);
                case REGISTER:
                    // Handle REGISTER token
                    break;
                case PORT:
                    //Handle PORT token
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

        }
        return null;
    }

}