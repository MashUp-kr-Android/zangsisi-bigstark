package com.bigstark.zangsisi.app.home;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bigstark.zangsisi.model.ComicModel;

import java.util.List;

/**
 * Created by bigstark on 2016. 6. 21..
 */

public class ComicAdapter extends RecyclerView.Adapter<ComicViewHolder> {


    private List<ComicModel> comics;


    public void setComics(List<ComicModel> comics) {
        this.comics = comics;
    }


    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ComicViewHolder.newInstance(parent.getContext());
    }


    @Override
    public void onBindViewHolder(ComicViewHolder holder, int position) {
        holder.onBind(comics.get(position));
    }



    @Override
    public int getItemCount() {
        return comics == null ? 0 : comics.size();
    }
}
