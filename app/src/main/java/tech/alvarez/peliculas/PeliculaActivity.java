package tech.alvarez.peliculas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.alvarez.peliculas.models.PeliculaDetalle;
import tech.alvarez.peliculas.peliculasapi.TheMovieDBServicio;

public class PeliculaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);

        String id = getIntent().getStringExtra("id");

        Log.i("MIAPP", "ID res : " + id);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDBServicio servicio = retrofit.create(TheMovieDBServicio.class);
        Call<PeliculaDetalle> peliculaDetalleCall = servicio.obtenerInfoPelicula(id, "589e10387e0ca4ece633f5836fb0383f");

        peliculaDetalleCall.enqueue(new Callback<PeliculaDetalle>() {
            @Override
            public void onResponse(Call<PeliculaDetalle> call, Response<PeliculaDetalle> response) {
                PeliculaDetalle peliculaDetalle = response.body();

                Log.i("MIAPP", " poster: " + peliculaDetalle.getPoster_path());
                Log.i("MIAPP", " Titulo: " + peliculaDetalle.getTitle());
                Log.i("MIAPP", " Desc:" + peliculaDetalle.getOverview());
                Log.i("MIAPP", " voto: " + peliculaDetalle.getVote_average());
            }

            @Override
            public void onFailure(Call<PeliculaDetalle> call, Throwable t) {

            }
        });


    }
}
