package com.bigstark.zangsisi.db;

import com.bigstark.zangsisi.model.ComicModel;
import com.bigstark.zangsisi.model.ContentsModel;
import com.bigstark.zangsisi.model.EpisodeModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by bigstark on 2016. 6. 21..
 */

public class ZangsisiRealmHelper {

    private static volatile ZangsisiRealmHelper instance;

    public static ZangsisiRealmHelper getInstance() {
        if (instance == null) {
            synchronized (ZangsisiRealmHelper.class) {
                if (instance == null) {
                    instance = new ZangsisiRealmHelper();
                }
            }
        }

        return instance;
    }


    public void updateComics(List<ComicModel> comics) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(comics);
        realm.commitTransaction();

        realm.close();
    }


    public List<ComicModel> getComics() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ComicModel> results = realm.where(ComicModel.class).findAll();
        return new ArrayList<>(results);
    }


    public ComicModel getComic(String comicId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ComicModel> results = realm.where(ComicModel.class).equalTo("comicId", comicId).findAll();
        return results.get(0);
    }


    public void updateEpisodes(String comicId, List<EpisodeModel> episodes) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        ComicModel comic = getComic(comicId);
        comic.getEpisodes().addAll(episodes);
        realm.commitTransaction();

        realm.close();
    }


    public List<EpisodeModel> getEpisodes(String comicId) {
        return new ArrayList<>(getComic(comicId).getEpisodes());
    }


    public EpisodeModel getEpisode(String episodeId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<EpisodeModel> results = realm.where(EpisodeModel.class).equalTo("episodeId", episodeId).findAll();
        return results.get(0);
    }


    public void updateContents(String episodeId, List<ContentsModel> contents) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        EpisodeModel episode = getEpisode(episodeId);
        episode.getContents().addAll(contents);
        realm.commitTransaction();

        realm.close();
    }


    public List<ContentsModel> getContents(String episodeId) {
        return new ArrayList<>(getEpisode(episodeId).getContents());
    }

}
