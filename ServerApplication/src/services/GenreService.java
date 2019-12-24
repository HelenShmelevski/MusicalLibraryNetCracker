package services;
import models.Genre;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenreService {
    private final JsonService<Genre> jsonService;
    private final ArtistService artistService;
    private final TrackService trackService;

    private static final String GENRE_FILE_PATH  = "Genre.json";
    private List<Genre> genreList;

    public GenreService(JsonService<Genre> jsonService, ArtistService artistService, TrackService trackService) {
        this.jsonService = jsonService;
        this.artistService = artistService;
        this.trackService = trackService;
        File genreFile = new File(GENRE_FILE_PATH);
        if (!genreFile.exists())
        {
            genreList = new ArrayList<Genre>();
        } else {
            Genre[] genres = jsonService.read(genreFile, Genre[].class);
            genreList = Arrays.asList(genres);
        }
    }

    public Genre[] getAll() {
        return genreList.toArray(new Genre[0]);
    }

    public void insert(Genre genre){
        genreList.add(genre);
        jsonService.write(new File(GENRE_FILE_PATH), genreList.toArray(new Genre[0]));
    }

    public void update(int id, Genre newGenre) {
        int length =  genreList.size();
        for (int i = 0; i < length; i++) {
            if (genreList.get(i).getGenreID() == id)
            {
                genreList.get(i).setTitle(newGenre.getTitle());
            }
        }
        jsonService.write(new File(GENRE_FILE_PATH), genreList.toArray(new Genre[0]));
    }

    public void delete(int id) {
        int length =  genreList.size();
        for (int i = 0; i < length; i++) {
            if (genreList.get(i).getGenreID() == id)
            {
                genreList.remove(i);
                length--;
            }
        }
        jsonService.write(new File(GENRE_FILE_PATH), genreList.toArray(new Genre[0]));
    }

    public Genre getById(int id)
    {
        for (Genre genre: genreList) {
            if (genre.getGenreID() == id) {
                return genre;
            }
        }
        return null;
    }
}
