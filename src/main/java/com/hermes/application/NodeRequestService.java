package com.hermes.application;

import com.hermes.domain.entity.CrawlingContentsLastUrl;
import com.hermes.presentation.dto.feignclient.JobCrawlingDto;
import com.hermes.presentation.dto.feignclient.YoutubeAndNewsCrawlingDto;
import com.hermes.presentation.client.CrawlingClient;
import com.hermes.domain.util.ContentsProviderType;
import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NodeRequestService {
    private final CrawlingClient crawlingClient;

    public List<YoutubeAndNewsCrawlingDto> crawlingNews(ContentsProviderType contentsProviderType, String lastTitle) {
        //String crawlingContentsLastUrl = findCrawlingContentsYoutubeAndNewsLastUrl(contentsProviderType, lastTitleList);
        System.out.println(crawlingClient.crawlingNews(contentsProviderType.getRequestPath(), lastTitle));
        return crawlingClient.crawlingNews(contentsProviderType.getRequestPath(), lastTitle);
    }

    public List<YoutubeAndNewsCrawlingDto> crawlingYoutube(ContentsProviderType contentsProviderType, String lastTitle) {
        //String crawlingContentsLastUrl = findCrawlingContentsYoutubeAndNewsLastUrl(contentsProviderType, lastTitle);
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtos = crawlingClient.crawlingYoutube(contentsProviderType.getRequestPath(), contentsProviderType.getTitle(), lastTitle);
        System.out.println(youtubeAndNewsCrawlingDtos);
        return youtubeAndNewsCrawlingDtos;
    }

    /*
    public List<JobCrawlingDto> crawlingJob(ContentsProviderType contentsProviderType, JobType jobType
            , GradeType gradeType, List<CrawlingContentsLastUrl> lastTitleList) {
        String crawlingContentsLastUrl = findCrawlingContentsJobLastUrl(contentsProviderType, lastTitleList, jobType, gradeType);
        return crawlingClient.crawlingJob(contentsProviderType.getRequestPath(), jobType.getTitle(), gradeType.getMinExperience(), gradeType.getMaxExperience(), crawlingContentsLastUrl);
    }*/

    /*
    private String findCrawlingContentsYoutubeAndNewsLastUrl(ContentsProviderType contentsProviderType, String lastTitle) {
        if (lastTitle == null) return "noData";
        return lastTitleList.stream()
                .filter(v -> v.getContentsProvider().equals(contentsProviderType))
                .map(v -> v.getLastUrl())
                .findFirst()
                .orElse("noData");
    }*/

    /*
    private String findCrawlingContentsJobLastUrl(ContentsProviderType contentsProviderType, List<CrawlingContentsLastUrl> lastTitleList, JobType jobType, GradeType gradeType) {
        if (lastTitleList == null) return "noData";
        return lastTitleList.stream()
                .filter(v -> v.getContentsProvider().equals(contentsProviderType))
                .filter(v -> v.getJob().equals(jobType))
                .filter(v -> v.getGrade().equals(gradeType))
                .map(v -> v.getLastUrl())
                .findFirst()
                .orElse("noData");
    }*/
}
