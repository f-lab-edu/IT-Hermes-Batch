package com.hermes.schedule;

import com.hermes.dto.*;
import com.hermes.service.HermesRequestService;
import com.hermes.service.NodeRequestService;
import com.hermes.util.CategoryType;
import com.hermes.util.ContentsProviderType;
import com.hermes.util.GradeType;
import com.hermes.util.JobType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final HermesRequestService hermesRequestService;
    private final NodeRequestService nodeRequestService;

    // 10000ms(10초)에 한번 실행된다.
    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedRateTask() {
        log.info("10초에 한번 씩, 실행 -  {}", LocalDateTime.now());

        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList;
        List<JobCrawlingDto> jobCrawlingDtoList;

        List<CrawlingContentsLastUrlDto> lastTitleList = hermesRequestService.findAllCrawlingContentsLastTitle().getBody().getCrawlingContentsLastUrlDtoList();

        youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingNews(ContentsProviderType.YOZM, lastTitleList);
        hermesRequestService.insertYoutubeAndNews(CategoryType.NEWS, ContentsProviderType.YOZM, youtubeAndNewsCrawlingDtoList);

        youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingYoutube(ContentsProviderType.DREAM_CODING, lastTitleList);
        hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.DREAM_CODING, youtubeAndNewsCrawlingDtoList);

        jobCrawlingDtoList = nodeRequestService.crawlingJob(ContentsProviderType.SARAMIN,
                JobType.BACKEND, GradeType.JUNIOR.getMinExperience(), GradeType.JUNIOR.getMaxExperience(), lastTitleList);

        hermesRequestService.insertJob(GradeType.JUNIOR, ContentsProviderType.SARAMIN, jobCrawlingDtoList);
    }
}
