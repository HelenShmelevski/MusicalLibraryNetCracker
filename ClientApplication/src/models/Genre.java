package models;

import java.io.Serializable;

public class Genre implements Serializable{

    private int genreID;
    private String title;

    public int getGenreID() {
        return genreID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre(int genreID, String title) {
        this.genreID = genreID;
        this.title = title;
    }

    public Genre() { }
}
