package tech.alvarez.peliculas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import tech.alvarez.peliculas.R;
import tech.alvarez.peliculas.models.PeliculaCartelera;

/**
 * Created by Daniel Alvarez on 21/7/16.
 * Copyright © 2016 Alvarez.tech. All rights reserved.
 */
public class CarteleraAdapter extends RecyclerView.Adapter<CarteleraAdapter.ViewHolder> {

    private ArrayList<PeliculaCartelera> dataset;
    private Context context;
    private ItemClickOyente itemClickOyente;

    public CarteleraAdapter(Context context, ItemClickOyente itemClickOyente) {
        this.dataset = new ArrayList<>();
        this.context = context;
        this.itemClickOyente = itemClickOyente;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PeliculaCartelera peliculaCartelera = dataset.get(position);

        holder.tituloTextView.setText(peliculaCartelera.getTitle());
        holder.descripcionTextView.setText(peliculaCartelera.getOverview());

        Glide.with(context).load("http://image.tmdb.org/t/p/w185" + peliculaCartelera.getBackdrop_path())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);

        holder.setOnClickItem(peliculaCartelera, itemClickOyente);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoImageView;
        private TextView tituloTextView;
        private TextView descripcionTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            tituloTextView = (TextView) itemView.findViewById(R.id.tituloTextView);
            descripcionTextView = (TextView) itemView.findViewById(R.id.descripcionTextView);
        }

        public void setOnClickItem(final PeliculaCartelera peliculaCartelera, final ItemClickOyente itemClickOyente) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickOyente.onItemClick(peliculaCartelera);
                }
            });
        }
    }

    public void adicionar(PeliculaCartelera p) {
        dataset.add(p);
        notifyDataSetChanged();
    }
}
