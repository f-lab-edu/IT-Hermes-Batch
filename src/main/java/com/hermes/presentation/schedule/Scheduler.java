package com.hermes.presentation.schedule;

import com.hermes.application.AlarmRequestService;
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

    @Scheduled(fixedDelay = 900000)
    public void scheduleFixedRateTask() {
        log.info("15분에 한번 씩, 실행 -  {}", LocalDateTime.now());

        List<CrawlingContentsLastUrl> lastTitleList = hermesRequestService.findAllCrawlingContentsLastTitle().getBody().getCrawlingContentsLastUrlDtoList();

        hermesRequestService.finaAndInsertNewsCrawling(lastTitleList, ContentsProviderType.YOZM);
        hermesRequestService.finaAndInsertNewsCrawling(lastTitleList,ContentsProviderType.CODING_WORLD);
        hermesRequestService.finaAndInsertYoutubeCrawling(lastTitleList, ContentsProviderType.DREAM_CODING);
        hermesRequestService.finaAndInsertYoutubeCrawling(lastTitleList, ContentsProviderType.NOMAD_CODERS);
        hermesRequestService.finaAndInsertYoutubeCrawling(lastTitleList, ContentsProviderType.WHITESHIP);
        hermesRequestService.finaAndInsertYoutubeCrawling(lastTitleList, ContentsProviderType.FI);
        hermesRequestService.finaAndInsertYoutubeCrawling(lastTitleList, ContentsProviderType.LINE_DEVELOP);
        hermesRequestService.finaAndInsertYoutubeCrawling(lastTitleList, ContentsProviderType.DEVELOP_FOOT);
        hermesRequestService.finaAndInsertYoutubeCrawling(lastTitleList, ContentsProviderType.NULLNULL_DEVELOP);
        hermesRequestService.finaAndInsertYoutubeCrawling(lastTitleList, ContentsProviderType.DONGBINNA);
        hermesRequestService.finaAndInsertYoutubeCrawling(lastTitleList, ContentsProviderType.POPE);
        hermesRequestService.finaAndInsertYoutubeCrawling(lastTitleList, ContentsProviderType.WOOWA_COURSE);
        hermesRequestService.findAndInsertJobCrawling(lastTitleList, ContentsProviderType.SARAMIN);
        hermesRequestService.findAndInsertJobCrawling(lastTitleList,ContentsProviderType.WANTED);
    }

    /*
    @Scheduled(fixedDelay = 20000)
    public void scheduleSubscribeAlarmTask(){
        alarmRequestService.getSubscribeAlarm();
    }

    @Scheduled(fixedDelay = 6000000)
    public void scheduleRecommendAlarmTask(){
        alarmRequestService.getRecommendAlarm();
    }*/

}
