package com.qianwang.wechatfriends.bean;

/**
 * Created by luo on 2017/5/26.
 */

public class FriendsZone {

    private String name;
    private String icon;
    private String content;

    public FriendsZone(String name, String icon, String content) {
        this.name = name;
        this.icon = icon;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FriendsZone{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
