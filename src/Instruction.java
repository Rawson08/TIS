
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

    public MoveInstruction(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

    @Override
    public void execute() {
        // execute MOVE instruction
        System.out.println("moving: " + src + " to:" + dst);
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
    private Silo_A silo;
    public SwapInstruction(Silo_A silo){
        this.silo = silo;
    }
    @Override
    public void execute() {
        // execute SWAP instruction
        int temp = silo.getAcc();
        silo.setAcc(silo.getBak());
        silo.setBak(temp);
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
        Interpreter_A.arrayOfSilos[i][j].setAcc(5);
        System.out.println("saving: " + Interpreter_A.arrayOfSilos[i][j].getAcc());
        Interpreter_A.arrayOfSilos[i][j].setBak(Interpreter_A.arrayOfSilos[i][j].getAcc());
    }
}

class AddInstruction extends Instruction {
    private String src;

    public AddInstruction(String src) {
        this.src = src;
    }

    @Override
    public void execute() {
        // execute ADD instruction should get src value and add to acc
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




