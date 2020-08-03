package com.twu.refactoring;

public class NumberCruncher {
    private final int[] numbers;
    private int countEven = 0;
    private int countOdd = 0;
    private int countNegative = 0;
    private int countPositive = 0;



    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public void countForAll(){
        for(int number: numbers){
            if (number % 2 == 0) countEven++;
            if (number % 2 == 1) countOdd++;
            if (number >= 0) countPositive++;
            if (number < 0) countNegative++;
        }
    }

    public int countEven() {
        return countEven;
    }

    public int countOdd() {
        return countOdd;
    }

    public int countPositive() {
        return countPositive;
    }

    public int countNegative() {
        return countNegative;
    }
}
