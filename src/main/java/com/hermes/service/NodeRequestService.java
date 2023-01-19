package com.hermes.service;

import com.hermes.dto.CrawlingCommonRequestDto;
import com.hermes.util.ContentsProviderType;
import com.hermes.util.ServerUrlType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeRequestService {
    public List<CrawlingCommonRequestDto> findCrawlingData() {
        return ContentsProviderType.YOZM.getWebClient().apply(ServerUrlType.CRAWLING.getWebClient().get());
    }
}
