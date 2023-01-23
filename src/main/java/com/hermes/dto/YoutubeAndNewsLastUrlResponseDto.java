package com.hermes.dto;

import com.hermes.util.ContentsProviderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YoutubeAndNewsLastUrlResponseDto {
    private ContentsProviderType contentsProvider;
    private String lastUrl;
}
