package com.wmz.ormlite.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by wmz on 25/5/16.
 */
@DatabaseTable
public class Group implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
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
        return name!=null?name:"";
    }
}
