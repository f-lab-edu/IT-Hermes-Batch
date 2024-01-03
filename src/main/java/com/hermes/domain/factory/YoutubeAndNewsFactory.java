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
    public YoutubeAndNews insertYoutubeAndNews(CategoryType categoryType, ContentsProviderType contentsProvider, YoutubeAndNewsCrawlingDto youtubeAndNewsCrawlingDto) {

            String title = youtubeAndNewsCrawlingDto.getTitle();
            String description = youtubeAndNewsCrawlingDto.getDescription();
            String thumbnail = youtubeAndNewsCrawlingDto.getThumbnail();
            String url = youtubeAndNewsCrawlingDto.getUrl();
            LocalDateTime date = CommonUtil.parseLocalDateTime(youtubeAndNewsCrawlingDto.getDate());

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

        return youtubeAndNews;
    }
    public List<YoutubeAndNews> insertYoutubeAndNewsList(YoutubeAndNewsInsertRequestDto youtubeAndNewsCrawlingDtoList) {
        List<YoutubeAndNews> youtubeAndNewsList = new ArrayList<>();
        List<YoutubeAndNewsCrawlingDto> crawlingList = youtubeAndNewsCrawlingDtoList.getYoutubeAndNewsCrawlingDtoList();
        CategoryType categoryType = youtubeAndNewsCrawlingDtoList.getCategory();
        ContentsProviderType contentsProvider = youtubeAndNewsCrawlingDtoList.getContentsProvider();
        crawlingList.stream().forEach(v -> {
            YoutubeAndNews youtubeAndNews = insertYoutubeAndNews(categoryType, contentsProvider, v);
            youtubeAndNewsList.add(youtubeAndNews);
        });
        return youtubeAndNewsList;
    }
}
