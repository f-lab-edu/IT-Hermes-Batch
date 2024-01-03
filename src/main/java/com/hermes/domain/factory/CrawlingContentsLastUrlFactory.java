package com.hermes.domain.factory;

import com.hermes.domain.entity.CrawlingContentsLastUrl;
import com.hermes.domain.entity.Job;
import com.hermes.domain.entity.YoutubeAndNews;
import com.hermes.domain.util.JobType;
import com.hermes.infrastructure.CrawlingContentsLastUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CrawlingContentsLastUrlFactory {
    private final CrawlingContentsLastUrlRepository crawlingContentsLastUrlRepository;

    public List<CrawlingContentsLastUrl> parseAllCrawlingContentsLastTitle() {
        List<CrawlingContentsLastUrl> crawlingContentsLastUrlRepositoryAll = crawlingContentsLastUrlRepository.findAll();
        return crawlingContentsLastUrlRepositoryAll;
    }

    public CrawlingContentsLastUrl parseCrawlingContentsLastUrlToYoutubeAndNews(YoutubeAndNews youtubeAndNews) {
        return CrawlingContentsLastUrl.builder()
                .lastUrl(youtubeAndNews.getUrl())
                .contentsProvider(youtubeAndNews.getContentsProvider())
                .build();
    }

    public CrawlingContentsLastUrl parseCrawlingContentsLastUrlToJob(Job job, JobType jobType) {
        return CrawlingContentsLastUrl.builder()
                .lastUrl(job.getUrl())
                .grade(job.getGrade())
                .job(jobType)
                .contentsProvider(job.getContentsProvider())
                .build();
    }
}
