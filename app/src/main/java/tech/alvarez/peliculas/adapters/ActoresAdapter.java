package tech.alvarez.peliculas.adapters;

/**
 * Created by Daniel Alvarez on 22/7/16.
 * Copyright © 2016 Alvarez.tech. All rights reserved.
 */

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
import tech.alvarez.peliculas.models.Actor;

/**
 * Created by Daniel Alvarez on 21/7/16.
 * Copyright © 2016 Alvarez.tech. All rights reserved.
 */
public class ActoresAdapter extends RecyclerView.Adapter<ActoresAdapter.ViewHolder> {

    private ArrayList<Actor> dataset;
    private Context context;
    private ItemClickActorOyente itemClickOyente;

    public ActoresAdapter(Context context, ItemClickActorOyente itemClickOyente) {
        this.dataset = new ArrayList<>();
        this.context = context;
        this.itemClickOyente = itemClickOyente;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Actor actor = dataset.get(position);

        holder.nombreTextView.setText(actor.getName());
        holder.repartoTextView.setText(actor.getCharacter());

        Glide.with(context).load("http://image.tmdb.org/t/p/w185" + actor.getProfile_path())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);

        holder.setOnClickItem(actor, itemClickOyente);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoImageView;
        private TextView nombreTextView;
        private TextView repartoTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            repartoTextView = (TextView) itemView.findViewById(R.id.repartoTextView);
        }

        public void setOnClickItem(final Actor actor, final ItemClickActorOyente itemClickOyente) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickOyente.onItemClick(actor);
                }
            });
        }
    }

    public void adicionar(Actor p) {
        dataset.add(p);
        notifyDataSetChanged();
    }
}
