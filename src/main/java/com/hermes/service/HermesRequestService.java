package com.hermes.service;

import com.hermes.dto.*;
import com.hermes.dto.temp.CrawlingCommonRequestDto;
import com.hermes.dto.temp.CrawlingCommonResponseDto;
import com.hermes.feign.client.HermesClient;
import com.hermes.util.CategoryType;
import com.hermes.util.ContentsProviderType;
import com.hermes.util.GradeType;
import com.hermes.util.ServerUrlType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HermesRequestService {
    private final HermesClient hermesClient;

    public ResponseEntity<CrawlingCommonResponseDto> insertYoutubeAndNewsData(List<CrawlingCommonRequestDto> crawlingCommonRequestDtoList) {
        return ServerUrlType.HERMES.getWebClient().get().post().uri(uriBuilder -> uriBuilder.path("/youtube-and-news/")
                .build()).bodyValue(crawlingCommonRequestDtoList)
                .retrieve().toEntity(CrawlingCommonResponseDto.class).block();
    }

    public ResponseEntity<CrawlingCommonResponseDto> insertYoutubeAndNews(CategoryType categoryType, ContentsProviderType contentsProviderType, List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList) {
        YoutubeAndNewsInsertRequestDto youtubeAndNewsInsertRequestDto = YoutubeAndNewsInsertRequestDto.builder()
                .category(categoryType)
                .contentsProvider(contentsProviderType)
                .youtubeAndNewsCrawlingDtoList(youtubeAndNewsCrawlingDtoList)
                .build();
        return hermesClient.insertYoutubeAndNews(youtubeAndNewsInsertRequestDto);
    }

    public ResponseEntity<CrawlingCommonResponseDto> insertJob(GradeType gradeType, ContentsProviderType contentsProviderType, List<JobCrawlingDto> jobCrawlingDtoList) {
        JobInsertRequestDto jobInsertRequestDto = JobInsertRequestDto.builder()
                .grade(gradeType)
                .contentsProvider(contentsProviderType)
                .jobCrawlingDtoList(jobCrawlingDtoList)
                .build();
        return hermesClient.insertJob(jobInsertRequestDto);
    }
    public ResponseEntity<YoutubeAndNewsLastUrlResponseDto> findYoutubeAndNewsLastUrl(ContentsProviderType contentsProviderType) {
        YoutubeAndNewsLastUrlRequestDto youtubeAndNewsLastUrlRequestDto = YoutubeAndNewsLastUrlRequestDto.builder()
                .contentsProvider(contentsProviderType)
                .build();
        return hermesClient.findYoutubeAndNewsLastUrl(youtubeAndNewsLastUrlRequestDto);
    }

    public ResponseEntity<JobLastUrlResponseDto> findJobLastUrl(ContentsProviderType contentsProviderType) {
        JobLastUrlRequestDto findJobLastUrl = JobLastUrlRequestDto.builder()
                .contentsProvider(contentsProviderType)
                .build();
        return hermesClient.findJobLastUrl(findJobLastUrl);
    }
}
