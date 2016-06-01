package com.sur.ultra.contacta.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis on 5/31/16.
 */
public class Providers {

    protected ArrayList<Provider> providers;

    public Providers() {
        this.providers = new ArrayList<Provider>();

        this.addOne("1", "Banco Mercantil", "Este es Banco Mercantil", "avatar");
        this.addOne("2", "Cantv", "Este es Cantv", "avatar");
        this.addOne("3", "Movistar de Venezuela", "Este es Movistar de Venezuela", "avatar");
        this.addOne("4", "GMVV", "Este es GMVV", "avatar");
        this.addOne("5", "LaIguana.TV", "Este es LaIguana.TV", "avatar");
        this.addOne("6", "Banco Provincial", "Este es Banco Provincial", "avatar");
    }

    public ArrayList<Provider> getAll() {
        return providers;
    }

    public void addOne(String id, String name, String info, String avatar) {
        this.providers.add(new Provider(id, name, info, avatar));
    }
}