package services;

import models.Track;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrackService {
    private JsonService<Track> jsonService;
    private static final String TRACK_FILE_PATH  = "Track.json";
    private List<Track> trackList;

    public TrackService(JsonService<Track> jsonService) {
    this.jsonService = jsonService;
    File trackFile = new File(TRACK_FILE_PATH);
        if (!trackFile.exists())
    {
        trackList = new ArrayList<Track>();
    } else {
        Track[] tracks = jsonService.read(trackFile,Track[].class);
        trackList = Arrays.asList(tracks);
    }
}

    public Track[] getAll() {
        return trackList.toArray(new Track[0]);
    }

    public void insert(Track track){
        trackList.add(track);
        jsonService.write(new File(TRACK_FILE_PATH), trackList.toArray(new Track[0]));
    }

    public void update(int id, Track newTrack) {
        int length =  trackList.size();
        for (int i = 0; i < length; i++) {
            if (trackList.get(i).getTrackID() == id)
            {
                trackList.get(i).setTitle(newTrack.getTitle());
            }
        }
        jsonService.write(new File(TRACK_FILE_PATH), trackList.toArray(new Track[0]));
    }

    public void delete(int id) {
        int length =  trackList.size();
        for (int i = 0; i < length; i++) {
            if (trackList.get(i).getTrackID() == id)
            {
                trackList.remove(i);
                length--;
            }
        }
        jsonService.write(new File(TRACK_FILE_PATH), trackList.toArray(new Track[0]));
    }

    public Track getById(int id)
    {
        for (Track track: trackList) {
            if (track.getTrackID() == id) {
                return track;
            }
        }
        return null;
    }

    public Track[] getTrackByGenreId(int genreId)
    {
        List<Track> trackList = new ArrayList<Track>();
        for (Track track: this.trackList) {
            if (track.getGenre().getGenreID() == genreId) {
                trackList.add(track);
            }
        }

        return trackList.toArray(new Track[0]);
    }

    public Track[] getTrackByArtistId(int artistId)
    {
        List<Track> trackList = new ArrayList<Track>();
        for (Track track: this.trackList) {
            if (track.getArtist().getArtistID() == artistId) {
                trackList.add(track);
            }
        }

        return trackList.toArray(new Track[0]);
    }
}
