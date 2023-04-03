import java.util.LinkedList;
import java.util.List;

public class Silo_J {
    private int siloNumber = 0;
    private List commandList;
    public Silo_J(){
        this.siloNumber = ++siloNumber;
        this.commandList = new LinkedList();
    }
}
