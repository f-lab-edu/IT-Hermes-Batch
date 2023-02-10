package com.hermes.presentation.client;

import com.hermes.presentation.dto.feignclient.JobCrawlingDto;
import com.hermes.presentation.dto.feignclient.YoutubeAndNewsCrawlingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "crawling", url = "${springboot.ip.crawling}")

public interface CrawlingClient {
    @RequestMapping(method = RequestMethod.GET, value = "/news/{contentsProvider}")
    List<YoutubeAndNewsCrawlingDto> crawlingNews(@PathVariable("contentsProvider") String contentsProvider, @RequestParam("url") String url);

    @RequestMapping(method = RequestMethod.GET, value = "/videos/youtube")
    List<YoutubeAndNewsCrawlingDto> crawlingYoutube(@RequestParam("contentsProvider") String contentsProvider, @RequestParam("youtuber") String youtuber, @RequestParam("url") String url);

    @RequestMapping(method = RequestMethod.GET, value = "/job/{contentsProvider}")
    List<JobCrawlingDto> crawlingJob(@PathVariable("contentsProvider") String contentsProvider, @RequestParam("job") String job
            , @RequestParam("minExperience") int minExperience, @RequestParam("maxExperience") int maxExperience, @RequestParam("url") String url);
}
