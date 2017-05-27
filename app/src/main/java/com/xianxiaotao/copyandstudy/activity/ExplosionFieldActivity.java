package com.xianxiaotao.copyandstudy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.xianxiaotao.copyandstudy.R;

import tyrantgit.explosionfield.ExplosionField;

/**
 * Created by xianxiaotao on 17/5/19.
 *
 * 粒子效果
 * https://github.com/tyrantgit/ExplosionField
 */

public class ExplosionFieldActivity extends AppCompatActivity {

    private ExplosionField mExplosionField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explosion_field);
        mExplosionField = ExplosionField.attach2Window(this);
        addListener(findViewById(R.id.root));

        Toolbar mToolbar = (Toolbar) this.findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
    }

    private void addListener(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++)
                addListener(parent.getChildAt(i));
        } else {
            root.setClickable(true);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mExplosionField.explode(v);
                    v.setOnClickListener(null);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_explosion_field,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_reset) {
            View root = findViewById(R.id.root);
            reset(root);
            addListener(root);
            mExplosionField.clear();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reset(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                reset(parent.getChildAt(i));
            }
        } else {
            root.setScaleX(1);
            root.setScaleY(1);
            root.setAlpha(1);
        }
    }
}
