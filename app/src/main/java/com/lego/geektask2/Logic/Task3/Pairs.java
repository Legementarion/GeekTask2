package com.lego.geektask2.Logic.Task3;

import android.util.Log;

import java.util.ArrayList;

/**
 * @author Lego on 26.07.2016.
 */
public class Pairs {

    private static Pairs instance;
    private ArrayList<Integer[]> checkedList = new ArrayList<>();

    public static Pairs getInstance() {
        return instance == null ? (instance = new Pairs()) : instance;
    }

    public String start(int[][] pairs) {

        for (int[] pair : pairs) {
            checkPairs(pair[0], pair[1]);
        }

        StringBuilder result = new StringBuilder();
        for (Integer[] entry : checkedList) {
            result.append("(").append(entry[0]).append(", ").append(entry[1]).append("); ");
        }
        return result.toString();
    }

    private void checkPairs(int ascending, int descending) {
        if (checkedList.size() == 0) {
            Integer[] temp = new Integer[2];
            temp[0] = ascending;
            temp[1] = descending;
            checkedList.add(temp);
        } else if (checkedList.size() == 1) {
            if (!(checkedList.get(checkedList.size() - 1)[0] < ascending && checkedList.get(checkedList.size() - 1)[1] > descending)) {
                checkedList.remove(checkedList.get(0));
            }
            Integer[] temp = new Integer[2];
            temp[0] = ascending;
            temp[1] = descending;
            checkedList.add(temp);
        } else if (checkedList.size() > 1) {
            if (checkedList.get(checkedList.size() - 1)[0] < ascending && checkedList.get(checkedList.size() - 1)[1] > descending) {
                Integer[] temp = new Integer[2];
                temp[0] = ascending;
                temp[1] = descending;
                checkedList.add(temp);
            }
        }
    }
}



