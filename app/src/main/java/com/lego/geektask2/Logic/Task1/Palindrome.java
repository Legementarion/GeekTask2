package com.lego.geektask2.Logic.Task1;

import java.util.*;

/**
 * @author Lego on 26.07.2016.
 */
public class Palindrome {

    private static Palindrome instance;
    public static Palindrome getInstance() {
        return instance == null ? (instance = new Palindrome()) : instance;
    }

    public String start(int max) {
        int leftI = 0, rightI = 0,value;
        String maxAsStr = Integer.toString( max );
        int maxSize = maxAsStr.length();
        for (int i = max; i > 100; i--) {               //palindrome search start
            for (int j = i - (int)(max/Math.pow( 10,maxSize/2 )) ; j <= i ; j++) {
                value = i*j;
                ArrayList<Integer> digitSequence = new ArrayList<>();
        /*            Converts number to array of numerals          */
                while (value > 0) {
                    digitSequence.add( 0, value % 10 );
                    value /= 10;
                }

                leftI = digitSequence.size()/2-1;     //divide the number into two parts
                if ( digitSequence.size() % 2 == 0 ) {
                    rightI = leftI+1;
                } else {
                    rightI = leftI+2;
                }

                while (leftI >= 0){               //compare two parts
                    int nextLeft = digitSequence.get( leftI );
                    int nextRight = digitSequence.get( rightI );
                    if ( nextLeft !=nextRight ) {
                        break;
                    }
                    leftI--;
                    rightI++;
                    if (leftI < 0) {
                        StringBuilder result = new StringBuilder();
                        for(int r=0; r<digitSequence.size();r++ ){
                         result.append(digitSequence.get(r));
                        }
                        return result.toString();
                    }
                }
            }
        }
        return "";
    }
}
