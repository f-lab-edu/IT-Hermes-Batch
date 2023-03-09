package com.hermes.application;

import com.hermes.presentation.client.HermesClient;
import com.hermes.presentation.dto.feignclient.CrawlingCommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmRequestService {

    private final HermesClient hermesClient;

    public ResponseEntity<CrawlingCommonResponseDto> getSubscribeAlarm(){
        return hermesClient.alarmSubscribeContents();
    }

    public ResponseEntity<CrawlingCommonResponseDto> getRecommendAlarm(){
        return hermesClient.alarmRecommendContents();
    }

}
