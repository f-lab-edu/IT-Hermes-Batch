package com.hermes.presentation.dto.feignclient;

import com.hermes.domain.util.ContentsProviderType;
import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrawlingContentsLastUrl {
    private ContentsProviderType contentsProvider;
    private String lastUrl;
    private JobType job;
    private GradeType grade;
}
