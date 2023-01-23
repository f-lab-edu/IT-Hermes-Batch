package com.hermes.util;

import com.hermes.dto.temp.CrawlingCommonRequestDto;
import com.hermes.dto.temp.JobRequestDto;
import com.hermes.dto.temp.NewsRequestDto;
import lombok.Getter;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum ContentsProviderType {
    SARAMIN("SARAMIN", "saramin",
            (webClient) -> webClient.get().uri("/job/saramin").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    WANTED("WANTED", "wanted",
            (webClient) -> webClient.get().uri("/job/saramin").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    CODING_WORLD("CONING_WORLD", "codingworld",
            (webClient) -> webClient.get().uri("/job/saramin").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    NAVER("NAVER", "naver",
            (webClient) -> webClient.get().uri("/job/saramin").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    YOZM("YOZM", "yozm",
            (webClient) -> webClient.get().uri("/news/yozm").retrieve().bodyToFlux(NewsRequestDto.class).toStream().collect(Collectors.toList())),
    NOMAD_CODERS("NOMAD_CODERS", "@nomadcoders",
            (webClient) -> webClient.get().uri("/job/saramin").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    DREAM_CODING("DREAM_CODING", "@dream-coding",
            (webClient) -> webClient.get().uri("/job/saramin").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList()));

    private String title;
    private String requestPath;
    private Function<WebClient, List<CrawlingCommonRequestDto>> webClient;

    ContentsProviderType(String title, String requestPath, Function<WebClient, List<CrawlingCommonRequestDto>> webClient) {
        this.title = title;
        this.requestPath = requestPath;
        this.webClient = webClient;
    }
}