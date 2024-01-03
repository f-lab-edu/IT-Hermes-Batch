package com.hermes.presentation.dto.feignclient;

import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobCrawlingDto {
    private String company;
    private String title;
    private String url;
    private String location;
    private GradeType grade;
    private JobType job;
    private String startDate;
    private String endDate;
    private String crawlingIndex;
}
