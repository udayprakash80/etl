package com.saras.etl.utility;

public class AssessmentMath {
    public static double calculatePercentage(double part, double total) {
        if (total == 0) {
            throw new IllegalArgumentException("Total must not be zero");
        }
        return (part / total) * 100;
    }
}
