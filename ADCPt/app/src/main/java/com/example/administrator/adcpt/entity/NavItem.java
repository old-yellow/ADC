package com.example.administrator.adcpt.entity;

/**
 * Created by Administrator on 2019/5/14.
 */

public class NavItem {

    private String itemTitle;

    private int itemId;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public NavItem (String itemTitle, int itemId) {
        this.itemTitle = itemTitle;
        this.itemId = itemId;
    }
}
