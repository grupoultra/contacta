package com.sur.ultra.contacta.Models;

import com.sur.ultra.contacta.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alexis on 5/31/16.
 */
public class Providers {

    protected HashMap<String, Provider> providers;

    public Providers() {
        this.providers = new HashMap<String, Provider>();

        String dummyInfo  = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed congue justo, vel pulvinar nunc. Morbi ultricies porta dolor a auctor. Mauris vel lorem imperdiet, sagittis leo at, faucibus justo. Vestibulum id lacus lorem. Proin ipsum sem, dignissim vel luctus porttitor, condimentum vel purus. Integer id efficitur arcu, quis venenatis neque. Proin metus dui, mattis ac ullamcorper ac, vehicula ut metus. Aenean mattis, nunc malesuada suscipit ultrices, leo diam porta nulla, quis iaculis elit augue vel odio. Sed dapibus augue et mi vulputate, auctor malesuada dui suscipit. Integer id urna fermentum, sollicitudin orci a, eleifend est. Quisque suscipit eget velit eget hendrerit. Fusce mi leo, tempus porta eleifend sed, aliquet sit amet eros. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Phasellus et dolor lacus. Integer hendrerit, tellus ac posuere congue, augue augue ultrices risus, quis convallis justo diam et urna. \nMaecenas congue, odio sed sodales dictum, diam risus efficitur nisl, ac venenatis nisl arcu et dolor. Etiam vel tristique turpis. Pellentesque lectus felis, porttitor at porttitor ut, cursus id mauris. Vivamus elit augue, porttitor vel vulputate nec, varius feugiat est. Vivamus et egestas diam, vel sollicitudin lorem. Vivamus diam libero, egestas et semper quis, iaculis sit amet enim. In hac habitasse platea dictumst. Proin pretium lacus ac ullamcorper finibus";

        this.addOne("1", "Banco Mercantil", dummyInfo, "avatar");
        this.addOne("2", "Cantv", dummyInfo, "avatar");
        this.addOne("3", "Movistar de Venezuela", dummyInfo, "avatar");
        this.addOne("4", "GMVV", dummyInfo, "avatar");
        this.addOne("5", "LaIguana.TV", dummyInfo, "avatar");
        this.addOne("6", "Banco Provincial", dummyInfo, "avatar");
    }

    public Provider getOne(String id){
        return this.providers.get(id);
    }

    public ArrayList<Provider> getAllList() {
        return new ArrayList<Provider>(this.providers.values());
    }

    public void addOne(String id, String name, String info, String avatar) {
        this.providers.put(id, new Provider(id, name, info, avatar));
    }

    public void removeOne(String id){
        this.providers.remove(id);
    }
}