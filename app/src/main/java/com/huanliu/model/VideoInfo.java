package com.huanliu.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class VideoInfo implements Serializable{
        private String name;
        private String path;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }


    