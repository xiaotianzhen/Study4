package com.qianwang.selectdeletedemo.bean;

/**
 * Created by luo on 2017/5/16.
 */

public class RecyclerItem {

    private int resID;
    private String title;
    private String s_title;

    public RecyclerItem() {
    }

    public RecyclerItem(int resID, String title, String s_title) {
        this.resID = resID;
        this.title = title;
        this.s_title = s_title;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getS_title() {
        return s_title;
    }

    public void setS_title(String s_title) {
        this.s_title = s_title;
    }
    @Override
    public String toString() {
        return "RecyclerItem{" +
                "resID=" + resID +
                ", title='" + title + '\'' +
                ", s_title='" + s_title + '\'' +
                '}';
    }

}
