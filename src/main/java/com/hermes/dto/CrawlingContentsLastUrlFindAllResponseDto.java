package com.hermes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CrawlingContentsLastUrlFindAllResponseDto {
    List<CrawlingContentsLastUrlDto> crawlingContentsLastUrlDtoList;
}
