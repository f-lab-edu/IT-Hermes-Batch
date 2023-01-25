package com.hermes.presentation.dto.feignclient;

import com.hermes.domain.util.ContentsProviderType;
import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
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
