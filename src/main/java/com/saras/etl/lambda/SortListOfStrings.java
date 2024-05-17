package com.saras.etl.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortListOfStrings {
    public static void main(String args[]){
        List<String> strs = Arrays.asList("udaya", "prakash", "bihari");
        strs.sort(Comparator.comparingInt(String::length).reversed());
        strs.forEach(System.out::println);
//        List<Integer> num = strs.stream().map(s -> ).sorted().collect(Collectors.toList());
//        System.out.println(num);
    }
}
