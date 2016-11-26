package com.bigstark.zangsisi.model;

import org.parceler.Parcel;

import io.realm.ContentsModelRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bigstark on 2016. 6. 20..
 */

@Parcel(implementations = { ContentsModelRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { ContentsModel.class })
public class ContentsModel extends RealmObject {

    @PrimaryKey
    private String contentsUrl;


    public String getContentsUrl() {
        return contentsUrl;
    }

    public void setContentsUrl(String contentsUrl) {
        this.contentsUrl = contentsUrl;
    }


    @Override
    public String toString() {
        return "ContentsModel{" +
                "contentsUrl='" + contentsUrl + '\'' +
                '}';
    }
}
