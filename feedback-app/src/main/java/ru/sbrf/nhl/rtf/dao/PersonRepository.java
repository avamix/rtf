package ru.sbrf.nhl.rtf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person getBySuccessFactorId(String senderSuccessFactoryId);
}
