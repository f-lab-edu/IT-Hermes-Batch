package com.hermes.infrastructure;

import com.hermes.domain.entity.Job;
import com.hermes.domain.util.ContentsProviderType;
import com.hermes.domain.util.GradeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findJobByUrlGreaterThanAndContentsProviderAndGrade(String url, ContentsProviderType contentsProviderType, GradeType gradeType);
    List<Job> findJobByContentsProvider(ContentsProviderType contentsProviderType);
    Optional<List<Job>> findByUrl(@Param("url") String url);
    Long countBy();
}
