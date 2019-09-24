<<<<<<< HEAD
package sample;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Random rand = new Random();
        Color[] colors = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED};



//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        BorderPane root = new BorderPane();
        BorderPane bpane = new BorderPane();
        Label gameName = new Label("Wumpus World!");
        gameName.setTextFill(Color.valueOf("white"));
        bpane.setLeft(gameName);
        bpane.setStyle("-fx-background-color:darkslateblue;-fx-padding: 10px;\n" +
                "    -fx-font-size: 20px;-fx-color:white");
        root.setTop(bpane);

        VBox vbox = new VBox();
        GridPane gp = new GridPane();
        vbox.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-border-width: 1;\n" +
                "    -fx-border-color: transparent #E8E8E8 transparent transparent;\n" +
                "    -fx-background-color: #E8E8E8;");

        gp.setStyle("-fx-padding: 8 15 15 15");
        gp.setVgap(2.5);
        gp.setHgap(2.5);
        
        WmpsWorld myWorld = new WmpsWorld();
		String world[][]= myWorld.generateWorld();
		String list[] = {"Normal", "Wumpus", "Stench", "Pit", "Breeze", "Gold"};
        
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Rectangle rec =  new Rectangle();
                rec.setWidth(50);
                rec.setHeight(50);
                rec.setFill(Color.valueOf("beige"));
                Text text = new Text(world[row][col]);
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                GridPane.setRowIndex(text, row);
                GridPane.setColumnIndex(text, col);
                gp.getChildren().addAll(rec,text);
            }
        }
//        gp.setGridLinesVisible(true);
        Circle circle =new Circle();
        circle.setFill(Color.valueOf("Red"));
        circle.setRadius(8);
        circle.setUserData("Player");
        GridPane.setRowIndex(circle,0);
        GridPane.setColumnIndex(circle,0);
        gp.getChildren().add(circle);



        vbox.getChildren().add(gp);
        root.setLeft(vbox);
        primaryStage.setTitle("Wumpus World!");
        primaryStage.setScene(new Scene(root, 750, 630));
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(700);
        primaryStage.show();

        Node player = getByUserData(gp,"Player");
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), circle);
        tt.setByX(80);


       // tt.setCycleCount(Animation.INDEFINITE);//set to 1

        tt.play();
        tt = new TranslateTransition(Duration.millis(500), circle);

    }



    public static void main(String[] args) {
        launch(args);
    }
    private Node getByUserData(Parent parent,Object data) {

        for (Node n : parent.getChildrenUnmodifiable()) {
            if (data.equals(n.getUserData())) {
                return n;
            }
            else return null;

        }
        return  null;
    }
}
=======
package sample;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Random rand = new Random();
        Color[] colors = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED};



//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        BorderPane root = new BorderPane();
        BorderPane bpane = new BorderPane();
        Label gameName = new Label("Wumpus World!");
        gameName.setTextFill(Color.valueOf("white"));
        bpane.setLeft(gameName);
        bpane.setStyle("-fx-background-color:darkslateblue;-fx-padding: 10px;\n" +
                "    -fx-font-size: 20px;-fx-color:white");
        root.setTop(bpane);

        VBox vbox = new VBox();
        GridPane gp = new GridPane();
        vbox.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-border-width: 1;\n" +
                "    -fx-border-color: transparent #E8E8E8 transparent transparent;\n" +
                "    -fx-background-color: #E8E8E8;");

        gp.setStyle("-fx-padding: 8 15 15 15");
        gp.setVgap(2.5);
        gp.setHgap(2.5);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Rectangle rec =  new Rectangle();
                rec.setWidth(50);
                rec.setHeight(50);
                rec.setFill(Color.valueOf("beige"));

                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                gp.getChildren().addAll(rec);
            }
        }
//        gp.setGridLinesVisible(true);
        Circle circle =new Circle();
        circle.setFill(Color.valueOf("Red"));
        circle.setRadius(8);
        circle.setUserData("Player");
        GridPane.setRowIndex(circle,0);
        GridPane.setColumnIndex(circle,0);
        gp.getChildren().add(circle);



        vbox.getChildren().add(gp);
        root.setLeft(vbox);
        primaryStage.setTitle("Wumpus World!");
        primaryStage.setScene(new Scene(root, 750, 630));
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(700);
        primaryStage.show();

        Node player = getByUserData(gp,"Player");
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), circle);
        tt.setByX(80);


       // tt.setCycleCount(Animation.INDEFINITE);//set to 1

        tt.play();
        tt = new TranslateTransition(Duration.millis(500), circle);

    }



    public static void main(String[] args) {
        launch(args);
    }
    private Node getByUserData(Parent parent,Object data) {

        for (Node n : parent.getChildrenUnmodifiable()) {
            if (data.equals(n.getUserData())) {
                return n;
            }
            else return null;

        }
        return  null;
    }
}
>>>>>>> ab4774bd278c80d693a4ff83ec4546d15b8312f3
