package com.bigstark.zangsisi.rest;

import android.util.Log;

import com.bigstark.zangsisi.model.ComicModel;
import com.bigstark.zangsisi.model.ContentsModel;
import com.bigstark.zangsisi.model.EpisodeModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by bigstark on 2016. 6. 20..
 */

public class ZangsisiClient {

    private static volatile ZangsisiClient client;


    public static ZangsisiClient getClient() {
        if (client == null) {
            synchronized (ZangsisiClient.class) {
                if (client == null) {
                    client = new ZangsisiClient();
                }
            }
        }

        return client;
    }

    private ZangsisiService zangsisiService;


    public ZangsisiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://zangsisi.net/")
                .build();

        zangsisiService = retrofit.create(ZangsisiService.class);
    }


    public void getComics(final Callback<ComicModel> callback) {
        Call<ResponseBody> call = zangsisiService.getHtml("10707");
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String html = response.body().string();
                    callback.onSuccess(parseComicList(html));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFail(t);
            }
        });
    }


    private List<ComicModel> parseComicList(String html) {
        Document doc = Jsoup.parse(html);
        Elements mangaList = doc.getElementById("manga-list").getElementsByClass("lists");
        Elements endMangaList = doc.getElementsByClass("fixed-top").get(1).getElementById("recent-post").getElementsByClass("contents").get(0).getElementsByClass("tx-link");

        List<ComicModel> comics = new ArrayList<>(mangaList.size() + endMangaList.size());

        for (Element manga : mangaList) {
            ComicModel comic = new ComicModel();
            comic.setComicId(manga.attr("href").replace("http://zangsisi.net/?page_id=", ""));
            comic.setTitle(manga.text());

            comics.add(comic);
        }


        for (Element manga : endMangaList) {
            ComicModel comic = new ComicModel();
            comic.setComicId(manga.attr("href").replace("http://zangsisi.net/?page_id=", ""));
            comic.setTitle(manga.text());

            comics.add(comic);
        }

        return comics;
    }


    public void getEpisodes(String comicId, final Callback<EpisodeModel> callback) {
        Call<ResponseBody> call = zangsisiService.getHtml(comicId);
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String html = response.body().string();
                    callback.onSuccess(parseEpisodeList(html));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFail(t);
            }
        });
    }


    private List<EpisodeModel> parseEpisodeList(String html) {
        Document doc = Jsoup.parse(html);
        Elements episodeList = doc.getElementsByClass("fixed-top").get(1).getElementById("recent-post").getElementsByClass("contents").get(0).getElementsByTag("a");

        List<EpisodeModel> episodes = new ArrayList<>(episodeList.size());

        for (Element episode : episodeList) {
            EpisodeModel episodeModel = new EpisodeModel();
            episodeModel.setEpisodeId(episode.attr("href").replace("http://zangsisi.net/?p=", ""));

            episodeModel.setTitle(episode.text());

            episodes.add(episodeModel);
        }

        return episodes;
    }


    public void getContents(String episodeId, final Callback<ContentsModel> callback) {
        Call<ResponseBody> call = zangsisiService.getContentsHtml(episodeId);
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String html = response.body().string();
                    callback.onSuccess(parseContentsList(html));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFail(t);
            }
        });
    }


    private List<ContentsModel> parseContentsList(String html) {
        Document doc = Jsoup.parse(html);

        Elements contentsList = doc.getElementsByClass("fixed-top").get(1).getElementById("post").getElementsByClass("contents").get(0).getElementsByTag("img");

        List<ContentsModel> contents = new ArrayList<>(contentsList.size());
        for (Element content : contentsList) {
            ContentsModel contentsModel = new ContentsModel();
            contentsModel.setContentsUrl(content.attr("src"));

            Log.v("TAG", "url : " + content.attr("src"));

            contents.add(contentsModel);
        }


        return contents;
    }


    public interface Callback<T extends RealmObject> {
        public void onSuccess(List<T> lists);

        public void onFail(Throwable t);
    }


}
