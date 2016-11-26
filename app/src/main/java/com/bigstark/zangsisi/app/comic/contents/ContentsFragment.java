package com.bigstark.zangsisi.app.comic.contents;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigstark.zangsisi.R;
import com.bigstark.zangsisi.model.ContentsModel;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bigstark on 2016. 6. 21..
 */

public class ContentsFragment extends Fragment {

    public static final String KEY_CONTENT_URL = "CONTENT_URL";


    public static ContentsFragment newInstance(ContentsModel contents) {
        Bundle argument = new Bundle();
        argument.putString(KEY_CONTENT_URL, contents.getContentsUrl());

        ContentsFragment fragment = new ContentsFragment();
        fragment.setArguments(argument);
        return fragment;
    }

    @BindView(R.id.iv_content) SimpleDraweeView ivContent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.contents_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        String contentUrl = getArguments().getString(KEY_CONTENT_URL);
        Uri uri = Uri.parse(contentUrl);
        ivContent.setImageURI(uri);
    }
}
