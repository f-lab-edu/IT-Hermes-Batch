package com.hermes.util;

import com.hermes.dto.CrawlingCommonRequestDto;
import com.hermes.dto.JobRequestDto;
import com.hermes.dto.NewsRequestDto;
import lombok.Getter;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum ContentsProviderType {
    SARAMIN("SARAMIN",(webClient)->  webClient.get().uri("/job/saramin").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    WANTED("WANTED",(webClient)->webClient.get().uri("/job/saramin").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    CODING_WORLD("CONING_WORLD",(webClient)->webClient.get().uri("/job/saramin").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    NAVER("NAVER",(webClient)->webClient.get().uri("/job/saramin").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    YOZM("YOZM",(webClient)->webClient.get().uri("/news/yozm").retrieve().bodyToFlux(NewsRequestDto.class).toStream().collect(Collectors.toList())),
    NOMAD_CODERS("NOMAD_CODERS",(webClient)->webClient.get().uri("/job/saramin").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    DREAM_CODING("DREAM_CODING",(webClient)->webClient.get().uri("/job/saramin").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList()));

    private String title;
    private Function<WebClient, List<CrawlingCommonRequestDto>> webClient;

    ContentsProviderType(String title, Function<WebClient, List<CrawlingCommonRequestDto>> webClient) {
        this.title = title;
        this.webClient = webClient;
    }
}