package com.bigstark.zangsisi.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bigstark on 2016. 6. 20..
 */

public class ComicModel extends RealmObject {

    @PrimaryKey
    private String comicId;

    private String title;
    private RealmList<EpisodeModel> episodes;


    public String getComicId() {
        return comicId;
    }

    public void setComicId(String comicId) {
        this.comicId = comicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<EpisodeModel> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(RealmList<EpisodeModel> episodes) {
        this.episodes = episodes;
    }


    @Override
    public String toString() {
        return "ComicModel{" +
                "comicId='" + comicId + '\'' +
                ", title='" + title + '\'' +
                ", episodes=" + episodes +
                '}';
    }
}
