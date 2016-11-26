package com.bigstark.zangsisi.app.comic.episode;

/**
 * Created by bigstark on 2016. 6. 20..
 */

public class EventEpisodeClicked {
    private String episodeId;

    public EventEpisodeClicked(String episodeId) {
        this.episodeId = episodeId;
    }

    public String getEpisodeId() {
        return episodeId;
    }
}
