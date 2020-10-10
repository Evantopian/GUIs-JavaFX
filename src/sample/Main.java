package sample;
import com.sun.javafx.iio.ImageStorage;
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

import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application implements EventHandler {

    private Scene scene, scene2;


    @Override
    public void start(Stage primaryStage) throws Exception{

        StackPane root = new StackPane();
        root.setId("root");

        int pos;

        Image logo = new Image("/Imgs/ticIcon.png");
        ImageView logoImg = new ImageView(logo);

        logoImg.setTranslateY(-130);

        Image play = new Image("/Imgs/ticPlay.png");
        ImageView playImg = new ImageView(play);

        playImg.setTranslateY(75);

        root.getChildren().addAll(logoImg, playImg);


        TicTacToe game = new TicTacToe();
        Pane layout = game.getRoot();
        layout.setId("layout");


        // 0 = continue playing, 1 = game has stopped, 2 = stop application.
        playImg.addEventHandler(MouseEvent.MOUSE_CLICKED, MouseEvent -> {


                primaryStage.setScene(scene2);

                primaryStage.setFullScreen(true);


                Image turn = new Image("/Imgs/turn.png");
                ImageView turnImg = new ImageView(turn);
                turnImg.setTranslateY(400);
                turnImg.setTranslateX(150);

                ImageView newLogo = new ImageView(logo);
                newLogo.setTranslateX(750);
                newLogo.setTranslateY(200);

                layout.getChildren().addAll(turnImg, newLogo);

                game.run();

                Image winner = new Image("/Imgs/winnerx.png");
                ImageView winnerImg = new ImageView(winner);

                Image xImg = new Image("/Imgs/x.png");
                ImageView xImages = new ImageView(xImg);
                xImages.setTranslateX(500);
                xImages.setTranslateY(400);
                xImages.setFitWidth(75);
                xImages.setFitHeight(75);

                winnerImg.setTranslateX(830);
                winnerImg.setTranslateY(350);

                layout.getChildren().addAll(winnerImg, xImages);

                scene2.setOnMouseClicked( e-> {
                        game.handleMouseClick();
                });

        });

        scene = new Scene(root, 800, 450);
        scene.getStylesheets().addAll(this.getClass().getResource("styles.css").toExternalForm());

        scene2 = new Scene(layout, 800, 450);
        scene2.getStylesheets().addAll(this.getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("TicTacToe");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(Event event) {

    }
}
