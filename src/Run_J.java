public class Run_J implements Runnable {
    private int i,j;
    private int index = 0;

    public Run_J(int i, int j) {
        this.i= i;
        this.j=j;
        this.index = index;
    }

    public void run() {
        // code to be executed in this thread
        while(GUI_R.isRunning()) {
                    int index = 0;
                    int length = Interpreter_A.arrayOfSilos[i][j].getInstructions().size();
                    if(!GUI_R.isRunning()) break;
                    while(index < length && GUI_R.isRunning()) {
                        if(Interpreter_A.arrayOfSilos[i][j].getInstructions().get(index).execute()) {
                            index++;
                        }
                        if(index == length) index = 0;
                        try {
                            Thread.sleep(1000); // pause the thread for 1 second
                        } catch (InterruptedException e) {
                            // handle the exception if the thread is interrupted
                        }
                    }

        }
        System.out.println("outputs:" + Interpreter_A.getOutputValuesList());
    }

    public int getIndex() {
        return index;
    }
}
