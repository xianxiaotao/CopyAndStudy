package com.xianxiaotao.copyandstudy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.bigkoo.svprogresshud.listener.OnDismissListener;
import com.xianxiaotao.copyandstudy.R;

/**
 * Created by xianxiaotao on 17/5/19.
 *
 * 仿IOS SVProgressHUD 控件库
 * https://github.com/saiwu-bigkoo/Android-SVProgressHUD
 */

public class SVProgressHUDActivity extends AppCompatActivity {

    private SVProgressHUD mSVProgressHUD;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svprogress_hud);

        Toolbar mToolbar = (Toolbar) this.findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        mSVProgressHUD = new SVProgressHUD(this);
        mSVProgressHUD.setOnDismissListener(new OnDismissListener(){
            @Override
            public void onDismiss(SVProgressHUD hud) {
                // todo something, like: finish current activity
                Toast.makeText(getApplicationContext(),"dismiss",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void show(View view){
        mSVProgressHUD.show();
    }
    public void showWithMaskType(View view){
        mSVProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.None);
//        mSVProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.Black);
//        mSVProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.BlackCancel);
//        mSVProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.Clear);
//        mSVProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.ClearCancel);
//        mSVProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.Gradient);
//        mSVProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
    }
    public void showWithStatus(View view){
        mSVProgressHUD.showWithStatus("加载中...");
    }
    public void showInfoWithStatus(View view){
        mSVProgressHUD.showInfoWithStatus("这是提示", SVProgressHUD.SVProgressHUDMaskType.None);
    }
    public void showSuccessWithStatus(View view){
        mSVProgressHUD.showSuccessWithStatus("恭喜，提交成功！");
    }
    public void showErrorWithStatus(View view){
        mSVProgressHUD.showErrorWithStatus("不约，叔叔我们不约～", SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress = progress + 5;
            if (mSVProgressHUD.getProgressBar().getMax() != mSVProgressHUD.getProgressBar().getProgress()) {
                mSVProgressHUD.getProgressBar().setProgress(progress);
                mSVProgressHUD.setText("进度 "+progress+"%");

                mHandler.sendEmptyMessageDelayed(0,500);
            } else {
                mSVProgressHUD.dismiss();
            }
        }
    };

    public void showWithProgress(View view) {
        progress = 0;
        mSVProgressHUD.getProgressBar().setProgress(progress);//先重设了进度再显示，避免下次再show会先显示上一次的进度位置所以要先将进度归0
        mSVProgressHUD.showWithProgress("进度 " + progress + "%", SVProgressHUD.SVProgressHUDMaskType.Black);
        mHandler.sendEmptyMessageDelayed(0,500);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if(mSVProgressHUD.isShowing()){
                mSVProgressHUD.dismiss();
                mHandler.removeCallbacksAndMessages(null);
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
