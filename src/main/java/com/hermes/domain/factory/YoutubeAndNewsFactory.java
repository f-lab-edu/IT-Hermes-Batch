package com.hermes.domain.factory;

import com.hermes.domain.entity.YoutubeAndNews;
import com.hermes.domain.util.CategoryType;
import com.hermes.domain.util.CommonUtil;
import com.hermes.domain.util.ContentsProviderType;
import com.hermes.presentation.dto.feignclient.YoutubeAndNewsCrawlingDto;
import com.hermes.presentation.dto.feignclient.YoutubeAndNewsInsertRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class YoutubeAndNewsFactory {

    public List<YoutubeAndNews> parseYoutubeAndNews(YoutubeAndNewsInsertRequestDto youtubeAndNewsCrawlingDtoList) {
        List<YoutubeAndNews> youtubeAndNewsList = new ArrayList<>();
        List<YoutubeAndNewsCrawlingDto> crawlingList = youtubeAndNewsCrawlingDtoList.getYoutubeAndNewsCrawlingDtoList();
        CategoryType categoryType = youtubeAndNewsCrawlingDtoList.getCategory();
        ContentsProviderType contentsProvider = youtubeAndNewsCrawlingDtoList.getContentsProvider();
        crawlingList.stream().forEach(v -> {
            String title = v.getTitle();
            String description = v.getDescription();
            String thumbnail = v.getThumbnail();
            String url = v.getUrl();
            LocalDateTime date = CommonUtil.parseLocalDateTime(v.getDate());

            YoutubeAndNews youtubeAndNews = YoutubeAndNews
                    .builder()
                    .description(description)
                    .title(title)
                    .image(thumbnail)
                    .url(url)
                    .contentsStartAt(date)
                    .isDelete(false)
                    .viewCount(0L)
                    .category(categoryType)
                    .contentsProvider(contentsProvider)
                    .build();
            youtubeAndNewsList.add(youtubeAndNews);
        });
        return youtubeAndNewsList;
    }

    public YoutubeAndNews parseCrawlingYoutubeAndNews(YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto,ContentsProviderType contentsProviderType,CategoryType categoryType){
        YoutubeAndNews youtubeAndNews = YoutubeAndNews
                .builder()
                .title(youtubeAndNewsCrawlingDto.getTitle())
                .description(youtubeAndNewsCrawlingDto.getDescription())
                .image(youtubeAndNewsCrawlingDto.getThumbnail())
                .url(youtubeAndNewsCrawlingDto.getUrl())
                .contentsStartAt(CommonUtil.parseLocalDateTime(youtubeAndNewsCrawlingDto.getDate()))
                .viewCount(0L)
                .isDelete(false)
                .category(categoryType)
                .contentsProvider(contentsProviderType)
                .build();
        return youtubeAndNews;
    }

}
