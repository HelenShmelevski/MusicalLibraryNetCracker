import controllers.ArtistController;
import controllers.GenreController;
import controllers.TrackController;
import models.*;
import server.SocketSettings;
import services.ArtistService;
import services.GenreService;
import services.JsonService;
import services.TrackService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;

    private static ArtistController artistController;
    private static GenreController genreController;
    private static TrackController trackController;

    private static ArtistService artistService;
    private static GenreService genreService;
    private static TrackService trackService;

    public static void main(String[] args) {
        CreateServices();
        CreateControllers();

        try {
            serverSocket = new ServerSocket(SocketSettings.getPort());
            Log("Сервер запущен на порту: " + SocketSettings.getPort());
        } catch (IOException e) {
            Log("Ошибка при запуске сервера: " + e.getMessage());
        }
        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());
                 ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream())) {

                Log("Клиент подключился");
                CommandType commandType = (CommandType) reader.readObject();
                ModelType modelType = (ModelType) reader.readObject();

                // TODO: Убрать лишнее дублирование кода
                switch (commandType) {
                    case GET_ALL: {
                        switch (modelType) {
                            case ARTIST: {
                                Artist[] artists = artistController.getAllArtists();
                                writer.writeObject(artists);
                                writer.flush();
                            }
                            break;
                            case GENRE: {
                                Genre[] genres = genreController.getAllGenres();
                                writer.writeObject(genres);
                                writer.flush();
                            }
                            break;
                            case TRACK: {
                                Track[] tracks = trackController.getAllTracks();
                                writer.writeObject(tracks);
                                writer.flush();
                            }
                            break;
                        }
                    }
                    break;
                    case GET: {
                        int id = (int) reader.readObject();
                        switch (modelType) {
                            case ARTIST: {
                                Artist artist = artistController.getArtistById(id);
                                writer.writeObject(artist);
                                writer.flush();
                            }
                            break;
                            case GENRE: {
                                Genre genre = genreController.getGenreById(id);
                                writer.writeObject(genre);
                                writer.flush();
                            }
                            break;
                            case TRACK: {
                                Track track = trackController.getTrackById(id);
                                writer.writeObject(track);
                                writer.flush();
                            }
                            break;
                        }
                    }
                    break;
                    case ADD:
                        switch (modelType) {
                            case ARTIST: {
                                Artist artist = (Artist) reader.readObject();
                                artistController.addArtist(artist);
                                writer.writeObject(true);
                                writer.flush();
                            }
                            break;
                            case GENRE: {
                                Genre genre = (Genre) reader.readObject();
                                genreController.addGenre(genre);
                                writer.writeObject(true);
                                writer.flush();
                            }
                            break;
                            case TRACK: {
                                Track track = (Track) reader.readObject();
                                trackController.addTrack(track);
                                writer.writeObject(true);
                                writer.flush();
                            }
                            break;
                        }
                        break;
                    case UPDATE: {
                        int id = (int) reader.readObject();
                        switch (modelType) {
                            case ARTIST: {
                                Artist newArtist = (Artist) reader.readObject();
                                artistController.updateArtist(id, newArtist);
                                writer.writeObject(true);
                                writer.flush();
                            }
                            break;
                            case GENRE: {
                                Genre newGenre = (Genre) reader.readObject();
                                genreController.updateGenre(id, newGenre);
                                writer.writeObject(true);
                                writer.flush();
                            }
                            break;
                            case TRACK: {
                                Track newTrack = (Track) reader.readObject();
                                trackController.updateTrack(id, newTrack);
                                writer.writeObject(true);
                                writer.flush();
                            }
                            break;
                        }
                    }
                    break;
                    case REMOVE: {
                        int id = (int) reader.readObject();
                        switch (modelType) {
                            case ARTIST: {
                                artistController.deleteArtist(id);
                                writer.writeObject(true);
                                writer.flush();
                            }
                            break;
                            case GENRE: {
                                genreController.deleteGenre(id);
                                writer.writeObject(true);
                                writer.flush();
                            }
                            break;
                            case TRACK: {
                                trackController.deleteTrack(id);
                                writer.writeObject(true);
                                writer.flush();
                            }
                            break;
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                Log("В работе сервера возникла ошибка", e.getMessage(), "Клиент будет отключен");
            } catch (ClassNotFoundException e) {
                Log("Не удалось найти класс", e.getMessage(), "Клиент будет отключен");
            }
        }
    }

    private static void Log(String... params)
    {
        for (String param: params) {
            System.out.println("Server: " + param);
        }
    }

    private static void CreateControllers() {
        genreController = new GenreController(genreService);
        artistController = new ArtistController(artistService);
        trackController = new TrackController(trackService);
    }

    private static void CreateServices() {
        trackService = new TrackService(new JsonService<Track>());
        artistService = new ArtistService(new JsonService<Artist>(), trackService);
        genreService = new GenreService(new JsonService<Genre>(), artistService, trackService);
    }
}