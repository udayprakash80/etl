package com.saras.etl.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByLength {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("one", "two", "three", "four", "five");
        Map<Integer, List<String>> groupedByLength = strings.stream().collect(Collectors.groupingBy(String::length));
        strings.stream().collect(Collectors.groupingBy(String::length)).forEach((len, strs) -> {
            System.out.println("Length: " + len);
            strs.forEach(System.out::println);
        });
    }
}
