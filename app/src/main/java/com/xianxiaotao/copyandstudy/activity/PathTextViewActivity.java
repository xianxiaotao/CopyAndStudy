package com.xianxiaotao.copyandstudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xianxiaotao.copyandstudy.R;
import com.xianxiaotao.copyandstudy.ui.MatchPath;
import com.xianxiaotao.copyandstudy.ui.PathTextView;

/**
 * Created by xianxiaotao on 17/5/19.
 *
 * PathEffectTextView写字效果
 * android 5.0
 */

public class PathTextViewActivity extends AppCompatActivity {

    private PathTextView mPathTextView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_textview);

        Toolbar mToolbar = (Toolbar) this.findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        mPathTextView = (PathTextView) findViewById(R.id.path);
        mEditText = (EditText) findViewById(R.id.edit);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mText = mEditText.getText().toString();
                if (MatchPath.getPath(mText) != null)
                    mPathTextView.init(mText);
                else
                    Toast.makeText(PathTextViewActivity.this, "包含不能识别的字符", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
