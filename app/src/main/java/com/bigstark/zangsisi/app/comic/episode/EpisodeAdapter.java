package com.bigstark.zangsisi.app.comic.episode;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bigstark.zangsisi.model.EpisodeModel;

import java.util.List;

/**
 * Created by bigstark on 2016. 6. 20..
 */

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeViewHolder> {

    private List<EpisodeModel> episodes;


    public void setEpisodes(List<EpisodeModel> episodes) {
        this.episodes = episodes;
    }


    @Override
    public EpisodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return EpisodeViewHolder.newInstance(parent.getContext());
    }

    @Override
    public void onBindViewHolder(EpisodeViewHolder holder, int position) {
        EpisodeModel episode = episodes.get(position);
        holder.onBind(episode);
    }

    @Override
    public int getItemCount() {
        return episodes == null ? 0 : episodes.size();
    }
}
