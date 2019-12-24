package sample;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ArtistController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button genreButton;

    @FXML
    private Button trackButton;

    @FXML
    private Button changeArtistButton1;

    @FXML
    private Button artistButton;

    @FXML
    private Button addArtistButton;

    @FXML
    private Button returnButton;

    @FXML
    private Button deleteArtistButton;


    @FXML
    void initialize() {
        genreButton.setOnAction(event -> {
            setSceneOnStage("genre.fxml");
        });
        trackButton.setOnAction(event -> {
            setSceneOnStage("track.fxml");
        });
        addArtistButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("Имя артиста");

            dialog.setTitle("Добавление нового артиста");
            dialog.setHeaderText("Введите имя артиста:");
            dialog.setContentText("Name:");

            Optional<String> result = dialog.showAndWait();

            //ЕСЛИ ИМЯ ВВЕДЕНО, ТО...СЕРВЕР ЧТО-ТО ДЕЛАЕТ
//            result.ifPresent(name -> {
//                this.label.setText(name);
//            });
        });
        deleteArtistButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("ID артиста");

            dialog.setTitle("Удаление артиста");
            dialog.setHeaderText("Введите ID артиста:");
            dialog.setContentText("ID:");

            Optional<String> result = dialog.showAndWait();

            //ЕСЛИ ID ВВЕДЕНО, ТО...СЕРВЕР ЧТО-ТО ДЕЛАЕТ
//            result.ifPresent(name -> {
//                this.label.setText(name);
//            });
        });

        changeArtistButton1.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("ID артиста");

            dialog.setTitle("Изменение артиста");
            dialog.setHeaderText("Введите ID артиста:");
            dialog.setContentText("ID:");

            Optional<String> result = dialog.showAndWait();

            //ЕСЛИ ID ВВЕДЕНО, ТО получаем id и узнаем новое имя артиста
            result.ifPresent(id -> {
                try {
                    int artistID = Integer.parseInt(id);
                    TextInputDialog dialog2 = new TextInputDialog("Новое имя артиста");

                    dialog2.setTitle("Изменение артиста");
                    dialog2.setHeaderText("Введите имя артиста:");
                    dialog2.setContentText("Имя артиста:");

                    Optional<String> result2 = dialog2.showAndWait();

                    //ЕСЛИ имя ВВЕДЕНО, ТО...СЕРВЕР ЧТО-ТО ДЕЛАЕТ
//            result.ifPresent(name -> {
//                this.label.setText(name);
//            });
                }
                catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            });
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
