/*
 * Project 4: TIS-100 Game
 * Class: CS351L
 * Group Members: Joel Gloetzner, Roshan Subedi, and Aayush Kafle
 * Description: This is the implementation of the game TIS:100 in JavaFX.
 *
 * This is the main GUI class which handles the drawing of the GUI, calling the Parsers and Interpreters, logics for
 * the buttons, implementation of SiloGUI, and all sort of drawing stuffs along with their logic.
 * */

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class GUI_R extends Application {

    private  int SILO_ROW;
    private static int SILO_COL;
    private static final int LINES = 15;
    private static final int CHARS = 20;

    public static boolean isRunning() {
        return isRunning;
    }

    private static boolean isRunning = false;
    private static Button startButton;
    private static Button pauseStepButton;
    private static Button stopButton;
    Interpreter_A interpreterA1 = new Interpreter_A("");
    private static HBox vBox;
    private static AnimationTimer animationTimer;
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void start(Stage primaryStage) {


        //To get the current screen Width and Height
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = visualBounds.getWidth();
        double screenHeight = visualBounds.getHeight();

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, screenWidth, screenHeight);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("buttonStyles.css")).toExternalForm());        // Create silo grid
        GridPane siloGrid = new GridPane();
        siloGrid.setAlignment(Pos.CENTER);
        siloGrid.setHgap(10);
        siloGrid.setVgap(10);
        siloGrid.setPadding(new Insets(10));
        siloGrid.setStyle("-fx-background-color: #1a1a1a;");



        interpreterA1.initialStartFromCmd();
        SILO_ROW = interpreterA1.getNumRows();
        SILO_COL = interpreterA1.getNumCols();

        //Input and Output labels creation and initialization in the vBox
        vBox = new HBox();
        for (int i=0; i<interpreterA1.getInputValuesList().size(); i++){
            System.out.println("list contains: " + interpreterA1.getInputValuesList().get(i));
            String inputValueStr = interpreterA1.getInputValuesList().get(i).toString().replaceAll(",", "\n").replaceAll("[ \\[\\]]","");
            vBox.getChildren().add(createInput(inputValueStr));
        }
        for (int j = 0; j<interpreterA1.getOutputCoordinatesList().size(); j++){
            vBox.getChildren().add(createOutput(""));
        }


        vBox.setPadding(new Insets(10));
        vBox.prefWidthProperty().bind(root.widthProperty().multiply(0.25));
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


            for (int i=0; i<SILO_ROW; i++){
                for (int j=0; j<SILO_COL; j++) {
                    for (int k = 0; k < Interpreter_A.arrayOfSilos[i][j].getListOfInstructions().size(); k++) {
                        String commandFromGUI = Interpreter_A.arrayOfSilos[i][j].getListOfInstructions().get(k);
                        interpreterA1.addInstruction(commandFromGUI,i,j);
                    }
                }
            }

            //Running the game from the method runGame
            runGame();


            startButton.setDisable(true);
            pauseStepButton.setDisable(false);
            stopButton.setDisable(false);
            siloGrid.setDisable(true);
        });

        pauseStepButton.setOnAction(event -> {
            startButton.setDisable(false);
            if (isRunning) {
                pauseStepButton.setText("Step");
                isRunning = false;
            } else {
                stepButtonExecute();
                // TODO: Execute 1 instruction from each silo
            }
        });



        stopButton.setOnAction(event -> {
            isRunning = false;
            startButton.setDisable(false);
            pauseStepButton.setDisable(true);
            pauseStepButton.setText("Pause");
            stopButton.setDisable(true);
            siloGrid.setDisable(false);
            System.out.println("outputs:" + Interpreter_A.getOutputValuesList());

            // TODO: Reset all of the silos
        });

        // Disable the stop buttons initially
        pauseStepButton.setDisable(false);
        stopButton.setDisable(true);

        // Add the buttons to an HBox
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(startButton, pauseStepButton, stopButton);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10));
        root.setBottom(buttonBox);

        int currentInstructionIndex = 1;
        // TODO: This has to get the current line of Instruction from Interpreter

        SiloGUI silo;
        for (int i = 0; i < SILO_ROW; i++) {
            for (int j = 0; j < SILO_COL; j++){
                silo = new SiloGUI();
                String cmdCommands = Interpreter_A.arrayOfSilos[i][j].getListOfInstructions().toString().replaceAll("[\\[\\]]", "");
                siloGrid.add(silo.drawSilo(Collections.singletonList(cmdCommands), currentInstructionIndex, i, j), j, i);

                // disable textAreaInputForNewInstructions when the start button is pressed
                silo.textAreaInputForNewInstructions(Interpreter_A.arrayOfSilos, i, j);

                // TODO: start button if pressed, then disable texts input and start the program by calling another method.
            }
        }


        siloGrid.prefWidthProperty().bind(root.widthProperty().multiply(0.75));
        root.setCenter(siloGrid);

        primaryStage.setTitle("Project 4: TIS-100");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void stepButtonExecute() {
        pauseStepButton.setOnAction(event1 -> {
            isRunning = true;
            runGame();
            isRunning = false;
            executor.shutdownNow();
        });
    }

    private void runGame() {
        ExecutorService executor = Executors.newFixedThreadPool(SILO_ROW * SILO_COL);
        for (int i = 0; i < SILO_ROW; i++) {
            for (int j = 0; j < SILO_COL; j++) {
                final int row = i;
                final int col = j;
                executor.submit(() -> {
                    Run_J run = new Run_J(row, col);
                    run.run();
                });
            }
        }

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isRunning) {
                    animationTimer.stop();
                } else {
                    printOutputValues();
                }
            }
        };
        animationTimer.start();
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
        inputArea.setMaxHeight(Double.MAX_VALUE); // Set the maximum height to Double.MAX_VALUE
        VBox.setVgrow(inputArea, Priority.ALWAYS); // Set VBox.vgrow to Priority.ALWAYS

        // Limit the number of lines to 15
        textAreaLimiter(inputArea);

        inputArea.setFont(Font.font("Monospaced", 12));
        inputArea.setStyle("-fx-control-inner-background: #222222; -fx-text-fill: #ffffff;");
        inputArea.setEditable(false);
        vBox1.getChildren().addAll(inputLabel, inputArea);
        return vBox1;
    }

    public void printOutputValues(){
        int temp = Interpreter_A.getOutputValuesList().size();
        for (int s = 0; s < temp; s++){
            String outputValueStr = Interpreter_A.getOutputValuesList().get(s).toString().replaceAll(",", "\n").replaceAll("[ \\[\\]]","");
            GUI_R.drawOutput(outputValueStr);
        }
    }

    public static void drawOutput(String str){
        for (int j = 0; j<Interpreter_A.getOutputCoordinatesList().size() + 1; j++){
            vBox.getChildren().remove(Interpreter_A.getInputValuesList().size());
            vBox.getChildren().add(createOutput(str));
        }
    }


    public static VBox createOutput(String str){
        VBox vBox = new VBox();
        Label outputLabel = new Label("OUTPUT");
        outputLabel.setFont(Font.font("Monospaced", 14));
        outputLabel.setTextFill(Color.WHITE);

        TextArea outputArea = new TextArea(str);
        outputArea.setEditable(false);
        outputArea.setPrefHeight(200);
        outputArea.setWrapText(true);
        outputArea.setMaxHeight(Double.MAX_VALUE); // Set the maximum height to Double.MAX_VALUE
        VBox.setVgrow(outputArea, Priority.ALWAYS); // Set VBox.vgrow to Priority.ALWAYS

        // Limit the number of lines to 15
        textAreaLimiter(outputArea);

        outputArea.setFont(Font.font("Monospaced", 12));
        outputArea.setStyle("-fx-control-inner-background: #222222; -fx-text-fill: #ffffff;");
        vBox.getChildren().addAll(outputLabel, outputArea);
        return vBox;
    }

    public static void main(String[] args) {launch(args);}
}
