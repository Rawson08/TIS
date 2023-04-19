
/**
 * this abstract class provides creation of instruction nodes
 * that can be placed in a silos instructions List
 * each subclass overrides execute() depending on what action needs to occur
 */

// TODO: implement the logic for each execute function

public abstract class Instruction {
    public abstract void execute();
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
    public void execute() {
        // execute MOVE instruction
        int valueToMove = 0;
        System.out.println("moving: " + src + " to:" + dst);
        if(src.equals("UP")){
            int k = 0;
            while(k < Interpreter_A.getInputCoordinatesList().size()) {
                if (i == (Interpreter_A.getInputCoordinatesList().get(k).get(0) + 1)
                        && (j == Interpreter_A.getInputCoordinatesList().get(k).get(1))) {
                    if (Interpreter_A.getInputValuesList().get(k).size() > 0) {
                        valueToMove = Interpreter_A.getInputValuesList().get(k).remove(0);
                    }
                    System.out.println("input" + k);
                    break;
                }
                else if(i > 0) valueToMove = Interpreter_A.arrayOfSilos[i - 1][j].getPortA().getDownPortAccValue();
                k++;
            }
            //else if(i > 0) valueToMove = Interpreter_A.arrayOfSilos[i - 1][j].getPortA().getDownPortAccValue();
            //else valueToMove = 0;
            //System.out.println("moving value: " + valueToMove);
        }
        else if(src.equals("DOWN")){
            valueToMove = Interpreter_A.arrayOfSilos[i][j].getPortA().getUpPortAccValue();
        }
        else if(src.equals("LEFT")){
            if(j > 0) {
                valueToMove = Interpreter_A.arrayOfSilos[i][j-1].getPortA().getRightPortAccValue();
                //System.out.println("moving value: " + valueToMove);
            }
        }
        else if(src.equals("RIGHT")){
            if(j < Interpreter_A.arrayOfSilos[0].length) {
                valueToMove = Interpreter_A.arrayOfSilos[i][j + 1].getPortA().getLeftPortAccValue();
                //System.out.println("moving value: " + valueToMove);
            }
        }
        else if(src.equals("ACC")){
            valueToMove = Interpreter_A.arrayOfSilos[i][j].getAcc();
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
                else Interpreter_A.arrayOfSilos[i][j].getPortA().setDownPortAccValue(valueToMove);
            }
        }
        else if(dst.equals("LEFT")){
            if(j > 0) {
                Interpreter_A.arrayOfSilos[i][j].getPortA().setRightPortAccValue(valueToMove);
            }
        }
        else if(dst.equals("RIGHT")){
            if(j < Interpreter_A.arrayOfSilos[0].length) {
                Interpreter_A.arrayOfSilos[i][j].getPortA().setLeftPortAccValue(valueToMove);
            }
        }
        else if(dst.equals("ACC")){
            Interpreter_A.arrayOfSilos[i][j].setAcc(valueToMove);
        }
        System.out.println("moving value: " + valueToMove);
    }
}

class LabelInstruction extends Instruction {
    private String label;

    public LabelInstruction(String label) {
        this.label = label;
    }

    @Override
    public void execute() {
        // execute LABEL instruction (does nothing)
    }
}

class NoopInstruction extends Instruction {
    @Override
    public void execute() {
        // execute NOOP instruction (does nothing)
    }
}

class SwapInstruction extends Instruction {
int i, j;
    public SwapInstruction(int i, int j){
        this.i = i;
        this.j = j;
    }
    @Override
    public void execute() {
        // execute SWAP instruction
        int temp = Interpreter_A.arrayOfSilos[i][j].getAcc();
        Interpreter_A.arrayOfSilos[i][j].setAcc(Interpreter_A.arrayOfSilos[i][j].getBak());
        Interpreter_A.arrayOfSilos[i][j].setBak(temp);
    }
}

class SaveInstruction extends Instruction {
    private int i, j;
    public SaveInstruction(int i, int j){
        this.i = i;
        this.j = j;
    }

    @Override
    public void execute() {
        // execute SAVE instruction
        System.out.println("saving: " + Interpreter_A.arrayOfSilos[i][j].getAcc());
        Interpreter_A.arrayOfSilos[i][j].setBak(Interpreter_A.arrayOfSilos[i][j].getAcc());
    }
}

class AddInstruction extends Instruction {
    private int src;
    int i, j;

    public AddInstruction(int src, int i, int j) {
        this.src = src;
        this.i = i;
        this.j = j;
    }

    @Override
    public void execute() {
        // execute ADD instruction should get src value and add to acc
        int number = src;
        Interpreter_A.arrayOfSilos[i][j].setAcc(Interpreter_A.arrayOfSilos[i][j].getAcc() + number);
        System.out.println("adding: " + number);
    }
}

class SubInstruction extends Instruction {
    private String src;

    public SubInstruction(String src) {
        this.src = src;
    }

    @Override
    public void execute() {
        // execute SUB instruction will subtract src value from acc
    }
}

class NegateInstruction extends Instruction {
    @Override
    public void execute() {
        // execute NEGATE instruction
        TestMain.acc = (-1) * TestMain.acc;
    }
}

class JumpInstruction extends Instruction {
    private String label;

    public JumpInstruction(String label) {
        this.label = label;
    }

    @Override
    public void execute() {
        // execute JUMP instruction
    }
}

class JezInstruction extends Instruction {
    private String label;

    public JezInstruction(String label) {
        this.label = label;
    }

    @Override
    public void execute() {
        // execute JEZ instruction
        if(TestMain.acc == 0){
            //goto label somehow
        }
    }
}

class JnzInstruction extends Instruction {
    private String label;

    public JnzInstruction(String label) {
        this.label = label;
    }

    @Override
    public void execute() {
        // execute JNZ instruction
        if(TestMain.acc != 0){
            //goto label somehow
        }
    }
}

class JgzInstruction extends Instruction {
    private String label;

    public JgzInstruction(String label) {
        this.label = label;
    }

    @Override
    public void execute() {
        // execute JGZ instruction
        if(TestMain.acc > 0){
            //goto label somehow
        }

    }
}

class JlzInstruction extends Instruction {
    private String label;

    public JlzInstruction(String label) {
        this.label = label;
    }

    @Override
    public void execute() {
        // execute JLZ instruction
        if(TestMain.acc < 0){
            //goto label somehow
        }
    }
}

class JroInstruction extends Instruction {
    private int offset;

    public JroInstruction(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute() {
        // execute JRO instruction (move offset number of Instructions somehow)
    }
}




