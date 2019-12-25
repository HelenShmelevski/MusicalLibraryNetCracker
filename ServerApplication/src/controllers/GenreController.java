package controllers;

import models.Genre;
import services.ArtistService;
import services.GenreService;
import services.TrackService;


public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    public Genre[] getAllGenres() {
        return genreService.getAll();
    }

    public void addGenre(Genre genre) {
        genreService.insert(genre);
    }

    public void updateGenre(int id, Genre newGenre) {
        genreService.update(id, newGenre);
    }

    public void deleteGenre(int id) {
        genreService.delete(id);
    }

    public Genre getGenreById(int id)
    {
        return genreService.getById(id);
    }
}
