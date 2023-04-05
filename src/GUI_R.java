import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI_R extends Application {

    private static final int SILO_ROW = 3;
    private static final int SILO_COL = 4;
    private static final int LINES = 15;
    private static final int CHARS = 20;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #222222;");

        // Create input/output panel
        HBox ioPanel = new HBox(10);
        ioPanel.setAlignment(Pos.CENTER);
        ioPanel.setPadding(new Insets(10));
        ioPanel.setPrefWidth(200);
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

//        root.setOnSwipeLeft(event -> );
        root.setLeft(ioPanel);

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
            siloArea.setFont(Font.font("Monospaced", 12));
            siloArea.setStyle("-fx-control-inner-background: #222222; -fx-text-fill: #ffffff;");
            siloGrid.add(siloArea, i % 4, i / 4);
        }

        root.setCenter(siloGrid);

        primaryStage.setTitle("Project 4: TIS-100");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
