package com.saras.etl.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindSecondHighest {
    public static void main(String args[]){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 5, 4);
        int value = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow();
        System.out.println(value);
    }

}
