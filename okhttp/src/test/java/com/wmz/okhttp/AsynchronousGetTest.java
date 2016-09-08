package com.wmz.okhttp;

import junit.framework.TestCase;

/**
 * Created by wmz on 24/5/16.
 */
public class AsynchronousGetTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
      System.out.println("setUp");
    }

    public void testRun() throws Exception {
        System.out.println("testRun");
        new AsynchronousGet().run();
    }
}