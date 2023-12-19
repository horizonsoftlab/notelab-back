package sn.hsl.notelabback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import sn.hsl.notelabback.entities.SchoolEntity;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {
}
