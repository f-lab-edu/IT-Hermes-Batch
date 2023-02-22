package com.hermes.presentation.controller;

import com.hermes.application.HermesRequestService;
import com.hermes.domain.util.ContentsProviderType;
import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
import com.hermes.presentation.client.CrawlingClient;
import com.hermes.presentation.dto.feignclient.JobCrawlingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/batch/dummy")
@RequiredArgsConstructor
public class DummyController {
    private final CrawlingClient crawlingClient;
    private final HermesRequestService hermesRequestService;

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
}
