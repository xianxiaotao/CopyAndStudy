package com.xianxiaotao.copyandstudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xianxiaotao.copyandstudy.R;
import com.xianxiaotao.copyandstudy.db.Acts;
import com.xianxiaotao.copyandstudy.db.ActsManager;

import java.util.List;

/**
 * RecyclerView
 *
 * Created by xianxiaotao on 17/4/24.
 */
public class RecyclerNormalAdapter extends RecyclerView.Adapter<RecyclerNormalAdapter.ViewHolder> {

    private Context mContext;
    private List<Acts> mDatas;
    private XxtOnItemClickListener mXxtOnItemClickListener;

    public RecyclerNormalAdapter(Context mContext) {
        this(mContext, ActsManager.getInstance().getAll());
    }

    public RecyclerNormalAdapter(Context mContext, List<Acts> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTitleTextView.setText(mDatas.get(position).getTitle());
        holder.mDescTextView.setText(mDatas.get(position).getDesc());

        if (mXxtOnItemClickListener != null) {
            holder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mXxtOnItemClickListener.onItemClick(holder.rootView, holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView mTitleTextView;
        TextView mDescTextView;

        ViewHolder(View view) {
            super(view);
            rootView = view.findViewById(R.id.item_container_ll);
            mTitleTextView = (TextView) view.findViewById(R.id.item_list_tv_title);
            mDescTextView = (TextView) view.findViewById(R.id.item_list_tv_desc);
        }
    }

    public void setOnItemClickListener(XxtOnItemClickListener mXxtOnItemClickListener) {
        this.mXxtOnItemClickListener = mXxtOnItemClickListener;
    }

    public interface XxtOnItemClickListener {
        void onItemClick(View view, int position);
    }
}
