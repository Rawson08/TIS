/**
 * this abstract class provides creation of instruction nodes
 * that can be placed in a silos instructions List
 * each subclass overrides execute() depending on what action needs to occur
 */
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
    }
}

class LabelInstruction extends Instruction {
    private String label;

    public LabelInstruction(String label) {
        this.label = label;
    }

    @Override
    public void execute() {
        // execute LABEL instruction
    }
}

class NoopInstruction extends Instruction {
    @Override
    public void execute() {
        // execute NOOP instruction
    }
}

class SwapInstruction extends Instruction {
    @Override
    public void execute() {
        // execute SWAP instruction
    }
}

class SaveInstruction extends Instruction {
    @Override
    public void execute() {
        // execute SAVE instruction
    }
}

class AddInstruction extends Instruction {
    private String src;

    public AddInstruction(String src) {
        this.src = src;
    }

    @Override
    public void execute() {
        // execute ADD instruction
    }
}

class SubInstruction extends Instruction {
    private String src;

    public SubInstruction(String src) {
        this.src = src;
    }

    @Override
    public void execute() {
        // execute SUB instruction
    }
}

class NegateInstruction extends Instruction {
    @Override
    public void execute() {
        // execute NEGATE instruction
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
    }
}

class JroInstruction extends Instruction {
    private int offset;

    public JroInstruction(int offset) {
        this.offset = offset;
    }

    @Override
    public void execute() {
        // execute JRO instruction
    }
}




