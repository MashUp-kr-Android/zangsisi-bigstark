package com.bigstark.zangsisi;

import android.app.Application;

import com.bigstark.zangsisi.model.ComicModel;
import com.bigstark.zangsisi.model.ContentsModel;
import com.bigstark.zangsisi.model.EpisodeModel;
import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.annotations.RealmModule;

/**
 * Created by bigstark on 2016. 6. 20..
 */

public class ZangsisiApplication extends Application {
    private static final int REALM_SCHEMA_VERSION = 1;
    private static ZangsisiApplication instance;

    public static ZangsisiApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);
        initRealm();
    }

    // initialize Realm Configuration
    private void initRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("Zangsisi.realm")
                .schemaVersion(REALM_SCHEMA_VERSION)
                .modules(new ZangsisiModule())
                .build();
        Realm.setDefaultConfiguration(config);
    }

    @RealmModule(classes = {
            ComicModel.class,
            EpisodeModel.class,
            ContentsModel.class
    })
    private class ZangsisiModule{}
}
