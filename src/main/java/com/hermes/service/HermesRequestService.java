package com.hermes.service;

import com.hermes.dto.*;
import com.hermes.dto.temp.CrawlingCommonResponseDto;
import com.hermes.feign.client.HermesClient;
import com.hermes.util.CategoryType;
import com.hermes.util.ContentsProviderType;
import com.hermes.util.GradeType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HermesRequestService {
    private final HermesClient hermesClient;

    public ResponseEntity<CrawlingCommonResponseDto> insertYoutubeAndNews(CategoryType categoryType, ContentsProviderType contentsProviderType, List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList) {
        if(youtubeAndNewsCrawlingDtoList.isEmpty()) return null;
        YoutubeAndNewsInsertRequestDto youtubeAndNewsInsertRequestDto = YoutubeAndNewsInsertRequestDto.builder()
                .category(categoryType)
                .contentsProvider(contentsProviderType)
                .youtubeAndNewsCrawlingDtoList(youtubeAndNewsCrawlingDtoList)
                .build();
        return hermesClient.insertYoutubeAndNews(youtubeAndNewsInsertRequestDto);
    }

    public ResponseEntity<CrawlingCommonResponseDto> insertJob(GradeType gradeType, ContentsProviderType contentsProviderType, List<JobCrawlingDto> jobCrawlingDtoList) {
        if(jobCrawlingDtoList.isEmpty()) return null;
        JobInsertRequestDto jobInsertRequestDto = JobInsertRequestDto.builder()
                .grade(gradeType)
                .contentsProvider(contentsProviderType)
                .jobCrawlingDtoList(jobCrawlingDtoList)
                .build();
        return hermesClient.insertJob(jobInsertRequestDto);
    }

    public ResponseEntity<CrawlingContentsLastUrlFindAllResponseDto> findAllCrawlingContentsLastTitle() {
        return hermesClient.findAllCrawlingContentsLastTitle();
    }
}
