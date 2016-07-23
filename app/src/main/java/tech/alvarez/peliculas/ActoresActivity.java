package tech.alvarez.peliculas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.alvarez.peliculas.adapters.ActoresAdapter;
import tech.alvarez.peliculas.adapters.ItemClickActorOyente;
import tech.alvarez.peliculas.models.Actor;
import tech.alvarez.peliculas.models.ActoresRespuesta;
import tech.alvarez.peliculas.peliculasapi.TheMovieDBServicio;

public class ActoresActivity extends AppCompatActivity implements ItemClickActorOyente {

    private ProgressBar progressBar;

    private RecyclerView actoresRecyclerView;
    private ActoresAdapter actoresAdapter;

    private String idPeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actores);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        actoresRecyclerView = (RecyclerView) findViewById(R.id.actoresRecyclerView);
        actoresRecyclerView.setHasFixedSize(true);
        actoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        actoresAdapter = new ActoresAdapter(this, this);
        actoresRecyclerView.setAdapter(actoresAdapter);



        idPeli = getIntent().getStringExtra("idPeli");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDBServicio servicio = retrofit.create(TheMovieDBServicio.class);
        Call<ActoresRespuesta> respuestaCall = servicio.obtenerActores(idPeli, "589e10387e0ca4ece633f5836fb0383f");

        respuestaCall.enqueue(new Callback<ActoresRespuesta>() {
            @Override
            public void onResponse(Call<ActoresRespuesta> call, Response<ActoresRespuesta> response) {
                ActoresRespuesta respuesta = response.body();

                mostrarActores(respuesta.getCast());
            }

            @Override
            public void onFailure(Call<ActoresRespuesta> call, Throwable t) {

            }
        });
    }

    private void mostrarActores(ArrayList<Actor> cast) {

        progressBar.setVisibility(View.GONE);

        for (int i = 0; i < cast.size(); i++) {
            Actor a = cast.get(i);
            Log.i("MIAPP", a.getName());
            Log.i("MIAPP", a.getCharacter());
//            Log.i("MIAPP", a.getProfile_path());

            actoresAdapter.adicionar(a);
        }
    }

    @Override
    public void onItemClick(Actor actor) {

    }
}
