package com.hermes.service;

import com.hermes.dto.CrawlingCommonRequestDto;
import com.hermes.dto.CrawlingCommonResponseDto;
import com.hermes.util.ServerUrlType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HermesRequestService {
    public ResponseEntity<CrawlingCommonResponseDto> insertYoutubeAndNewsData(List<CrawlingCommonRequestDto> crawlingCommonRequestDtoList) {
        return ServerUrlType.HERMES.getWebClient().get().post().uri(uriBuilder -> uriBuilder.path("/youtube-and-news/")
                .build()).bodyValue(crawlingCommonRequestDtoList)
                .retrieve().toEntity(CrawlingCommonResponseDto.class).block();
    }
}
