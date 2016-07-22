package tech.alvarez.peliculas.models;

/**
 * Created by Daniel Alvarez on 21/7/16.
 * Copyright © 2016 Alvarez.tech. All rights reserved.
 */
public class PeliculaDetalle {

    private String overview;
    private String title;
    private String vote_average;
    private String poster_path;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
