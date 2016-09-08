package com.wmz.okhttp;

import com.google.gson.Gson;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wmz on 26/5/16.
 */
public class TestTest extends TestCase {

    public void testExecute() throws Exception {
//        String content = new Test().execute("https://raw.github.com/square/okhttp/master/README.md");
//        System.out.println("content="+content);
        Gson gson = new Gson();
        HashMap<String,String> map = new HashMap<>();
        map.put("username","1");
        map.put("password","1");
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        String json = gson.toJson(list);
        System.out.println("json=" + json);

    }


}