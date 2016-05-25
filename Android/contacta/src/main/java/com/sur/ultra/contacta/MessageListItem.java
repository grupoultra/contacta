package com.sur.ultra.contacta;

import java.util.Date;

/**
 * Created by alexis on 5/24/16.
 */
public class MessageListItem {
    public String messageSummary;
    public String author;
    public Boolean connected;
    public String image;
    public Date date;

    public MessageListItem(String messageSummary, String author, Boolean connected, String image, Date date){
        this.messageSummary = messageSummary;
        this.author = author;
        this.connected = connected;
        this.image = image;
        this.date = date;
    }
}
