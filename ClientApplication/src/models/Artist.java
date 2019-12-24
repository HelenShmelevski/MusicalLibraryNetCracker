package models;

import java.util.ArrayList;

public class Artist {
    private int artistID;
    private String artistName;
    private String country;
    private Genre genre;
    private ArrayList<Track> tracks;

    //обязательный конструктор
    public Artist(int artistID,String name) {
        this.artistName = name;
        this.artistID = artistID;
        this.tracks = new ArrayList<Track>();
    }

    public Artist(int artistID, String artistName, String country, Genre genre, ArrayList<Track> tracks) {
        this(artistID, artistName);
        this.country = country;
        this.genre = genre;
        this.tracks = tracks;
    }

    public Artist() { }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public int getArtistID() {
        return artistID;
    }
}
