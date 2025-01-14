package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.AI.PropositionalLogicResolution;
import sample.utils.AgentPercept;
import sample.utils.WmpsWorld;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main extends Application {

    AgentPercept agentPercept = new AgentPercept();
    GridPane gp = new GridPane();
    String worldEnv[][];
    Circle circle =new Circle();
    PropositionalLogicResolution propositionalLogicResolution = new PropositionalLogicResolution();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Random rand = new Random();
        Color[] colors = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED};

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        BorderPane root = new BorderPane();
        BorderPane bpane = new BorderPane();
        Label gameName = new Label("Wumpus World!");
        gameName.setTextFill(Color.valueOf("white"));

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

//        gp.setGridLinesVisible(true);

//        circle.setUserData("Player");
        circle.setFill(Color.valueOf("Red"));
        circle.setRadius(8);
        circle.setUserData("Player");
        GridPane.setRowIndex(circle,agentPercept.getCurrRow());
        GridPane.setColumnIndex(circle,agentPercept.getCurrCol());
        gp.getChildren().add(circle);
        GridPane.setMargin(circle, new Insets(12,0,0,17));
        vbox.getChildren().add(gp);
        root.setLeft(vbox);
        primaryStage.setTitle("Wumpus World!");
        primaryStage.setScene(new Scene(root, 750, 630));
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(700);
        primaryStage.show();
        changePlayerPosition(0, 0);
        agentPercept.getKnowledgeBaseSingleton().printKB();

        startSimulation();

    }



    private void startSimulation(){
        new Thread(()->{ //use another thread so long process does not block gui
            changePlayerPosition(agentPercept.getCurrCol(), agentPercept.getCurrRow());
            while(true){
                try {Thread.sleep(500);} catch (InterruptedException ex) { ex.printStackTrace();}

                int dir = pickMove();
                if(dir == 0) changePlayerPosition(agentPercept.getCurrCol(), agentPercept.getCurrRow()-1);
                else if(dir == 1) changePlayerPosition(agentPercept.getCurrCol()+1, agentPercept.getCurrRow());
                else if(dir == 2) changePlayerPosition(agentPercept.getCurrCol(), agentPercept.getCurrRow()+1);
                else if(dir == 3) changePlayerPosition(agentPercept.getCurrCol()-1, agentPercept.getCurrRow());
                agentPercept.getKnowledgeBaseSingleton().printKB();

            }

        }).start();
    }


    private void changePlayerPosition( int x, int y) {

        agentPercept.setCurrCol(x);
        agentPercept.setCurrRow(y);
        agentPercept.addToVisitedList(y, x);

        GridPane.setRowIndex(circle, y);
        GridPane.setColumnIndex(circle, x);
        agentPercept.addKnowledgeFromPercept(worldEnv[y][x]);

    }


    private int pickMove(){
        int priority = -10;
        int moveDir = 2;

//        if(agentPercept.getCurrCol() == 0 || agentPercept.getCurrCol()== 9) moveDir = 1;
        if(agentPercept.getCurrRow() == 0 || agentPercept.getCurrRow()== 9) moveDir = 1;
        if(agentPercept.getCurrCol() == 0 || agentPercept.getCurrCol()== 9) moveDir = 2;

        if(worldEnv[agentPercept.getCurrCol()][agentPercept.getCurrRow()].equals("")) return moveDir;



        if(agentPercept.getCurrCol()-1>-1){
            int p = getPriority(agentPercept.getCurrCol()-1, agentPercept.getCurrRow());
            if(priority < p){
                priority = p;
                moveDir = 3;
            }

        }

        if(agentPercept.getCurrCol()+1<10){
            int p = getPriority(agentPercept.getCurrCol()+1, agentPercept.getCurrRow());
            if(priority < p){
                priority = p;
                moveDir = 1;
            }

        }

        if(agentPercept.getCurrRow()-1>-1){
            int p = getPriority(agentPercept.getCurrCol(), agentPercept.getCurrRow()-1);
            if(priority < p){
                priority = p;
                moveDir = 0;
            }

        }


        if(agentPercept.getCurrRow()+1<10){
            int p = getPriority(agentPercept.getCurrCol(), agentPercept.getCurrRow()+1);
            if(priority < p){
                priority = p;
                moveDir = 2;
            }

        }

        return moveDir;
    }

    private int getPriority(int x, int y) {

        //check if visited
        if(agentPercept.isVisited(y, x)) return 1;
        if(propositionalLogicResolution.getResolutionResult("B"+y+x)) {
            System.out.println("Breeze at " + x + " " + y);
            if (propositionalLogicResolution.getResolutionResult("~P" + x + y)) {
                System.out.println("No Pit at " + x + " " + y);
                return 10;

            } else {
                System.out.println("Pit at " + x + " " + y);
            }
        }
        if(propositionalLogicResolution.getResolutionResult("S"+x+y)) {
            System.out.println("Stench at " + x + " " + y);

            if (propositionalLogicResolution.getResolutionResult("~W" + x + y)) {
                return 5;
            } else {
                System.out.println("WUMPUS at " + x + " " + y);
                if (propositionalLogicResolution.getResolutionResult("Gl" + x + y))
                    if (propositionalLogicResolution.getResolutionResult("G" + x + y)) {
                        System.out.println("GOLD at " + x + " " + y);
                        return 100;
                    } else
                        System.out.println("No GOLD at " + x + " " + y);

            }
        }
        if(propositionalLogicResolution.getResolutionResult("Gl"+x+y)) {
            System.out.println("Glitter at " + x + " " + y);

            if (propositionalLogicResolution.getResolutionResult("G" + x + y)) {
                System.out.println("GOLD at " + x + " " + y);
                return 100;
            } else
                System.out.println("No GOLD at " + x + " " + y);
        }




        return 3;
    }


    public static void main(String[] args) {
        launch(args);
    }


//    private Node getByUserData(Parent parent, Object data) {
//
//        for (Node n : parent.getChildrenUnmodifiable()) {
//            if (data.equals(n.getUserData())) {
//                return n;
//            } else return null;
//
//        }
//        return null;
//    }
}


