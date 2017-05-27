package com.xianxiaotao.copyandstudy.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.xianxiaotao.copyandstudy.R;
import com.xianxiaotao.copyandstudy.adapter.FragmentAdapter;
import com.xianxiaotao.copyandstudy.db.Acts;
import com.xianxiaotao.copyandstudy.fragment.ListFragment;
import com.xianxiaotao.copyandstudy.ui.XViewPager;
import com.xianxiaotao.copyandstudy.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements XViewPager.OnFirstCurrentToLeftListener {

    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private XViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) this.findViewById(R.id.act_main_drawer_layout);
        mViewPager = (XViewPager) this.findViewById(R.id.act_main_view_pager);
        mViewPager.setOnFirstCurrentToLeftListener(this);

        TabLayout mTabLayout = (TabLayout) this.findViewById(R.id.act_main_tab);
        mTabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        mTabLayout.setSelectedTabIndicatorHeight(CommonUtils.dp2px(MainActivity.this, 2));

        Toolbar mToolbar = (Toolbar) this.findViewById(R.id.tool_bar);
        mToolbar.setNavigationIcon(R.drawable.ic_personal_center);
        setSupportActionBar(mToolbar);

        mNavigationView = (NavigationView) this.findViewById(R.id.act_main_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(naviListener);
        mNavigationView.setItemIconTintList(null);
        // 处理Viewpager与DrawerLayout滑动的冲突问题
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mViewPager.setNoScroll(true);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                mViewPager.setNoScroll(true);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                mViewPager.setNoScroll(false);  // 解除Viewpager的禁止滑动
            }
            @Override
            public void onDrawerStateChanged(int newState) {}
        });

        // 初始化TabLayout的title数据集
        List<String> titles = new ArrayList<>();
        titles.add(getResources().getString(R.string.item_tab_normal_list));
        titles.add(getResources().getString(R.string.item_tab_water_fall));
        titles.add(getResources().getString(R.string.item_tab_other_apps));

        // 初始化TabLayout的title
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));

        // 初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            ListFragment fragment = new ListFragment();
            Bundle args = new Bundle();
            if (i == 0)
                args.putSerializable(ListFragment.EXTRA_KEY, Acts.TYPE_TEST);
            else if (i == 1)
                args.putSerializable(ListFragment.EXTRA_KEY, Acts.TYPE_GOOGLE);
            else if (i == 2)
                args.putSerializable(ListFragment.EXTRA_KEY, Acts.TYPE_SHOW);

            fragment.setArguments(args);
            fragments.add(fragment);
        }

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 左侧抽屉menu的item事件处理，关闭并
     * 回到主界面选中的tab的fragment页
     */
    private NavigationView.OnNavigationItemSelectedListener naviListener =
            new NavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.menu_main_drawer_my_cv:
                            mViewPager.setCurrentItem(0);
                            break;
                        case R.id.menu_main_drawer_code_path:
                            mViewPager.setCurrentItem(1);
                            break;
                        case R.id.menu_main_drawer_successful_case:
                            mViewPager.setCurrentItem(2);
                            break;
                    }

                    mDrawerLayout.closeDrawer(mNavigationView);
                    return false;
                }
            };

    /**
     * 加载主界面右上角的menu菜单界面
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        return true;
    }

    /**
     * 左上角和右上角事件处理
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // 左上角
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.item_tab_normal_list: // 右上角第一项
                mViewPager.setCurrentItem(0);
                break;
            case R.id.item_tab_water_fall:  // 右上角第二项
                mViewPager.setCurrentItem(1);
                break;
            case R.id.item_tab_other_apps:  // 右上角第三项
                mViewPager.setCurrentItem(2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void toLeft() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }
}
