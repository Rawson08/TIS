import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;

public class GUI_R extends Application {

    private static final int SILO_ROW = 3;
    private static final int SILO_COL = 4;
    private static final int LINES = 15;
    private static final int CHARS = 20;
    private boolean isRunning = false;
    private static Button startButton;
    private static Button pauseStepButton;
    private static Button stopButton;

    @Override
    public void start(Stage primaryStage) {

        //To get the current screen Width and Height
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = visualBounds.getWidth();
        double screenHeight = visualBounds.getHeight();

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, screenWidth, screenHeight);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("buttonStyles.css")).toExternalForm());
        root.setStyle("-fx-background-color: #222222;");

        // Create input/output panel
        HBox ioPanel = new HBox(10);
        ioPanel.setAlignment(Pos.CENTER);
        ioPanel.setPadding(new Insets(10));
        ioPanel.setPrefWidth(screenWidth/3);
        ioPanel.setPrefHeight(screenHeight*0.75);
        ioPanel.setStyle("-fx-background-color: #1a1a1a;");

        HBox ioLabel = new HBox(10);
        ioLabel.setAlignment(Pos.TOP_CENTER);
        ioLabel.setPadding(new Insets(10));
        ioLabel.setPrefWidth(200);
        ioLabel.setStyle("-fx-background-color: #1a1a1a;");


        Label inputLabel = new Label("INPUT");
        inputLabel.setFont(Font.font("Monospaced", 14));
        inputLabel.setTextFill(Color.WHITE);
        TextArea inputArea = new TextArea();
        inputArea.setPrefHeight(200);
        inputArea.setWrapText(true);
        // Limit the number of lines to 15
        int maxLines = 15;
        TextFormatter<String> textFormatterIn = new TextFormatter<>(change -> {
            if (change.getControlNewText().lines().count() > maxLines) {
                return null;
            }
            return change;
        });
        inputArea.setTextFormatter(textFormatterIn);
        inputArea.setFont(Font.font("Monospaced", 12));
        inputArea.setStyle("-fx-control-inner-background: #222222; -fx-text-fill: #ffffff;");
        Label outputLabel = new Label("OUTPUT");
        outputLabel.setFont(Font.font("Monospaced", 14));
        outputLabel.setTextFill(Color.WHITE);
        TextArea outputArea = new TextArea();
        inputArea.setEditable(false);
        outputArea.setEditable(false);
        outputArea.setPrefHeight(200);
        outputArea.setWrapText(true);
        // Limit the number of lines to 15
        TextFormatter<String> textFormatterOut = new TextFormatter<>(change -> {
            if (change.getControlNewText().lines().count() > maxLines) {
                return null;
            }
            return change;
        });
        outputArea.setTextFormatter(textFormatterOut);
        outputArea.setFont(Font.font("Monospaced", 12));
        outputArea.setStyle("-fx-control-inner-background: #222222; -fx-text-fill: #ffffff;");

        ioLabel.getChildren().addAll(inputLabel, outputLabel);
        ioPanel.getChildren().addAll(inputArea, outputArea);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(ioLabel, ioPanel);
        vBox.setPadding(new Insets(10));
        vBox.prefWidthProperty().bind(root.widthProperty().multiply(0.25));

//        root.setOnSwipeLeft(event -> );
        root.setLeft(vBox);


        //TODO: Buttons with the change on click functionality
        startButton = new Button("Start");
        startButton.getStyleClass().add("button");
        pauseStepButton = new Button("Pause");
        stopButton = new Button("Stop");

        startButton.setOnAction(event -> {
            isRunning = true;
            //TODO: Execution
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

        for (int i = 0; i < SILO_ROW * SILO_COL; i++) {
            TextArea siloArea = new TextArea();
            siloArea.setEditable(true);
            siloArea.setPrefColumnCount(CHARS);
            siloArea.setPrefRowCount(LINES);
            siloArea.setWrapText(true);
            // Limit the number of lines to 15
            TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
                if (change.getControlNewText().lines().count() > maxLines) {
                    return null;
                }
                return change;
            });
            siloArea.setTextFormatter(textFormatter);
            siloArea.setFont(Font.font("Monospaced", 15));
            siloArea.setStyle("-fx-control-inner-background: #222222; -fx-text-fill: #ffffff;");
            siloGrid.add(siloArea, i % 4, i / 4);
        }

        siloGrid.prefWidthProperty().bind(root.widthProperty().multiply(0.75));
        root.setCenter(siloGrid);



        primaryStage.setTitle("Project 4: TIS-100");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
