public class Run implements Runnable {
    private Interpreter_A interpreterA;

    public Run(Interpreter_A interpreterA) {
        this.interpreterA = interpreterA;
    }

    public void run() {
        // code to be executed in this thread
        System.out.println("NumRows: " + interpreterA.getNumRows() + " NumCols: " + interpreterA.getNumCols());
        while(GUI_R.isRunning()) {
            for (int i = 0; i < interpreterA.getNumRows(); i++) {
                if(!GUI_R.isRunning()) break;
                for (int j = 0; j < interpreterA.getNumCols(); j++) {
                    if(!GUI_R.isRunning()) break;
                    for (int k = 0; k < Interpreter_A.arrayOfSilos[i][j].getListOfInstructions().size(); k++) {
                        String commandFromGUI = Interpreter_A.arrayOfSilos[i][j].getListOfInstructions().get(k);
                        interpreterA.addInstruction(commandFromGUI, i, j);
                        interpreterA.runInstructions(commandFromGUI, i, j);
                        try {
                            Thread.sleep(1000); // pause the thread for 1 second
                        } catch (InterruptedException e) {
                            // handle the exception if the thread is interrupted
                        }
                    }
                }
            }
            System.out.println("output:" + Interpreter_A.getOutputValues());
        }
    }
}
