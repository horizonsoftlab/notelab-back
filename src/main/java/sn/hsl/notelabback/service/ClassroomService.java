package sn.hsl.notelabback.service;

import sn.hsl.notelabback.web.dto.request.ClassroomReqDto;
import sn.hsl.notelabback.web.dto.response.ClassroomRspDto;

public interface ClassroomService {
    ClassroomRspDto createClassroom(Long schoolId, ClassroomReqDto classroomReqDto);
}
