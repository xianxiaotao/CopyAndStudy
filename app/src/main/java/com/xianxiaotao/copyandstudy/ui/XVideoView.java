package com.xianxiaotao.copyandstudy.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 登录注册界面背景播放MP4
 * 便于设置为全屏
 */
public class XVideoView extends VideoView {
    public XVideoView(Context context) {
        super(context);
    }

    public XVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, heightSize);
        } else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
