package com.hermes.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Slf4j
@Component
public class Scheduler {
    // 10000ms(10초)에 한번 실행된다.
    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedRateTask() {
        log.info("10초에 한번 씩, 실행 -  {}", LocalDateTime.now());
        // 추후 서비스에 있는 Node, UserCustom, Alarm 메소드 호출!
    }
}
