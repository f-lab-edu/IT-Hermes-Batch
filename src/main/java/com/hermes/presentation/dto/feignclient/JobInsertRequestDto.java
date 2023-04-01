package com.hermes.presentation.dto.feignclient;

import com.hermes.domain.util.ContentsProviderType;
import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
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