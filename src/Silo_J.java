import java.util.LinkedList;
import java.util.List;

public class Silo_J {
    private int siloNumber = 0;
    private List commandList;
    private Port_A ports;
    public Silo_J(){
        this.siloNumber = ++siloNumber;
        this.commandList = new LinkedList();
    }
}
