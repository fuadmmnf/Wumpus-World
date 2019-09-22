package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
        gp.setVgap(5);
        gp.setHgap(5);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                int n = rand.nextInt(4);
                Rectangle rec =  new Rectangle();
                rec.setWidth(30);
                rec.setHeight(30);
                rec.setFill(colors[n]);
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                gp.getChildren().addAll(rec);
            }
        }

        vbox.getChildren().add(gp);
        root.setLeft(vbox);


        primaryStage.setTitle("Wumpus World!");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
