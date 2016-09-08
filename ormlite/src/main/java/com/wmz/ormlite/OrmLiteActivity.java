package com.wmz.ormlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.wmz.ormlite.bean.Group;
import com.wmz.ormlite.bean.User;
import com.wmz.ormlite.dao.GroupDao;
import com.wmz.ormlite.dao.UserDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class OrmLiteActivity extends Activity {

    private final String TAG = OrmLiteActivity.this.getClass().getSimpleName();

    private UserDao userDao;
    private ListView listView;
    private TextView mTvshow;
    private Spinner spinner;
    /**
     * 插入数据
     */
    private Button mBtnInsert;
    /**
     * 删除数据
     */
    private Button mBtnDelete;
    /**
     * 查询数据
     */
    private Button mBtnQuery;
    /**
     * 更新数据
     */
    private Button mBtnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.content_main);
        initView();
        initDao();
        initData();
        initListener();
    }

    private List<User> userList = null;
    private void initData() {
        GroupDao groupDao = new GroupDao(this);
        List<Group> groupList = groupDao.queryForAll();
        ArrayAdapter<Group> groupAdapter = new ArrayAdapter<Group>(this,android.R.layout.simple_spinner_item,groupList);
        spinner.setAdapter(groupAdapter);

        UserDao userDao = new UserDao(this);
        QueryBuilder<User,Integer> builder = userDao.getDao().queryBuilder();
        builder.orderBy("date",false).limit(10L);
        try {
            userList = userDao.getDao().query(builder.prepare());
            ArrayAdapter<User> userAdapter = new UserAdapter(this,android.R.layout.simple_list_item_1,userList);
            listView.setAdapter(userAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class UserAdapter extends ArrayAdapter<User> {
        private Context context;
        public UserAdapter(Context context, int resource, List<User> objects) {
            super(context, resource, objects);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_user,parent,false);
            }
            User user = getItem(position);
            Group group = user.getGroup();
            if(group==null){
//                fillTextView(convertView,R.id.tv,"");
            }else{
                try {
                    new GroupDao(context).getDao().refresh(group);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
//                fillTextView(convertView,R.id.tv,group.getName());
            }
            fillTextView(convertView,R.id.tv,user.toString());
            return convertView;
        }

        private void fillTextView(View view,int id,String text){
            TextView textView = (TextView) view.findViewById(id);
            textView.setText(text);
        }
    }

    private void initDao() {
        userDao = new UserDao(this);
    }

    private void initView() {
        Log.d(TAG,"wmz:initView");
        listView = (ListView) findViewById(R.id.listview);
        spinner = (Spinner) findViewById(R.id.spinner);
        mBtnInsert = (Button) findViewById(R.id.btn_insert);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnQuery = (Button) findViewById(R.id.btn_query);
        mBtnUpdate = (Button) findViewById(R.id.btn_update);

    }
    private void initListener(){
    Log.d(TAG, "wmz:initListener");
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(OrmLiteActivity.this).setMessage("操作").setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user = userList.get(position);
                        userDao.delete(user);
                        showMsg();
                    }
                }).setNegativeButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user = userList.get(position);
                        user.setDate(new Date());
                        userDao.update(user);
                        showMsg();
                    }
                }).create().show();
                return false;
            }
        });


    }


    private void showMsg() {
        List<User> list = userDao.queryForAll();
        if(list==null) return;
//        StringBuffer sb = new StringBuffer();
//        sb.append("query=" + list.toString()).append("\n");
//        mTvshow.setText(sb.toString());
        ArrayAdapter<User> adapter = new UserAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

    }


    public void insert(View view) {
        User user = new User("insert",new Date());
        Group group = (Group) spinner.getSelectedItem();
        user.setGroup(group);
        int result = userDao.create(user);

        Log.d(TAG, "wmz:insert=" + result);

        showMsg();

    }

    public void delete(View view) {
        try {
            Dao<User, Integer> dao = userDao.getDao();
            Where<User, Integer> where = dao.queryBuilder().where().eq("name", "insert");
            CloseableIterator<User> it = where.iterator();
            ArrayList<User> list = new ArrayList<>();
            while (it.hasNext()) {
                list.add(it.next());
            }
            Log.d(TAG, "wmz:delete.size=" + list.size());
            for (User user : list) {
                int result = dao.delete(user);
                Log.d(TAG, "wmz:delete=" + result);
            }
            showMsg();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void query(View view) {
        showMsg();
    }

    public void update(View view) {
        User user = userDao.queryForId(1);
        user.setName(user.getName() + ",update");
        user.setDate(new Date());
        int result = userDao.update(user);
        Log.d(TAG, "wmz:update=" + result);
        showMsg();

    }

}
