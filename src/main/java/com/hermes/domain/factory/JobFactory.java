package com.hermes.domain.factory;

import com.hermes.domain.entity.Job;
import com.hermes.domain.util.CommonUtil;
import com.hermes.domain.util.ContentsProviderType;
import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
import com.hermes.presentation.dto.feignclient.JobCrawlingDto;
import com.hermes.presentation.dto.feignclient.JobInsertRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JobFactory {

    public Job insertJob(ContentsProviderType contentsProvider, JobCrawlingDto jobCrawlingDto) {

        GradeType grade = jobCrawlingDto.getGrade();
        JobType jobType = jobCrawlingDto.getJob();

        String company = jobCrawlingDto.getCompany();
        String title = jobCrawlingDto.getTitle();
        String url = jobCrawlingDto.getUrl();
        String location = jobCrawlingDto.getLocation();
        LocalDateTime startDate = CommonUtil.parseLocalDateTime(jobCrawlingDto.getStartDate());
        LocalDateTime endDate = CommonUtil.parseLocalDateTime(jobCrawlingDto.getStartDate());

        Job job = Job
                .builder()
                .company(company)
                .title(title)
                .url(url)
                .location(location)
                .grade(grade)
                .contentsStartAt(startDate)
                .contentsEndAt(endDate)
                .isDelete(false)
                .viewCount(0L)
                .contentsProvider(contentsProvider)
                .jobType(jobType)
                .build();

        return job;
    }

    public List<Job> insertJobList(JobInsertRequestDto jobInsertRequestDto) {
        List<Job> jobList = new ArrayList<>();
        List<JobCrawlingDto> jobCrawlingDtoList = jobInsertRequestDto.getJobCrawlingDtoList();
        ContentsProviderType contentsProvider = jobInsertRequestDto.getContentsProvider();
        jobCrawlingDtoList.stream().forEach(v -> {
            Job job = insertJob(contentsProvider, v);
            jobList.add(job);
        });
        return jobList;
    }
}
