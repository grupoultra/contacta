package com.sur.ultra.contacta;

import java.util.Date;

/**
 * Created by alexis on 5/24/16.
 */
public class CProvider {
    public String messageSummary;
    public Boolean connected;
    public String image;

    public CProvider(String messageSummary, Boolean connected, String image){
        this.messageSummary = messageSummary;
        this.connected = connected;
        this.image = image;
    }
}
