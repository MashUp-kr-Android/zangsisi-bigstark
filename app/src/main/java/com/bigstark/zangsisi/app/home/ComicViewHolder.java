package com.bigstark.zangsisi.app.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigstark.zangsisi.R;
import com.bigstark.zangsisi.app.base.BusProvider;
import com.bigstark.zangsisi.model.ComicModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bigstark on 2016. 6. 21..
 */

public class ComicViewHolder extends RecyclerView.ViewHolder {


    public static ComicViewHolder newInstance(Context context) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.home_comic, null);
        return new ComicViewHolder(itemView);
    }


    @BindView(R.id.tv_comic) TextView tvComic;
    private ComicModel comic;


    @OnClick(R.id.selector_comic)
    void onComicClicked() {
        BusProvider.getInstance().post(new EventComicClicked(comic));
    }


    protected ComicViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void onBind(ComicModel comic) {
        this.comic = comic;
        tvComic.setText(comic.getTitle());
    }
}
