package models;

public class Track {
    private int trackID;
    private String title;
    private String album;
    private float length;
    private Artist artist;
    private Genre genre;

    public Track(String title, Artist artist, float length, String album) {
        this.title = title;
        this.artist = artist;
        this.length = length;
        this.album = album;
    }

    public Track(String title, Artist artist, float length) {
        this.title = title;
        this.artist = artist;
        this.length = length;
    }

    public Track(int trackID, String album, float length, Artist artist) {
        this.trackID = trackID;
        this.title = "unknown";
        this.album = album;
        this.length = length;
        this.artist = artist;
    }
    public Track(int trackID,String title) {
        this.title = title;
        this.trackID = trackID;
    }

    public Track(int trackID, String title, Artist artist) {
        this.title = title;
        this.trackID = trackID;
        this.artist = artist;
    }

    public Track() { }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtistName() {
        return artist.getArtistName();
    }

    public float getLength() {
        return length;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    public int getTrackID() {
        return trackID;
    }

    public Artist getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}