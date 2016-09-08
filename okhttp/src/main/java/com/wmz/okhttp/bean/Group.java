package com.wmz.okhttp.bean;

import java.io.Serializable;

/**
 * Created by wmz on 26/5/16.
 */
public class Group implements Serializable{
    private int id;
    private String name;

    public Group() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
