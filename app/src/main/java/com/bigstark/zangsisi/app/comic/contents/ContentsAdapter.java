package com.bigstark.zangsisi.app.comic.contents;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.bigstark.zangsisi.model.ContentsModel;

import java.util.List;

/**
 * Created by bigstark on 2016. 6. 21..
 */

public class ContentsAdapter extends FragmentStatePagerAdapter {


    private List<ContentsModel> contents;


    public ContentsAdapter(FragmentManager fm) {
        super(fm);
    }


    public void setContents(List<ContentsModel> contents) {
        this.contents = contents;
    }


    @Override
    public Fragment getItem(int position) {
        return ContentsFragment.newInstance(contents.get(position));
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return contents == null ? 0 : contents.size();
    }
}
