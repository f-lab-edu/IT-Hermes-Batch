package com.hermes.dto;

import com.hermes.util.ContentsProviderType;
import com.hermes.util.GradeType;
import com.hermes.util.JobType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CrawlingContentsLastUrlDto {
    ContentsProviderType contentsProvider;
    String lastUrl;
    JobType job;
    GradeType grade;
}
