package controllers;

import models.Track;
import services.TrackService;

import java.io.IOException;

public class TrackController {
    private final TrackService trackService;

    public TrackController(TrackService trackService){
        this.trackService = trackService;
    }

    public Track[] getAllTracks() throws IOException {
        return trackService.getAll();
    }
    public void addTrack(Track track) throws IOException {
        trackService.insert(track);
    }

    public void updateGenre(int id, Track newTrack) {
        trackService.update(id, newTrack);
    }

    public void deleteGenre(int id) {
        trackService.delete(id);
    }

    public Track getGenreById(int id)
    {
        return trackService.getById(id);
    }
}
