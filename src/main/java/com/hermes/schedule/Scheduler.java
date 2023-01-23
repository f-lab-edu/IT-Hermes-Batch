package com.hermes.schedule;

import com.hermes.dto.JobCrawlingDto;
import com.hermes.dto.JobLastUrlResponseDto;
import com.hermes.dto.YoutubeAndNewsCrawlingDto;
import com.hermes.dto.YoutubeAndNewsLastUrlResponseDto;
import com.hermes.service.HermesRequestService;
import com.hermes.service.NodeRequestService;
import com.hermes.util.CategoryType;
import com.hermes.util.ContentsProviderType;
import com.hermes.util.GradeType;
import com.hermes.util.JobType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
        String lastUrl;
        ResponseEntity<YoutubeAndNewsLastUrlResponseDto> youtubeAndNewsLastUrl;

        youtubeAndNewsLastUrl = hermesRequestService.findYoutubeAndNewsLastUrl(ContentsProviderType.YOZM);
        lastUrl = youtubeAndNewsLastUrl.getBody().getLastUrl();

        if (lastUrl == null)
            youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingNews(ContentsProviderType.YOZM, "noData");
        else youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingNews(ContentsProviderType.YOZM, lastUrl);
        hermesRequestService.insertYoutubeAndNews(CategoryType.NEWS, ContentsProviderType.YOZM, youtubeAndNewsCrawlingDtoList);

        youtubeAndNewsLastUrl = hermesRequestService.findYoutubeAndNewsLastUrl(ContentsProviderType.DREAM_CODING);
        lastUrl = youtubeAndNewsLastUrl.getBody().getLastUrl();

        if (lastUrl == null)
            youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingYoutube(ContentsProviderType.DREAM_CODING, "noData");
        else
            youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingYoutube(ContentsProviderType.DREAM_CODING, lastUrl);

        hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.DREAM_CODING, youtubeAndNewsCrawlingDtoList);


        ResponseEntity<JobLastUrlResponseDto> jobLastUrl = hermesRequestService.findJobLastUrl(ContentsProviderType.SARAMIN);
        lastUrl = jobLastUrl.getBody().getLastUrl();

        if (lastUrl == null) {
            jobCrawlingDtoList = nodeRequestService.crawlingJob(ContentsProviderType.SARAMIN,
                    JobType.BACKEND, GradeType.JUNIOR.getMinExperience(), GradeType.JUNIOR.getMaxExperience(), "noData");
        } else {
            jobCrawlingDtoList = nodeRequestService.crawlingJob(ContentsProviderType.SARAMIN,
                    JobType.BACKEND, GradeType.JUNIOR.getMinExperience(), GradeType.JUNIOR.getMaxExperience(), lastUrl);
        }
        hermesRequestService.insertJob(GradeType.JUNIOR, ContentsProviderType.SARAMIN, jobCrawlingDtoList);
    }
}
