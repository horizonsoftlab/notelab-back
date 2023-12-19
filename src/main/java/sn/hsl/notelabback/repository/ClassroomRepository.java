package sn.hsl.notelabback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.hsl.notelabback.entities.ClassroomEntity;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Long> {
    boolean existsByNameIgnoreCaseAndSchoolId(String classroomName, Long schoolId);
}
