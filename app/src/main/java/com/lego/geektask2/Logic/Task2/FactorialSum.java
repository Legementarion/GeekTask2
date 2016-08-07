package com.lego.geektask2.Logic.Task2;


/**
 * @author Lego on 26.07.2016.
 */
public class FactorialSum {

    public int start(int n){
        long time = System.nanoTime();
        StringBuilder tmp = new StringBuilder(MyBigInteger.factorial(n).toDecimalString()); //find the required number and converts number to array of numerals
        System.out.println("Factorial - "+tmp.toString());
        int result = 0;
        for (int i = 0; i < tmp.length(); i++) {
            result+= Integer.parseInt(tmp.substring(i,i+1));    // sum up all the numbers of array
        }

        System.out.println("Factorial sum - "+result);
        System.out.println("\n Time of execution -" + (System.nanoTime() - time) * (Math.pow(10, -9)) + " \n");
        return result;
    }


}
