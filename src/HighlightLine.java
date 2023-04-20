public class HighlightLine {
    SiloGUI siloGUI = new SiloGUI();
    Interpreter_A interpreterA = new Interpreter_A("");
    Silo_A siloA = new Silo_A();
    private static int siloTextLength;

    public void highLightLineInSilo(){
        siloTextLength = siloA.getListOfInstructions().toArray().length;

    }
}
