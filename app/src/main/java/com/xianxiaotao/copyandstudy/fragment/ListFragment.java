package com.xianxiaotao.copyandstudy.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xianxiaotao.copyandstudy.R;
import com.xianxiaotao.copyandstudy.adapter.RecyclerNormalAdapter;
import com.xianxiaotao.copyandstudy.db.Acts;
import com.xianxiaotao.copyandstudy.db.ActsManager;
import com.xianxiaotao.copyandstudy.ui.RecycleViewDivider;
import com.xianxiaotao.copyandstudy.util.CommonUtils;
import com.xianxiaotao.copyandstudy.xcopy.recycleranim.adapter.SlideInLeftAnimatorAdapter;

import java.util.List;

/**
 * Test Demos List
 *
 * Created by xianxiaotao on 17/4/24.
 */
public class ListFragment extends Fragment {

    public static final String EXTRA_KEY = "com.xxtking.demo.ListFragment.EXTRA_KEY";

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Integer dataType = (Integer) getArguments().getSerializable(EXTRA_KEY);
        if (dataType != null) {
            final List<Acts> datas = ActsManager.getInstance().getActs(dataType);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
            mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), RecycleViewDivider.VERTICAL_LIST,
                     CommonUtils.dp2px(getActivity(), 1), ContextCompat.getColor(getActivity(), R.color.color_line)));
            RecyclerNormalAdapter mAdapter = new RecyclerNormalAdapter(getActivity(), datas);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                SlideInLeftAnimatorAdapter slideInLeftAnimationRecyclerViewAdapter = new SlideInLeftAnimatorAdapter(mAdapter, mRecyclerView);
                mRecyclerView.setAdapter(slideInLeftAnimationRecyclerViewAdapter);
            } else {
                mRecyclerView.setAdapter(mAdapter);
            }

            mAdapter.setOnItemClickListener(new RecyclerNormalAdapter.XxtOnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    try {
                        Class<?> clazz = Class.forName(datas.get(position).getClassName());
                        startActivity(new Intent(getActivity(), clazz));
                    } catch (ClassNotFoundException e) {
                        Toast.makeText(getActivity(), "Open " + datas.get(position).getClassName() +
                                " failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        /* // 保存代码：使用系统的分割线API
        ColorDrawable drawable = new ColorDrawable(ContextCompat.getColor(getActivity(), R.color.color_line)) {
            public int getIntrinsicHeight() {
                return CommonUtils.dp2px(getActivity(), 1);
            }
        };
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        decoration.setDrawable(drawable);
        mRecyclerView.addItemDecoration(decoration);
        */
    }
}
