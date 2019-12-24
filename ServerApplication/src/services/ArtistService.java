package services;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Artist;
import models.Track;

public class ArtistService {
    private JsonService<Artist> jsonService;
    private static final String ARTIST_FILE_PATH = "Artist.json";
    private List<Artist> artistList;

    public ArtistService(JsonService<Artist> jsonService) {
        this.jsonService = jsonService;
        File trackFile = new File(ARTIST_FILE_PATH);
        if (!trackFile.exists()) {
            artistList = new ArrayList<Artist>();
        } else {
            Artist[] tracks = jsonService.read(trackFile, Artist[].class);
            artistList = Arrays.asList(tracks);
        }
    }

    public Artist[] getAll() {
        return artistList.toArray(new Artist[0]);
    }

    public void insert(Artist track) {
        artistList.add(track);
        jsonService.write(new File(ARTIST_FILE_PATH), artistList.toArray(new Artist[0]));
    }

    public void update(int id, Artist newArtist) {
        int length = artistList.size();
        for (int i = 0; i < length; i++) {
            if (artistList.get(i).getArtistID() == id) {
                artistList.get(i).setArtistName(newArtist.getArtistName());
                artistList.get(i).setCountry(newArtist.getCountry());
                artistList.get(i).setGenre(newArtist.getGenre());
                artistList.get(i).setTracks(newArtist.getTracks());
            }
        }
        jsonService.write(new File(ARTIST_FILE_PATH), artistList.toArray(new Artist[0]));
    }

    public void delete(int id) {
        int length = artistList.size();
        for (int i = 0; i < length; i++) {
            if (artistList.get(i).getArtistID() == id) {
                artistList.remove(i);
                length--;
            }
        }
        jsonService.write(new File(ARTIST_FILE_PATH), artistList.toArray(new Artist[0]));
    }

    public Artist getById(int id) {
        for (Artist artist : artistList) {
            if (artist.getArtistID() == id) {
                return artist;
            }
        }
        return null;
    }

    public Artist[] getArtistsByGenreId(int genreId) {
        List<Artist> artistList = new ArrayList<Artist>();
        for (Artist artist : this.artistList) {
            if (artist.getGenre().getGenreID() == genreId) {
                artistList.add(artist);
            }
        }

        return artistList.toArray(new Artist[0]);
    }

    public Track[] getTracksByArtistId(int artistId) {
        for (Artist artist : artistList) {
            if (artist.getArtistID() == artistId) {
                return artist.getTracks().toArray(new Track[0]);
            }
        }
        return null;
    }
}
