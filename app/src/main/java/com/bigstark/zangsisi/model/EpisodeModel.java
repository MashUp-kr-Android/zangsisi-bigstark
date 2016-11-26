package com.bigstark.zangsisi.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bigstark on 2016. 6. 20..
 */

public class EpisodeModel extends RealmObject {

    @PrimaryKey
    private String episodeId;

    private String title;
    private RealmList<ContentsModel> contents;


    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<ContentsModel> getContents() {
        return contents;
    }

    public void setContents(RealmList<ContentsModel> contents) {
        this.contents = contents;
    }


    @Override
    public String toString() {
        return "EpisodeModel{" +
                "episodeId='" + episodeId + '\'' +
                ", title='" + title + '\'' +
                ", contents=" + contents +
                '}';
    }
}
