package ru.sbrf.nhl.rtf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilitySnapshotRepository extends JpaRepository<AbilitySnapshot, Long> {
}
