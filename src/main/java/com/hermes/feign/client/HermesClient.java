package com.hermes.feign.client;

import com.hermes.dto.*;
import com.hermes.dto.temp.CrawlingCommonResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "hermes", url = "localhost:8080")
public interface HermesClient {
    @RequestMapping(method = RequestMethod.POST, value = "/youtube-and-news/")
    ResponseEntity<CrawlingCommonResponseDto> insertYoutubeAndNews(@RequestBody YoutubeAndNewsInsertRequestDto youtubeAndNewsCrawlingDtoList);

    @RequestMapping(method = RequestMethod.POST, value = "/job/")
    ResponseEntity<CrawlingCommonResponseDto> insertJob(@RequestBody JobInsertRequestDto jobInsertRequestDto);

    @RequestMapping(method = RequestMethod.POST, value = "/youtube-and-news/last-url")
    ResponseEntity<YoutubeAndNewsLastUrlResponseDto> findYoutubeAndNewsLastUrl(@RequestBody YoutubeAndNewsLastUrlRequestDto youtubeAndNewsLastUrlRequestDto);

    @RequestMapping(method = RequestMethod.POST, value = "/job/last-url")
    ResponseEntity<JobLastUrlResponseDto> findJobLastUrl(@RequestBody JobLastUrlRequestDto jobLastUrlRequestDto);
}
