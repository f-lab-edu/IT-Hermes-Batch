package com.hermes.presentation.schedule;

import com.hermes.application.AlarmRequestService;
import com.hermes.presentation.dto.feignclient.CrawlingContentsLastUrlDto;
import com.hermes.presentation.dto.feignclient.CrawlingContentsLastUrl;
import com.hermes.application.HermesRequestService;
import com.hermes.domain.util.ContentsProviderType;
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
    private final AlarmRequestService alarmRequestService;

    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedRateTask() {
        log.info("10초에 한번 씩, 실행 -  {}", LocalDateTime.now());

        List<CrawlingContentsLastUrl> lastTitleList = hermesRequestService.findAllCrawlingContentsLastTitle().getBody().getCrawlingContentsLastUrlDtoList();

        hermesRequestService.finaAndInsertNewsCrawling(lastTitleList, ContentsProviderType.YOZM);
        hermesRequestService.finaAndInsertNewsCrawling(lastTitleList,ContentsProviderType.NAVER);
        hermesRequestService.finaAndInsertNewsCrawling(lastTitleList,ContentsProviderType.CODING_WORLD);
        hermesRequestService.finaAndInsertYoutubeCrawling(lastTitleList, ContentsProviderType.DREAM_CODING);
        hermesRequestService.findAndInsertJobCrawling(lastTitleList, ContentsProviderType.SARAMIN);
        hermesRequestService.findAndInsertJobCrawling(lastTitleList,ContentsProviderType.WANTED);
    }

    @Scheduled(fixedDelay = 20000)
    public void scheduleSubscribeAlarmTask(){
        alarmRequestService.getSubscribeAlarm();
    }

    @Scheduled(fixedDelay = 6000000)
    public void scheduleRecommendAlarmTask(){
        alarmRequestService.getRecommendAlarm();
    }

}
