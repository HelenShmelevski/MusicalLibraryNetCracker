package services;
import models.Artist;
import models.Genre;
import models.Track;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class GenreService {
    private static final String GENRE_FILE_PATH  = "Genre.json";
    private static final File GENRE_FILE = new File(GENRE_FILE_PATH);

    private final JsonService<Genre> jsonService;
    private final ArtistService artistService;
    private final TrackService trackService;

    public GenreService(JsonService<Genre> jsonService, ArtistService artistService, TrackService trackService) {
        this.jsonService = jsonService;
        this.artistService = artistService;
        this.trackService = trackService;
    }

    public Genre[] getAll() {
        return jsonService.read(GENRE_FILE, Genre[].class);
    }

    public void insert(Genre genre){
        Genre[] allGenres = getAll();
        List<Genre> genreList = Arrays.asList(allGenres);
        jsonService.write(GENRE_FILE, genreList.toArray(new Genre[0]));
    }

    public void update(int id, Genre newGenre) {
        Genre[] allGenres = getAll();
        for (int i = 0; i < allGenres.length; i++) {
            if (allGenres[i].getGenreID() == id)
            {
                allGenres[i] = newGenre;
            }
        }
        jsonService.write(GENRE_FILE, allGenres);
    }

    public void delete(int id) {
        Genre[] allGenres = getAll();
        List<Genre> genreList = Arrays.asList(allGenres);
        for (int i = 0; i < allGenres.length; i++) {
            if (genreList.get(i).getGenreID() == id)
            {
                Artist[] dependArtists = artistService.getGenreArtist(genreList.get(i).getGenreID());
                for (Artist artist: dependArtists) {
                    artistService.delete(artist.getArtistID());
                }

                Track[] dependTrack = trackService.getTracksByGenre(genreList.get(i).getGenreID());
                for (Track track: dependTrack) {
                    trackService.delete(track.getTrackID());
                }

                genreList.remove(i);
            }
        }
        jsonService.write(GENRE_FILE, genreList.toArray(new Genre[0]));
    }

    public Genre getById(int id)
    {
        return Arrays.stream(getAll()).filter(genre -> genre.getGenreID() == id)
                .findFirst()
                .orElse(null);
    }
}
