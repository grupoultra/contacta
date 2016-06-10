package com.sur.ultra.contacta.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.sur.ultra.contacta.Models.Message;

/**
 * Created by alexis on 6/2/16.
 */
public class Message {
    public int id;
    public String messageSummary;
    public String author;
    public Date date;
    public String type;

    public Message(int id, String messageSummary, String author, Date date, String type) {
        this.id = id;
        this.messageSummary = messageSummary;
        this.author = author;
        this.date = date;
        this.type = type;
    }

    public final static List<Message> MESSAGES = new ArrayList<Message>();
    public final static List<Message> NEWS = new ArrayList<Message>();

    static {
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message(1, "Message 1", "LaIguana.TV", new Date(), "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message(2, "Message 2", "Banco Mercantil", new Date() , "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message(3, "Message 3", "GMVV", new Date(), "message"));

        NEWS.add(new com.sur.ultra.contacta.Models.Message(15, "News 1", "LaIguana.TV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(16, "News 2", "Banco Mercantil", new Date() , "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(17, "News 3", "Banco Mercantil", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(18, "News 4", "GMVV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(19, "News 5", "CANTV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(20, "News 6", "Banco Mercantil", new Date() , "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(21, "News 7", "Movistar de Venezuela", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(22, "News 8", "GMVV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(23, "News 9", "GMVV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(24, "News 10", "LaIguana.TV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(25, "News 11", "CANTV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(26, "News 12", "GMVV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(27, "News 13", "Banco Mercantil", new Date() , "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message(28, "News 14", "LaIguana.TV", new Date(), "news"));
    }
}
