package com.hermes.application;

import com.hermes.domain.entity.CrawlingContentsLastUrl;
import com.hermes.domain.entity.Job;
import com.hermes.domain.entity.YoutubeAndNews;
import com.hermes.domain.factory.CrawlingContentsLastUrlFactory;
import com.hermes.domain.factory.JobFactory;
import com.hermes.domain.factory.YoutubeAndNewsFactory;
import com.hermes.domain.util.CategoryType;
import com.hermes.domain.util.ContentsProviderType;
import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
import com.hermes.infrastructure.CrawlingContentsLastUrlRepository;
import com.hermes.infrastructure.JobRepository;
import com.hermes.infrastructure.YoutubeAndNewsRepository;
import com.hermes.presentation.dto.feignclient.JobCrawlingDto;
import com.hermes.presentation.dto.feignclient.JobInsertRequestDto;
import com.hermes.presentation.dto.feignclient.YoutubeAndNewsCrawlingDto;
import com.hermes.presentation.dto.feignclient.YoutubeAndNewsInsertRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentsService {
    private final JobFactory jobFactory;
    private final JobRepository jobRepository;
    private final YoutubeAndNewsFactory youtubeAndNewsFactory;
    private final YoutubeAndNewsRepository youtubeAndNewsRepository;
    private final CrawlingContentsLastUrlFactory crawlingContentsLastUrlFactory;
    private final CrawlingContentsLastUrlRepository crawlingContentsLastUrlRepository;

    @Transactional
    public void insertJob(ContentsProviderType contentsProviderType, JobCrawlingDto jobCrawlingDto) {
        Job job = jobFactory.insertJob(contentsProviderType, jobCrawlingDto);
        jobRepository.save(job);
        int crawlingIndex = Integer.parseInt(jobCrawlingDto.getCrawlingIndex());
        if (crawlingIndex == 0) {
            updateJobLastUrl(job, job.getJobType());
        }
    }

    @Transactional
    public void insertYoutubeAndNews(CategoryType categoryType, ContentsProviderType contentsProviderType,
                                     YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto) {
        YoutubeAndNews youtubeAndNews = youtubeAndNewsFactory.insertYoutubeAndNews(categoryType, contentsProviderType, youtubeAndNewsCrawlingDto);
        youtubeAndNewsRepository.save(youtubeAndNews);

        int crawlingIndex = Integer.parseInt(youtubeAndNewsCrawlingDto.getCrawlingIndex());
        if (crawlingIndex == 0) {
            updateYoutubeAndNewsLastUrl(youtubeAndNews);
        }
    }

    @Transactional
    public void insertJobList(JobInsertRequestDto jobInsertRequestDto) {
        if (jobInsertRequestDto.getJobCrawlingDtoList().isEmpty()) throw new EmptyStackException();

        List<Job> jobList = jobFactory.insertJobList(jobInsertRequestDto);
        jobList.stream().forEach(v -> jobRepository.save(v));

        Job recentJob = jobList.get(0);

        updateJobLastUrl(recentJob, jobInsertRequestDto.getJob());
    }

    @Transactional
    public void insertYoutubeAndNewsList(YoutubeAndNewsInsertRequestDto youtubeAndNewsCrawlingDtoList) {
        if (youtubeAndNewsCrawlingDtoList.getYoutubeAndNewsCrawlingDtoList().isEmpty()) throw new EmptyStackException();

        List<YoutubeAndNews> youtubeAndNewsList = youtubeAndNewsFactory.insertYoutubeAndNewsList(youtubeAndNewsCrawlingDtoList);
        youtubeAndNewsList.stream().forEach(v -> youtubeAndNewsRepository.save(v));

        YoutubeAndNews recentYoutubeAndNews = youtubeAndNewsList.get(0);

        updateYoutubeAndNewsLastUrl(recentYoutubeAndNews);
    }

    private void updateJobLastUrl(Job job, JobType jobType) {
        ContentsProviderType contentsProvider = job.getContentsProvider();
        GradeType grade = job.getGrade();

        Optional<CrawlingContentsLastUrl> contentsLastTitle = crawlingContentsLastUrlRepository.findByContentsProviderAndGradeAndJob(contentsProvider, grade, jobType);
        CrawlingContentsLastUrl recentCrawlingContentsLastUrl = crawlingContentsLastUrlFactory.parseCrawlingContentsLastUrlToJob(job, jobType);

        contentsLastTitle.ifPresentOrElse(
                v -> {
                    v.updateLastUrl(recentCrawlingContentsLastUrl);
                },
                () -> {
                    crawlingContentsLastUrlRepository.save(recentCrawlingContentsLastUrl);
                }
        );
    }

    private void updateYoutubeAndNewsLastUrl(YoutubeAndNews youtubeAndNews) {
        ContentsProviderType contentsProvider = youtubeAndNews.getContentsProvider();
        Optional<CrawlingContentsLastUrl> contentsLastTitle = crawlingContentsLastUrlRepository.findByContentsProvider(contentsProvider);
        CrawlingContentsLastUrl recentCrawlingContentsLastUrl = crawlingContentsLastUrlFactory.parseCrawlingContentsLastUrlToYoutubeAndNews(youtubeAndNews);

        contentsLastTitle.ifPresentOrElse(
                v -> {
                    v.updateLastUrl(recentCrawlingContentsLastUrl);
                },
                () -> {
                    crawlingContentsLastUrlRepository.save(recentCrawlingContentsLastUrl);
                }
        );
    }
}
