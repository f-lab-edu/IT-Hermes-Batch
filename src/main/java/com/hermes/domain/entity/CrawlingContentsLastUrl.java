package com.hermes.domain.entity;

import com.hermes.domain.util.ContentsProviderType;
import com.hermes.domain.util.GradeType;
import com.hermes.domain.util.JobType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CrawlingContentsLastUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentsProviderType contentsProvider;

    @Enumerated(EnumType.STRING)
    private GradeType grade;

    @Enumerated(EnumType.STRING)
    private JobType job;

    @Column(nullable = false, length = 1000)
    private String lastUrl;

    public void updateLastUrl(CrawlingContentsLastUrl crawlingContentsLastUrl) {
        this.lastUrl = crawlingContentsLastUrl.getLastUrl();
    }
}