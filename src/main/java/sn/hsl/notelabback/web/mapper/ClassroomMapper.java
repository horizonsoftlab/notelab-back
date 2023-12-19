package sn.hsl.notelabback.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sn.hsl.notelabback.entities.ClassroomEntity;
import sn.hsl.notelabback.web.dto.request.ClassroomReqDto;
import sn.hsl.notelabback.web.dto.response.ClassroomRspDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ClassroomMapper extends EntityMapper<ClassroomEntity, ClassroomReqDto, ClassroomRspDto> {
}
