/**
 * Project 4: TIS-100 Game
 * Class: CS351L
 * Group Members: Joel Gloetzner, Roshan Subedi, and Aayush Kafle
 * Description: This is the implementation of the game TIS:100 in JavaFX.
 *
 * This is the Thread class which handles threading for each silos respectively. Called from the GUI_R Class.
 */

public class Run_J implements Runnable {
    private int i,j;
    private int index = 0;
    private static int sleepTime = 1000;

    public Run_J(int i, int j) {
        this.i= i;
        this.j=j;
        this.index = index;
        this.sleepTime = sleepTime;
    }

    @Override
    public synchronized void run() {
        while(GUI_R.isRunning()) {
            int length = Interpreter_A.arrayOfSilos[i][j].getInstructions().size();
            if(!GUI_R.isRunning()) break;
            while(index < length && GUI_R.isRunning()) {
                if(Interpreter_A.arrayOfSilos[i][j].getInstructions().get(index).execute()) {
                    index++;
                    Interpreter_A.arrayOfSilos[i][j].setInstructionIndex(index);
                }
                else System.out.println("waiting");
                notify();
                if(index == length) index = 0;
                try {
                    wait(sleepTime); // pause the thread for 1 second
                } catch (InterruptedException e) {
                    // handle the exception if the thread is interrupted
                }
            }

        }
    }

    public int getIndex() {
        return index;
    }
}
