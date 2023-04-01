package com.hermes.presentation.dto.feignclient;

import com.hermes.domain.util.CategoryType;
import com.hermes.domain.util.ContentsProviderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class YoutubeAndNewsInsertRequestDto {
    CategoryType category;
    ContentsProviderType contentsProvider;
    List<YoutubeAndNewsCrawlingDto> youtubeAndNewsCrawlingDtoList;

}
