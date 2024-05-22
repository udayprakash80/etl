package com.saras.etl.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LongestString {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "cherry", "date");
        strings.sort(Comparator.comparing(String::length).reversed());
        strings.stream().limit(1).forEach(System.out::println);
//        strings.forEach(System.out::println);
        String longest = strings.stream()
                .max((s1, s2) -> Integer.compare(s1.length(), s2.length()))
                .orElse(null);
    }
}
