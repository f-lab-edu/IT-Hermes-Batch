package com.hermes.dto.temp;

import com.hermes.dto.temp.CrawlingCommonRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestDto implements CrawlingCommonRequestDto {
    private String title;
    private String url;
    private String description;
    private String thumbnail;
    private String date;
}
