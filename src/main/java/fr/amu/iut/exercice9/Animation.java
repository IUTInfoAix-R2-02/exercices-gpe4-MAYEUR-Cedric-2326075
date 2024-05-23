package fr.amu.iut.exercice9;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animation extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        CustomButton customButton = new CustomButton();
        root.setCenter(customButton);
        Scene scene = new Scene(root, 400, 400);

        Duration duration = Duration.millis(1500);
        //t1
        TranslateTransition transition1 = new TranslateTransition(duration, customButton);
        transition1.setByX(150);
        transition1.setByY(-150);
        transition1.setAutoReverse(false);
        transition1.setCycleCount(1);
        //t2
        TranslateTransition transition2 = new TranslateTransition(duration, customButton);
        transition2.setByX(0);
        transition2.setByY(300);
        transition2.setAutoReverse(false);
        transition2.setCycleCount(1);
        //t3
        TranslateTransition transition3 = new TranslateTransition(duration, customButton);
        transition3.setByX(-300);
        transition3.setByY(0);
        transition3.setAutoReverse(false);
        transition3.setCycleCount(1);
        //t4
        TranslateTransition transition4 = new TranslateTransition(duration, customButton);
        transition4.setByX(0);
        transition4.setByY(-300);
        transition4.setAutoReverse(false);
        transition4.setCycleCount(1);
        //t5
        TranslateTransition transition5 = new TranslateTransition(duration, customButton);
        transition5.setByX(150);
        transition5.setByY(150);
        transition5.setAutoReverse(false);
        transition5.setCycleCount(1);

        SequentialTransition st = new SequentialTransition(transition1, transition2, transition3, transition4, transition5);
        st.setAutoReverse(true);
        st.setCycleCount(2);

        customButton.setOnMousePressed(mouseEvent -> st.play());

        primaryStage.setTitle("Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}