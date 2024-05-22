package com.saras.etl.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ConvertUppercase {
    public static void main(String args[]){
        List<String> strs = Arrays.asList("udaya", "prakash", "bihari");
        strs.stream().map(String::toUpperCase).forEach(System.out::println);
    }
}
