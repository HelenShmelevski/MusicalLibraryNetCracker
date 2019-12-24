package controllers;

import models.Artist;
import services.ArtistService;

import java.io.IOException;

public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService)
    {
        this.artistService = artistService;
    }
    public Artist[] getAllArtists() throws IOException {
        return artistService.getAll();
    }
    public void addArtist(Artist artist) throws IOException {
        artistService.insert(artist);
    }

    public void updateGenre(int id, Artist newTrack) {
        artistService.update(id, newTrack);
    }

    public void deleteGenre(int id) {
        artistService.delete(id);
    }

    public Artist getGenreById(int id)
    {
        return artistService.getById(id);
    }
}
