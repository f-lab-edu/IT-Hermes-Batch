package com.hermes.presentation.client;

import com.hermes.presentation.dto.feignclient.CrawlingCommonResponseDto;
import com.hermes.presentation.dto.feignclient.CrawlingContentsLastUrlFindAllResponseDto;
import com.hermes.presentation.dto.feignclient.JobInsertRequestDto;
import com.hermes.presentation.dto.feignclient.YoutubeAndNewsInsertRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "hermes", url = "${springboot.ip.server}/api")
public interface HermesClient {

    @RequestMapping(method = RequestMethod.POST, value = "/youtube-and-news/")
    ResponseEntity<CrawlingCommonResponseDto> insertYoutubeAndNews(@RequestBody YoutubeAndNewsInsertRequestDto youtubeAndNewsCrawlingDtoList);

    @RequestMapping(method = RequestMethod.POST, value = "/job/")
    ResponseEntity<CrawlingCommonResponseDto> insertJob(@RequestBody JobInsertRequestDto jobInsertRequestDto);

    @RequestMapping(method = RequestMethod.GET, value = "/crawling-contents-last-title/")
    ResponseEntity<CrawlingContentsLastUrlFindAllResponseDto> findAllCrawlingContentsLastTitle();

    @RequestMapping(method = RequestMethod.GET, value = "/alarm/subscription")
    ResponseEntity<CrawlingCommonResponseDto> alarmSubscribeContents();

    @RequestMapping(method = RequestMethod.GET, value = "/alarm/recommend")
    ResponseEntity<CrawlingCommonResponseDto> alarmRecommendContents();

    @RequestMapping(method = RequestMethod.GET, value = "/contents/elasticsearch")
    ResponseEntity<CrawlingCommonResponseDto> updateContentsElasticSearch();

    @RequestMapping(method = RequestMethod.GET, value = "/subscribe/elasticsearch")
    ResponseEntity<CrawlingCommonResponseDto> updateSubscribeElasticSearch();

}
