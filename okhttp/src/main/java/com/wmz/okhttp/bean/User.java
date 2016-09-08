package com.wmz.okhttp.bean;

import java.io.Serializable;

/**
 * Created by wmz on 26/5/16.
 */
public class User implements Serializable {
    private int id;
    private String name;
    private Group group;

    public User() {
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group=" + group +
                '}';
    }
}
