package com.hermes.dto;

import com.hermes.util.CategoryType;
import com.hermes.util.ContentsProviderType;
import com.hermes.util.GradeType;
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
    List<JobCrawlingDto> jobCrawlingDtoList;
}