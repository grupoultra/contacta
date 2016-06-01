package com.sur.ultra.contacta.Models;

/**
 * Created by alexis on 6/1/16.
 */
public class Provider {

    private String id;
    private String name;
    private String info;
    private String avatar;

    public Provider(String id, String name, String info, String avatar) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getAvatar() {
        return avatar;
    }
}
