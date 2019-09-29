package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.ToggleSwitch;
import sample.utils.AgentPercept;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class Main extends Application {

    AgentPercept agentPercept = new AgentPercept();
    GridPane gp = new GridPane();
    String worldEnv[][];
    Circle circle =new Circle();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Random rand = new Random();
        Color[] colors = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED};

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        BorderPane root = new BorderPane();
        BorderPane bpane = new BorderPane();
        Label gameName = new Label("Wumpus World!");
        gameName.setTextFill(Color.valueOf("white"));

//        ToggleSwitch tg = new ToggleSwitch();
//        Label start = new Label("Start");
//        start.setTextFill(Color.WHITE);
//        GridPane topgp =new GridPane();
//        GridPane.setRowIndex(start,0);
//        GridPane.setRowIndex(tg,0);
//        GridPane.setColumnIndex(start,0);
//        GridPane.setColumnIndex(tg,1);
//        topgp.setStyle("-fx-padding: 8 15 15 15;-fx-font-size:15px;");
//        topgp.setVgap(2);
//        topgp.setHgap(2);
//
//        topgp.getChildren().addAll(start,tg);
//        bpane.setRight(topgp);
        Button startBtn = new Button("Start");
        bpane.setRight(startBtn);


        bpane.setLeft(gameName);
        bpane.setStyle("-fx-background-color:darkslateblue;-fx-padding: 10px;\n" +
                "    -fx-font-size: 20px;-fx-color:white");
        root.setTop(bpane);

        VBox vbox = new VBox();

        vbox.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-border-width: 1;\n" +
                "    -fx-border-color: transparent #E8E8E8 transparent transparent;\n" +
                "    -fx-background-color: #E8E8E8;");

        gp.setStyle("-fx-padding: 8 15 15 15");
        gp.setVgap(2.5);
        gp.setHgap(2.5);

        WmpsWorld myWorld = new WmpsWorld();
        worldEnv = myWorld.generateWorld();
        Map<String, Integer> list = new HashMap<String, Integer>();
        list.put("", 0);
        list.put("W", 1);
        list.put("S", 2);
        list.put("P", 3);
        list.put("B", 4);
        list.put("G", 5);
        list.put("Gl", 6);


        int[][] listOfStuff = new int[10][10];

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                boolean[] status = {false, false, false, false, false, false, false};

                Rectangle rec = new Rectangle();
                rec.setWidth(50);
                rec.setHeight(50);
                rec.setFill(Color.valueOf("beige"));
                String[] input = worldEnv[row][col].split(" ");
                String output = "";

                for (int i = 0; i < input.length; i++) {
                    if (status[list.get(input[i])] == false) {
                        output = output + " " + input[i];
                        status[list.get(input[i])] = true;
                    }
                }

                Text text = new Text(output);
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                GridPane.setRowIndex(text, row);
                GridPane.setColumnIndex(text, col);
                text.setVisible(true);

//


                if (text.getText().equals(" ")) {
                    listOfStuff[row][col] = 1;
                } else listOfStuff[row][col] = 2;
//                text.setVisible(false);
//                gp.getChildren().addAll(rec, text, circle);
                gp.getChildren().addAll(rec, text);
            }
        }
        System.out.println(Arrays.deepToString(listOfStuff));

//        gp.setGridLinesVisible(true);

//        circle.setUserData("Player");
        circle.setFill(Color.valueOf("Red"));
        circle.setRadius(8);
        circle.setUserData("Player");
        GridPane.setRowIndex(circle,agentPercept.getCurrY());
        GridPane.setColumnIndex(circle,agentPercept.getCurrX());
        gp.getChildren().add(circle);
        GridPane.setMargin(circle, new Insets(12,0,0,17));
        vbox.getChildren().add(gp);
        root.setLeft(vbox);
        primaryStage.setTitle("Wumpus World!");
        primaryStage.setScene(new Scene(root, 750, 630));
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(700);
        primaryStage.show();


        startSimulation();
    }



    private void startSimulation(){
        changePlayerPosition(0, 1);
        new Thread(()->{ //use another thread so long process does not block gui

            try {Thread.sleep(1000);} catch (InterruptedException ex) { ex.printStackTrace();}
            changePlayerPosition(0, 2);
        }).start();
    }


    private void changePlayerPosition( int x, int y) {

        System.out.println("POS");
        agentPercept.setCurrX(x);
        agentPercept.setCurrY(y);


        GridPane.setRowIndex(circle, agentPercept.getCurrY());
        GridPane.setColumnIndex(circle, agentPercept.getCurrX());
        agentPercept.addKnowledgeFromPercept(worldEnv[x][y]);

    }

//    public void movePlayer(int x, int y){
//        Node player = getByUserData(gp, "Player");
//        TranslateTransition tt = new TranslateTransition(Duration.millis(500), circle);
//        tt.setFromX(agentPercept.getCurrX());
//        tt.setFromY(agentPercept.getCurrY());
//        tt.setByX(0);
//        tt.setByY(50);
//
//        tt.play();
//        tt.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                changePlayerPosition(x, y);
//            }
//        });
//
//    }

    public static void main(String[] args) {
        launch(args);
    }

    private Node getByUserData(Parent parent, Object data) {

        for (Node n : parent.getChildrenUnmodifiable()) {
            if (data.equals(n.getUserData())) {
                return n;
            } else return null;

        }
        return null;
    }
}


