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

    public void insertYoutubeAndNews(CategoryType categoryType, ContentsProviderType contentsProviderType, List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList) {
        if (youtubeAndNewsCrawlingDtoList.isEmpty()) return;
        YoutubeAndNewsInsertRequestDto youtubeAndNewsInsertRequestDto = YoutubeAndNewsInsertRequestDto.builder()
                .category(categoryType)
                .contentsProvider(contentsProviderType)
                .youtubeAndNewsCrawlingDtoList(youtubeAndNewsCrawlingDtoList)
                .build();
        //contentsService.insertYoutubeAndNews(youtubeAndNewsInsertRequestDto);
    }

    /*
    public void insertJob(GradeType gradeType, ContentsProviderType contentsProviderType
            , JobType job, List<JobCrawlingDto> jobCrawlingDtoList) {
        if (jobCrawlingDtoList.isEmpty()) return;
        JobInsertRequestDto jobInsertRequestDto = JobInsertRequestDto.builder()
                .grade(gradeType)
                .contentsProvider(contentsProviderType)
                .job(job)
                .jobCrawlingDtoList(jobCrawlingDtoList)
                .build();
        contentsService.insertJob(jobInsertRequestDto);
    }*/

    public void finaAndInsertYoutubeCrawling(String lastTitleList, ContentsProviderType contentsProviderType) {
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingYoutube(contentsProviderType, lastTitleList);
        insertYoutubeAndNews(CategoryType.YOUTUBE, contentsProviderType, youtubeAndNewsCrawlingDtoList);
    }


    public void finaAndInsertNewsCrawling(String lastTitleList, ContentsProviderType contentsProviderType) {
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = nodeRequestService.crawlingNews(contentsProviderType, lastTitleList);
        insertYoutubeAndNews(CategoryType.NEWS, contentsProviderType, youtubeAndNewsCrawlingDtoList);
    }

    /*
    public void findAndInsertJobCrawling(List<CrawlingContentsLastUrl> lastTitleList, ContentsProviderType contentsProviderType) {
        Arrays.stream(JobType.values()).toList().stream().forEach(job -> {
            Arrays.stream(GradeType.values()).toList().stream().forEach(grade -> {
                List<JobCrawlingDto> jobCrawlingDtoList = nodeRequestService.crawlingJob(contentsProviderType,
                        job, grade, lastTitleList);
                insertJob(grade, contentsProviderType, job, jobCrawlingDtoList);
            });
        });
    }*/

    public List<CrawlingContentsLastUrl> findAllCrawlingContentsLastTitle() {
        return crawlingContentsLastUrlFactory.parseAllCrawlingContentsLastTitle();
    }
}
