package com.hermes.application;

import com.hermes.domain.entity.CrawlingContentsLastUrl;
import com.hermes.domain.factory.CrawlingContentsLastUrlFactory;
import com.hermes.domain.util.CategoryType;
import com.hermes.domain.util.ContentsProviderType;
import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
import com.hermes.presentation.dto.feignclient.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HermesRequestService {
    private final NodeRequestService nodeRequestService;
    private final CrawlingContentsLastUrlFactory crawlingContentsLastUrlFactory;
    private final ContentsService contentsService;

    public void insertYoutubeAndNews(CategoryType categoryType, ContentsProviderType contentsProviderType,YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto) {
        contentsService.insertYoutubeAndNews(categoryType, contentsProviderType,youtubeAndNewsCrawlingDto);
    }

    public void insertJob(ContentsProviderType contentsProviderType, JobCrawlingDto jobCrawlingDto) {
        contentsService.insertJob(contentsProviderType, jobCrawlingDto);
    }

    public void insertYoutubeAndNewsList(CategoryType categoryType, ContentsProviderType contentsProviderType, List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList) {
        if (youtubeAndNewsCrawlingDtoList.isEmpty()) return;
        YoutubeAndNewsInsertRequestDto youtubeAndNewsInsertRequestDto = YoutubeAndNewsInsertRequestDto.builder()
                .category(categoryType)
                .contentsProvider(contentsProviderType)
                .youtubeAndNewsCrawlingDtoList(youtubeAndNewsCrawlingDtoList)
                .build();
        contentsService.insertYoutubeAndNewsList(youtubeAndNewsInsertRequestDto);
    }

    public void insertJobList(ContentsProviderType contentsProviderType, List<JobCrawlingDto> jobCrawlingDtoList) {
        if (jobCrawlingDtoList.isEmpty()) return;
        JobInsertRequestDto jobInsertRequestDto = JobInsertRequestDto.builder()
                .grade(jobCrawlingDtoList.get(0).getGrade())
                .contentsProvider(contentsProviderType)
                .job(jobCrawlingDtoList.get(0).getJob())
                .jobCrawlingDtoList(jobCrawlingDtoList)
                .build();
        contentsService.insertJobList(jobInsertRequestDto);
    }

    public void finaAndInsertYoutubeCrawling(List<CrawlingContentsLastUrl> lastTitleList, ContentsProviderType contentsProviderType) {
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingYoutube(contentsProviderType, lastTitleList);
        //insertYoutubeAndNews(CategoryType.YOUTUBE, contentsProviderType, youtubeAndNewsCrawlingDtoList);
    }

    public void finaAndInsertNewsCrawling(List<CrawlingContentsLastUrl> lastTitleList, ContentsProviderType contentsProviderType) {
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingNews(contentsProviderType, lastTitleList);
        //insertYoutubeAndNews(CategoryType.NEWS, contentsProviderType, youtubeAndNewsCrawlingDtoList);
    }

    public void findAndInsertJobCrawling(List<CrawlingContentsLastUrl> lastTitleList, ContentsProviderType contentsProviderType) {
        Arrays.stream(JobType.values()).toList().stream().forEach(job -> {
            Arrays.stream(GradeType.values()).toList().stream().forEach(grade -> {
                List<JobCrawlingDto> jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                        job, grade, lastTitleList);
                //insertJob(grade, contentsProviderType, job, jobCrawlingDtoList);
            });
        });
    }

    public List<CrawlingContentsLastUrl> findAllCrawlingContentsLastTitle() {
        return crawlingContentsLastUrlFactory.parseAllCrawlingContentsLastTitle();
    }
}
