package tech.alvarez.peliculas.models;

/**
 * Created by Daniel Alvarez on 21/7/16.
 * Copyright © 2016 Alvarez.tech. All rights reserved.
 */
public class PeliculaCartelera {

    private String title;
    private String overview;
    private String backdrop_path;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
