package com.wmz.ormlite.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.wmz.ormlite.bean.User;
import com.wmz.ormlite.helper.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wmz on 21/5/16.
 */
public class UserDao {

    private Context context;
    private Dao<User,Integer> dao;
    private DatabaseHelper helper;

    public UserDao(Context context){
        this.context = context;
        helper = DatabaseHelper.getHelper(context);
        try {
            dao = helper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<User, Integer> getDao() {
        return dao;
    }

    public int create(User user){
        try {
           return dao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int delete(User user){
        try {
           return dao.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<User> queryForAll(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User queryForId(Integer id){
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int update(User user){
        try {
            return dao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
