package services;

import models.Genre;
import models.Track;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrackService {
    private static final String TRACK_FILE_PATH  = "Track.json";
    private static final File TRACK_FILE = new File(TRACK_FILE_PATH);

    private final JsonService<Track> jsonService;

    public TrackService(JsonService<Track> jsonService) {
        this.jsonService = jsonService;
    }

    public Track[] getAll() {
        return jsonService.read(TRACK_FILE ,Track[].class);
    }

    public void insert(Track track){
        List<Track> trackList = Arrays.asList(getAll());
        trackList.add(track);
        jsonService.write(TRACK_FILE, trackList.toArray(new Track[0]));
    }

    public void update(int id, Track newTrack) {
        Track[] allTracks = getAll();
        for (int i = 0; i < allTracks.length; i++) {
            if (allTracks[i].getTrackID() == id)
            {
                allTracks[i] = newTrack;
            }
        }
        jsonService.write(TRACK_FILE, allTracks);
    }

    public void delete(int id) {
        List<Track> trackList = Arrays.asList(getAll());
        int length = trackList.size();
        for (int i = 0; i < length; i++) {
            if (trackList.get(i).getTrackID() == id)
            {
                trackList.remove(i);
            }
        }
        jsonService.write(TRACK_FILE, trackList.toArray(new Track[0]));
    }

    public Track getById(int id)
    {
        return Arrays.stream(getAll()).filter(track -> track.getTrackID() == id)
                .findFirst()
                .orElse(null);
    }

    public Track[] getTracksByArtist(int artistId) {
        Track[] artistTracks = (Track[]) Arrays.stream(getAll())
                .filter(track -> track.getArtist().getArtistID() == artistId)
                .toArray();

        return artistTracks;
    }
    public Track[] getTracksByGenre(int genreId) {
        Track[] genreTracks = (Track[]) Arrays.stream(getAll())
                .filter(track -> track.getGenre().getGenreID() == genreId)
                .toArray();

        return genreTracks;
    }

}
