import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class SiloGUI {
    private static VBox rightOfSilo;
    private static int accIntValue;
    private static int bakIntValue;
    private static HBox accBakBox;
    private static Label accLabel;
    private static Label bakLabel;
    private static Label accValue;
    private static Label bakValue;
    private static Label upArrow;
    private static Label downArrow;
    private static Label leftArrow;
    private static Label rightArrow;
    private static HBox arrowBox;
    private static TextArea siloArea;

    public SiloGUI(){

    }

    public BorderPane drawSilo(List<String> str, int currentInstructionIndex){
        drawAccBak();
        drawArrows();
        rightOfSilo = new VBox();
        rightOfSilo.getChildren().addAll(accLabel, accValue, bakLabel, bakValue, rightArrow);
        rightOfSilo.setSpacing(5);

        //Setting the co-ordinates for the arrows and BAK/ACC
        //TODO: Currently the co-ordinates only works for a fixed 1920X1080 Display, when resized, they don't move with the silo
        BorderPane.setMargin(leftArrow, new Insets(105, 10, 0, 0));
        BorderPane.setMargin(rightOfSilo, new Insets(10, 0, 0, 10));
        BorderPane.setMargin(upArrow, new Insets(10, 0, 0, 125));
        BorderPane.setMargin(downArrow, new Insets(0, 0, 10, 125));

        //This is the Silo TextArea section
        siloArea = new TextArea(str.toString().replaceAll("[\\[\\]]", "").replaceAll("[,]","\n"));
        siloArea.textProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue));
        siloArea.setEditable(true);
        siloArea.setPrefColumnCount(20);
        siloArea.setPrefRowCount(15);
        siloArea.setWrapText(true);

        // Limit the number of lines to 15
        GUI_R.textAreaLimiter(siloArea);

        // TODO: Highlight the current instruction



        siloArea.setFont(Font.font("Monospaced", 12));
        siloArea.setStyle("-fx-control-inner-background: #222222; -fx-text-fill: #ffffff;");
        return new BorderPane(siloArea, upArrow, rightOfSilo, downArrow, leftArrow);
    }

    public void drawAccBak(){

        //TODO: Get the Acc and Bak from Interpreter
        accIntValue = 0;
        bakIntValue = 0;

        // Add the Acc and Bak labels
        accBakBox = new HBox();
        accBakBox.setAlignment(Pos.TOP_RIGHT);
        accBakBox.setSpacing(10);
        accLabel = new Label("ACC");
        accLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 15));
        accLabel.setTextFill(Color.WHITE);
        accValue = new Label(String.valueOf(accIntValue));
        accValue.setFont(Font.font("Monospaced", 15));
        accValue.setTextFill(Color.WHITE);
        bakLabel = new Label("BAK");
        bakLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 15));
        bakLabel.setTextFill(Color.WHITE);
        bakValue = new Label(String.valueOf(bakIntValue));
        bakValue.setFont(Font.font("Monospaced", 15));
        bakValue.setTextFill(Color.WHITE);

    }

    public void drawArrows(){
        //TODO: Arrows needs more work, as currently we can't be sure if any arrow is in use. I am thinking to
        // import the 'in-use' arrows as well which will be used if any arrow is used.

        // Importing the Arrows PNGs
        ImageView upArrowPNG = new ImageView("file:src/Arrows/up-arrow.png");
        ImageView downArrowPNG = new ImageView("file:src/Arrows/down-arrow.png");
        ImageView leftArrowPNG = new ImageView("file:src/Arrows/left-arrow.png");
        ImageView rightArrowPNG = new ImageView("file:src/Arrows/right-arrow.png");

        ImageView upArrowOnPNG = new ImageView("file:src/Arrows/up-arrow-on.png");
        ImageView downArrowOnPNG = new ImageView("file:src/Arrows/down-arrow-on.png");
        ImageView leftArrowOnPNG = new ImageView("file:src/Arrows/left-arrow-on.png");
        ImageView rightArrowOnPNG = new ImageView("file:src/Arrows/right-arrow-on.png");

        //2D Array to store the arrows and we can use the arrow giving 0 for unlit, and 1 for lit arrow
        ImageView [][] imageViewArray = {{upArrowPNG, downArrowPNG, leftArrowPNG, rightArrowPNG},
                {upArrowOnPNG, downArrowOnPNG, leftArrowOnPNG, rightArrowOnPNG}};

        if (true){
            int i = 0;
            arrowBox = new HBox();
            arrowBox.setAlignment(Pos.CENTER);
            arrowBox.setSpacing(10);
            upArrow = new Label();
            upArrow.setGraphic(imageViewArray[i][0]);
            upArrow.setFont(Font.font("Monospaced", FontWeight.BOLD, 15));
            upArrow.setTextFill(Color.LIGHTGRAY);
            downArrow = new Label();
            downArrow.setGraphic(imageViewArray[i][1]);
            downArrow.setFont(Font.font("Monospaced", FontWeight.BOLD, 15));
            downArrow.setTextFill(Color.LIGHTGRAY);
            leftArrow = new Label();
            leftArrow.setGraphic(imageViewArray[i][2]);
            leftArrow.setFont(Font.font("Monospaced", FontWeight.BOLD, 15));
            leftArrow.setTextFill(Color.LIGHTGRAY);
            rightArrow = new Label();
            rightArrow.setGraphic(imageViewArray[i][3]);
            rightArrow.setFont(Font.font("Monospaced", FontWeight.BOLD, 15));
            rightArrow.setTextFill(Color.LIGHTGRAY);
        }
    }

}