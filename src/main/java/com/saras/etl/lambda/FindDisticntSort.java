package com.saras.etl.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindDisticntSort {
    public static void main(String args[]){
        List<Integer> al = Arrays.asList(1, 3, 2, 4, 3, 1, 3, 1, 2);
        List<Integer> sortedAl = al.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(sortedAl);
    }
}
