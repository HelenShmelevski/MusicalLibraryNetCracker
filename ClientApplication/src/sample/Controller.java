package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button genreButton;

    @FXML
    private Button trackButton;

    @FXML
    private Button artistButton;


    @FXML
    void initialize() {
        artistButton.setOnAction(event -> {
            setSceneOnStage("artist.fxml");
        });

        trackButton.setOnAction(event -> {
            setSceneOnStage("track.fxml");

        });
        genreButton.setOnAction(event -> {
            setSceneOnStage("genre.fxml");
        });
    }

    protected void setSceneOnStage(String titleScene) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(titleScene));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}




