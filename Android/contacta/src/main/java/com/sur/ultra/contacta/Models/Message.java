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
    public String author;
    public Date date;
    public String type;

    public Message(int id, String title, String body, String author, Date date, String type) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.date = date;
        this.type = type;
    }

    public final static HashMap<Integer, Message> MESSAGES = new HashMap<Integer, Message>();
    public final static HashMap<Integer, Message> NEWS = new HashMap<Integer, Message>();

    public static List<Message> getMESSAGES() {
        return new ArrayList<Message>(MESSAGES.values());
    }

    public static List<Message> getNEWS() {
        return new ArrayList<Message>(NEWS.values());
    }

    public static final String dummyBody =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed congue justo, vel" +
            "pulvinar nunc. Morbi ultricies porta dolor a auctor. Mauris vel lorem imperdiet, sagittis" +
            "leo at, faucibus justo. Vestibulum id lacus lorem. Proin ipsum sem, dignissim vel luctus" +
            "porttitor, condimentum vel purus. Integer id efficitur arcu, quis venenatis neque. Proin" +
            "metus dui, mattis ac ullamcorper ac, vehicula ut metus. Aenean mattis, nunc malesuada" +
            "suscipit ultrices, leo diam porta nulla, quis iaculis elit augue vel odio. Sed dapibus" +
            "augue et mi vulputate, auctor malesuada dui suscipit. Integer id urna fermentum," +
            "sollicitudin orci a, eleifend est. Quisque suscipit eget velit eget hendrerit. Fusce mi" +
            "leo, tempus porta eleifend sed, aliquet sit amet eros. Cum sociis natoque penatibus et" +
            "magnis dis parturient montes, nascetur ridiculus mus. Phasellus et dolor lacus. Integer" +
            "hendrerit, tellus ac posuere congue, augue augue ultrices risus, quis convallis justo diam" +
            "et urna Maecenas congue, odio sed sodales dictum, diam risus efficitur nisl, ac venenatis" +
            "nisl arcu et dolor. Etiam vel tristique turpis. Pellentesque lectus felis, porttitor at" +
            "porttitor ut, cursus id mauris. Vivamus elit augue, porttitor vel vulputate nec, varius" +
            "feugiat est. Vivamus et egestas diam, vel sollicitudin lorem. Vivamus diam libero, egestas" +
            "et semper quis, iaculis sit amet enim. In hac habitasse platea dictumst. Proin pretium" +
            "lacus ac ullamcorper finibus.";

    static {
        MESSAGES.put(1, new Message(1, "Message 1 " , "Message 1 " + dummyBody, "LaIguana.TV", new Date(), "message"));
        MESSAGES.put(2, new Message(2, "Message 2 " , "Message 2 " + dummyBody, "Banco Mercantil", new Date() , "message"));
        MESSAGES.put(3, new Message(3, "Message 3 " , "Message 3 " + dummyBody, "GMVV", new Date(), "message"));

        NEWS.put(4, new Message(4, "News 1 " , "News 1 " + dummyBody, "LaIguana.TV", new Date(), "news"));
        NEWS.put(5, new Message(5, "News 2 " , "News 2 " + dummyBody, "Banco Mercantil", new Date() , "news"));
        NEWS.put(6, new Message(6, "News 3 " , "News 3 " + dummyBody, "Banco Mercantil", new Date(), "news"));
        NEWS.put(7, new Message(7, "News 4 " , "News 4 " + dummyBody, "GMVV", new Date(), "news"));
        NEWS.put(8, new Message(8, "News 5 " , "News 5 " + dummyBody, "CANTV", new Date(), "news"));
        NEWS.put(9, new Message(9, "News 6 " , "News 6 " + dummyBody, "Banco Mercantil", new Date() , "news"));
        NEWS.put(10, new Message(10, "News 7 " , "News 7 " + dummyBody, "Movistar de Venezuela", new Date(), "news"));
        NEWS.put(11, new Message(11, "News 8 " , "News 8 " + dummyBody, "GMVV", new Date(), "news"));
        NEWS.put(12, new Message(12, "News 9 " , "News 9 " + dummyBody, "GMVV", new Date(), "news"));
        NEWS.put(13, new Message(13, "News 10 " , "News 10 " + dummyBody, "LaIguana.TV", new Date(), "news"));
        NEWS.put(14, new Message(14, "News 11 " , "News 11 " + dummyBody, "CANTV", new Date(), "news"));
        NEWS.put(15, new Message(15, "News 12 " , "News 12 " + dummyBody, "GMVV", new Date(), "news"));
        NEWS.put(16, new Message(16, "News 13 " , "News 13 " + dummyBody, "Banco Mercantil", new Date() , "news"));
        NEWS.put(17, new Message(17, "News 14 " , "News 14 " + dummyBody, "LaIguana.TV", new Date(), "news"));
    }
}
