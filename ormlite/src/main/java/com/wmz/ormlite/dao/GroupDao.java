package com.wmz.ormlite.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.wmz.ormlite.bean.Group;
import com.wmz.ormlite.helper.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wmz on 25/5/16.
 */
public class GroupDao {
    private Context context;
    private Dao<Group,Integer> dao;
    private DatabaseHelper helper;

    public GroupDao(Context context){
        this.context = context;
            helper = DatabaseHelper.getHelper(context);
        try {
            dao = helper.getDao(Group.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Group, Integer> getDao() {
        return dao;
    }

    public void create(Group group){
        try {
            dao.create(group);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int delete(Group group){
        try {
            return dao.delete(group);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Group> queryForAll(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Group queryForId(Integer id){
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int update(Group group){
        try {
            return dao.update(group);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
