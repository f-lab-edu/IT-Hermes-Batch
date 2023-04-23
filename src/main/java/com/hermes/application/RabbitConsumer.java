package com.hermes.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hermes.domain.factory.JobFactory;
import com.hermes.domain.factory.YoutubeAndNewsFactory;
import com.hermes.domain.util.CategoryType;
import com.hermes.domain.util.ContentsProviderType;
import com.hermes.presentation.dto.feignclient.JobCrawlingDto;
import com.hermes.presentation.dto.feignclient.YoutubeAndNewsCrawlingDto;
import io.javalin.http.NotFoundResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitConsumer {
    private final HermesRequestService hermesRequestService;

    /*
    @RabbitListener(queues = "CODINGWORLDQueue")
    public ResponseEntity<String> receiveCodingWorldNewsMessage(final String message) {
        YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto = parseYoutubeAndNewsCrawling(message);
        //hermesRequestService.insertYoutubeAndNews(CategoryType.NEWS, ContentsProviderType.CODING_WORLD, youtubeAndNewsCrawlingDto);
        return ResponseEntity.ok("success");
    }
    */
    @RabbitListener(queues = "YOZMQueue")
    public void receiveYozmNewsMessage(final String message) {
        YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto = parseYoutubeAndNewsCrawling(message);
        //hermesRequestService.insertYoutubeAndNews(CategoryType.NEWS, ContentsProviderType.YOZM, youtubeAndNewsCrawlingDto);
        System.out.println(youtubeAndNewsCrawlingDto);
    }

    @RabbitListener(queues = "NOMAD_CODERSQueue")
    public void receiveNomadCodersMessage(final String message) {
        YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto = parseYoutubeAndNewsCrawling(message);
        //hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.NOMAD_CODERS, youtubeAndNewsCrawlingDto);
        System.out.println(youtubeAndNewsCrawlingDto);
    }

    @RabbitListener(queues = "DREAM_CODINGQueue")
    public void receiveDreamCodingMessage(final String message) {
        YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto = parseYoutubeAndNewsCrawling(message);
        //hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.DREAM_CODING, youtubeAndNewsCrawlingDto);
        System.out.println(youtubeAndNewsCrawlingDto);
    }

    @RabbitListener(queues = "WHITESHIPQueue")
    public void receiveWhiteShipMessage(final String message) {
        YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto = parseYoutubeAndNewsCrawling(message);
        //hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.WHITESHIP, youtubeAndNewsCrawlingDto);
        System.out.println(youtubeAndNewsCrawlingDto);
    }

    @RabbitListener(queues = "FIQueue")
    public void receiveFiMessage(final String message) {
        YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto = parseYoutubeAndNewsCrawling(message);
        //hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.FI, youtubeAndNewsCrawlingDto);
        System.out.println(youtubeAndNewsCrawlingDto);
    }

    @RabbitListener(queues = "LINE_DEVELOPQueue")
    public void receiveLineDevelopMessage(final String message) {
        YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto = parseYoutubeAndNewsCrawling(message);
        //hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.LINE_DEVELOP, youtubeAndNewsCrawlingDto);
        System.out.println(message);
    }

    @RabbitListener(queues = "DEVELOP_FOOTQueue")
    public void receiveDevelopFootMessage(final String message) {
        YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto = parseYoutubeAndNewsCrawling(message);
        //hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.DEVELOP_FOOT, youtubeAndNewsCrawlingDto);
        System.out.println(youtubeAndNewsCrawlingDto);
    }

    @RabbitListener(queues = "NULLNULL_DEVELOPQueue")
    public void receiveNullNullDevelopMessage(final String message) {
        YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto = parseYoutubeAndNewsCrawling(message);
        //hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.NULLNULL_DEVELOP, youtubeAndNewsCrawlingDto);
        System.out.println(youtubeAndNewsCrawlingDto);
    }

    @RabbitListener(queues = "DONGBINNAQueue")
    public void receiveDongBinNaMessage(final String message) {
        YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto = parseYoutubeAndNewsCrawling(message);
        //hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.DONGBINNA, youtubeAndNewsCrawlingDto);
        System.out.println(youtubeAndNewsCrawlingDto);
       // return ResponseEntity.ok("success");
    }

    @RabbitListener(queues = "POPEQueue")
    public void receivePopeMessage(final String message) {
        YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto = parseYoutubeAndNewsCrawling(message);
        //hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.POPE, youtubeAndNewsCrawlingDto);
        System.out.println(youtubeAndNewsCrawlingDto);
        //return ResponseEntity.ok("success");
    }

    @RabbitListener(queues = "WOOWA_COURSEQueue")
    public void receiveWoowaCourseMessage(final String message) {
        YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto = parseYoutubeAndNewsCrawling(message);
        //hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.WOOWA_COURSE, youtubeAndNewsCrawlingDto);
        System.out.println(youtubeAndNewsCrawlingDto);
        //return ResponseEntity.ok("success");
    }

    /*
    @RabbitListener(queues = "SARAMINQueue")
    public void receiveSaraminMessage(final String message) {
        JobCrawlingDto jobCrawlingDto = parseJobCrawling(message);
        hermesRequestService.insertJob(ContentsProviderType.SARAMIN, jobCrawlingDto);
    }

    @RabbitListener(queues = "WANTEDQueue")
    public void receiveWantedMessage(final String message) {
        JobCrawlingDto jobCrawlingDto = parseJobCrawling(message);
        hermesRequestService.insertJob(ContentsProviderType.WANTED, jobCrawlingDto);
    }*/

    public YoutubeAndNewsCrawlingDto parseYoutubeAndNewsCrawling(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(message, YoutubeAndNewsCrawlingDto.class);
        } catch (JsonProcessingException e) {
            throw new NotFoundResponse();

        }
    }

    public JobCrawlingDto parseJobCrawling(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(message, JobCrawlingDto.class);
        } catch (JsonProcessingException e) {
            throw new NotFoundResponse();
        }
    }
}
