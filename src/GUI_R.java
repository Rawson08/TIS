import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GUI_R extends Application {

    private  int SILO_ROW;
    private static int SILO_COL;
    private static final int LINES = 15;
    private static final int CHARS = 20;
    private boolean isRunning = false;
    private static Button startButton;
    private static Button pauseStepButton;
    private static Button stopButton;
    Interpreter_A interpreterA1 = new Interpreter_A("");


    @Override
    public void start(Stage primaryStage) {


        //To get the current screen Width and Height
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = visualBounds.getWidth();
        double screenHeight = visualBounds.getHeight();

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, screenWidth, screenHeight);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("buttonStyles.css")).toExternalForm());
//        root.setStyle("-fx-background-color: #222222;");


        interpreterA1.initialStartFromCmd();
        SILO_ROW = interpreterA1.getNumRows();
        SILO_COL = interpreterA1.getNumCols();
        //Silo_A[][] arraylist1 = interpreterA1.getArrayOfSilos();

        // Create input/output panel
//        HBox ioPanel = new HBox(10);
//        ioPanel.setAlignment(Pos.CENTER);
//        ioPanel.setPadding(new Insets(10));
//        ioPanel.setPrefWidth(screenWidth/3);
//        ioPanel.setPrefHeight(screenHeight*0.75);
//        ioPanel.setStyle("-fx-background-color: #1a1a1a;");
//
//        HBox ioLabel = new HBox(10);
//        ioLabel.setAlignment(Pos.TOP_CENTER);
//        ioLabel.setPadding(new Insets(10));
//        ioLabel.setPrefWidth(200);
//        ioLabel.setStyle("-fx-background-color: #1a1a1a;");


//        createInput();
//        createOutput();


        //TODO: Put inputLabel with inputArea in one VBox and same for output
//        ioLabel.getChildren().addAll(inputLabel, outputLabel);
//        ioPanel.getChildren().addAll(inputArea, outputArea);
        HBox vBox = new HBox();
        for (int i=0; i<interpreterA1.getInputValuesList().size(); i++){
            System.out.println("list contains: " + interpreterA1.getInputValuesList().get(i));
            String inputValueStr = interpreterA1.getInputValuesList().get(i).toString().replaceAll(",", "\n").replaceAll("[ \\[\\]]","");
            vBox.getChildren().add(createInput(inputValueStr));
        }
        for (int j=0; j<interpreterA1.getOutputValuesList().size(); j++){
            vBox.getChildren().add(createOutput());
        }


        vBox.setPadding(new Insets(10));
        vBox.prefWidthProperty().bind(root.widthProperty().multiply(0.25));
//        vBox.prefHeightProperty().bind(root.heightProperty().multiply(0.75));
        vBox.setPrefHeight(200);
        root.setLeft(vBox);
        root.setStyle("-fx-background-color: #222222;");


        //TODO: Buttons with the change on click functionality
        startButton = new Button("Start");
        startButton.setStyle("-fx-border-color: white; -fx-border-width: 1px;");
        pauseStepButton = new Button("Pause");
        pauseStepButton.setStyle("-fx-border-color: white; -fx-border-width: 1px;");
        stopButton = new Button("Stop");
        stopButton.setStyle("-fx-border-color: white; -fx-border-width: 1px;");

        startButton.setOnAction(event -> {
            isRunning = true;

            //TODO: Execution
            for (int i=0; i<SILO_ROW; i++){
                for (int j=0; j<SILO_COL; j++) {
                    for (int k = 0; k < Interpreter_A.arrayOfSilos[i][j].getListOfInstructions().size(); k++) {
                        String commandFromGUI = Interpreter_A.arrayOfSilos[i][j].getListOfInstructions().get(k);
                        interpreterA1.addInstruction(commandFromGUI,i,j);
                        interpreterA1.runInstructions(commandFromGUI, i, j);
                    }
                }
            }


            startButton.setDisable(true);
            pauseStepButton.setDisable(false);
            stopButton.setDisable(false);
        });

        pauseStepButton.setOnAction(event -> {
            if (isRunning) {
                pauseStepButton.setText("Step");

                isRunning = false;
            } else {
                // TODO: Execute 1 instruction from each silo
            }
        });

        stopButton.setOnAction(event -> {
            isRunning = false;
            startButton.setDisable(false);
            pauseStepButton.setDisable(true);
            pauseStepButton.setText("Pause");
            stopButton.setDisable(true);
            // TODO: Reset all of the silos
        });

        // Disable the pause/step and stop buttons initially
        pauseStepButton.setDisable(true);
        stopButton.setDisable(true);

        // Add the buttons to an HBox
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(startButton, pauseStepButton, stopButton);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10));
        root.setBottom(buttonBox);

        // Create silo grid
        GridPane siloGrid = new GridPane();
        siloGrid.setAlignment(Pos.CENTER);
        siloGrid.setHgap(10);
        siloGrid.setVgap(10);
        siloGrid.setPadding(new Insets(10));
        siloGrid.setStyle("-fx-background-color: #1a1a1a;");

        int currentInstructionIndex = 1;
        // TODO: This has to get the current line of Instruction from Interpreter

        SiloGUI silo;
        for (int i = 0; i < SILO_ROW; i++) {
            for (int j = 0; j < SILO_COL; j++){
                silo = new SiloGUI();
                String listString1 = Interpreter_A.arrayOfSilos[i][j].getListOfInstructions().toString().replaceAll("[\\[\\]]", "");
                System.out.println("listString1" + listString1);
                siloGrid.add(silo.drawSilo(Collections.singletonList(listString1), currentInstructionIndex, i, j), j, i);

                // disable textAreaInputForNewInstructions when the start button is pressed
                silo.textAreaInputForNewInstructions(Interpreter_A.arrayOfSilos, i, j);

                // TODO: start button if pressed, then disable texts input and start the program by calling another method.
                //interpreterA1.setArrayOfSilos(arraylist1);
                System.out.println("New Instruc: " + Interpreter_A.arrayOfSilos[i][j]);
            }
        }


        siloGrid.prefWidthProperty().bind(root.widthProperty().multiply(0.75));
        root.setCenter(siloGrid);

        primaryStage.setTitle("Project 4: TIS-100");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void textAreaLimiter(TextArea textArea){
        int maxLines = 15;
        TextFormatter<String> textFormatterIn = new TextFormatter<>(change -> {
            if (change.getControlNewText().lines().count() > maxLines) {
                return null;
            }
            return change;
        });
        textArea.setTextFormatter(textFormatterIn);
    }

    public void createSilos(){
        Interpreter_A interpreterA1 = new Interpreter_A("");
        interpreterA1.getArrayOfSilos();
        interpreterA1.getNumCols();
    }

    public VBox createInput(String str){
        VBox vBox1 = new VBox();
        Label inputLabel = new Label("INPUT");
        inputLabel.setFont(Font.font("Monospaced", 14));
        inputLabel.setTextFill(Color.WHITE);

        TextArea inputArea = new TextArea(str);
        inputArea.setPrefHeight(200);
        inputArea.setWrapText(true);

        // Limit the number of lines to 15
        textAreaLimiter(inputArea);

        inputArea.setFont(Font.font("Monospaced", 12));
        inputArea.setStyle("-fx-control-inner-background: #222222; -fx-text-fill: #ffffff;");
        inputArea.setEditable(false);
        vBox1.getChildren().addAll(inputLabel, inputArea);
        return vBox1;
    }

    public VBox createOutput(){
        VBox vBox = new VBox();
        Label outputLabel = new Label("OUTPUT");
        outputLabel.setFont(Font.font("Monospaced", 14));
        outputLabel.setTextFill(Color.WHITE);

        //Output
        String listString2 = interpreterA1.getInputValues().toString().replaceAll(",", "\n").replaceAll("[ \\[\\]]","");
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(200);
        outputArea.setWrapText(true);

        // Limit the number of lines to 15
//        textAreaLimiter(outputArea);

        outputArea.setFont(Font.font("Monospaced", 12));
        outputArea.setStyle("-fx-control-inner-background: #222222; -fx-text-fill: #ffffff;");
        vBox.getChildren().addAll(outputLabel, outputArea);
        return vBox;
    }

    public static void main(String[] args) {launch(args);}
}
