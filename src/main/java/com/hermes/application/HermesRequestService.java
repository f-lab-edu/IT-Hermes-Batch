package com.hermes.application;

import com.hermes.presentation.client.HermesClient;
import com.hermes.domain.util.CategoryType;
import com.hermes.domain.util.ContentsProviderType;
import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
import com.hermes.presentation.dto.feignclient.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HermesRequestService {
    private final HermesClient hermesClient;
    private final NodeRequestService nodeRequestService;

    public ResponseEntity<CrawlingCommonResponseDto> insertYoutubeAndNews(CategoryType categoryType, ContentsProviderType contentsProviderType, List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList) {
        if (youtubeAndNewsCrawlingDtoList.isEmpty()) return null;
        YoutubeAndNewsInsertRequestDto youtubeAndNewsInsertRequestDto = YoutubeAndNewsInsertRequestDto.builder()
                .category(categoryType)
                .contentsProvider(contentsProviderType)
                .youtubeAndNewsCrawlingDtoList(youtubeAndNewsCrawlingDtoList)
                .build();
        return hermesClient.insertYoutubeAndNews(youtubeAndNewsInsertRequestDto);
    }

    public ResponseEntity<CrawlingCommonResponseDto> insertJob(GradeType gradeType, ContentsProviderType contentsProviderType
            , JobType job, List<JobCrawlingDto> jobCrawlingDtoList) {
        if (jobCrawlingDtoList.isEmpty()) return null;
        JobInsertRequestDto jobInsertRequestDto = JobInsertRequestDto.builder()
                .grade(gradeType)
                .contentsProvider(contentsProviderType)
                .job(job)
                .jobCrawlingDtoList(jobCrawlingDtoList)
                .build();
        return hermesClient.insertJob(jobInsertRequestDto);
    }

    public void finaAndInsertYoutubeCrawling(List<CrawlingContentsLastUrl> lastTitleList, ContentsProviderType contentsProviderType) {
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingYoutube(contentsProviderType, lastTitleList);
        insertYoutubeAndNews(CategoryType.YOUTUBE, contentsProviderType, youtubeAndNewsCrawlingDtoList);
    }

    public void finaAndInsertNewsCrawling(List<CrawlingContentsLastUrl> lastTitleList, ContentsProviderType contentsProviderType) {
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingNews(contentsProviderType, lastTitleList);
        insertYoutubeAndNews(CategoryType.NEWS, contentsProviderType, youtubeAndNewsCrawlingDtoList);
    }

    public void findAndInsertJobCrawling(List<CrawlingContentsLastUrl> lastTitleList, ContentsProviderType contentsProviderType) {
        Arrays.stream(JobType.values()).toList().stream().forEach(job -> {
            Arrays.stream(GradeType.values()).toList().stream().forEach(grade -> {
                List<JobCrawlingDto> jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                        job, grade, lastTitleList);
                insertJob(grade, contentsProviderType, job, jobCrawlingDtoList);
            });
        });
    }

    public ResponseEntity<CrawlingContentsLastUrlFindAllResponseDto> findAllCrawlingContentsLastTitle() {
        return hermesClient.findAllCrawlingContentsLastTitle();
    }
}
