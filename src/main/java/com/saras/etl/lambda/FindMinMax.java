package com.saras.etl.lambda;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Arrays;
import java.util.List;

public class FindMinMax {
    public static void main(String args[]){
        List<Integer> numbers = Arrays.asList(11, 2, 4, 5, 33, 9, 5, 1, 32);

        System.out.println(numbers.stream().min(Integer::compare).orElseThrow());
        System.out.println(numbers.stream().max(Integer::compare).orElseThrow());
    }
}
