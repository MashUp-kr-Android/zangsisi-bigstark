package com.bigstark.zangsisi.app.comic.contents;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.bigstark.zangsisi.Defines;
import com.bigstark.zangsisi.R;
import com.bigstark.zangsisi.app.base.BaseActivity;
import com.bigstark.zangsisi.db.ZangsisiRealmHelper;
import com.bigstark.zangsisi.model.ContentsModel;
import com.bigstark.zangsisi.rest.ZangsisiClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bigstark on 2016. 6. 20..
 */

public class ContentsActivity extends BaseActivity implements ZangsisiClient.Callback<ContentsModel>{

    private static final String KEY_CURRENT_POSITION = "CURRENT_POSITION";


    private String episodeId;

    @BindView(R.id.vp_contents) ViewPager vpContents;
    private ContentsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(   WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FULLSCREEN  );
        setContentView(R.layout.contents_main);
        ButterKnife.bind(this);

        initPager();

        episodeId = getIntent().getStringExtra(Defines.INTENT_EPISODE_ID);
        List<ContentsModel> contents = ZangsisiRealmHelper.getInstance().getContents(episodeId);

        if (contents == null || contents.size() == 0) {
            getContents(episodeId);
            return;
        }

        int currentPosition = 0;
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION);
        }

        adapter.setContents(ZangsisiRealmHelper.getInstance().getContents(episodeId));
        adapter.notifyDataSetChanged();

        vpContents.setCurrentItem(currentPosition);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_CURRENT_POSITION, vpContents.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    private void initPager() {
        adapter = new ContentsAdapter(getSupportFragmentManager());
        vpContents.setAdapter(adapter);
        vpContents.setOffscreenPageLimit(3);
    }


    private void getContents(String episodeId) {
        if (TextUtils.isEmpty(episodeId)) {
            return;
        }

        ZangsisiClient.getClient().getContents(episodeId, this);
    }

    @Override
    public void onSuccess(List<ContentsModel> lists) {
        ZangsisiRealmHelper.getInstance().updateContents(episodeId, lists);

        adapter.setContents(lists);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(Throwable t) {

    }
}
