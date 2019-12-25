package controllers;

import models.Artist;
import models.Genre;
import models.Track;
import services.ArtistService;
import services.GenreService;
import services.TrackService;

import java.io.IOException;
import java.util.Arrays;

public class TrackController {
    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    public Track[] getAllTracks() throws IOException {
        return trackService.getAll();
    }
    public void addTrack(Track track) throws IOException {
        trackService.insert(track);
    }

    public void updateTrack(int id, Track newTrack) {
        trackService.update(id, newTrack);
    }

    public void deleteTrack(int id) {
        trackService.delete(id);
    }

    public Track getTrackById(int id)
    {
        return trackService.getById(id);
    }

    public Track[] getArtistTrack(int artistId) {
        return trackService.getTracksByArtist(artistId);
    }

    public Track[] getGenreTrack(int genreId) {
        return trackService.getTracksByGenre(genreId);
    }
}
