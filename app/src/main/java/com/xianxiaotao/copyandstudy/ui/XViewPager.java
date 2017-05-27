package com.xianxiaotao.copyandstudy.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by xianxiaotao on 17/5/18.
 *
 * 解决ViewPager与DrawerLayout的冲突问题
 * 把第一个Fragment的ViewPager的右滑给禁掉
 */

public class XViewPager extends ViewPager {

    private static final int MIN_FLING_VELOCITY = 400; // dips
    private static final int MIN_DISTANCE_FOR_FLING = 25; // dips

    private int distance, xLast;
    private boolean noScroll = false;
    private OnFirstCurrentToLeftListener listener;

    private int mFlingDistance;

    public XViewPager(Context context) {
        super(context);
    }

    public XViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        final float density = context.getResources().getDisplayMetrics().density;
        mFlingDistance = (int) (MIN_DISTANCE_FOR_FLING * density);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (noScroll)
            return false;

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                distance = 0;
                xLast = (int)ev.getX();
                noScroll = false;
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                distance = (int) (xLast - curX);
                if (distance < -mFlingDistance && getCurrentItem() == 0) { // 禁止第一个右滑
                    if (listener != null)
                        listener.toLeft();
                    noScroll = true;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return !noScroll && super.onTouchEvent(ev);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    public void setOnFirstCurrentToLeftListener(OnFirstCurrentToLeftListener listener) {
        this.listener = listener;
    }

    public interface OnFirstCurrentToLeftListener {
        void toLeft();
    }
}
