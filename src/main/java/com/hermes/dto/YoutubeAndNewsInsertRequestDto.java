package com.hermes.dto;

import com.hermes.util.CategoryType;
import com.hermes.util.ContentsProviderType;
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
