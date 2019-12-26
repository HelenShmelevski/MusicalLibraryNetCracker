package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Artist;
import models.CommandType;
import models.Genre;
import models.ModelType;
import models.Track;

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
    private TableView<Artist> artistTable;

    @FXML
    private TableColumn<Artist, String> ArtistIdColumn;

    @FXML
    private TableColumn<Artist, String> NameColumn;

    @FXML
    private TableColumn<Artist, Genre> GenreColumn;

    private ClientSocket socket;

    @FXML
    void initialize() {
        socket = new ClientSocket();
        socket.connect("localhost",1234);
        ArtistIdColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("artistID"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("artistName"));
        GenreColumn.setCellValueFactory(new PropertyValueFactory<Artist, Genre>("genre"));

        artistTable.getItems().setAll();
        updateArtistTable();

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

            result.ifPresent(name -> {
               addNewArtist();

            });

            artistTable.getItems().setAll();
            updateArtistTable();
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

    private void updateArtistTable() {
        socket.sendObject(CommandType.GET_ALL);
        socket.sendObject(ModelType.ARTIST);

        Artist[] artists = (Artist[]) socket.getData();
        if(artists != null) {
            for (Artist artist : artists) {
                artistTable.getItems().add(artist);
            }
        }
    }

    private void addNewArtist() {
        socket.sendObject(CommandType.ADD);
        socket.sendObject(ModelType.ARTIST);

      //  Artist newArtist = new Artist( id, name, "unknown", new Genre(3,"pam"), new ArrayList<Track>());
        Artist newArtist = new Artist();
        socket.sendObject(newArtist);

    }

}
