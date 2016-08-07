package com.lego.geektask2.Logic.Task2;

import java.util.Arrays;
import java.util.Formatter;

/**
 * @author Lego on 02.08.2016.
 */
public class MyBigInteger {

    private final static int RANGE_DECIMAL_DIGITS = 9;
    public final static int RANGE = 1000000000;
    private int[] digits;

    public final static MyBigInteger ONE = new MyBigInteger(1);

    public MyBigInteger(int... digits) {
        int zeroCount = 0;
        boolean zero = true;
        for (int digit : digits) {
            if (digit < 0) {
                throw new IllegalArgumentException("digit " + digit +
                        " out of range!");
            }
            if (zero) {
                if (digit != 0) {
                    zero = false;
                } else {
                    zeroCount++;
                }
            }
        }
        this.digits = Arrays.copyOfRange(digits, zeroCount, digits.length);
    }

    public StringBuilder toDecimalString() {
        StringBuilder b =
                new StringBuilder(RANGE_DECIMAL_DIGITS * digits.length);
        Formatter f = new Formatter(b);
        System.out.println("Factorial sum - "+Arrays.toString(digits));
        f.format("%d", digits[0]);
        for (int i = 1; i < digits.length; i++) {
            f.format("%09d", digits[i]);
        }
        return b;
    }

    private void addDigits(int[] result, int resultIndex, int... addend) {
        int addendIndex = addend.length - 1;
        while (addendIndex >= 0) {
            addDigit(result, resultIndex,
                    addend[addendIndex]);
            addendIndex--;
            resultIndex--;
        }
    }

    private void addDigit(int[] result, int resultIndex, int addendDigit) {
        int sum = result[resultIndex] + addendDigit;
        result[resultIndex] = sum % RANGE;
        int carry = sum / RANGE;
        if (carry > 0) {
            addDigit(result, resultIndex - 1, carry);
        }
    }

    private void multiplyDigit(int[] result, int resultIndex, int firstFactor, int secondFactor) {
        long prod = (long) firstFactor * (long) secondFactor;
        int prodDigit = (int) (prod % RANGE);
        int carry = (int) (prod / RANGE);
        addDigits(result, resultIndex, carry, prodDigit);
    }

    private void multiplyDigits(int[] result, int resultIndex, int[] leftFactor, int[] rightFactor) {
        for (int i = 0; i < leftFactor.length; i++) {
            for (int j = 0; j < rightFactor.length; j++) {
                multiplyDigit(result, resultIndex - (i + j),
                        leftFactor[leftFactor.length - i - 1],
                        rightFactor[rightFactor.length - j - 1]);
            }
        }
    }

    public MyBigInteger times(MyBigInteger that) {
        int[] result = new int[this.digits.length + that.digits.length];
        multiplyDigits(result, result.length - 1,
                this.digits, that.digits);
        return new MyBigInteger(result);
    }

    public static MyBigInteger factorial(int n) {
        MyBigInteger fac = MyBigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            fac = fac.times(new MyBigInteger(i));
        }
        return fac;
    }
}