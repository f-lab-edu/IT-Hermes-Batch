package com.hermes.service;

import com.hermes.dto.JobCrawlingDto;
import com.hermes.dto.YoutubeAndNewsCrawlingDto;
import com.hermes.feign.client.CrawlingClient;
import com.hermes.util.ContentsProviderType;
import com.hermes.util.JobType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NodeRequestService {
    private final CrawlingClient crawlingClient;

    public List<YoutubeAndNewsCrawlingDto> crawlingNews(ContentsProviderType contentsProviderType, String lastUrl) {
        return crawlingClient.crawlingNews(contentsProviderType.getRequestPath(), lastUrl);
        //return ContentsProviderType.YOZM.getWebClient().apply(ServerUrlType.CRAWLING.getWebClient().get());
    }

    public List<YoutubeAndNewsCrawlingDto> crawlingYoutube(ContentsProviderType contentsProviderType, String lastUrl) {
        return crawlingClient.crawlingYoutube(contentsProviderType.getRequestPath(), contentsProviderType.getTitle(),lastUrl);
    }

    public List<JobCrawlingDto> crawlingJob(ContentsProviderType contentsProviderType, JobType jobType
            , int minExperience, int maxExperience, String lastUrl) {
        return crawlingClient.crawlingJob(contentsProviderType.getRequestPath(), jobType.getTitle(), minExperience, maxExperience, lastUrl);
    }
}
