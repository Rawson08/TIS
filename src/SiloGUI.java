/*
 * Project 4: TIS-100 Game
 * Class: CS351L
 * Group Members: Joel Gloetzner, Roshan Subedi, and Aayush Kafle
 * Description: This is the implementation of the game TIS:100 in JavaFX.
 *
 * This is the GUI class for the components of Silo, like it's arrows, BAK/ACC Labels and all
 * of the Silo GUI implementations and logics.
 * */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.InputStream;
import java.util.ArrayList;
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

    public static void setSiloArea(TextArea siloArea) {
        SiloGUI.siloArea = siloArea;
    }

    public static TextArea getSiloArea() {
        return siloArea;
    }

    private static TextArea siloArea;

    public SiloGUI(){

    }


    public BorderPane drawSilo(List<String> str, int currentInstructionIndex, int i, int j){
        accIntValue = Interpreter_A.arrayOfSilos[i][j].getAcc();
        bakIntValue = Interpreter_A.arrayOfSilos[i][j].getBak();

        //Taking values from the Interpreter class of particular silo
        int arrowUpValue = Interpreter_A.arrayOfSilos[i][j].getPortA().getUpPortAccValue();
        int arrowDownValue = Interpreter_A.arrayOfSilos[i][j].getPortA().getDownPortAccValue();
        int arrowLeftValue = Interpreter_A.arrayOfSilos[i][j].getPortA().getLeftPortAccValue();
        int arrowRightValue = Interpreter_A.arrayOfSilos[i][j].getPortA().getRightPortAccValue();
        drawAccBak(accIntValue, bakIntValue);
        drawArrows(arrowUpValue, arrowDownValue, arrowLeftValue, arrowRightValue);
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
        siloArea = new TextArea(str.toString().replaceAll("[\\[\\]]", "").replaceAll(", ","\n"));

        // TODO: Decided to make the program run from the cmd first and then make it run from the Silo inputs
        siloArea.setEditable(true);
        siloArea.setPrefColumnCount(20);
        siloArea.setPrefRowCount(15);
        siloArea.setWrapText(true);

        // Limit the number of lines to 15
        GUI_R.textAreaLimiter(siloArea);

        // TODO: Highlight the current instruction
        siloArea.selectRange(5, 10);

        siloArea.setFont(Font.font("Monospaced", 12));
        siloArea.setStyle("-fx-control-inner-background: #222222; -fx-text-fill: #ffffff;");
        return new BorderPane(siloArea, upArrow, rightOfSilo, downArrow, leftArrow);
    }

    public void textAreaInputForNewInstructions(Silo_A[][] arrayOfSilos, int i, int j){
        final List<String> newList = new ArrayList<>();
        siloArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.endsWith("\n")) {
                System.out.println("clicked enter;");
            }
            Interpreter_A.arrayOfSilos[i][j].setListOfInstructions(newList);
        });
    }

    public void drawAccBak(int accIntValue, int bakIntValue){
        // TODO: Get the Acc and Bak from Interpreter

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

    public void drawArrows(int upValue, int downValue, int leftValue, int rightValue){
        //TODO: Arrows needs more work, as currently we can't be sure if any arrow is in use. I am thinking to
        // import the 'in-use' arrows as well which will be used if any arrow is used.

        // Importing the Arrows PNGs
        ImageView upArrowPNG = makeArrows("up-arrow.png");
        ImageView downArrowPNG = makeArrows("down-arrow.png");
        ImageView leftArrowPNG = makeArrows("left-arrow.png");
        ImageView rightArrowPNG = makeArrows("right-arrow.png");

        ImageView upArrowOnPNG = makeArrows("up-arrow-on.png");
        ImageView downArrowOnPNG = makeArrows("down-arrow-on.png");
        ImageView leftArrowOnPNG = makeArrows("left-arrow-on.png");
        ImageView rightArrowOnPNG = makeArrows("right-arrow-on.png");

        //2D Array to store the arrows and we can use the arrow giving 0 for unlit, and 1 for lit arrow
        ImageView [][] imageViewArray = {{upArrowPNG, downArrowPNG, leftArrowPNG, rightArrowPNG},
                {upArrowOnPNG, downArrowOnPNG, leftArrowOnPNG, rightArrowOnPNG}};

        int up, down, left, right;

        up = (upValue != 0) ? 1 : 0;
        down = (downValue != 0) ? 1 : 0;
        left = (leftValue != 0) ? 1 : 0;
        right = (rightValue != 0) ? 1 : 0;


        arrowBox = new HBox();
        arrowBox.setAlignment(Pos.CENTER);
        arrowBox.setSpacing(10);

        upArrow = new Label(String.valueOf(upValue));
        upArrow.setGraphic(imageViewArray[up][0]);
        upArrow.setFont(Font.font("Monospaced", FontWeight.BOLD, 15));
        upArrow.setTextFill(Color.LIGHTGRAY);
        downArrow = new Label(String.valueOf(downValue));
        downArrow.setGraphic(imageViewArray[down][1]);
        downArrow.setFont(Font.font("Monospaced", FontWeight.BOLD, 15));
        downArrow.setTextFill(Color.LIGHTGRAY);
        leftArrow = new Label(String.valueOf(leftValue));
        leftArrow.setGraphic(imageViewArray[left][2]);
        leftArrow.setFont(Font.font("Monospaced", FontWeight.BOLD, 15));
        leftArrow.setTextFill(Color.LIGHTGRAY);
        rightArrow = new Label(String.valueOf(rightValue));
        rightArrow.setGraphic(imageViewArray[right][3]);
        rightArrow.setFont(Font.font("Monospaced", FontWeight.BOLD, 15));
        rightArrow.setTextFill(Color.LIGHTGRAY);
    }

    public ImageView makeArrows (String inputStream){
        InputStream inputStream1 = SiloGUI.class.getClassLoader().getResourceAsStream(inputStream);
        Image image = new Image(inputStream1);
        ImageView tempImageView = new ImageView(image);
        return tempImageView;
    }
}
