package com.example.administrator.cameraresultproject;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.cameraresultproject.gallery.GalleryPopUpWindow;
import com.leochuan.CircleScaleLayoutManager;
import com.leochuan.GalleryLayoutManager;

public class ResultActivity extends BaseActivity<GalleryLayoutManager, GalleryPopUpWindow> {
    private boolean isLinearLayout = true;

    @Override
    protected GalleryLayoutManager createLayoutManager() {
        return new GalleryLayoutManager(this, Util.Dp2px(this, 10));
    }

    @Override
    protected GalleryPopUpWindow createSettingPopUpWindow() {
        return new GalleryPopUpWindow(this, getViewPagerLayoutManager(), getRecyclerView());
    }

    int moveX2 = 0;
    int moveY2 = 0;
    int startX = 0;
    int startY = 0;
    private int mCurrentPosition;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                moveX2 = (int) ev.getX();
                moveY2 = (int) ev.getY();

                break;
            case MotionEvent.ACTION_UP:
                if (isLinearLayout) {
                    mCurrentPosition = getViewPagerLayoutManager().getCurrentPosition();
                }
                Log.d("ACTION_UP____child", "++++++++++" + mCurrentPosition);
                if (Math.abs((moveX2 - startX)) > 30 && moveX2 != 0) {
//                   左右滑 粘效果
                    RecyclerView mRecycleview = getRecyclerView();
                    if (mRecycleview.getChildCount() > 2) {
                        View child = mRecycleview.getChildAt(0);
                        View childSecond = mRecycleview.getChildAt(1);
                        int distance = Math.abs(child.getLeft() - childSecond.getLeft());

                        if (Math.abs((moveX2 - startX)) > distance / 2) {
                            int count = Math.abs((moveX2 - startX)) / distance;
                            if (moveX2 > startX) {
                                mCurrentPosition = mCurrentPosition == 1 ? 1 : mCurrentPosition - 1;

                            } else {
                                mCurrentPosition = mCurrentPosition == mRecycleview.getChildCount() ? mRecycleview.getChildCount() : mCurrentPosition + 1;
                            }
                        }
                        mRecycleview.scrollToPosition(mCurrentPosition);
                        Log.d("ACTION_UP____child", child.getLeft() + "+++++++" + childSecond.getLeft() + "++++++" + Math.abs((moveX2 - startX)) + "++++++++++" + mCurrentPosition);
                    }

                } else {

//                    点击抬起时切换模式
                    if (isLinearLayout) {
//                        圆弧
//                        DataAdapter dataAdapter = getAdapter();
//                        dataAdapter.setOnclick(new DataAdapter.Onclick() {
//
//                            @Override
//                            public void SetOnclick(ImageView imageView) {
//                                // 设置动画时长  动画未实现
//                            }
//                        });
                        mCurrentPosition = getViewPagerLayoutManager().getCurrentPosition();
                        int firstCount=getViewPagerLayoutManager().getChildCount();
                        CircleScaleLayoutManager mCircleScaleLayoutManager = new CircleScaleLayoutManager(ResultActivity.this);
                        mCircleScaleLayoutManager.setRadius(getWindowManager().getDefaultDisplay().getWidth() / 2);
                        setReLayoutManager(mCircleScaleLayoutManager);
                    } else {
//                        水平
                        setReLayoutManager(new GalleryLayoutManager(this, Util.Dp2px(this, 10)));
                    }
                    isLinearLayout = !isLinearLayout;

                }
                moveX2 = 0;
                moveY2 = 0;
                startX = 0;
                startY = 0;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
