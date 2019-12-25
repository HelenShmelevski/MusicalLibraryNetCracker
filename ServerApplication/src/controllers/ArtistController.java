package controllers;

import models.Artist;
import models.Genre;
import models.Track;
import services.ArtistService;
import services.GenreService;
import services.TrackService;

import java.io.IOException;
import java.util.Arrays;

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

    public void updateArtist(int id, Artist newTrack) {
        artistService.update(id, newTrack);
    }

    public void deleteArtist(int id) {
        artistService.delete(id);
    }

    public Artist getArtistById(int id)
    {
        return artistService.getById(id);
    }
}
