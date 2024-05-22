package com.saras.etl.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionList {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<Boolean, List<Integer>> partition = numbers.stream().collect(Collectors.partitioningBy(n -> n%2 == 0));
        System.out.println(partition.get(true));
        System.out.println(partition.get(false));

    }
}
