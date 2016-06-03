package com.sur.ultra.contacta.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexis on 6/2/16.
 */
public class Message {
    public String messageSummary;
    public String author;
    public Date date;
    public String type;

    public Message(String messageSummary, String author, Date date, String type) {
        this.messageSummary = messageSummary;
        this.author = author;
        this.date = date;
        this.type = type;
    }

    public final static List<com.sur.ultra.contacta.Models.Message> MESSAGES = new ArrayList<com.sur.ultra.contacta.Models.Message>();
    public final static List<com.sur.ultra.contacta.Models.Message> NEWS = new ArrayList<com.sur.ultra.contacta.Models.Message>();

    static {
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Cras sed velit sed velit viverra mollis.", "LaIguana.TV", new Date(), "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Donec congue ligula vel auctor faucibus.", "Banco Mercantil", new Date() , "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Aenean et diam dignissim, facilisis urna eget, venenatis urna.", "Banco Mercantil", new Date(), "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Sed at leo vehicula, rhoncus nulla vitae, dictum odio", "GMVV", new Date(), "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Aliquam eu nisl eu magna euismod ullamcorper.", "CANTV", new Date(), "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Donec congue ligula vel auctor faucibus.", "Banco Mercantil", new Date() , "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Nam eleifend augue eget lorem dapibus tincidunt.", "Movistar de Venezuela", new Date(), "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Proin sed massa a nisl pellentesque mattis.", "GMVV", new Date(), "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Sed at leo vehicula, rhoncus nulla vitae, dictum odio", "GMVV", new Date(), "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Cras sed velit sed velit viverra mollis.", "LaIguana.TV", new Date(), "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Nulla auctor ante ac diam ultrices blandit in gravida arcu.", "CANTV", new Date(), "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Sed at leo vehicula, rhoncus nulla vitae, dictum odio", "GMVV", new Date(), "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Donec congue ligula vel auctor faucibus.", "Banco Mercantil", new Date() , "message"));
        MESSAGES.add(new com.sur.ultra.contacta.Models.Message("Cras sed velit sed velit viverra mollis.", "LaIguana.TV", new Date(), "message"));
    
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Cras sed velit sed velit viverra mollis.", "LaIguana.TV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Donec congue ligula vel auctor faucibus.", "Banco Mercantil", new Date() , "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Aenean et diam dignissim, facilisis urna eget, venenatis urna.", "Banco Mercantil", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Sed at leo vehicula, rhoncus nulla vitae, dictum odio", "GMVV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Aliquam eu nisl eu magna euismod ullamcorper.", "CANTV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Donec congue ligula vel auctor faucibus.", "Banco Mercantil", new Date() , "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Nam eleifend augue eget lorem dapibus tincidunt.", "Movistar de Venezuela", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Proin sed massa a nisl pellentesque mattis.", "GMVV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Sed at leo vehicula, rhoncus nulla vitae, dictum odio", "GMVV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Cras sed velit sed velit viverra mollis.", "LaIguana.TV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Nulla auctor ante ac diam ultrices blandit in gravida arcu.", "CANTV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Sed at leo vehicula, rhoncus nulla vitae, dictum odio", "GMVV", new Date(), "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Donec congue ligula vel auctor faucibus.", "Banco Mercantil", new Date() , "news"));
        NEWS.add(new com.sur.ultra.contacta.Models.Message("Cras sed velit sed velit viverra mollis.", "LaIguana.TV", new Date(), "news"));
    }
}
