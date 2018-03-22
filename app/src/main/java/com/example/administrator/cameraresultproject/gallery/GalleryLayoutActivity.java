package com.example.administrator.cameraresultproject.gallery;

import com.example.administrator.cameraresultproject.BaseActivity;
import com.example.administrator.cameraresultproject.Util;
import com.leochuan.GalleryLayoutManager;


/**
 * Created by Dajavu on 27/10/2017.
 */

public class GalleryLayoutActivity extends BaseActivity<GalleryLayoutManager, GalleryPopUpWindow> {

    @Override
    protected GalleryLayoutManager createLayoutManager() {
        return new GalleryLayoutManager(this, Util.Dp2px(this, 30));
    }

    @Override
    protected GalleryPopUpWindow createSettingPopUpWindow() {
        return new GalleryPopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }
}
