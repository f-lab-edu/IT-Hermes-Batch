package com.hermes.presentation.controller;

import com.hermes.application.HermesRequestService;
import com.hermes.domain.entity.CrawlingContentsLastUrl;
import com.hermes.domain.util.CategoryType;
import com.hermes.domain.util.ContentsProviderType;
import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
import com.hermes.presentation.client.CrawlingClient;
import com.hermes.presentation.dto.feignclient.JobCrawlingDto;
import com.hermes.presentation.dto.feignclient.YoutubeAndNewsCrawlingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/batch/dummy")
@RequiredArgsConstructor
public class DummyController {
    private final CrawlingClient crawlingClient;
    private final HermesRequestService hermesRequestService;

    @RequestMapping(value = "/mq", method = RequestMethod.GET)
    public ResponseEntity<String> findAndInsertDummySaramin() {
        List<CrawlingContentsLastUrl> crawlingContentsLastTitle = hermesRequestService.findAllCrawlingContentsLastTitle();
        List<CategoryType> categoryTypes = Arrays.stream(CategoryType.values()).toList();
        categoryTypes.stream().forEach(categoryType -> CategoryType.findAndInsertCategoryFunctional(categoryType, crawlingContentsLastTitle, hermesRequestService));
        return ResponseEntity.ok("success");
    }

    /*
    @RequestMapping(value = "/saramin", method = RequestMethod.GET)
    public ResponseEntity<String> findAndInsertDummySaramin() {
        for (int page = 1; page <= Integer.MAX_VALUE; page++) {
            List<JobCrawlingDto> jobCrawlingList = crawlingClient.crawlingDummyJob(ContentsProviderType.SARAMIN.getTitle(), String.valueOf(page));
            if (jobCrawlingList.size() == 0) break;
            Arrays.stream(JobType.values()).toList().stream().forEach(job -> {
                Arrays.stream(GradeType.values()).toList().stream().forEach(grade -> {
                    hermesRequestService.insertJob(grade, ContentsProviderType.SARAMIN, job, jobCrawlingList);
                });
            });
        }
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/wanted",method = RequestMethod.GET)
    public ResponseEntity<String> findAndInsertDummyWanted(){
        for(int page=0; page<=3000; page+=20){
            List<JobCrawlingDto> wantedCrawlingList = crawlingClient.crawlingDummyJob(ContentsProviderType.WANTED.getTitle(), String.valueOf(page));
            if(wantedCrawlingList.size() == 0) break;
            Arrays.stream(JobType.values()).toList().stream().forEach(job -> {
                Arrays.stream(GradeType.values()).toList().stream().forEach(grade ->{
                    hermesRequestService.insertJob(grade,ContentsProviderType.WANTED,job,wantedCrawlingList);
                });
            });
        }
        return ResponseEntity.ok("success");
    }*/

    @RequestMapping(value = "/yozm",method = RequestMethod.GET)
    public ResponseEntity<String> findAndInsertDummyYozm(){
        for(int page=1; page<=47; page++){
            List<YoutubeAndNewsCrawlingDto> yozmCrawlingList = crawlingClient.crawlingDummyNews(ContentsProviderType.YOZM.getTitle(), String.valueOf(page));
            if(yozmCrawlingList.size() == 0) break;
            hermesRequestService.insertYoutubeAndNews(CategoryType.NEWS,ContentsProviderType.YOZM,yozmCrawlingList);
        }
        return ResponseEntity.ok("success");
    }
}
