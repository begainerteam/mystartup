package com.simplesite.komalauthor.bharatyuvasangh2017;

/**
 * Created by KOMAL on 07-07-2017.
 */

public class Blog {
    private String image;
    private String title;
    private String desc;


    private String username;


    public Blog() {

    }

    public Blog(String image, String title, String desc,String username) {
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.username=username;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

