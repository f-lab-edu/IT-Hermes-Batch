package com.hermes.domain.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum GradeType {
    BEGINNER("BEGINNER",0,0),
    JUNIOR("JUNIOR",1,4),
    INTERMEDIATE("INTERMEDIATE",5,9),
    SENIOR("SENIOR",10,30);

    private String name;
    private int minExperience;
    private int maxExperience;

    GradeType(String name, int minExperience, int maxExperience) {
        this.name = name;
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
    }

    @JsonCreator
    public static JobType fromValue(String grade) {
        return JobType.valueOf(grade.toUpperCase());
    }
}
