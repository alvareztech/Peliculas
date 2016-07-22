package tech.alvarez.peliculas.peliculasapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tech.alvarez.peliculas.models.CarteleraRespuesta;
import tech.alvarez.peliculas.models.PeliculaDetalle;

/**
 * Created by Daniel Alvarez on 21/7/16.
 * Copyright © 2016 Alvarez.tech. All rights reserved.
 */
public interface TheMovieDBServicio {

    @GET("movie/now_playing?language=es")
    Call<CarteleraRespuesta> obtenerPeliculasEnCartelera(@Query("api_key") String apiKey);

    @GET("movie/{ide}")
    Call<PeliculaDetalle> obtenerInfoPelicula( @Path("ide") String id, @Query("api_key") String apiKey);
}
