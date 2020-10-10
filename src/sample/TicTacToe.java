package sample;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

import java.util.HashMap;
import java.util.List;


public class TicTacToe {

    //I could randomize which, but for now I'll stick to this.
    private int turnVal = 0;
    private String turn = "x";
    private String[][] board;

    private Pane sPane = new Pane();

    private HashMap<Integer, Rectangle> recs = new HashMap<>();

    private int arrayID = 0;




    TicTacToe(){

        //creates the board in array form.
        board = new String[3][3];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                board[i][j] = "";
            }
        }
        System.out.println(Arrays.deepToString(board));

        //x lines
        Line line2 = new Line (860, 540, 1060, 540);
        line2.setStrokeWidth(5);

        Line line3 = new Line (860, 600, 1060, 600);
        line3.setStrokeWidth(5);

        sPane.getChildren().addAll(line2, line3);

        //y lines
        Line line4 = new Line(930, 480, 930, 660);
        line4.setStrokeWidth(5);

        Line line5 = new Line(1000, 480, 1000, 660);
        line5.setStrokeWidth(5);

        sPane.getChildren().addAll(line4, line5);


        final int xVal = 860;
        int yVal = 480;
        int xChange = 70;

        int mul = 0;
        for (int i = 0; i < 9; i++){
            Rectangle rec = new Rectangle(xVal + (xChange * mul),yVal, 67, 57);
            rec.setFill(Color.rgb(0, 0, 0, 0.5));


            mul++;
            if ((i+1) % 3 == 0){
                yVal += 60;
                mul = 0;
            }

            recs.put(i, rec);
            sPane.getChildren().add(rec);
        }
    }

    public void run(){

        for (int j = 0; j < recs.size(); j++){
            int finalJ = j;

            recs.get(j).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(recs.get(finalJ).getX() + " : " + recs.get(finalJ).getY());

                    //!exists(arrayID, "x", board) && !exists(arrayID, "o", board)

                    if (checkPos(recs.get(finalJ))){

                        Image turnImg;

                        if (turn.equalsIgnoreCase("x")){
                            turnImg = new Image("/Imgs/x.png");
                        }
                        else{
                            turnImg = new Image("/Imgs/o.png");
                        }

                        ImageView newImg = new ImageView(turnImg);
                        newImg.setTranslateX(recs.get(finalJ).getX());
                        newImg.setTranslateY(recs.get(finalJ).getY());
                        newImg.setFitHeight(50);
                        newImg.setFitWidth(50);



                        sPane.getChildren().addAll(newImg);
                    }
                    else{
                        System.out.println("Already placed.");
                    }



                    if ((finalJ +1) % 3 == 0){
                        arrayID++;
                    }

                    System.out.println(Arrays.deepToString(board));
                }
            });
        }



    }


    public void handleMouseClick(){
        if (checkDiag() || checkRow() || checkCol()){
            System.out.println("Player " + turn + " has won!");
        }


    }

    private boolean checkPos(Rectangle rec){
        String cords = rec.getX() + " : " + rec.getY();


        if (turnVal == 0){
            turn = "x";
            turnVal++;
        }
        else {
            turn = "0";
            turnVal--;
        }

        boolean valid = true;
        //Sorts out which.
        switch (cords){
            //Top row
            case "860.0 : 480.0" : if (board[0][0].equals("")); board[0][0] = turn; break;
            case "930.0 : 480.0" : if (board[0][1].equals("")); board[0][1] = turn; break;
            case "1000.0 : 480.0" : if (board[0][2].equals("")); board[0][2] = turn; break;
            //Middle row
            case "860.0 : 540.0" : if (board[1][0].equals("")); board[1][0] = turn; break;
            case "930.0 : 540.0" : if (board[1][1].equals("")); board[1][1] = turn; break;
            case "1000.0 : 540.0" : if (board[1][2].equals("")); board[1][2] = turn; break;
            //Bottom row
            case "860.0 : 600.0" : if (board[2][0].equals("")); board[2][0] = turn; break;
            case "930.0 : 600.0" : if (board[2][1].equals("")); board[2][1] = turn; break;
            case "1000.0 : 600.0" : if (board[2][2].equals("")); board[2][2] = turn; break;

            default: valid = false; break;
        }

        return valid;
    }


    public static boolean exists(int row, String value, String[][] array) {
        if(row >= array.length) return false;
        List<String> rowVals = Arrays.asList(Arrays.asList(array).get(row));

        if(rowVals.contains(value)) return true;

        return false;
    }



    public Pane getRoot(){
        return sPane;
    }

    //this method returns the current turn
    public String getTurn()
    {
        return this.turn;
    }

    /*This method prints out the board array on to the console
     */
    public void printBoard()
    {
        for (String[] strings : board) {
            for (int k = 0; k < board[0].length; k++) {
                System.out.print(strings[k]);
            }
            System.out.println();
        }
    }

    //turns true if space row, col is a valid space
    public boolean pickLocation(int row, int col)
    {
        return board[row + 1][col + 1].equals("- ");
    }

    //returns a boolean that returns true if a row has three X or O's in a row
    public boolean checkRow()
    {
        for (String[] strings : board) {
            if(exists(0,null,board)) {
                if (strings[0].equals(strings[1]) && strings[1].equals(strings[2])) {
                    return true;
                }
            }
        }


        return false;
    }

    //returns a boolean that returns true if a col has three X or O's
    public boolean checkCol()
    {
        for (int i = 0; i < board.length; i++){
            if (board[0][i] != null) {
                if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                    return true;
                }

            }
        }
        return false;
    }

    //returns a boolean that returns true if either diagonal has three X or O's
    public boolean checkDiag()
    {
        if (board[0][0] != null || board[0][2] != null) {
            if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
                return true;
            }
            if (board[0][2].equals(board[1][1]) && board[2][2].equals(board[2][0])) {
                return true;
            }


        }

        return false;
    }

    //This method returns a boolean that checks if someone has won the game
    public boolean checkWin()
    {
        return !checkDiag() && !checkCol() && !checkRow();
    }


}


