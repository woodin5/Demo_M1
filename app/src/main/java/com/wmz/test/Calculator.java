package com.wmz.test;

/**
 * Created by wmz on 15/5/16.
 */
public class Calculator {
    public enum Operator{ADD,SUB,DIV,MUL}

    public double sum(double a,double b){
        return a+b;
    }

    public double sub(double a ,double b){
        return a-b;
    }

    public double div(double a,double b){

        return a/b;
    }

    public double mul(double a,double b){
        return a*b;
    }
}
