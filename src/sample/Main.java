package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();

        // linking circle with its java object
        Circle myDevice = (Circle) root.lookup("#myDevice");
        Line myLine = (Line) root.lookup("#mySegment");


        // Defining animation
        double secondsPerCompleteCycle = 10;
        // Button was clicked, do something...
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(secondsPerCompleteCycle));
        transition.setFromX(myLine.getStartX());
        //transition.setFromY(myLine.getStartY());

        //TimeLine definition
        //Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(secondsPerCompleteCycle)));


        transition.setToX(myLine.getEndX());
        //transition.setToY(myLine.getEndY());
        transition.setNode(myDevice);

        //creating  play button logic
        Button playButton = (Button) root.lookup("#playButton");

        playButton.setOnAction((event) -> {
            transition.play();
            //timeline.play();
        });

        //creating pause button logic
        Button pauseButton = (Button) root.lookup("#pauseButton");
        pauseButton.setOnAction((event) -> {
            transition.pause();
        });

        //creating stop button logic
        Button stopButton = (Button) root.lookup("#stopButton");
        stopButton.setOnAction((event) -> {
            transition.stop();
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
