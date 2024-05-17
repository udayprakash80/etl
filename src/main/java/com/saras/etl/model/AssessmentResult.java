package com.saras.etl.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssessmentResult {
    private int total;
    private int correct;
    private int incorrect;
    private int skipped;
    private double percentage;
    private String feedback;
    private String remark;
}
