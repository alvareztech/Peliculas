package tech.alvarez.peliculas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.alvarez.peliculas.adapters.CarteleraAdapter;
import tech.alvarez.peliculas.adapters.ItemClickOyente;
import tech.alvarez.peliculas.models.CarteleraRespuesta;
import tech.alvarez.peliculas.models.PeliculaCartelera;
import tech.alvarez.peliculas.peliculasapi.TheMovieDBServicio;

public class MainActivity extends AppCompatActivity implements ItemClickOyente {

    private RecyclerView carteleraRecyclerView;
    private CarteleraAdapter carteleraAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carteleraRecyclerView = (RecyclerView) findViewById(R.id.carteleraRecyclerView);
        carteleraRecyclerView.setHasFixedSize(true);
        carteleraRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        carteleraAdapter = new CarteleraAdapter(this, this);

        carteleraRecyclerView.setAdapter(carteleraAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDBServicio servicio = retrofit.create(TheMovieDBServicio.class);
        Call<CarteleraRespuesta> respuestaCall = servicio.obtenerPeliculasEnCartelera("589e10387e0ca4ece633f5836fb0383f");

        respuestaCall.enqueue(new Callback<CarteleraRespuesta>() {
            @Override
            public void onResponse(Call<CarteleraRespuesta> call, Response<CarteleraRespuesta> response) {
                CarteleraRespuesta carteleraRespuesta = response.body();

                for (int i = 0; i < carteleraRespuesta.getResults().size(); i++) {
                    PeliculaCartelera peliculaCartelera = carteleraRespuesta.getResults().get(i);
                    Log.i("MIAPP", "Pelicula");
                    Log.i("MIAPP", " ID: " + peliculaCartelera.getId());
                    Log.i("MIAPP", " Titulo: " + peliculaCartelera.getTitle());
                    Log.i("MIAPP", " Desc:" + peliculaCartelera.getOverview());
                    Log.i("MIAPP", " Imagen: " + peliculaCartelera.getBackdrop_path());

                    carteleraAdapter.adicionar(peliculaCartelera);
                }
            }

            @Override
            public void onFailure(Call<CarteleraRespuesta> call, Throwable t) {

            }
        });

    }

    @Override
    public void onItemClick(PeliculaCartelera peliculaCartelera) {
        Intent intent = new Intent(this, PeliculaActivity.class);
        intent.putExtra("id", peliculaCartelera.getId());
        startActivity(intent);
    }
}
