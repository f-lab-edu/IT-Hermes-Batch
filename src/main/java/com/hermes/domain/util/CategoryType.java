package com.hermes.domain.util;

import java.util.Arrays;
import java.util.List;

public enum CategoryType {
    JOB("JOB", Arrays.asList(ContentsProviderType.SARAMIN, ContentsProviderType.WANTED)),
    NEWS("NEWS", Arrays.asList(ContentsProviderType.CODING_WORLD, ContentsProviderType.NAVER, ContentsProviderType.YOZM)),
    YOUTUBE("YOUTUBE", Arrays.asList(ContentsProviderType.NOMAD_CODERS, ContentsProviderType.DREAM_CODING));

    private String title;
    private List<ContentsProviderType> contentsProviderTypes;

    CategoryType(String title, List<ContentsProviderType> contentsProviderTypes) {
        this.title = title;
        this.contentsProviderTypes = contentsProviderTypes;
    }
}
