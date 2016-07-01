package com.huanliu.model;

import android.graphics.Bitmap;

/**
* Created by Administrator on 2016/6/28 0028.
*/
public class Video {
    public Bitmap icon;
    public String name;
    public String size;
    public String path;

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
