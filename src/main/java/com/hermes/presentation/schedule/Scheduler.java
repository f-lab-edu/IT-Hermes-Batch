package com.hermes.presentation.schedule;

import com.hermes.presentation.dto.feignclient.CrawlingContentsLastUrlDto;
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

    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedRateTask() {
        log.info("10초에 한번 씩, 실행 -  {}", LocalDateTime.now());

        List<CrawlingContentsLastUrlDto> lastTitleList = hermesRequestService.findAllCrawlingContentsLastTitle().getBody().getCrawlingContentsLastUrlDtoList();

        hermesRequestService.finaAndInsertNewsCrawling(lastTitleList, ContentsProviderType.YOZM);
        hermesRequestService.finaAndInsertYoutubeCrawling(lastTitleList, ContentsProviderType.DREAM_CODING);
        hermesRequestService.findAndInsertJobCrawling(lastTitleList, ContentsProviderType.SARAMIN);
    }
}
