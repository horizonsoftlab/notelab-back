package sn.hsl.notelabback.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sn.hsl.notelabback.entities.UserEntity;
import sn.hsl.notelabback.web.dto.request.OwnerReqDto;
import sn.hsl.notelabback.web.dto.request.StudentReqDto;
import sn.hsl.notelabback.web.dto.response.UserRspDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(OwnerReqDto ownerReqDto);
    UserEntity toEntity(StudentReqDto studentReqDto);
    UserRspDto toDto(UserEntity entity);
    List<UserEntity> toOwnerEntities(List<OwnerReqDto> dtos);
    List<UserEntity> toStudentEntities(List<StudentReqDto> dtos);
    List<UserRspDto> toDtos(List<UserEntity> entities);
}
