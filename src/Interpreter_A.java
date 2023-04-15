/**
 * This is the interpreter to execute internal AT from the silo.
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interpreter_A {
    private Silo_A[][] arrayOfSilos;

    /**
     * This class handles the execution of internal AST. The value of the parameter tokenizer will be
     * passed in the TestMain or the GUI part.
     * @param aLineInstruction
     */
    public Interpreter_A(String aLineInstruction){
        Tokenizer_J tokenizer = new Tokenizer_J(aLineInstruction);
        Parser_J parser = new Parser_J(tokenizer);
        parser.parse();
    }
    public Silo_A[][] getArrayOfSilos() {
        return arrayOfSilos;
    }

    public void setArrayOfSilos(Silo_A[][] arrayOfSilos) {
        this.arrayOfSilos = arrayOfSilos;
    }

    public void addSiloToArray(Silo_A siloA, int row, int column){
        this.arrayOfSilos[row][column] = siloA;
    }

}

