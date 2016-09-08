package com.wmz.okhttp;

import junit.framework.TestCase;

/**
 * Created by wmz on 24/5/16.
 */
public class GetExampleTest extends TestCase {
    GetExample example;
    public void setUp() throws Exception {
        super.setUp();
         example = new GetExample();

    }
    public void testRun() throws  Exception{
        String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }
}