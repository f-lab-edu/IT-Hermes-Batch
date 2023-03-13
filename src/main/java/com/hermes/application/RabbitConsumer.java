package com.hermes.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hermes.domain.factory.JobFactory;
import com.hermes.domain.factory.YoutubeAndNewsFactory;
import com.hermes.domain.util.CategoryType;
import com.hermes.domain.util.ContentsProviderType;
import com.hermes.infrastructure.JobRepository;
import com.hermes.infrastructure.YoutubeAndNewsRepository;
import com.hermes.presentation.dto.feignclient.JobCrawlingDto;
import com.hermes.presentation.dto.feignclient.YoutubeAndNewsCrawlingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitConsumer {

    private final YoutubeAndNewsRepository youtubeAndNewsRepository;
    private final YoutubeAndNewsFactory youtubeAndNewsFactory;
    private final JobRepository jobRepository;
    private final JobFactory jobFactory;
    private final HermesRequestService hermesRequestService;

    @RabbitListener(queues = "CODINGWORLDQueue")
    public void receiveCodingWorldNewsMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertYoutubeAndNews(CategoryType.NEWS, ContentsProviderType.CODING_WORLD, youtubeAndNewsCrawlingDtoList);
    }

    @RabbitListener(queues = "YOZMQueue")
    public void receiveYozmNewsMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertYoutubeAndNews(CategoryType.NEWS, ContentsProviderType.YOZM, youtubeAndNewsCrawlingDtoList);
    }

    @RabbitListener(queues = "NOMAD_CODERSQueue")
    public void receiveNomadCodersMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.NOMAD_CODERS, youtubeAndNewsCrawlingDtoList);
    }

    @RabbitListener(queues = "DREAM_CODINGQueue")
    public void receiveDreamCodingMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.DREAM_CODING, youtubeAndNewsCrawlingDtoList);
    }

    @RabbitListener(queues = "WHITESHIPQueue")
    public void receiveWhiteShipMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.WHITESHIP, youtubeAndNewsCrawlingDtoList);
    }

    @RabbitListener(queues = "FIQueue")
    public void receiveFiMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.FI, youtubeAndNewsCrawlingDtoList);
    }

    @RabbitListener(queues = "LINE_DEVELOPQueue")
    public void receiveLineDevelopMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.LINE_DEVELOP, youtubeAndNewsCrawlingDtoList);
    }

    @RabbitListener(queues = "DEVELOP_FOOTQueue")
    public void receiveDevelopFootMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.DEVELOP_FOOT, youtubeAndNewsCrawlingDtoList);
    }

    @RabbitListener(queues = "NULLNULL_DEVELOPQueue")
    public void receiveNullNullDevelopMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.NULLNULL_DEVELOP, youtubeAndNewsCrawlingDtoList);
    }

    @RabbitListener(queues = "DONGBINNAQueue")
    public void receiveDongBinNaMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.DONGBINNA, youtubeAndNewsCrawlingDtoList);
    }

    @RabbitListener(queues = "POPEQueue")
    public void receivePopeMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.POPE, youtubeAndNewsCrawlingDtoList);
    }

    @RabbitListener(queues = "WOOWA_COURSEQueue")
    public void receiveWoowaCourseMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertYoutubeAndNews(CategoryType.YOUTUBE, ContentsProviderType.WOOWA_COURSE, youtubeAndNewsCrawlingDtoList);
    }

    @RabbitListener(queues = "SARAMINQueue")
    public void receiveSaraminMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<JobCrawlingDto> jobCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertJob(ContentsProviderType.SARAMIN, jobCrawlingDtoList);
    }

    @RabbitListener(queues = "WANTEDQueue")
    public void receiveWantedMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<JobCrawlingDto> jobCrawlingDtoList = objectMapper.readValue(message, new TypeReference<>() {});
        hermesRequestService.insertJob(ContentsProviderType.WANTED, jobCrawlingDtoList);
    }
}
