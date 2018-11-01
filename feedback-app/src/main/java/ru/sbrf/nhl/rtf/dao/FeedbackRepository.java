package ru.sbrf.nhl.rtf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query(value = "select f.* from FEEDBACK f where f.TARGET_ID = :targetId and f.POSTED_AT > :lastDate", nativeQuery = true)
    Collection<Feedback> findNewByPerson(@Param("targetId") Long targetPersonId, @Param("lastDate") Date lastDate);

    @Query(value = "select f.* from FEEDBACK f where f.TARGET_ID = :targetId", nativeQuery = true)
    Collection<Feedback> findNewByPerson(@Param("targetId") Long targetPersonId);
}
