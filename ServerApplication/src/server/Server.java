package server;

import controllers.ArtistController;
import controllers.GenreController;
import controllers.TrackController;
import models.*;
import services.ArtistService;
import services.GenreService;
import services.JsonService;
import services.TrackService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    private static ObjectInputStream reader;
    private static ObjectOutputStream writer;

    private static ArtistController artistController;
    private static GenreController genreController;
    private static TrackController trackController;

    private static ArtistService artistService;
    private static GenreService genreService;
    private static TrackService trackService;

    public static void main(String[] args) {
            artistService = new ArtistService(new JsonService<Artist>());
            artistController = new ArtistController(artistService);

            trackService = new TrackService(new JsonService<Track>());
            trackController = new TrackController(trackService);

            genreService = new GenreService(new JsonService<Genre>(), artistService, trackService);
            genreController = new GenreController(genreService);
            try {
                serverSocket = new ServerSocket(SocketSettings.getPort());
                System.out.println("Сервер запущен на порту " + SocketSettings.getPort());
                Socket clientSocket = serverSocket.accept();
                reader = new ObjectInputStream(clientSocket.getInputStream());
                writer = new ObjectOutputStream(clientSocket.getOutputStream());

                while (true) {
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
                            int id = reader.
                            switch (modelType) {
                                case ARTIST: {

                                }
                                break;
                                case GENRE: {
                                }
                                break;
                                case TRACK: {
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
                                    writer.writeBoolean(true);
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
                                    writer.writeBoolean(true);
                                    writer.flush();
                                }
                                break;
                            }
                            break;
                        case UPDATE:
                            switch (modelType) {
                                case ARTIST:
                                    break;
                                case GENRE:
                                    break;
                                case TRACK:
                                    break;
                            }
                            break;
                        case REMOVE:
                            switch (modelType) {
                                case ARTIST:
                                    break;
                                case GENRE:
                                    break;
                                case TRACK:
                                    break;
                            }
                            break;
                    }
                }

            }
            catch (Exception e) {
                    System.out.println(e.getMessage());
            } finally {
                System.out.println("Сервер закрыт!");
                try {
                    reader.close();
                    writer.close();
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}