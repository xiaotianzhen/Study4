package com.qianwang.meituanmeun.bean;

/**
 * Created by luo on 2017/5/12.
 */

public class Menu {

    private String menuName;
    private int iconId;

    public Menu() {

    }

    public Menu(String menuName, int iconId) {
        this.menuName = menuName;
        this.iconId = iconId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getIconId() {
        return iconId;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuName='" + menuName + '\'' +
                ", iconId=" + iconId +
                '}';
    }
}
