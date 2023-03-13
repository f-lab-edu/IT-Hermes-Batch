package com.hermes.domain.util;

import com.hermes.application.HermesRequestService;
import com.hermes.domain.entity.CrawlingContentsLastUrl;

import java.util.List;

@FunctionalInterface
public interface CategoryFunctional {
    void findAndCategoryCrawling(List<CrawlingContentsLastUrl> crawlingContentsLastUrlList, HermesRequestService hermesRequestService);
}
