package com.sur.ultra.contacta.Models;

import com.sur.ultra.contacta.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alexis on 6/2/16.
 */
public class Message {
    public int id;
    public String title;
    public String body;
    public String name;
    public String date;
    public String avatar;
    public String type;

    public Message(int id, String title, String body, String author, String date, String type) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.name = author;
        this.date = date;
        this.avatar = type;
    }

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }
    public String getType() {
        return type;
    }


}
