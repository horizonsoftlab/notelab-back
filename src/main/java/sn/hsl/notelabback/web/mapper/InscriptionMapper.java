package sn.hsl.notelabback.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sn.hsl.notelabback.entities.InscriptionEntity;
import sn.hsl.notelabback.web.dto.request.InscriptionReqDto;
import sn.hsl.notelabback.web.dto.response.InscriptionRspDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InscriptionMapper extends EntityMapper<InscriptionEntity, InscriptionReqDto, InscriptionRspDto> {
}
