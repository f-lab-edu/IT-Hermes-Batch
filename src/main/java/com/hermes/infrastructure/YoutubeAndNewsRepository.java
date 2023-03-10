package com.hermes.infrastructure;

import com.hermes.domain.entity.YoutubeAndNews;
import com.hermes.domain.util.CategoryType;
import com.hermes.domain.util.ContentsProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface YoutubeAndNewsRepository extends JpaRepository<YoutubeAndNews, Long> {

    List<YoutubeAndNews> findYoutubeAndNewsByContentsProvider(ContentsProviderType contentsProvider);
    Optional<YoutubeAndNews> findByUrl(@Param("url") String url);
    Long countYoutubeAndNewsByCategory(@Param("category") CategoryType category);

}