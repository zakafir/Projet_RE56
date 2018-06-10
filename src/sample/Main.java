package sample;

//import com.oracle.tools.packager.Log;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Controller.Calcul;
import javafx.scene.input.MouseEvent;

import sample.Model.BSC;
import sample.Model.BTS;
import sample.Model.Device;
import sample.Model.ConsoleArea;

import java.util.*;

public class Main extends Application {
    public static int count = 0;
    // Cordonnées repère
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    boolean appLaunched = false;
    boolean appPause = false;

    public static int btsRank = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        primaryStage.setTitle("Handover RE56");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();

        // List of BTS
        ArrayList<BTS> btsList = new ArrayList<>();
        HashMap<String, Float> distances = new HashMap<>();
        Map<String, Double> receivingPowerMap = new HashMap<String, Double>();


        // instanciating textarea object
        TextArea consoleTextArea = (TextArea) root.lookup("#consoleTextArea");
        consoleTextArea.setStyle("-fx-control-inner-background:#000000; -fx-font-family: Consolas; -fx-highlight-fill: #00ff00; -fx-highlight-text-fill: #000000; -fx-text-fill: #00ff00; ");
        ConsoleArea appConsole = new ConsoleArea(consoleTextArea);

        // linking circle with its java object

        Device myDevice = new Device();
        Circle deviceShape = (Circle) root.lookup("#myDevice");
        myDevice.setShape(deviceShape);
        Line myLine = (Line) root.lookup("#mySegment");


        // Defining animation
        double secondsPerCompleteCycle = 10;
        // Button was clicked, do something...
        TranslateTransition transition = new TranslateTransition();
        Duration duration = Duration.seconds(secondsPerCompleteCycle);
        transition.setDuration(duration);
        transition.setFromX(myLine.getStartX());
        //transition.setFromY(myLine.getStartY());

        //TimeLine definition
        //Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(secondsPerCompleteCycle)));


        transition.setToX(myLine.getEndX());
        //transition.setToY(myLine.getEndY());
        transition.setNode(deviceShape);

        //creating  play button logic
        Button playButton = (Button) root.lookup("#playButton");

        playButton.setOnAction((event) -> {
            transition.play();
            appLaunched = true;
        });

        //creating stop button logic
        Button stopButton = (Button) root.lookup("#stopButton");
        stopButton.setOnAction((event) -> {
            transition.stop();
            appLaunched = false;
        });

        // initialize bts 1
        Rectangle bts1 = (Rectangle) root.lookup("#bts1");
        BSC bsc = new BSC();
        BTS firstBts = new BTS("First BTS", 133.0, 12.0,
                3000L, 100, 3, bts1);
        double lamda1 = Calcul.lamda(firstBts.getFrequency());
        appConsole.printToConsole("lamda du BTS 1: " + lamda1);

        //Image bts1Image = new Image("../assets/antenna.png");
        //Image bts1Image = new Image("/assets/antenna.png");
        //bts1.setFill(new ImagePattern(bts1Image));


        Line networkLink = new Line();
        networkLink.setStartX(deviceShape.getCenterX());
        //networkLink.setStartY(myDevice.getCenterY());
        networkLink.setEndX(bts1.getX());
        //networkLink.setEndY(bts1.getY());


        Circle testCircle = new Circle(250, 300, 300);

        AnchorPane anchorPane = (AnchorPane) root.lookup("#Content");
        anchorPane.getChildren().addAll(networkLink);
        //System.out.println(anchorPane.getChildren().add(networkLink));
        //anchorPane.getChildren().add(networkLink)

        /* **** Creating Dialog box when clicking BTS button ***** */
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Adding BTS");
        dialog.setHeaderText("Please insert BTS properties");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField btsNameInput = new TextField();
        btsNameInput.setPromptText("Name of the BTS");

        TextField gainTrInput = new TextField();
        gainTrInput.setPromptText("Gain transmitter");

        TextField powerTrInput = new TextField();
        powerTrInput.setPromptText("Power transmitter");

        TextField antennaPosXIntput = new TextField();
        antennaPosXIntput.setPromptText("Antenna position X:");

        TextField antennaPosYIntput = new TextField();
        antennaPosYIntput.setPromptText("Antenna position Y:");

        TextField antennaFrequency = new TextField();
        antennaFrequency.setPromptText("Antenna Frequency:");

        TextField antennaCapcity = new TextField();
        antennaCapcity.setPromptText("Antenna Capacity:");

        grid.add(new Label("BTS Name"), 0, 0);
        grid.add(btsNameInput, 1, 0);

        grid.add(new Label("Position X:"), 0, 1);
        grid.add(antennaPosXIntput, 1, 1);

        grid.add(new Label("Position Y:"), 0, 2);
        grid.add(antennaPosYIntput, 1, 2);

        grid.add(new Label("Gain Transmitter:"), 0, 3);
        grid.add(gainTrInput, 1, 3);

        grid.add(new Label("Power Transmitter:"), 0, 4);
        grid.add(powerTrInput, 1, 4);

        grid.add(new Label("Antenna Frequency:"), 0, 5);
        grid.add(antennaFrequency, 1, 5);

        grid.add(new Label("Antenna Capacity:"), 0, 6);
        grid.add(antennaCapcity, 1, 6);


        ButtonType btsValidateButtonType = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btsValidateButtonType, ButtonType.CANCEL);

        // Enable/Disable validate button depending on whether values are entered
        Node validateButton = dialog.getDialogPane().lookupButton(btsValidateButtonType);
        validateButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        antennaPosXIntput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!antennaPosYIntput.getText().trim().isEmpty())
                validateButton.setDisable(newValue.trim().isEmpty());

        });
        antennaPosYIntput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!antennaPosXIntput.getText().trim().isEmpty())
                validateButton.setDisable(newValue.trim().isEmpty());
        });


        dialog.getDialogPane().setContent(grid);

        // when validate button of bts form is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == btsValidateButtonType) {
                btsRank = btsList.size()+1;
                BTS addedBTS = new BTS(btsNameInput.getText(),
                        Integer.parseInt(antennaPosXIntput.getText()),
                        Integer.parseInt(antennaPosYIntput.getText()),
                        Double.parseDouble(gainTrInput.getText()),
                        Double.parseDouble(powerTrInput.getText()),
                        Long.parseLong(antennaFrequency.getText()),
                        Integer.parseInt(antennaCapcity.getText()),
                        btsRank);

                Rectangle btsShape = new Rectangle();
                btsShape.setX(addedBTS.getPositionX());
                btsShape.setY(addedBTS.getPositionY());
                btsShape.setWidth(50);
                btsShape.setHeight(90);
                btsShape.setArcHeight(5);
                btsShape.setArcWidth(5);
                btsShape.setStroke(Color.BLACK);
                btsShape.setFill(Color.valueOf("#1f93ff00"));
                // setting bts image
                //Image bts1Image = new Image("file:antenna.png",100,0, false , false);
                //btsShape.setFill(new ImagePattern(bts1Image));

                //event click sur shape
                EventHandler<MouseEvent> btsShapeOnMousePressedEventHandler =
                        new EventHandler<MouseEvent>() {

                            @Override
                            public void handle(MouseEvent t) {
                                orgSceneX = t.getSceneX();
                                orgSceneY = t.getSceneY();
                                orgTranslateX = ((Rectangle) (t.getSource())).getTranslateX();
                                orgTranslateY = ((Rectangle) (t.getSource())).getTranslateY();
                            }
                        };

                // drag event on shape
                EventHandler<MouseEvent> btsShapeOnMouseDraggedEventHandler =
                        new EventHandler<MouseEvent>() {

                            @Override
                            public void handle(MouseEvent t) {
                                if (!appLaunched) {
                                    double offsetX = t.getSceneX() - orgSceneX;
                                    double offsetY = t.getSceneY() - orgSceneY;
                                    double newTranslateX = orgTranslateX + offsetX;
                                    double newTranslateY = orgTranslateY + offsetY;

                                    ((Rectangle) (t.getSource())).setTranslateX(newTranslateX);
                                    ((Rectangle) (t.getSource())).setTranslateY(newTranslateY);
                                }
                            }
                        };

                btsShape.setOnMousePressed(btsShapeOnMousePressedEventHandler);
                btsShape.setOnMouseDragged(btsShapeOnMouseDraggedEventHandler);


                addedBTS.setShape(btsShape);
                // adding a BTS to the BTS list
                btsList.add(addedBTS);


                anchorPane.getChildren().addAll(addedBTS.getShape());

                System.out.println(addedBTS);
                appConsole.printToConsole("added BTS");
                appConsole.printToConsole("added BTS 2");

            }
            return null;
        });
        //createLink(deviceShape, bts1);


        networkLink.startXProperty().bind(bts1.xProperty().add(bts1.layoutXProperty()));
        networkLink.startYProperty().bind(bts1.yProperty().add(bts1.layoutYProperty()));

        networkLink.endXProperty().bind(deviceShape.layoutXProperty().add(deviceShape.translateXProperty()));
        networkLink.endYProperty().bind(deviceShape.layoutYProperty().add(deviceShape.translateYProperty()));

        networkLink.setStroke(Color.RED);


        Button btsButton = (Button) root.lookup("#btsButton");

        // event handler on BTS button click
        btsButton.setOnAction((event) -> {
            System.out.print("bts button clicked!");
            //Circle myBTS = new Circle(250,250,250 , Color.YELLOW);
            //root.getChildrenUnmodifiable().add(1 , myBTS);
            //createLink(deviceShape, bts1);
            //alert.showAndWait();
            Optional<String> result = dialog.showAndWait();

        });

        //creating pause button logic
        Button pauseButton = (Button) root.lookup("#pauseButton");
        pauseButton.setOnAction((event) -> {
            appPause = true;
            count++;
            transition.pause();

            distances.put("d1", Calcul.distance(
                    deviceShape.translateXProperty().getValue(),
                    deviceShape.translateYProperty().getValue(),
                    bts1.getLayoutX(),
                    bts1.getLayoutY()));

            bsc.setBts(btsList);
            System.out.println("BSC created");
            System.out.println("Elements: "+btsList.toString());
            System.out.println("--------------------------------------------------");
            System.out.println("distance between device and BTS 1 is : " + distances.get("d1"));
            System.out.println("Receiving power 1: " +
            appConsole.printToConsole("--------------------------------------------------");
            appConsole.printToConsole("distance between device and BTS 1 is : " + distances.get("d1"));
            appConsole.printToConsole("Receiving power 1: " +
                    Calcul.calculReceivingPower(firstBts, myDevice.getGainReceiving(), lamda1, distances.get("d1")));
            receivingPowerMap.put("pr1", Calcul.calculReceivingPower(firstBts, myDevice.getGainReceiving(), lamda1, distances.get("d1")));
            int compteur = 2;
            for (BTS b : btsList) {
                distances.put("d" + compteur, Calcul.distance(
                        deviceShape.translateXProperty().getValue(),
                        deviceShape.translateYProperty().getValue(),
                        b.getShape().getX(),
                        b.getShape().getY()));
                appConsole.printToConsole("distance between device and BTS " + compteur + " is: " + distances.get("d" + compteur));

                appConsole.printToConsole("Receiving power for BTS " + compteur + ": " +
                        Calcul.calculReceivingPower(b, myDevice.getGainReceiving(), Calcul.lamda(b.getFrequency())
                                , distances.get("d" + compteur)));
                receivingPowerMap.put("pr" + compteur, Calcul.calculReceivingPower(b,
                        myDevice.getGainReceiving(),
                        Calcul.lamda(b.getFrequency())
                        , distances.get("d" + compteur)));
                compteur++;

                appConsole.printToConsole("--------------------------------------------------");

                if (count == 3) {
//                    MapUtil.sortByValue(distances);
//                    Map.Entry<String,Double> entry = distances.entrySet().iterator().next();
//                    String key = entry.getKey();
//                    Double value = entry.getValue();
                    MyComparator comp = new MyComparator(receivingPowerMap);

                    Map<String, Double> newReceivingPowerMap = new TreeMap(comp);

                    newReceivingPowerMap.putAll(receivingPowerMap);


                    Map.Entry<String, Double> entry = newReceivingPowerMap.entrySet().iterator().next();
                    appConsole.printToConsole("The best Power receiving is : " + entry.getKey() + ", value :" + entry.getValue());
                    String[] numberOfBts = entry.getKey().split("pr");
                    String part2 = numberOfBts[1];
                    appConsole.printToConsole("BTS " + part2 + " choisi");

                    Line newNetworkLink = new Line();
                    newNetworkLink.setStartX(deviceShape.getCenterX());
                    newNetworkLink.setEndX(b.getShape().getX());

                    anchorPane.getChildren().addAll(newNetworkLink);


                    for(BTS newB : btsList) {
                        if (btsRank == newB.getBtsNumber()) {
                            System.out.println("BTS " + part2 + " choisi");
                            newNetworkLink.startXProperty().bind(newB.getShape().translateXProperty().add(newB.getShape().layoutXProperty()));
                            newNetworkLink.startYProperty().bind(newB.getShape().translateYProperty().add(newB.getShape().layoutYProperty()));

                            newNetworkLink.endXProperty().bind(deviceShape.layoutXProperty().add(deviceShape.translateXProperty()));
                            newNetworkLink.endYProperty().bind(deviceShape.layoutYProperty().add(deviceShape.translateYProperty()));


                            networkLink.setStroke(Color.LIGHTGRAY);
                            newNetworkLink.setStroke(Color.BLACK);

                            System.out.println("FIN DU PROGRAMME");

                        }
                    }
                    break;
                    /*
                     * todo
                     * add a pop up here, to show the chosen bts, and stop the program after 3 pauses
                     * add the console panel
                     * add BTS info's Rectangle using a Text (using hover)
                     * BSC can manage different BTS
                     * */

                }
            }
        });
    }
}
