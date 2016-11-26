package com.bigstark.zangsisi.app.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.bigstark.zangsisi.Defines;
import com.bigstark.zangsisi.R;
import com.bigstark.zangsisi.app.base.BaseActivity;
import com.bigstark.zangsisi.db.ZangsisiRealmHelper;
import com.bigstark.zangsisi.model.ComicModel;
import com.bigstark.zangsisi.rest.ZangsisiClient;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bigstark on 2016. 6. 20..
 */

public class HomeActivity extends BaseActivity implements ZangsisiClient.Callback<ComicModel> {


    @BindView(R.id.rv_comics) RecyclerView rvComics;
    private ComicAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Zangsisi");
        toolbar.setTitleTextColor(Color.WHITE);

        initRecycler();
        getComics();
    }

    private void initRecycler() {
        adapter = new ComicAdapter();
        rvComics.setLayoutManager(new LinearLayoutManager(this));
        rvComics.setAdapter(adapter);
    }


    private void getComics() {
        List<ComicModel> comics = ZangsisiRealmHelper.getInstance().getComics();
        if (comics != null) {
            adapter.setComics(comics);
            adapter.notifyDataSetChanged();
        }

        ZangsisiClient.getClient().getComics(this);
    }


    @Subscribe
    void onComicClicked(EventComicClicked event) {
        ComicModel comic = event.getComic();

        Intent intent = new Intent(Defines.ACTIVITY_COMIC);
        intent.putExtra(Defines.INTENT_COMIC_ID, comic.getComicId());
        intent.putExtra(Defines.INTENT_COMIC_TITLE, comic.getTitle());

        startActivity(intent);
    }


    @Override
    public void onSuccess(List<ComicModel> lists) {
        ZangsisiRealmHelper.getInstance().updateComics(lists);

        adapter.setComics(lists);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(Throwable t) {

    }
}
