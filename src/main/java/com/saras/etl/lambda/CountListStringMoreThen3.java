package com.saras.etl.lambda;

import java.util.Arrays;
import java.util.List;

public class CountListStringMoreThen3 {
    public static void main(String args[]){
        List<String> strs = Arrays.asList("udaya", "as", "bihari", "a", "asa");
        strs.stream().filter(s -> s.length() > 3).forEach(System.out::println);
    }
}
