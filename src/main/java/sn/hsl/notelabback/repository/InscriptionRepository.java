package sn.hsl.notelabback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.hsl.notelabback.entities.InscriptionEntity;

@Repository
public interface InscriptionRepository extends JpaRepository<InscriptionEntity, Long> {
    long countByClassroomIdAndYear(Long classroomId, String year);
}
