package com.back4app.quickstartexampleapp;

import android.graphics.Bitmap;

import java.io.File;

public class PostClass {

    private String title, username, idPost;
    private Bitmap img;

    public PostClass() {
    }

    public PostClass(String idPost, String title, String username, Bitmap img) {
        this.idPost = idPost;
        this.title = title;
        this.username = username;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }
}
