package com.lego.geektask2.Logic.Task2;


/**
 * @author Lego on 26.07.2016.
 */
public class FactorialSum {


    private static FactorialSum instance;
    public static FactorialSum getInstance() {
        return instance == null ? (instance = new FactorialSum()) : instance;
    }

    public String start(int n){
        StringBuilder tmp = new StringBuilder(MyBigInteger.factorial(n).toDecimalString()); //find the required number and converts number to array of numerals
        int result = 0;
        for (int i = 0; i < tmp.length(); i++) {
            result+= Integer.parseInt(tmp.substring(i,i+1));    // sum up all the numbers of array
        }
        return ""+result;
    }


}
