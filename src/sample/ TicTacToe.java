package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Random;


public class TicTacToe {

    private int turnVal; // variable for determining whether x or o goes first.
    private String turn; // string that references the current turn of the game.
    private final String[][] board; // intalizing a 2d array.

    private final Pane sPane = new Pane(); // creating a new pane for adding new nodes later.

    // Hashmap to assign an integer,k, to a rectangle object, v.
    private final HashMap<Integer, Rectangle> recs = new HashMap<>();

    // Initialized to display a visual of current player turn.
    private final Image xImg = new Image("/Imgs/x.png");
    private final Image oImg = new Image("/Imgs/o.png");
    private final ImageView xImages = new ImageView(xImg);
    private final ImageView oImages = new ImageView(oImg);


    // Constructor.
    TicTacToe(){
        // creates the board by traversing over the rows of the 2d array.
        board = new String[3][3];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                board[i][j] = "";
            }
            System.out.println(Arrays.deepToString(board));
        }

        // Radomizing which player goes first. Either x or o, 0 or 1.
        Random r = new Random();
        turnVal = r.nextInt(2);
        if (turnVal == 0){
            turn = "x";
        }
        else{
            turn = "o";
        }

        // Setting up the TicTacToe grid.
        //x lines
        Line line2 = new Line (860, 540, 1060, 540);
        line2.setStrokeWidth(5);
        Line line3 = new Line (860, 600, 1060, 600);
        line3.setStrokeWidth(5);

        //y lines
        Line line4 = new Line(930, 480, 930, 660);
        line4.setStrokeWidth(5);
        Line line5 = new Line(1000, 480, 1000, 660);
        line5.setStrokeWidth(5);

        // Translating the player turn images.
        xImages.setTranslateX(500);
        xImages.setTranslateY(400);
        xImages.setFitWidth(75);
        xImages.setFitHeight(75);
        xImages.setVisible(false);

        oImages.setTranslateX(500);
        oImages.setTranslateY(400);
        oImages.setFitWidth(75);
        oImages.setFitHeight(75);
        oImages.setVisible(false);

        // Adding all previous nodes to the pane.
        sPane.getChildren().addAll(line2, line3, line4, line5, xImages, oImages);

        // Creating the dimension of reach rectangle between the grid.
        // 3x3; therefore 9 rectangles sized proportionally.
        final int xVal = 860;
        int yVal = 480;
        int xChange = 70; // The difference between each rectangle in terms of x.

        int mul = 0; // Counter

        // Traversing over a for-loop 9 times to create 9 rectangles.
        for (int i = 0; i < 9; i++){
            // Setting the rectangle dimensions.
            Rectangle rec = new Rectangle(xVal + (xChange * mul),yVal, 67, 57);
            rec.setFill(Color.rgb(0, 0, 0, 0.5));

            mul++; // Incrementing the multiple to translate the rectangles accordingly.
            if ((i+1) % 3 == 0){ // Since the structure is 3x3, we mod by 3.
                yVal += 60; // The difference between each rectangle in terms of y.
                mul = 0; // Setting the multiple back to zero after every 3rd iteration.
            }
            recs.put(i, rec); // Assigning the rectangle an integer and appending it into the hashmap.
            sPane.getChildren().add(rec); // Adding the rectangles on to the pane, the UI.
        }
    }


    // Algorithm for the game.
    public void run(){
        // If statement calling methods to determine if there is a winner or tie.
        if (checkWin() || !checkMaxed()) {

            // Hiding and showing images depending on the player turn.
            if (turnVal == 0) {
                System.out.println("x img");
                xImages.setVisible(true);
                oImages.setVisible(false);
            } else {
                System.out.println("o img");
                xImages.setVisible(false);
                oImages.setVisible(true);
            }

            // Traversing 9 times to check all 9 rectangles for clicks based on their positioning.
            for (int j = 0; j < recs.size(); j++) {
                int finalJ = j;

                // if a rectangle is clicked, we execute the following commands.
                recs.get(j).setOnMouseClicked(mouseEvent -> {

                    // Displays the coordinate of the clicked rectangle on the console.
                    System.out.println(recs.get(finalJ).getX() + " : " + recs.get(finalJ).getY());

                    // Calls the hashmap with its respective integer to call the checkPos method.
                    if (checkPos(recs.get(finalJ))) {

                        // Creating new images after every round to ensure that the images remain on the board.
                        Image turnImg;

                        // Conditional to differentiate which player's turn it is and adding their image respectively.
                        if (turn.equalsIgnoreCase("x")) {
                            turnImg = new Image("/Imgs/x.png");
                        } else {
                            turnImg = new Image("/Imgs/o.png");
                        }

                        // Translating their image to the rectangle they clicked on.
                        ImageView newImg = new ImageView(turnImg);
                        newImg.setTranslateX(recs.get(finalJ).getX());
                        newImg.setTranslateY(recs.get(finalJ).getY());
                        newImg.setFitHeight(50);
                        newImg.setFitWidth(50);

                        // Adding their new image to the pane.
                        sPane.getChildren().addAll(newImg);
                    }
                    // Checking the 2d array on console.
                    System.out.println(Arrays.deepToString(board));
                });
            }
        }

    }


    // A void method to handle clicks, but also checking which player has won through console.
    public void handleMouseClick(){
        if (checkDiag() || checkRow() || checkCol()){
            System.out.println("Player " + turn + " has won!");
        }

    }


    // Method that takes a rectangle as parameter.
    private boolean checkPos(Rectangle rec){
        // Storing the rectangle's parameters.
        String cords = rec.getX() + " : " + rec.getY();

        // Sorts out the turn for the following player.
        if (turnVal == 0){
            turn = "x";
            turnVal++;
        }
        else {
            turn = "o";
            turnVal--;
        }

        // Switch statement with 9 cases representing
        // 9 rectangles to determine whether which rectangles has been occupied.
        boolean valid = false;
        switch (cords){
            //Top row
            case "860.0 : 480.0" : if (board[0][0].equals("")){ board[0][0] = turn; valid = true;} break;
            case "930.0 : 480.0" : if (board[0][1].equals("")){ board[0][1] = turn; valid = true;} break;
            case "1000.0 : 480.0" : if (board[0][2].equals("")){ board[0][2] = turn; valid = true;} break;
            //Middle row
            case "860.0 : 540.0" : if (board[1][0].equals("")){ board[1][0] = turn; valid = true;} break;
            case "930.0 : 540.0" : if (board[1][1].equals("")){ board[1][1] = turn; valid = true;} break;
            case "1000.0 : 540.0" : if (board[1][2].equals("")){ board[1][2] = turn; valid = true;} break;
            //Bottom row
            case "860.0 : 600.0" : if (board[2][0].equals("")){ board[2][0] = turn; valid = true;} break;
            case "930.0 : 600.0" : if (board[2][1].equals("")){ board[2][1] = turn; valid = true;} break;
            case "1000.0 : 600.0" : if (board[2][2].equals("")){ board[2][2] = turn; valid = true;} break;
        }
        return valid;
    }


    // getter for rooting.
    public Pane getRoot(){
        return sPane;
    }


    // getter to determine the current player turn.
    public String getTurn()
    {
        return this.turn;
    }


    //returns a boolean that returns true if a row has three X or O's in a row
    public boolean checkRow(){
        // for each row in board, check if all values are the same.
        // returns true if they are the same, false if not.
        for (String[] s : board) {
            if (s[0].equals(s[1]) && s[1].equals(s[2])) {
                if (!(s[1].equals(""))) {
                    System.out.println("Row Check");
                    return true;
                }
            }
        }
        return false;
    }


    //returns a boolean that returns true if a columns has three X or O's
    public boolean checkCol(){
        // Traverses over the columns by going row to row through one iteration and repeating 3x
        // While incrementing the following value to get the columns.
        for (int i = 0; i < board.length; i++){
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                if (!(board[0][i].equals(""))) {
                    System.out.println("Col Check");
                    return true;
                }
            }

        }
        return false;
    }


    // returns a boolean that returns true if either diagonal has three X or O's
    public boolean checkDiag(){
        // if the center is empty, don't bother checking.
        if (!(board[1][1].equals(""))) {
            // Left diagonal
            if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
                System.out.println("Diag Check");
                return true;
            }
            // right diagonal
            if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
                System.out.println("Diag Check");
                return true;
            }
        }
        return false;
    }

    // checks all three possible winning methods to determine if a player has won.
    public boolean checkWin(){
        if (checkDiag() || checkCol() || checkRow()){
            return false;
        }
        return true;
    }

    // iterates over the entire 2d array to check if there are empty spaces.
    // if not, return true--maxed out board.
    public boolean checkMaxed(){
        for (int i = 0; i < board.length; i++){
            for (int k = 0; k < board.length; k++){
                if (board[i][k].equals("")){
                    return false;
                }
            }
        }
        return true;
    }

}





