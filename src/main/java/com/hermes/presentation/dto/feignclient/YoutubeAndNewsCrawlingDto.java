package com.hermes.presentation.dto.feignclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeAndNewsCrawlingDto implements Serializable {
    private String title;
    private String description;
    private String thumbnail;
    private String url;
    private String date;
}
