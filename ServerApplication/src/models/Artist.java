package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Artist implements Serializable {
    private int artistID;
    private String artistName;
    private String country;
    private Genre genre;

    public Artist(int artistID,String name) {
        this.artistName = name;
        this.artistID = artistID;
    }

    public Artist(int artistID, String artistName, String country, Genre genre, ArrayList<Track> tracks) {
        this(artistID, artistName);
        this.country = country;
        this.genre = genre;
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

    public int getArtistID() {
        return artistID;
    }
}
