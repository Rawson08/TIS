
/**
 * this abstract class provides creation of instruction nodes
 * that can be placed in a silos instructions List
 * each subclass overrides execute() depending on what action needs to occur
 */

// TODO: implement the logic for each execute function

public abstract class Instruction {
    public abstract boolean execute();
}

class MoveInstruction extends Instruction {
    private String src;
    private String dst;
    private int i, j;

    public MoveInstruction(String src, String dst, int i, int j) {
        this.src = src;
        this.dst = dst;
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean execute() {
        // execute MOVE instruction
        int valueToMove = 0;
        if(src.equals("UP")){
            int k = 0;
            while(k < Interpreter_A.getInputCoordinatesList().size()) {
                if(i > 0) {
                    valueToMove = Interpreter_A.arrayOfSilos[i - 1][j].getPortA().getDownPortAccValue();
                    Interpreter_A.arrayOfSilos[i - 1][j].getPortA().setDownPortAccValue(0);
                    break;
                    //Interpreter_A.arrayOfSilos[i - 1][j].getPortA().setDownPortAccValue(0);
                }
                else if (i == (Interpreter_A.getInputCoordinatesList().get(k).get(0) + 1)
                        && (j == Interpreter_A.getInputCoordinatesList().get(k).get(1))) {
                    if (Interpreter_A.getInputValuesList().get(k).size() > 0) {
                        valueToMove = Interpreter_A.getInputValuesList().get(k).remove(0);
                        break;
                    }

                }
                valueToMove = 0;
                k++;
            }
            if(valueToMove == 0) return false;
            //else if(i > 0) valueToMove = Interpreter_A.arrayOfSilos[i - 1][j].getPortA().getDownPortAccValue();
            //else valueToMove = 0;
            //System.out.println("moving value: " + valueToMove);
        }
        else if(src.equals("DOWN")){
            valueToMove = Interpreter_A.arrayOfSilos[i][j].getPortA().getUpPortAccValue();
            Interpreter_A.arrayOfSilos[i][j].getPortA().setUpPortAccValue(0);
            if(valueToMove == 0) return false;
        }
        else if(src.equals("LEFT")){
            if(j > 0) {
                valueToMove = Interpreter_A.arrayOfSilos[i][j].getPortA().getLeftPortAccValue();
                Interpreter_A.arrayOfSilos[i][j].getPortA().setLeftPortAccValue(0);
                //System.out.println("moving value: " + valueToMove);
                if(valueToMove == 0) return false;
            }
        }
        else if(src.equals("RIGHT")){
            if(j < Interpreter_A.arrayOfSilos[0].length) {
                valueToMove = Interpreter_A.arrayOfSilos[i][j].getPortA().getRightPortAccValue();
                Interpreter_A.arrayOfSilos[i][j].getPortA().setRightPortAccValue(0);
                //System.out.println("moving value: " + valueToMove);
                if(valueToMove == 0) return false;
            }
        }
        else if(src.equals("ACC")){
            valueToMove = Interpreter_A.arrayOfSilos[i][j].getAcc();
            if(valueToMove == 0) return false;
        }

        if(dst.equals("UP")){
            Interpreter_A.arrayOfSilos[i][j].getPortA().setUpPortAccValue(valueToMove);
        }
        else if(dst.equals("DOWN")){
            for(int k = 0; k < Interpreter_A.getOutputCoordinatesList().size(); k++) {
                if (i == Interpreter_A.getOutputCoordinatesList().get(k).get(0) - 1
                        && j == Interpreter_A.getOutputCoordinatesList().get(k).get(1)) {
                    Interpreter_A.getOutputValuesList().get(k).add(valueToMove);
                    System.out.println("added: " + valueToMove + " to output" +k);
                }

            }
            //(i < Interpreter_A.arrayOfSilos.length - 1) {
                if (Interpreter_A.arrayOfSilos[i][j].getPortA().getDownPortAccValue() != 0) return false;
            //}
            Interpreter_A.arrayOfSilos[i][j].getPortA().setDownPortAccValue(valueToMove);
        }
        else if(dst.equals("LEFT")){
            if(j > 0) {
                if(Interpreter_A.arrayOfSilos[i][j - 1].getPortA().getRightPortAccValue() != 0) return false;
                Interpreter_A.arrayOfSilos[i][j - 1].getPortA().setRightPortAccValue(valueToMove);
            }
        }
        else if(dst.equals("RIGHT")){
            if(j < Interpreter_A.arrayOfSilos[0].length) {
                if(Interpreter_A.arrayOfSilos[i][j + 1].getPortA().getLeftPortAccValue() != 0) return false;
                Interpreter_A.arrayOfSilos[i][j + 1].getPortA().setLeftPortAccValue(valueToMove);
            }
        }
        else if(dst.equals("ACC")){
            Interpreter_A.arrayOfSilos[i][j].setAcc(valueToMove);
        }
        System.out.println("moving: " + src + " to:" + dst);
        System.out.println("moving value: " + valueToMove);
        return true;
    }
}

class LabelInstruction extends Instruction {
    private String label;

    public LabelInstruction(String label) {
        this.label = label;
    }

    @Override
    public boolean execute() {
        // execute LABEL instruction (does nothing)
        return true;
    }
}

class NoopInstruction extends Instruction {
    @Override
    public boolean execute() {
        // execute NOOP instruction (does nothing)
        return true;
    }
}

class SwapInstruction extends Instruction {
    int i, j;
    public SwapInstruction(int i, int j){
        this.i = i;
        this.j = j;
    }
    @Override
    public boolean execute() {
        // execute SWAP instruction
        int temp = Interpreter_A.arrayOfSilos[i][j].getAcc();
        Interpreter_A.arrayOfSilos[i][j].setAcc(Interpreter_A.arrayOfSilos[i][j].getBak());
        Interpreter_A.arrayOfSilos[i][j].setBak(temp);
        return true;
    }
}

class SaveInstruction extends Instruction {
    private int i, j;
    public SaveInstruction(int i, int j){
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean execute() {
        // execute SAVE instruction
        System.out.println("saving: " + Interpreter_A.arrayOfSilos[i][j].getAcc());
        Interpreter_A.arrayOfSilos[i][j].setBak(Interpreter_A.arrayOfSilos[i][j].getAcc());
        return true;
    }
}

class AddInstruction extends Instruction {
    private String src;
    int i, j;
    private Tokenizer_J.TokenType tokenizer;

    public AddInstruction(Tokenizer_J.TokenType tokenizer,String src, int i, int j) {
        this.src = src;
        this.i = i;
        this.j = j;
        this.tokenizer = tokenizer;
    }

    @Override
    public boolean execute() {
        // execute ADD instruction should get src value and add to acc
        int addNumber = 0;
        if(tokenizer == Tokenizer_J.TokenType.NUMBER){
            addNumber = Integer.parseInt(src);
        }
        else if(tokenizer == Tokenizer_J.TokenType.PORT){
            if(src.equals("UP")){
                addNumber = Interpreter_A.arrayOfSilos[i][j].getPortA().getUpPortAccValue();
            }
            else if(src.equals("DOWN")){
                addNumber = Interpreter_A.arrayOfSilos[i][j].getPortA().getDownPortAccValue();
            }
            else if(src.equals("LEFT")){
                addNumber = Interpreter_A.arrayOfSilos[i][j].getPortA().getLeftPortAccValue();
            }
            else if(src.equals("RIGHT")){
                addNumber = Interpreter_A.arrayOfSilos[i][j].getPortA().getRightPortAccValue();
            }
        } else if (tokenizer == Tokenizer_J.TokenType.REGISTER) {
            addNumber = Interpreter_A.arrayOfSilos[i][j].getAcc();
        }
        Interpreter_A.arrayOfSilos[i][j].setAcc(addNumber + Interpreter_A.arrayOfSilos[i][j].getAcc());
        System.out.println("adding: " + addNumber);
        return true;
    }
}

class SubInstruction extends Instruction {
    private String src;
    private Tokenizer_J.TokenType tokenType;
    private int i, j;

    public SubInstruction(Tokenizer_J.TokenType tokenType, String src, int i, int j) {
        this.src = src;
        this.tokenType = tokenType;
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean execute() {
        // execute SUB instruction will subtract src value from acc
        int subNumber = 0;
        if(tokenType == Tokenizer_J.TokenType.NUMBER){
            subNumber = Integer.parseInt(src);
        }
        else if(tokenType == Tokenizer_J.TokenType.PORT){
            if(src.equals("UP")){
                subNumber = Interpreter_A.arrayOfSilos[i][j].getPortA().getUpPortAccValue();
            }
            else if(src.equals("DOWN")){
                subNumber = Interpreter_A.arrayOfSilos[i][j].getPortA().getDownPortAccValue();
            }
            else if(src.equals("LEFT")){
                subNumber = Interpreter_A.arrayOfSilos[i][j].getPortA().getLeftPortAccValue();
            }
            else if(src.equals("RIGHT")){
                subNumber = Interpreter_A.arrayOfSilos[i][j].getPortA().getRightPortAccValue();
            }
        } else if (tokenType == Tokenizer_J.TokenType.REGISTER) {
            subNumber = Interpreter_A.arrayOfSilos[i][j].getAcc();
        }
        Interpreter_A.arrayOfSilos[i][j].setAcc(subNumber + Interpreter_A.arrayOfSilos[i][j].getAcc());
        System.out.println("subtracting: " + subNumber);
        return true;
    }
}

class NegateInstruction extends Instruction {
    int i,j;
    public NegateInstruction(int i, int j){
        this.i = i;
        this.j = j;
    }
    @Override
    public boolean execute() {
        // execute NEGATE instruction
        Interpreter_A.arrayOfSilos[i][j].setAcc((-1) * Interpreter_A.arrayOfSilos[i][j].getAcc());
        return true;
    }
}

class JumpInstruction extends Instruction {
    private String label;

    public JumpInstruction(String label) {
        this.label = label;
    }

    @Override
    public boolean execute() {
        // execute JUMP instruction
        return true;
    }
}

class JezInstruction extends Instruction {
    private String label;

    public JezInstruction(String label) {
        this.label = label;
    }

    @Override
    public boolean execute() {
        // execute JEZ instruction
        if(TestMain.acc == 0){
            //goto label somehow
        }
        return true;
    }
}

class JnzInstruction extends Instruction {
    private String label;

    public JnzInstruction(String label) {
        this.label = label;
    }

    @Override
    public boolean execute() {
        // execute JNZ instruction
        if(TestMain.acc != 0){
            //goto label somehow
        }
        return true;
    }
}

class JgzInstruction extends Instruction {
    private String label;

    public JgzInstruction(String label) {
        this.label = label;
    }

    @Override
    public boolean execute() {
        // execute JGZ instruction
        if(TestMain.acc > 0){
            //goto label somehow
        }
        return true;
    }
}

class JlzInstruction extends Instruction {
    private String label;

    public JlzInstruction(String label) {
        this.label = label;
    }

    @Override
    public boolean execute() {
        // execute JLZ instruction
        if(TestMain.acc < 0){
            //goto label somehow
        }
        return true;
    }
}

class JroInstruction extends Instruction {
    private int offset;

    public JroInstruction(int offset) {
        this.offset = offset;
    }

    @Override
    public boolean execute() {
        // execute JRO instruction (move offset number of Instructions somehow)
        return true;
    }
}




