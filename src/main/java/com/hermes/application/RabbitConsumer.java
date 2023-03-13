package com.hermes.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hermes.domain.factory.JobFactory;
import com.hermes.domain.factory.YoutubeAndNewsFactory;
import com.hermes.domain.util.CategoryType;
import com.hermes.domain.util.ContentsProviderType;
import com.hermes.infrastructure.JobRepository;
import com.hermes.infrastructure.YoutubeAndNewsRepository;
import com.hermes.presentation.dto.feignclient.YoutubeAndNewsCrawlingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitConsumer {

    private final YoutubeAndNewsRepository youtubeAndNewsRepository;
    private final YoutubeAndNewsFactory youtubeAndNewsFactory;
    private final JobRepository jobRepository;
    private final JobFactory jobFactory;

    @RabbitListener(queues = "codingWorldQueue")
    public void receiveCodingWorldNewsMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        youtubeAndNewsRepository.save(youtubeAndNewsFactory.parseCrawlingYoutubeAndNews(objectMapper.readValue(message,YoutubeAndNewsCrawlingDto.class), ContentsProviderType.CODING_WORLD,CategoryType.NEWS));
    }

    @RabbitListener(queues = "yozmQueue")
    public void receiveYozmNewsMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        youtubeAndNewsRepository.save(youtubeAndNewsFactory.parseCrawlingYoutubeAndNews(objectMapper.readValue(message,YoutubeAndNewsCrawlingDto.class),ContentsProviderType.YOZM,CategoryType.NEWS));
    }

    @RabbitListener(queues = "NOMAD_CODERSQueue")
    public void receiveNomadCodersMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        youtubeAndNewsRepository.save(youtubeAndNewsFactory.parseCrawlingYoutubeAndNews(objectMapper.readValue(message,YoutubeAndNewsCrawlingDto.class),ContentsProviderType.NOMAD_CODERS,CategoryType.YOUTUBE));
    }

    @RabbitListener(queues = "DREAM_CODINGQueue")
    public void receiveDreamCodingMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        youtubeAndNewsRepository.save(youtubeAndNewsFactory.parseCrawlingYoutubeAndNews(objectMapper.readValue(message,YoutubeAndNewsCrawlingDto.class),ContentsProviderType.DREAM_CODING,CategoryType.YOUTUBE));
    }

    @RabbitListener(queues = "WHITESHIPQueue")
    public void receiveWhiteShipMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        youtubeAndNewsRepository.save(youtubeAndNewsFactory.parseCrawlingYoutubeAndNews(objectMapper.readValue(message,YoutubeAndNewsCrawlingDto.class),ContentsProviderType.WHITESHIP,CategoryType.YOUTUBE));
    }

    @RabbitListener(queues = "FIQueue")
    public void receiveFiMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        youtubeAndNewsRepository.save(youtubeAndNewsFactory.parseCrawlingYoutubeAndNews(objectMapper.readValue(message,YoutubeAndNewsCrawlingDto.class),ContentsProviderType.FI,CategoryType.YOUTUBE));
    }

    @RabbitListener(queues = "LINE_DEVELOPQueue")
    public void receiveLineDevelopMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        youtubeAndNewsRepository.save(youtubeAndNewsFactory.parseCrawlingYoutubeAndNews(objectMapper.readValue(message,YoutubeAndNewsCrawlingDto.class),ContentsProviderType.LINE_DEVELOP,CategoryType.YOUTUBE));
    }

    @RabbitListener(queues = "DEVELOP_FOOTQueue")
    public void receiveDevelopFootMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        youtubeAndNewsRepository.save(youtubeAndNewsFactory.parseCrawlingYoutubeAndNews(objectMapper.readValue(message,YoutubeAndNewsCrawlingDto.class),ContentsProviderType.DEVELOP_FOOT,CategoryType.YOUTUBE));
    }

    @RabbitListener(queues = "NULLNULL_DEVELOPQueue")
    public void receiveNullNullDevelopMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        youtubeAndNewsRepository.save(youtubeAndNewsFactory.parseCrawlingYoutubeAndNews(objectMapper.readValue(message,YoutubeAndNewsCrawlingDto.class),ContentsProviderType.NULLNULL_DEVELOP,CategoryType.YOUTUBE));
    }

    @RabbitListener(queues = "DONGBINNAQueue")
    public void receiveDongBinNaMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        youtubeAndNewsRepository.save(youtubeAndNewsFactory.parseCrawlingYoutubeAndNews(objectMapper.readValue(message,YoutubeAndNewsCrawlingDto.class),ContentsProviderType.DONGBINNA,CategoryType.YOUTUBE));
    }

    @RabbitListener(queues = "POPEQueue")
    public void receivePopeMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        youtubeAndNewsRepository.save(youtubeAndNewsFactory.parseCrawlingYoutubeAndNews(objectMapper.readValue(message,YoutubeAndNewsCrawlingDto.class),ContentsProviderType.POPE,CategoryType.YOUTUBE));
    }

    @RabbitListener(queues = "WOOWA_COURSEQueue")
    public void receiveWoowaCourseMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        youtubeAndNewsRepository.save(youtubeAndNewsFactory.parseCrawlingYoutubeAndNews(objectMapper.readValue(message,YoutubeAndNewsCrawlingDto.class),ContentsProviderType.WOOWA_COURSE,CategoryType.YOUTUBE));
    }

    /*
    @RabbitListener(queues = "saraminQueue")
    public void receiveSaraminMessage(final String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

    }

    @RabbitListener(queues = "wantedQueue")
    public void receiveWantedMessage(final String message){
        ObjectMapper objectMapper = new ObjectMapper();
    }*/

}
