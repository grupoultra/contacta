package com.sur.ultra.contacta.Models;

import com.sur.ultra.contacta.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis on 6/2/16.
 */
public class Provider {
    private String id;
    private String name;
    private String info;
    private String avatar;

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getId() {
        return id;
    }

    public Provider(String id, String name, String info, String avatar) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.avatar = avatar;
    }


    public static final List<Provider> PROVEEDORES = new ArrayList<Provider>();

    private static String dummyInfo  = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sed congue justo, vel pulvinar nunc. Morbi ultricies porta dolor a auctor. Mauris vel lorem imperdiet, sagittis leo at, faucibus justo. Vestibulum id lacus lorem. Proin ipsum sem, dignissim vel luctus porttitor, condimentum vel purus. Integer id efficitur arcu, quis venenatis neque. Proin metus dui, mattis ac ullamcorper ac, vehicula ut metus. Aenean mattis, nunc malesuada suscipit ultrices, leo diam porta nulla, quis iaculis elit augue vel odio. Sed dapibus augue et mi vulputate, auctor malesuada dui suscipit. Integer id urna fermentum, sollicitudin orci a, eleifend est. Quisque suscipit eget velit eget hendrerit. Fusce mi leo, tempus porta eleifend sed, aliquet sit amet eros. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Phasellus et dolor lacus. Integer hendrerit, tellus ac posuere congue, augue augue ultrices risus, quis convallis justo diam et urna. \nMaecenas congue, odio sed sodales dictum, diam risus efficitur nisl, ac venenatis nisl arcu et dolor. Etiam vel tristique turpis. Pellentesque lectus felis, porttitor at porttitor ut, cursus id mauris. Vivamus elit augue, porttitor vel vulputate nec, varius feugiat est. Vivamus et egestas diam, vel sollicitudin lorem. Vivamus diam libero, egestas et semper quis, iaculis sit amet enim. In hac habitasse platea dictumst. Proin pretium lacus ac ullamcorper finibus";

    static {
        PROVEEDORES.add(new Provider("1", "Banco Mercantil", dummyInfo, "avatar"));
        PROVEEDORES.add(new Provider("2", "Cantv", dummyInfo, "avatar"));
        PROVEEDORES.add(new Provider("3", "Movistar de Venezuela", dummyInfo, "avatar"));
        PROVEEDORES.add(new Provider("4", "GMVV", dummyInfo, "avatar"));
        PROVEEDORES.add(new Provider("5", "LaIguana.TV", dummyInfo, "avatar"));
        PROVEEDORES.add(new Provider("6", "Banco Provincial", dummyInfo, "avatar"));
    }

}

