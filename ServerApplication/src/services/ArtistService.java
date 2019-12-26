package services;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Artist;
import models.Track;

public class ArtistService {
    private static final String ARTIST_FILE_PATH = "Artist.json";
    private static final File ARTIST_FILE = new File(ARTIST_FILE_PATH);

    private final JsonService<Artist> jsonService;
    private final TrackService trackService;

    public ArtistService(JsonService<Artist> jsonService, TrackService trackService) {
        this.jsonService = jsonService;
        this.trackService = trackService;
    }

    public Artist[] getAll() {
        return jsonService.read(ARTIST_FILE ,Artist[].class);
    }

    public void insert(Artist artist) {
        List<Artist> artistList = Arrays.asList(getAll());
        int maxId = artistList.stream().mapToInt(o->o.getArtistID()).max().getAsInt();
        Artist newArtist = new Artist(maxId+1, artist.getArtistName(), artist.getCountry(), artist.getGenre());
        artistList.add(newArtist);
        jsonService.write(ARTIST_FILE, artistList.toArray(new Artist[0]));
    }

    public void update(int id, Artist newArtist) {
        Artist[] allArtist = getAll();
        for (int i = 0; i < allArtist.length; i++) {
            if (allArtist[i].getArtistID() == id)
            {
                allArtist[i] = newArtist;
            }
        }
        jsonService.write(ARTIST_FILE, allArtist);
    }

    public void delete(int id) {
        List<Artist> artistList = Arrays.asList(getAll());
        int length = artistList.size();
        for (int i = 0; i < length; i++) {
            if (artistList.get(i).getArtistID() == id)
            {
                Track[] dependTracks = trackService.getTracksByArtist(id);
                for (Track track: dependTracks)
                {
                    trackService.delete(track.getTrackID());
                }

                artistList.remove(i);
            }
        }
        jsonService.write(ARTIST_FILE, artistList.toArray(new Artist[0]));
    }

    public Artist getById(int id) {
        return Arrays.stream(getAll()).filter(artist -> artist.getArtistID() == id)
                .findFirst()
                .orElse(null);
    }

    public Artist[] getGenreArtist(int genreId)
    {
        Artist[] genreArtists = (Artist[]) Arrays.stream(getAll())
                .filter(track -> track.getGenre().getGenreID() == genreId)
                .toArray();

        return genreArtists;
    }
}
