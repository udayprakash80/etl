package com.saras.etl.lambda;

import java.util.Arrays;
import java.util.List;

public class SumOfList {
    public static void main(String args[]){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }
}
