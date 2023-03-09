package com.hermes.domain.util;

import com.hermes.presentation.dto.webclient.CrawlingCommonRequestDto;
import com.hermes.presentation.dto.webclient.JobRequestDto;
import com.hermes.presentation.dto.webclient.NewsRequestDto;
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
            (webClient) -> webClient.get().uri("/job/wanted").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    CODING_WORLD("CODING_WORLD", "codingworld",
            (webClient) -> webClient.get().uri("/news/codingworld").retrieve().bodyToFlux(NewsRequestDto.class).toStream().collect(Collectors.toList())),
    YOZM("YOZM", "yozm",
            (webClient) -> webClient.get().uri("/news/yozm").retrieve().bodyToFlux(NewsRequestDto.class).toStream().collect(Collectors.toList())),
    NOMAD_CODERS("NOMAD_CODERS", "@nomadcoders",
            (webClient) -> webClient.get().uri("/videos/youtube").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    DREAM_CODING("DREAM_CODING", "@dream-coding",
            (webClient) -> webClient.get().uri("/videos/youtube").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    WHITESHIP("WHITESHIP", "@keesun.b",
                         (webClient) -> webClient.get().uri("/videos/youtube").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    FI("FI", "@foorogrammer4072",
                         (webClient) -> webClient.get().uri("/videos/youtube").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    LINE_DEVELOP("LINE_DEVELOP", "@linedevlog",
                         (webClient) -> webClient.get().uri("/videos/youtube").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    DEVELOP_FOOT("DEVELOP_FOOT", "@devbadak",
                         (webClient) -> webClient.get().uri("/videos/youtube").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    NULLNULL_DEVELOP("NULLNULL_DEVELOP", "@nullnull_not_eq_null",
                         (webClient) -> webClient.get().uri("/videos/youtube").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    DONGBINNA("DONGBINNA", "@dongbinna",
                         (webClient) -> webClient.get().uri("/videos/youtube").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    POPE("POPE", "@PopeTV",
                         (webClient) -> webClient.get().uri("/videos/youtube").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList())),
    WOOWA_COURSE("WOOWA_COURSE", "@woowatech",
                         (webClient) -> webClient.get().uri("/videos/youtube").retrieve().bodyToFlux(JobRequestDto.class).toStream().collect(Collectors.toList()));

    private String title;
    private String requestPath;
    private Function<WebClient, List<CrawlingCommonRequestDto>> webClient;

    ContentsProviderType(String title, String requestPath, Function<WebClient, List<CrawlingCommonRequestDto>> webClient) {
        this.title = title;
        this.requestPath = requestPath;
        this.webClient = webClient;
    }
}