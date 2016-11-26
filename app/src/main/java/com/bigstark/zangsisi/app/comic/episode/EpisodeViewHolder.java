package com.bigstark.zangsisi.app.comic.episode;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigstark.zangsisi.R;
import com.bigstark.zangsisi.app.base.BusProvider;
import com.bigstark.zangsisi.model.EpisodeModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bigstark on 2016. 6. 20..
 */

public class EpisodeViewHolder extends RecyclerView.ViewHolder {

    public static EpisodeViewHolder newInstance(Context context) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.comic_episode, null);
        return new EpisodeViewHolder(itemView);
    }


    @BindView(R.id.tv_episode) TextView tvEpisode;
    private EpisodeModel episode;

    @OnClick(R.id.selector_comic)
    void onEpisodeClicked() {
        EventEpisodeClicked event = new EventEpisodeClicked(episode.getEpisodeId());
        BusProvider.getInstance().post(event);
    }


    protected EpisodeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(EpisodeModel episode) {
        this.episode = episode;

        tvEpisode.setText(episode.getTitle());
    }
}
