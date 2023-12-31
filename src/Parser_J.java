/**
 * Project 4: TIS-100 Game
 * Class: CS351L
 * Group Members: Joel Gloetzner, Roshan Subedi, and Aayush Kafle
 * Description: This is the implementation of the game TIS:100 in JavaFX.
 *
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
    public Instruction parse(int i, int j) {
        return parseStatement(i, j);
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
    private Instruction parseStatement(int i, int j) {
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
                    String src = "";
                    tokenizer.nextToken();
                    // TODO: check for proper src and dst tokens
                    // update for src and dst tokens
                    if(tokenizer.getTokenType() == Tokenizer_J.TokenType.REGISTER) {
                        src = tokenizer.getTokenValue();
                    }
                    if(tokenizer.getTokenType() == Tokenizer_J.TokenType.PORT){
                        src = tokenizer.getTokenValue();
                    }
                    tokenizer.nextToken();
                    String dst = tokenizer.getTokenValue();
                    return new MoveInstruction(src, dst, i, j);
                case SWAP:
                    return new SwapInstruction(i, j);
                case SAVE:
                    return new SaveInstruction(i, j);
                case ADD:
                    tokenizer.nextToken();
                    String addName = tokenizer.getTokenValue();
                    Tokenizer_J.TokenType addType = tokenizer.getTokenType();
                    return new AddInstruction(addType,addName, i, j);
                case SUB:
                    tokenizer.nextToken();
                    // TODO: check for proper follow-up token
                    String subString = tokenizer.getTokenValue();
                    return new SubInstruction(tokenizer.getTokenType(), subString, i, j);
                case NEGATE:
                    return new NegateInstruction(i,j);
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