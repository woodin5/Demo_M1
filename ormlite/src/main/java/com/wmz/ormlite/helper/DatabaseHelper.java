package com.wmz.ormlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.wmz.ormlite.R;
import com.wmz.ormlite.bean.Group;
import com.wmz.ormlite.bean.User;
import com.wmz.ormlite.dao.GroupDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wmz on 21/5/16.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TABLE_NAME = "sqlite_test.db";
    private Map<String ,Dao> daos = new HashMap<String, Dao>();
    private Context context;
    private DatabaseHelper(Context context){
        super(context,TABLE_NAME,null,2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Group.class);
            TableUtils.createTable(connectionSource,User.class);
            createGroup();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createGroup() {
        String[] groups = context.getResources().getStringArray(R.array.group);
        GroupDao dao = new GroupDao(context);
        for(int i=0,size=groups.length;i<size;i++){
            dao.create(new Group(groups[i]));
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Group.class,true);
            TableUtils.dropTable(connectionSource,User.class,true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;
    public static synchronized DatabaseHelper getHelper(Context context){
        if(instance == null){
            synchronized (DatabaseHelper.class){
                if(instance == null){
                    instance = new DatabaseHelper(context);
                }
            }
        }
        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if(daos.containsKey(className)){
            dao = daos.get(className);
        }
        if(dao==null){
            dao = super.getDao(clazz);
            daos.put(className,dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        for(String key:daos.keySet()){
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
