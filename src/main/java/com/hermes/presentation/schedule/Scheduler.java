package com.hermes.presentation.schedule;

import com.hermes.application.AlarmRequestService;
import com.hermes.domain.entity.CrawlingContentsLastUrl;
import com.hermes.domain.util.CategoryType;
import com.hermes.application.HermesRequestService;
import com.hermes.domain.util.ContentsProviderType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final HermesRequestService hermesRequestService;
    private final AlarmRequestService alarmRequestService;

    //@Scheduled(fixedDelay = 1000 * 60 * 15)
    public void scheduleFixedRateTask() {
        log.info("15분에 한번 씩, 실행 -  {}", LocalDateTime.now());
        List<CrawlingContentsLastUrl> crawlingContentsLastTitle = hermesRequestService.findAllCrawlingContentsLastTitle();
        List<CategoryType> categoryTypes = Arrays.stream(CategoryType.values()).toList();
        categoryTypes.stream().forEach(categoryType -> CategoryType.findAndInsertCategoryFunctional(categoryType, crawlingContentsLastTitle, hermesRequestService));
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
