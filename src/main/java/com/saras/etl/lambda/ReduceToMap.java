package com.saras.etl.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReduceToMap {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "cherry");

        Map<String, Integer> map = strings.stream().collect(Collectors.toMap(s -> s, String::length));
        Map<String, Integer> stringLengthMap = strings.stream()
                .collect(Collectors.toMap(s -> s, String::length));

    }
}
