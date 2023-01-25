package com.hermes.dto;

import com.hermes.util.ContentsProviderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CrawlingContentsLastUrlDto {
    ContentsProviderType contentsProvider;
    String lastUrl;
}
