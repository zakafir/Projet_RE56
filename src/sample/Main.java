package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
import javafx.util.Duration;
import jdk.internal.util.xml.impl.Pair;

import java.io.File;
import java.util.Optional;

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

        // initialize bts 1
        Rectangle bts1 = (Rectangle) root.lookup("#bts1");
        //Image bts1Image = new Image("../assets/antenna.png");
        //bts1.setFill(new ImagePattern(bts1Image));


        Line networkLink = new Line();
        networkLink.setStartX(myDevice.getCenterX());
        //networkLink.setStartY(myDevice.getCenterY());
        networkLink.setEndX(bts1.getX());
        //networkLink.setEndY(bts1.getY());


        Circle testCircle = new Circle(250,300,300);

        AnchorPane anchorPane = (AnchorPane) root.lookup("#Content");
        anchorPane.getChildren().addAll(networkLink);
        //System.out.println(anchorPane.getChildren().add(networkLink));
        //anchorPane.getChildren().add(networkLink)

        Dialog dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField gainTrInput = new TextField();
        gainTrInput.setPromptText("Gain transmitter");

        TextField powerTrInput = new TextField();
        powerTrInput.setPromptText("Power transmitter");

        grid.add(new Label("Gain Transmitter:"),0,0);
        grid.add(gainTrInput, 1 ,0);
        grid.add(new Label("Power Transmitter:"),0,1);
        grid.add(powerTrInput, 1 ,1);

        dialog.getDialogPane().setContent(grid);

        createLink(myDevice, bts1);


        networkLink.startXProperty().bind(bts1.widthProperty().add(bts1.widthProperty()));
        networkLink.startYProperty().bind(bts1.widthProperty().add(bts1.widthProperty()));
        networkLink.endXProperty().bind(myDevice.centerXProperty().add(myDevice.translateXProperty()));
        networkLink.endYProperty().bind(myDevice.centerYProperty().add(myDevice.translateYProperty()));
        networkLink.setStroke(Color.RED);




        Button btsButton = (Button) root.lookup("#btsButton");
        btsButton.setOnAction((event) -> {
            System.out.print("bts button clicked!");
            //Circle myBTS = new Circle(250,250,250 , Color.YELLOW);
            //root.getChildrenUnmodifiable().add(1 , myBTS);
            createLink(myDevice, bts1);
            //alert.showAndWait();
            Optional<String> result = dialog.showAndWait();

        });


    }
    // function to create a network link between device and BTS
    public void createLink(Circle myDevice , Rectangle bts1) {
        Line networkLink = new Line();
        networkLink.setStartX(myDevice.getCenterX());
        networkLink.setStartY(myDevice.getCenterY());
        networkLink.setEndX(bts1.getX());
        networkLink.setEndY(bts1.getY());

    }

    // Function to compute distance between device and BTS
    public int computeDistance(){
    return 1;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
