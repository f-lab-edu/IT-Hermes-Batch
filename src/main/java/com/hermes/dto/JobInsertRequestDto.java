package com.hermes.dto;

import com.hermes.util.ContentsProviderType;
import com.hermes.util.GradeType;
import com.hermes.util.JobType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class JobInsertRequestDto {
    GradeType grade;
    ContentsProviderType contentsProvider;
    JobType job;
    List<JobCrawlingDto> jobCrawlingDtoList;
}