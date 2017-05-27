package com.xianxiaotao.copyandstudy.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.xianxiaotao.copyandstudy.R;
import com.xianxiaotao.copyandstudy.util.FastBlurUtil;

/**
 * Created by xianxiaotao on 17/5/22.
 *
 * 实现毛玻璃效果
 * https://github.com/wingjay/BlurImageView
 */

public class BlurImageActivity extends AppCompatActivity {

    private ImageView girlIv;
    private Bitmap mBitmap;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (mBitmap != null) {
                        girlIv.setBackground(new BitmapDrawable(getResources(), mBitmap));
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_image);

        Toolbar mToolbar = (Toolbar) this.findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        girlIv = (ImageView) findViewById(R.id.blur_image_view);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(200);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 毛玻璃效果
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl_bg_2);
                        mBitmap = FastBlurUtil.doBlur(bitmap, seekBar.getProgress(), false);
                        bitmap.recycle();
                        mHandler.sendEmptyMessage(1);
                    }
                }).start();
            }
        });
    }
}
