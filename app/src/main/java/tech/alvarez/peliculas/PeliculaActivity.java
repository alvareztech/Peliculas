package tech.alvarez.peliculas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.alvarez.peliculas.models.PeliculaDetalle;
import tech.alvarez.peliculas.peliculasapi.TheMovieDBServicio;

public class PeliculaActivity extends AppCompatActivity {

    private TextView tituloTextView;
    private TextView descripcionTextView;
    private TextView puntajeTextView;
    private ImageView posterImageView;

    private String idPeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);

        tituloTextView = (TextView) findViewById(R.id.tituloTextView);
        descripcionTextView = (TextView) findViewById(R.id.descripcionTextView);
        puntajeTextView = (TextView) findViewById(R.id.puntajeTextView);
        posterImageView = (ImageView) findViewById(R.id.posterImageView);


        idPeli = getIntent().getStringExtra("id");

        Log.i("MIAPP", "ID res : " + idPeli);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDBServicio servicio = retrofit.create(TheMovieDBServicio.class);
        Call<PeliculaDetalle> peliculaDetalleCall = servicio.obtenerInfoPelicula(idPeli, "589e10387e0ca4ece633f5836fb0383f");

        peliculaDetalleCall.enqueue(new Callback<PeliculaDetalle>() {
            @Override
            public void onResponse(Call<PeliculaDetalle> call, Response<PeliculaDetalle> response) {
                PeliculaDetalle peliculaDetalle = response.body();

                Log.i("MIAPP", " poster: " + peliculaDetalle.getPoster_path());
                Log.i("MIAPP", " Titulo: " + peliculaDetalle.getTitle());
                Log.i("MIAPP", " Desc:" + peliculaDetalle.getOverview());
                Log.i("MIAPP", " voto: " + peliculaDetalle.getVote_average());

                asignarValores(peliculaDetalle);
            }

            @Override
            public void onFailure(Call<PeliculaDetalle> call, Throwable t) {

            }
        });


    }

    private void asignarValores(PeliculaDetalle peliculaDetalle) {

        tituloTextView.setText(peliculaDetalle.getTitle());
        descripcionTextView.setText(peliculaDetalle.getOverview());
        puntajeTextView.setText(peliculaDetalle.getVote_average());


        Glide.with(this).load("http://image.tmdb.org/t/p/w185" + peliculaDetalle.getPoster_path())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(posterImageView);
    }


    public void irActores(View view) {
        Intent intent = new Intent(this, ActoresActivity.class);
        intent.putExtra("idPeli", idPeli);
        startActivity(intent);
    }
}
