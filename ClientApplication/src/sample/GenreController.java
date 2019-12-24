package sample;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
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
import models.CommandType;
import models.Genre;
import models.ModelType;

public class GenreController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button genreButton;

    @FXML
    private Button trackButton;

    @FXML
    private Button deleteGenreButton;

    @FXML
    private Button changeGenreButton;

    @FXML
    private Button artistButton;

    @FXML
    private Button addGenreButton;

    @FXML
    private Button returnButton;

    @FXML
    private TableView<Genre> genreTable;

    @FXML
    private TableColumn<Genre, String> GenreIdColumn;

    @FXML
    private TableColumn<Genre, String> TitleColumn;

    private ClientSocket socket;


    @FXML
    void initialize() {
        GenreIdColumn.setCellValueFactory(new PropertyValueFactory<Genre, String>("Id"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<Genre, String>("Название жанра"));
        tableView.getItems().setAll(update());

        artistButton.setOnAction(event -> {
            setSceneOnStage("artist.fxml");
        });

        trackButton.setOnAction(event -> {
            setSceneOnStage("track.fxml");

        });





        addGenreButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("Название жанра");
            dialog.setTitle("Добавление нового жанра");
            dialog.setHeaderText("Введите название жанра:");
            dialog.setContentText("Название жанра:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent((title) -> {
                Genre genre = new Genre();
                genre.setTitle(title);
                 genreTable.getItems().add(genre);
//                socket.sendObject(CommandType.ADD);
//                socket.sendObject(ModelType.GENRE);
//                socket.sendObject(genre);
//
//                boolean state = (boolean) socket.getData();
//                updateGenreTable();
            });
            //TODO: передать параметры в жанр, чтобы передавались на сервер



            //socket.disconnect();
            //ЕСЛИ ИМЯ ВВЕДЕНО, ТО...СЕРВЕР ЧТО-ТО ДЕЛАЕТ
//            result.ifPresent(name -> {
//                this.label.setText(name);
//            });
        });
        deleteGenreButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("ID жанра");

            dialog.setTitle("Удаление жанра");
            dialog.setHeaderText("Введите ID жанра:");
            dialog.setContentText("ID:");

            Optional<String> result = dialog.showAndWait();

            //ЕСЛИ ID ВВЕДЕНО, ТО...СЕРВЕР ЧТО-ТО ДЕЛАЕТ
//            result.ifPresent(name -> {
//                this.label.setText(name);
//            });
        });

        changeGenreButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("ID жанра");

            dialog.setTitle("Изменение жанра");
            dialog.setHeaderText("Введите ID жанра:");
            dialog.setContentText("ID:");

            Optional<String> result = dialog.showAndWait();

            //ЕСЛИ ID ВВЕДЕНО, ТО получаем id и узнаем новое название жанра
            result.ifPresent(id -> {
                try {
                    int artistID = Integer.parseInt(id);
                    TextInputDialog dialog2 = new TextInputDialog("Новое название жанра");

                    dialog2.setTitle("Изменение жанра");
                    dialog2.setHeaderText("Введите название жанра:");
                    dialog2.setContentText("Название жанра:");

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

//        ClientSocket socket = new ClientSocket();
//        socket.connect("localhost", 1234);
//        socket.sendObject(CommandType.GET_ALL);
//        socket.sendObject(ModelType.GENRE);
//        Genre[] genres = (Genre[]) socket.getData();
//        socket.disconnect();
//        for (Genre genre: genres) {
//            //TODO записать все жанры в таблицу
//        }

        //socket = new ClientSocket();
        //socket.connect(SocketSettings.getHost(), SocketSettings.getPort());
    }

    private void updateGenreTable() {
        socket.sendObject(CommandType.GET_ALL);
        socket.sendObject(ModelType.GENRE);

        Genre[] genres = (Genre[]) socket.getData();
        for (Genre genre: genres) {
            genreTable.getItems().add(genre);
        }
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
