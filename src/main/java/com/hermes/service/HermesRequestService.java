package com.hermes.service;

import com.hermes.dto.*;
import com.hermes.dto.temp.CrawlingCommonResponseDto;
import com.hermes.feign.client.HermesClient;
import com.hermes.util.CategoryType;
import com.hermes.util.ContentsProviderType;
import com.hermes.util.GradeType;
import com.hermes.util.JobType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HermesRequestService {
    private final HermesClient hermesClient;
    private final NodeRequestService nodeRequestService;

    public ResponseEntity<CrawlingCommonResponseDto> insertYoutubeAndNews(CategoryType categoryType, ContentsProviderType contentsProviderType, List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList) {
        if(youtubeAndNewsCrawlingDtoList.isEmpty()) return null;
        YoutubeAndNewsInsertRequestDto youtubeAndNewsInsertRequestDto = YoutubeAndNewsInsertRequestDto.builder()
                .category(categoryType)
                .contentsProvider(contentsProviderType)
                .youtubeAndNewsCrawlingDtoList(youtubeAndNewsCrawlingDtoList)
                .build();
        return hermesClient.insertYoutubeAndNews(youtubeAndNewsInsertRequestDto);
    }

    public ResponseEntity<CrawlingCommonResponseDto> insertJob(GradeType gradeType, ContentsProviderType contentsProviderType
            ,JobType job, List<JobCrawlingDto> jobCrawlingDtoList) {
        if(jobCrawlingDtoList.isEmpty()) return null;
        JobInsertRequestDto jobInsertRequestDto = JobInsertRequestDto.builder()
                .grade(gradeType)
                .contentsProvider(contentsProviderType)
                .job(job)
                .jobCrawlingDtoList(jobCrawlingDtoList)
                .build();
        return hermesClient.insertJob(jobInsertRequestDto);
    }

    public void finaAndInsertYoutubeCrawling(List<CrawlingContentsLastUrlDto> lastTitleList, ContentsProviderType contentsProviderType) {
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingYoutube(contentsProviderType, lastTitleList);
        insertYoutubeAndNews(CategoryType.YOUTUBE, contentsProviderType, youtubeAndNewsCrawlingDtoList);
    }

    public void finaAndInsertNewsCrawling(List<CrawlingContentsLastUrlDto> lastTitleList, ContentsProviderType contentsProviderType) {
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingNews(contentsProviderType, lastTitleList);
        insertYoutubeAndNews(CategoryType.NEWS, contentsProviderType, youtubeAndNewsCrawlingDtoList);
    }

    public void findAndInsertJobCrawling(List<CrawlingContentsLastUrlDto> lastTitleList, ContentsProviderType contentsProviderType) {
        List<JobCrawlingDto> jobCrawlingDtoList;

        jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                JobType.BACKEND, GradeType.BEGINNER, lastTitleList);
        insertJob(GradeType.BEGINNER, contentsProviderType, JobType.BACKEND, jobCrawlingDtoList);

        jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                JobType.BACKEND, GradeType.JUNIOR, lastTitleList);
        insertJob(GradeType.JUNIOR, contentsProviderType, JobType.BACKEND,  jobCrawlingDtoList);

        jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                JobType.BACKEND, GradeType.INTERMEDIATE, lastTitleList);
        insertJob(GradeType.INTERMEDIATE, contentsProviderType, JobType.BACKEND,  jobCrawlingDtoList);

        jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                JobType.BACKEND, GradeType.SENIOR, lastTitleList);
        insertJob(GradeType.SENIOR, contentsProviderType, JobType.BACKEND,  jobCrawlingDtoList);

        jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                JobType.FRONT, GradeType.BEGINNER, lastTitleList);
        insertJob(GradeType.BEGINNER, contentsProviderType, JobType.FRONT, jobCrawlingDtoList);

        jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                JobType.FRONT, GradeType.JUNIOR, lastTitleList);
        insertJob(GradeType.JUNIOR, contentsProviderType, JobType.FRONT,  jobCrawlingDtoList);

        jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                JobType.FRONT, GradeType.INTERMEDIATE, lastTitleList);
        insertJob(GradeType.INTERMEDIATE, contentsProviderType, JobType.FRONT,  jobCrawlingDtoList);

        jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                JobType.FRONT, GradeType.SENIOR, lastTitleList);
        insertJob(GradeType.SENIOR, contentsProviderType, JobType.FRONT,  jobCrawlingDtoList);

        jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                JobType.MOBILE, GradeType.BEGINNER, lastTitleList);
        insertJob(GradeType.BEGINNER, contentsProviderType, JobType.MOBILE, jobCrawlingDtoList);

        jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                JobType.MOBILE, GradeType.JUNIOR, lastTitleList);
        insertJob(GradeType.JUNIOR, contentsProviderType, JobType.MOBILE,  jobCrawlingDtoList);

        jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                JobType.MOBILE, GradeType.INTERMEDIATE, lastTitleList);
        insertJob(GradeType.INTERMEDIATE, contentsProviderType, JobType.MOBILE,  jobCrawlingDtoList);

        jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                JobType.MOBILE, GradeType.SENIOR, lastTitleList);
        insertJob(GradeType.SENIOR, contentsProviderType, JobType.MOBILE,  jobCrawlingDtoList);
    }

    public ResponseEntity<CrawlingContentsLastUrlFindAllResponseDto> findAllCrawlingContentsLastTitle() {
        return hermesClient.findAllCrawlingContentsLastTitle();
    }
}
