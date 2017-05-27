package com.xianxiaotao.copyandstudy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.xianxiaotao.copyandstudy.R;
import com.xianxiaotao.copyandstudy.ui.ProgressWheel;
import com.xianxiaotao.copyandstudy.ui.PieProgress;


/**
 * Created by xianxiaotao on 17/5/20.
 *
 * 一些常用的Android loadding
 */

public class CustomLoadingActivity extends AppCompatActivity {

    private ProgressWheel pwOne, pwTwo;
    private PieProgress mPieProgress1, mPieProgress2;
    boolean wheelRunning, pieRunning;
    int wheelProgress = 0, pieProgress = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_loading);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        pwOne = (ProgressWheel) findViewById(R.id.progress_bar_one);
        pwOne.spin();
        pwTwo = (ProgressWheel) findViewById(R.id.progress_bar_two);
        new Thread(r).start();

        mPieProgress1 = (PieProgress) findViewById(R.id.pie_progress1);
        mPieProgress2 = (PieProgress) findViewById(R.id.pie_progress2);
        new Thread(indicatorRunnable).start();


        Button startBtn = (Button) findViewById(R.id.btn_start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!wheelRunning) {
                    wheelProgress = 0;
                    pwTwo.resetCount();
                    new Thread(r).start();
                }
            }
        });

        Button pieStartBtn = (Button) findViewById(R.id.btn_start2);
        pieStartBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!pieRunning) {
                    pieProgress = 0;
                    new Thread(indicatorRunnable).start();
                }
            }
        });
    }

    final Runnable r = new Runnable() {
        public void run() {
            wheelRunning = true;
            while (wheelProgress < 361) {
                pwTwo.incrementProgress();
                wheelProgress++;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            wheelRunning = false;
        }
    };

    final Runnable indicatorRunnable = new Runnable() {
        public void run() {
            pieRunning = true;
            while (pieProgress < 361) {
                mPieProgress1.setProgress(pieProgress);
                mPieProgress2.setProgress(pieProgress);
                pieProgress += 2;;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pieRunning = false;
        }
    };

}
