package com.hermes.service;

import com.hermes.dto.CrawlingContentsLastUrlDto;
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

    public List<YoutubeAndNewsCrawlingDto> crawlingNews(ContentsProviderType contentsProviderType, List<CrawlingContentsLastUrlDto> lastTitleList) {
        String crawlingContentsLastUrl = findCrawlingContentsLastUrl(contentsProviderType, lastTitleList);
        return crawlingClient.crawlingNews(contentsProviderType.getRequestPath(), crawlingContentsLastUrl);
        //return ContentsProviderType.YOZM.getWebClient().apply(ServerUrlType.CRAWLING.getWebClient().get());
    }

    public List<YoutubeAndNewsCrawlingDto> crawlingYoutube(ContentsProviderType contentsProviderType, List<CrawlingContentsLastUrlDto> lastTitleList) {
        String crawlingContentsLastUrl = findCrawlingContentsLastUrl(contentsProviderType, lastTitleList);
        return crawlingClient.crawlingYoutube(contentsProviderType.getRequestPath(), contentsProviderType.getTitle(), crawlingContentsLastUrl);
    }

    public List<JobCrawlingDto> crawlingJob(ContentsProviderType contentsProviderType, JobType jobType
            , int minExperience, int maxExperience, List<CrawlingContentsLastUrlDto> lastTitleList) {
        String crawlingContentsLastUrl = findCrawlingContentsLastUrl(contentsProviderType, lastTitleList);
        return crawlingClient.crawlingJob(contentsProviderType.getRequestPath(), jobType.getTitle(), minExperience, maxExperience, crawlingContentsLastUrl);
    }

    String findCrawlingContentsLastUrl(ContentsProviderType contentsProviderType, List<CrawlingContentsLastUrlDto> lastTitleList) {
        if (lastTitleList == null) return "noData";
        return lastTitleList.stream()
                .filter(v -> v.getContentsProvider().equals(contentsProviderType))
                .map(v -> v.getLastUrl())
                .findFirst()
                .orElse("noData");
    }
}
