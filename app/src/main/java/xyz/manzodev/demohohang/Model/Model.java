package xyz.manzodev.demohohang.Model;

import java.io.Serializable;

public class Model implements Serializable{
    int id;
    String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Model(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Model() {
    }
}
