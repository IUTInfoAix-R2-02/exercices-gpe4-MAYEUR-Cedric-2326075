package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Label texteDuHaut;
    private Button vert;
    private Button rouge;
    private Button bleu;
    private BorderPane root;
    private Pane panneau;
    private HBox boutons;
    private Label texteDuBas;

    private IntegerProperty nbFois;
    private StringProperty message;
    private StringProperty couleurPanneau;

    public Palette() {
        nbFois = new SimpleIntegerProperty(0);
        message = new SimpleStringProperty("");
        couleurPanneau = new SimpleStringProperty("#000000");
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        texteDuHaut = new Label();
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);

        panneau = new Pane();
        panneau.setPrefSize(400, 200);

        VBox bas = new VBox();
        boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10, 5, 10, 5));
        texteDuBas = new Label();
        bas.setAlignment(Pos.CENTER_RIGHT);
        bas.getChildren().addAll(boutons, texteDuBas);

        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        vert.setOnAction(event -> {
            nbVert++;
            nbFois.set(nbFois.get() + 1);
            message.set("Le Vert est une jolie couleur !");
            couleurPanneau.set("#00FF00");
        });

        rouge.setOnAction(event -> {
            nbRouge++;
            nbFois.set(nbFois.get() + 1);
            message.set("Le Rouge est une jolie couleur !");
            couleurPanneau.set("#FF0000");
        });

        bleu.setOnAction(event -> {
            nbBleu++;
            nbFois.set(nbFois.get() + 1);
            message.set("Le Bleu est une jolie couleur !");
            couleurPanneau.set("#0000FF");
        });

        boutons.getChildren().addAll(vert, rouge, bleu);

        root.setCenter(panneau);
        root.setTop(texteDuHaut);
        root.setBottom(bas);

        createBindings();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createBindings() {
        BooleanProperty pasEncoreDeClic = new SimpleBooleanProperty(true);
        pasEncoreDeClic.bind(Bindings.equal(nbFois, 0));

        texteDuHaut.textProperty().bind(
                Bindings.when(pasEncoreDeClic)
                        .then("Aucune couleur sélectionnée")
                        .otherwise(Bindings.concat("Nombre de clics: ", nbFois.asString()))
        );

        panneau.styleProperty().bind(
                Bindings.concat("-fx-background-color: ", couleurPanneau)
        );

        texteDuBas.textProperty().bind(message);
        texteDuBas.textFillProperty().bind(Bindings.createObjectBinding(() -> {
            return Color.web(couleurPanneau.get());
        }, couleurPanneau));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
