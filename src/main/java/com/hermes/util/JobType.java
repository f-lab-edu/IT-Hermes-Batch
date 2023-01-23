package com.hermes.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum JobType {
    FRONT("FRONT"),
    BACKEND("BACKEND"),
    MOBILE("MOBILE");

    private String title;

    JobType(String title) {
        this.title = title;
    }
}

