package com.hermes.domain.util;

import com.hermes.application.HermesRequestService;
import com.hermes.domain.entity.CrawlingContentsLastUrl;

import java.util.Arrays;
import java.util.List;

public enum CategoryType {
    /*
    JOB("JOB", Arrays.asList(ContentsProviderType.SARAMIN, ContentsProviderType.WANTED),(crawlingContentsLastUrl, hermesRequestService) -> {
        List<ContentsProviderType> contentsProviderTypes = CategoryType.valueOf("JOB").contentsProviderTypes;
        contentsProviderTypes.stream().forEach(jobContentsProviderTypes-> {
            hermesRequestService.findAndInsertJobCrawling(crawlingContentsLastUrl,jobContentsProviderTypes);
        });
    }),*/
    /*
    NEWS("NEWS", Arrays.asList(ContentsProviderType.YOZM),(crawlingContentsLastUrl, hermesRequestService) -> {
        List<ContentsProviderType> contentsProviderTypes = CategoryType.valueOf("NEWS").contentsProviderTypes;
        contentsProviderTypes.stream().forEach(newsContentsProviderTypes -> {
            hermesRequestService.finaAndInsertNewsCrawling(crawlingContentsLastUrl, newsContentsProviderTypes);
        });
    }),*/
    YOUTUBE("YOUTUBE", Arrays.asList(
            ContentsProviderType.NOMAD_CODERS),
        (crawlingContentsLastUrl, hermesRequestService) -> {
        List<ContentsProviderType> contentsProviderTypes = CategoryType.valueOf("YOUTUBE").contentsProviderTypes;
        contentsProviderTypes.stream().forEach(youtubeContentsProviderTypes -> {
            hermesRequestService.finaAndInsertYoutubeCrawling(crawlingContentsLastUrl, youtubeContentsProviderTypes);
        });
    });

    private String title;
    private List<ContentsProviderType> contentsProviderTypes;
    private CategoryFunctional categoryFunctional;

    CategoryType(String title, List<ContentsProviderType> contentsProviderTypes, CategoryFunctional categoryFunctional) {
        this.title = title;
        this.contentsProviderTypes = contentsProviderTypes;
        this.categoryFunctional = categoryFunctional;
    }

    public static void findAndInsertCategoryFunctional(CategoryType categoryType, List<CrawlingContentsLastUrl> crawlingContentsLastUrlList
            , HermesRequestService hermesRequestService) {
        CategoryFunctional categoryFunctional = CategoryType.valueOf(categoryType.title).categoryFunctional;
        categoryFunctional.findAndCategoryCrawling(crawlingContentsLastUrlList, hermesRequestService);
    }
}
