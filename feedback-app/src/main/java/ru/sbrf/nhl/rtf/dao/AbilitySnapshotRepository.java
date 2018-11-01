package ru.sbrf.nhl.rtf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AbilitySnapshotRepository extends JpaRepository<AbilitySnapshot, Long> {
    @Query(value = "select s.* from ABILITY_SNAPSHOT s where s.PERSON_ID = :personId ORDER BY s.CREATED_AT DESC LIMIT 1", nativeQuery = true)
    AbilitySnapshot findLast(@Param("personId") Long personId);
}
