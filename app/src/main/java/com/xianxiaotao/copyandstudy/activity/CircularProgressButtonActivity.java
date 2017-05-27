package com.xianxiaotao.copyandstudy.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.dd.CircularProgressButton;
import com.xianxiaotao.copyandstudy.R;

/**
 * Created by xianxiaotao on 17/5/19.
 *
 * 挺赞的带进度动效Button
 * https://github.com/dmytrodanylyk/circular-progress-button
 */

public class CircularProgressButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_progress_button);

        Toolbar mToolbar = (Toolbar) this.findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        final CircularProgressButton circularButton1 = (CircularProgressButton) findViewById(R.id.circularButton1);
        circularButton1.setIndeterminateProgressMode(true);
        circularButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton1.getProgress() == 0) {
                    circularButton1.setProgress(50);
                } else if (circularButton1.getProgress() == 100) {
                    circularButton1.setProgress(0);
                } else {
                    circularButton1.setProgress(100);
                }
            }
        });

        final CircularProgressButton circularButton2 = (CircularProgressButton) findViewById(R.id.circularButton2);
        circularButton2.setIndeterminateProgressMode(true);
        circularButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton2.getProgress() == 0) {
                    circularButton2.setProgress(50);
                } else if (circularButton2.getProgress() == -1) {
                    circularButton2.setProgress(0);
                } else {
                    circularButton2.setProgress(-1);
                }
            }
        });

        final CircularProgressButton circularButton3 = (CircularProgressButton) findViewById(R.id.circularButton3);
        circularButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton3.getProgress() == 0) {
                    simulateSuccessProgress(circularButton3);
                } else {
                    circularButton3.setProgress(0);
                }
            }
        });

        final CircularProgressButton circularButton4 = (CircularProgressButton) findViewById(R.id.circularButton4);
        circularButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton4.getProgress() == 0) {
                    simulateErrorProgress(circularButton4);
                } else {
                    circularButton4.setProgress(0);
                }
            }
        });

        final CircularProgressButton circularButton5 = (CircularProgressButton) findViewById(R.id.circularButton5);
        circularButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton5.getProgress() == 0) {
                    circularButton5.setProgress(100);
                } else {
                    circularButton5.setProgress(0);
                }
            }
        });

        final CircularProgressButton circularButton6 = (CircularProgressButton) findViewById(R.id.circularButton6);
        circularButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton6.getProgress() == 0) {
                    circularButton6.setProgress(-1);
                } else {
                    circularButton6.setProgress(0);
                }
            }
        });

        final CircularProgressButton circularButton7 = (CircularProgressButton) findViewById(R.id.circularButton7);
        circularButton7.setIndeterminateProgressMode(true);
        circularButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton7.getProgress() == 0) {
                    circularButton7.setProgress(50);
                } else if (circularButton7.getProgress() == 100) {
                    circularButton7.setProgress(0);
                } else {
                    circularButton7.setProgress(100);
                }
            }
        });

        final CircularProgressButton circularButton8 = (CircularProgressButton) findViewById(R.id.circularButton8);
        circularButton8.setIndeterminateProgressMode(true);
        circularButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton8.getProgress() == 0) {
                    circularButton8.setProgress(50);
                } else if (circularButton8.getProgress() == -1) {
                    circularButton8.setProgress(0);
                } else {
                    circularButton8.setProgress(-1);
                }
            }
        });

        final CircularProgressButton circularButton9 = (CircularProgressButton) findViewById(R.id.circularButton9);
        circularButton9.setIndeterminateProgressMode(true);
        circularButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton9.getProgress() == 0) {
                    circularButton9.setProgress(50);
                } else if (circularButton9.getProgress() == 100) {
                    circularButton9.setProgress(0);
                } else {
                    circularButton9.setProgress(100);
                }
            }
        });

        final CircularProgressButton circularButton10 = (CircularProgressButton) findViewById(R.id.circularButton10);
        circularButton10.setIndeterminateProgressMode(true);
        circularButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton10.getProgress() == 0) {
                    circularButton10.setProgress(50);
                } else if (circularButton10.getProgress() == -1) {
                    circularButton10.setProgress(0);
                } else {
                    circularButton10.setProgress(-1);
                }
            }
        });
    }


    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
            }
        });
        widthAnimation.start();
    }

    private void simulateErrorProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if (value == 99) {
                    button.setProgress(-1);
                }
            }
        });
        widthAnimation.start();
    }

}
