package com.bigstark.zangsisi.app.comic.episode;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bigstark.zangsisi.Defines;
import com.bigstark.zangsisi.R;
import com.bigstark.zangsisi.app.base.BaseActivity;
import com.bigstark.zangsisi.db.ZangsisiRealmHelper;
import com.bigstark.zangsisi.model.EpisodeModel;
import com.bigstark.zangsisi.rest.ZangsisiClient;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicActivity extends BaseActivity implements ZangsisiClient.Callback<EpisodeModel> {

    private String comicId;

    @BindView(R.id.rv_episodes) RecyclerView rvEpisodes;
    private EpisodeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comic_main);

        comicId = getIntent().getStringExtra(Defines.INTENT_COMIC_ID);
        String comicTitle = getIntent().getStringExtra(Defines.INTENT_COMIC_TITLE);

        initToolbar(comicTitle);

        ButterKnife.bind(this);
        initRecycler();

        getEpisodes();
    }


    private void initToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(TextUtils.isEmpty(title) ? "Zangsisi" : title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initRecycler() {
        adapter = new EpisodeAdapter();
        rvEpisodes.setLayoutManager(new LinearLayoutManager(this));
        rvEpisodes.setAdapter(adapter);
    }


    private void getEpisodes() {
        if (TextUtils.isEmpty(comicId)) {
            return;
        }

        List<EpisodeModel> episodes = ZangsisiRealmHelper.getInstance().getEpisodes(comicId);
        if (episodes != null) {
            adapter.setEpisodes(episodes);
            adapter.notifyDataSetChanged();
        }

        ZangsisiClient.getClient().getEpisodes(comicId, this);
    }

    @Subscribe
    void onEpisodeClicked(EventEpisodeClicked event) {
        String episodeId = event.getEpisodeId();
        Intent intent = new Intent(Defines.ACTIVITY_CONTENTS);
        intent.putExtra(Defines.INTENT_EPISODE_ID, episodeId);
        startActivity(intent);
    }


    @Override
    public void onSuccess(List<EpisodeModel> lists) {
        ZangsisiRealmHelper.getInstance().updateEpisodes(comicId, lists);

        adapter.setEpisodes(lists);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(Throwable t) {

    }

}
