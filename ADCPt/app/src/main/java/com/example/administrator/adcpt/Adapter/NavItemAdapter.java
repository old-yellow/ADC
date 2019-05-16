package com.example.administrator.adcpt.adapter;

import android.provider.ContactsContract;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.adcpt.R;
import com.example.administrator.adcpt.entity.NavItem;

import java.util.List;

/**
 * Created by Administrator on 2019/5/14.
 * 导航栏具体条目适配器
 */

public class NavItemAdapter extends RecyclerView.Adapter<NavItemAdapter.ViewHolder> {

    private List<NavItem> mNavItemList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImg;
        TextView itemTitle;

        public ViewHolder(View view) {
            super(view);
            itemImg = (ImageView)view.findViewById(R.id.nav_item_img);
            itemTitle = (TextView)view.findViewById(R.id.nav_item_title);
        }
    }

    public NavItemAdapter(List<NavItem> navItemList) {
        mNavItemList = navItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.navigation_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NavItem navItem = mNavItemList.get(position);
        holder.itemImg.setImageResource(navItem.getItemId());
        holder.itemTitle.setText(navItem.getItemTitle());
    }

    @Override
    public int getItemCount() {
        return mNavItemList.size();
    }
}
