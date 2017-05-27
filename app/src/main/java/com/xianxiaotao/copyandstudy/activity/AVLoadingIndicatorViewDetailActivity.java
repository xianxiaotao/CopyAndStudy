package com.xianxiaotao.copyandstudy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;
import com.xianxiaotao.copyandstudy.R;

/**
 * Created by xianxiaotao on 17/5/19.
 *
 * Nice loading animations for Android
 * https://github.com/81813780/AVLoadingIndicatorView
 */

public class AVLoadingIndicatorViewDetailActivity extends AppCompatActivity {

    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avloading_indicator_detail);

        String indicator= getIntent().getStringExtra("indicator");
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator(indicator);
    }

    public void hideClick(View view) {
        avi.hide();
        // or avi.smoothToHide();
    }

    public void showClick(View view) {
        avi.show();
        // or avi.smoothToShow();
    }
}
