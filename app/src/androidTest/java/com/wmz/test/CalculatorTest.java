package com.wmz.test;

import junit.framework.TestCase;

/**
 * Created by wmz on 25/5/16.
 */
public class CalculatorTest extends TestCase {
    private Calculator calculator;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        calculator = new Calculator();
    }

    public void testSum() throws Exception {
        assertEquals(3,calculator.sum(1,2),0);
    }

    public void testSub() throws Exception {
        assertEquals(3,calculator.sum(1,2),0);
    }

    public void testDiv() throws Exception {

    }

    public void testMul() throws Exception {

    }
}