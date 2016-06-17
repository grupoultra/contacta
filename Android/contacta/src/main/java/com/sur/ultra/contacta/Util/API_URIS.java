package com.sur.ultra.contacta.Util;

/**
 * Created by alexis on 6/17/16.
 */
public class API_URIS {
    private static final String BASE_API_URI = "https://033j19n028.execute-api.us-east-1.amazonaws.com/beta/";

    public static String allProviders(){
        return BASE_API_URI + "providers";
    }
    public static String oneProvider(int id){
        return BASE_API_URI + "providers/" + id;
    }
}
