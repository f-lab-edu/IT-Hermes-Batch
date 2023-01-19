package com.hermes.schedule;

import com.hermes.dto.CrawlingCommonRequestDto;
import com.hermes.dto.CrawlingCommonResponseDto;
import com.hermes.service.HermesRequestService;
import com.hermes.service.NodeRequestService;
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
    private final NodeRequestService nodeRequestService;
    private final HermesRequestService hermesRequestService;
    // 10000ms(10초)에 한번 실행된다.
    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedRateTask() {
        log.info("10초에 한번 씩, 실행 -  {}", LocalDateTime.now());
        // 추후 서비스에 있는 Node, UserCustom, Alarm 메소드 호출!
        List<CrawlingCommonRequestDto> crawlingData = nodeRequestService.findCrawlingData();
        hermesRequestService.insertYoutubeAndNewsData(crawlingData);
    }
}
