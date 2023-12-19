package sn.hsl.notelabback.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.hsl.notelabback.exceptions.NotelabException;
import sn.hsl.notelabback.repository.ClassroomRepository;
import sn.hsl.notelabback.repository.SchoolRepository;
import sn.hsl.notelabback.service.ClassroomService;
import sn.hsl.notelabback.web.dto.request.ClassroomReqDto;
import sn.hsl.notelabback.web.dto.response.ClassroomRspDto;
import sn.hsl.notelabback.web.mapper.ClassroomMapper;

import static sn.hsl.notelabback.web.tools.response.ApiMessage.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class ClassroomServiceImpl implements ClassroomService {
    final ClassroomRepository classroomRepository;
    final ClassroomMapper classroomMapper;
    final SchoolRepository schoolRepository;

    @Override
    public ClassroomRspDto createClassroom(Long schoolId, ClassroomReqDto classroomReqDto) {
        var school = schoolRepository
                .findById(schoolId)
                .orElseThrow(() -> new NotelabException(SCHOOL_NOT_FOUND, "id", schoolId));

        if(!classroomReqDto.getDomain().getSpecialities().contains(classroomReqDto.getSpeciality())) {
            throw new NotelabException(DOMAIN_NOT_CONTAIN_SPECIALITY, classroomReqDto.getDomain().getDescription(), classroomReqDto.getSpeciality().getDescription());
        } else if(!classroomReqDto.getCycle().getLevels().contains(classroomReqDto.getLevel())) {
            throw new NotelabException(CYCLE_NOT_CONTAIN_LEVEL, classroomReqDto.getCycle().getDescription(), classroomReqDto.getLevel().getDescription());
        } else if(classroomRepository.existsByNameIgnoreCaseAndSchoolId(classroomReqDto.getName(), schoolId)) {
            throw new NotelabException(SCHOOL_ALREADY_HAS_CLASSROOM, classroomReqDto.getName());
        }

        var classroom = classroomMapper.toEntity(classroomReqDto);
        classroom.setSchool(school);

        return classroomMapper.toDto(classroomRepository.save(classroom));
    }
}
