/**
 * This class is where we index every silo through two dimensional array as we discussed in our weekly meeting.
 *
 */
public class Silo_A {
    // we will need to take in the value of numOfSilos from the command line per the instruction provided
    // in the command line.
        private int numOfSilos;
        private int[][] arrayOfSilos;

        public Silo_A(int numOfSilos) {
            this.numOfSilos = numOfSilos;
            this.arrayOfSilos = new int[numOfSilos][numOfSilos];
        }

        public int getNumOfSilos() {
            return numOfSilos;
        }

        public int getSiloAtIndex(int i, int j){
            return arrayOfSilos[i][j];
        }

        public int[][] getArrayOfSilos() {
            return arrayOfSilos;
        }

        public void setSiloIndex(int i, int j, int value) {
            this.arrayOfSilos[i][j] = value;
        }
    }

