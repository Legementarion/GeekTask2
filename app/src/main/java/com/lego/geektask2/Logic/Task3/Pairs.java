package com.lego.geektask2.Logic.Task3;

import java.util.ArrayList;

/**
 * @author Lego on 26.07.2016.
 */
public class Pairs {
    ArrayList<Integer[]> checkedList = new ArrayList<>();

    public void start() {
        long time = System.nanoTime();
        int[][] pairs = {{1, 4}, {2, 5}, {7, 3}, {4, 6}, {7, 7}};

        for (int i = 0; i < pairs.length; i++) {
            checkPairs(pairs[i][0], pairs[i][1]);
        }

        System.out.print("Pairs -");
        for (Integer[] entry : checkedList) {
            System.out.print( "("+entry[0] + ", " + entry[1]+"); ");
        }
        System.out.println("\n Time of execution - " + (System.nanoTime() - time) * (Math.pow(10, -9)) + " \n - Sorry, I don't know how make it code better");
    }

    public void checkPairs(int ascending, int descending) {
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



