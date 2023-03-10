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

    public List<Job> insertJob(JobInsertRequestDto jobInsertRequestDto) {
        List<Job> jobList = new ArrayList<>();
        List<JobCrawlingDto> jobCrawlingDtoList = jobInsertRequestDto.getJobCrawlingDtoList();
        ContentsProviderType contentsProvider = jobInsertRequestDto.getContentsProvider();
        GradeType grade = jobInsertRequestDto.getGrade();
        JobType jobType = jobInsertRequestDto.getJob();
        jobCrawlingDtoList.stream().forEach(v -> {
            String company = v.getCompany();
            String title = v.getTitle();
            String url = v.getUrl();
            String location = v.getLocation();
            LocalDateTime startDate = CommonUtil.parseLocalDateTime(v.getStartDate());
            LocalDateTime endDate = CommonUtil.parseLocalDateTime(v.getStartDate());

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

            jobList.add(job);
        });
        return jobList;
    }
}
