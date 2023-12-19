package sn.hsl.notelabback.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sn.hsl.notelabback.entities.SchoolEntity;
import sn.hsl.notelabback.web.dto.request.SchoolReqDto;
import sn.hsl.notelabback.web.dto.response.SchoolRspDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SchoolMapper extends EntityMapper<SchoolEntity, SchoolReqDto, SchoolRspDto> {

    @Override
    SchoolEntity toEntity(SchoolReqDto schoolReqDto);
}
