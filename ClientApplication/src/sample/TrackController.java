package sample;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class TrackController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button genreButton;

    @FXML
    private Button addTrackButton;

    @FXML
    private Button deleteTrackButton;

    @FXML
    private Button trackButton;

    @FXML
    private Button artistButton;

    @FXML
    private Button changeTrackButton;

    @FXML
    private Button returnButton;


    @FXML
    void initialize() {
        artistButton.setOnAction(event -> {
            setSceneOnStage("artist.fxml");
        });

        genreButton.setOnAction(event -> {
            setSceneOnStage("genre.fxml");
        });
        addTrackButton.setOnAction(event -> {
            Dialog<Pair<String, String>> dialog = new Dialog<>();

            dialog.setTitle("Добавление нового трека");
            dialog.setHeaderText("Введите информацию о треке:");

            // Set the button types.
            ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(20, 150, 10, 10));

            TextField from = new TextField();
            from.setPromptText("Название трека");
            from.appendText("Название трека");
            TextField to = new TextField();
            to.setPromptText("Имя артиста");
            to.appendText("Имя артиста");


            gridPane.add(from, 0, 0);
            //   gridPane.add(new Label("To:"), 1, 0);
            gridPane.add(to, 2, 0);

            dialog.getDialogPane().setContent(gridPane);

            Optional<Pair<String, String>> result = dialog.showAndWait();
//            result.ifPresent(pair -> {
//                System.out.println("Название трека =" + pair.getKey() + ", Имя артиста =" + pair.getValue());
//            });

        });
        deleteTrackButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("ID трека");

            dialog.setTitle("Удаление трека");
            dialog.setHeaderText("Введите ID трека:");
            dialog.setContentText("ID:");

            Optional<String> result = dialog.showAndWait();

            //ЕСЛИ ID ВВЕДЕНО, ТО...СЕРВЕР ЧТО-ТО ДЕЛАЕТ
//            result.ifPresent(ID -> {
//                this.label.setText(ID);
//            });
        });

        changeTrackButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("ID трека");

            dialog.setTitle("Изменение трека");
            dialog.setHeaderText("Введите ID трека:");
            dialog.setContentText("ID:");

            Optional<String> result = dialog.showAndWait();

            //ЕСЛИ ID ВВЕДЕНО, ТО получаем id и узнаем новое имя артиста
            result.ifPresent(id -> {
                try {
                    int artistID = Integer.parseInt(id);

                    Dialog<Pair<String, String>> dialog2 = new Dialog<>();

                    dialog2.setTitle("Добавление нового трека");
                    dialog2.setHeaderText("Введите информацию о треке:");

                    // Set the button types.
                    ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    dialog2.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

                    GridPane gridPane = new GridPane();
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);
                    gridPane.setPadding(new Insets(20, 150, 10, 10));

                    TextField from = new TextField();
                    from.setPromptText("Название трека");
                    from.appendText("Название трека");//сюда вставить название трека, которое хочет изменить пользователь
                    TextField to = new TextField();
                    to.setPromptText("Имя артиста");
                    to.appendText("Имя артиста");//сюда вставить имя артиста трека, которое хочет изменить пользователь


                    gridPane.add(from, 0, 0);
                    //   gridPane.add(new Label("To:"), 1, 0);
                    gridPane.add(to, 2, 0);

                    dialog2.getDialogPane().setContent(gridPane);

                    Optional<Pair<String, String>> result2 = dialog2.showAndWait();
//            result2.ifPresent(pair -> {
//                System.out.println("Название трека =" + pair.getKey() + ", Имя артиста =" + pair.getValue());
//            });
                } catch (Exception ex) {
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
