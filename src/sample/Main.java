package sample;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler {

    private Scene scene2;

    @Override
    public void start(Stage primaryStage) {

        // Begins the stack for UI structuring.
        // Setting the ID for the root for stylesheet purposes.
        StackPane root = new StackPane();
        root.setId("root");

        // Adding the Logo the GUI.
        Image logo = new Image("/Imgs/ticIcon.png");
        ImageView logoImg = new ImageView(logo);
        logoImg.setTranslateY(-130);    // Positioning

        // This image will act as an even handler--sharing the characteristics of a button.
        Image play = new Image("/Imgs/ticPlay.png");
        ImageView playImg = new ImageView(play);
        playImg.setTranslateY(75);

        // Adding previous nodes into the UI.
        root.getChildren().addAll(logoImg, playImg);

        // Creating the dimensions for the scene.
        // The variable is placed here for replaying purposes.
        Scene scene = new Scene(root, 800, 450);


        /* Go to lines 108 for a chronological order of the way the program is ran in user perspective.
           Event handler for when the user clicks on the "Play" Image/Button.
           Sends the user to a second scene where they will play TicTac toe.
           TicTacToe class will handle the gameplay.
         */
        playImg.addEventHandler(MouseEvent.MOUSE_CLICKED, MouseEvent -> {
            TicTacToe game = new TicTacToe(); // Creates the a TicTacToe object.
            Pane layout = game.getRoot(); // New pane for a free-styled structured scene.
            layout.setId("layout"); // Setting the ID again for style-sheet purposes.

            scene2 = new Scene(layout, 800, 450); // Dimensions for scene #2
            // References the CSS file to set the background for scene #2.
            scene2.getStylesheets().addAll(this.getClass().getResource("styles.css").toExternalForm());

            // Changes the primary scene to scene #2.
            primaryStage.setScene(scene2);
            primaryStage.setFullScreen(true); // Fullscreen

            // Overlays for scene #2
            Image turn = new Image("/Imgs/turn.png");
            ImageView turnImg = new ImageView(turn);
            turnImg.setTranslateY(400);
            turnImg.setTranslateX(150);

            ImageView newLogo = new ImageView(logo);
            newLogo.setTranslateX(750);
            newLogo.setTranslateY(200);
            layout.getChildren().addAll(turnImg, newLogo);

            /* User inputs are set as mouse clicks.
               Calls the run() method from the TicTacToe class.
               "handleMouseClick()" is simply there to check whether a player has won through the console.
             */
            scene2.setOnMouseClicked( e-> {
                game.handleMouseClick();
                game.run();


                // Initializing images for display after win or tie.
                Image winner;
                ImageView winnerImg;

                // Calls methods within "game" to check whether a player has won or tied.
                if (!game.checkWin() || game.checkMaxed()) {

                    // Conditional for winning.
                    if (!game.checkWin()) {
                        if (game.getTurn().equals("x")) {
                            winner = new Image("/Imgs/winnerX.png");
                        } else {
                            winner = new Image("/Imgs/winnerO.png");
                        }
                    } //Conditional for a draw game.
                    else{
                        winner = new Image("/Imgs/draw.png");
                    }

                    // After determining the image, it is added and translated.
                    winnerImg = new ImageView(winner);
                    winnerImg.setTranslateX(700);
                    winnerImg.setTranslateY(300);

                    // Creating a new image button for replaying.
                    Image home = new Image("/Imgs/HomeButton.png");
                    ImageView homeImg = new ImageView(home);
                    homeImg.setTranslateX(1500);
                    homeImg.setTranslateY(700);

                    // Adding previous nodes to a new pane.
                    layout.getChildren().addAll(winnerImg, homeImg);

                    // Re-directs to the homepage for replaying.
                    homeImg.setOnMouseClicked(mouseEvent  -> primaryStage.setScene(scene));
                    primaryStage.setFullScreen(true);
                }
            });
        });

        // References the CSS file to setup the background for scene #1.
        scene.getStylesheets().addAll(this.getClass().getResource("styles.css").toExternalForm());

        // Setting primary scene to scene #1.
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true); // Fullscreen.
        primaryStage.setTitle("TicTacToe");
        primaryStage.show(); // Display scene #1.
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(Event event) {
    }
}
