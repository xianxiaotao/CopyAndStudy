package com.xianxiaotao.copyandstudy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ObbInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.xianxiaotao.copyandstudy.R;
import com.xianxiaotao.copyandstudy.util.SaveUtil;

public class WelcomeActivity extends AppCompatActivity {

    private Button enterButton;
    private String mColor;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    enterButton.setBackgroundColor(Color.parseColor(mColor));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // 全屏无标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        setContentView(R.layout.activity_welcome);

        WebView webView = (WebView) findViewById(R.id.activity_welcome_web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("file:////android_asset/www/welcome.html");
        webView.addJavascriptInterface(new JavaScriptInterface(), "xian");

        enterButton = (Button) findViewById(R.id.button_enter);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (SaveUtil.isFirstBoot(getApplicationContext())) {
                    intent = new Intent(WelcomeActivity.this, SplashActivity.class);
                    SaveUtil.setFirstBoot(getApplicationContext(), false);
                } else if (SaveUtil.isLogged(getApplicationContext())) {
                    intent = new Intent(WelcomeActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Created by xianxiaotao on 17/5/22.
     * android与js的交互
     */
    private class JavaScriptInterface {

        @JavascriptInterface
        public void setButtonColor(String color) {
            mColor = color;
            mHandler.sendEmptyMessage(1);
        }
    }
}
