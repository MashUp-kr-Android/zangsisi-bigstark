package com.bigstark.zangsisi.app.home;

import com.bigstark.zangsisi.model.ComicModel;

/**
 * Created by bigstark on 2016. 6. 21..
 */

public class EventComicClicked {

    private ComicModel comic;


    public EventComicClicked(ComicModel comic) {
        this.comic = comic;
    }


    public ComicModel getComic() {
        return comic;
    }
}
