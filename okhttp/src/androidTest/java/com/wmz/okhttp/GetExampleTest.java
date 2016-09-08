package com.wmz.okhttp;

import com.wmz.okhttp.manager.OkHttpManager;

import junit.framework.TestCase;

import java.lang.System;

/**
 * Created by wmz on 26/5/16.
 */
public class GetExampleTest extends TestCase {

    public void testRun() throws Exception {
//        new GetExample().run("https://raw.github.com/square/okhttp/master/README.md");
        String content = new OkHttpManager().execute("http://publicobject.com/helloworld.txt",null);
        System.out.println("content="+content);
    }
}