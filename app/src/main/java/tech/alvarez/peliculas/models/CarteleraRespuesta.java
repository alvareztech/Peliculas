package tech.alvarez.peliculas.models;

import java.util.ArrayList;

/**
 * Created by Daniel Alvarez on 21/7/16.
 * Copyright © 2016 Alvarez.tech. All rights reserved.
 */
public class CarteleraRespuesta {

    private ArrayList<PeliculaCartelera> results;

    public ArrayList<PeliculaCartelera> getResults() {
        return results;
    }

    public void setResults(ArrayList<PeliculaCartelera> results) {
        this.results = results;
    }
}
