package com.saras.etl.model;

import java.util.HashMap;
import java.util.Map;

public enum AssessmentEnum {
    EXCELLENT(90),
    GOOD(80);

    private final int percentage;
    private static final Map<Integer, AssessmentEnum> PERCENTAGE_MAP = new HashMap<>();

    static {
        for(AssessmentEnum assessmentEnum : AssessmentEnum.values()){
            PERCENTAGE_MAP.put(assessmentEnum.getPercentage(), assessmentEnum);
        }
    }
    AssessmentEnum(int percentage){
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }

    public static AssessmentEnum fromPercentage(int percentage){
        return PERCENTAGE_MAP.get(percentage);
    }
}
