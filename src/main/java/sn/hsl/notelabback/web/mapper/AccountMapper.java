package sn.hsl.notelabback.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sn.hsl.notelabback.entities.AccountEntity;
import sn.hsl.notelabback.web.dto.request.AccountReqDto;
import sn.hsl.notelabback.web.dto.response.AccountRspDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AccountMapper extends EntityMapper<AccountEntity, AccountReqDto, AccountRspDto> {
}
