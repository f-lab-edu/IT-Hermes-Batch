package com.hermes.presentation.dto.feignclient;

import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobCrawlingDto {
    private String company;
    private String title;
    private String url;
    private String location;
    private String grade;
    private String job;
    private String startDate;
    private String endDate;
}
