package sn.hsl.notelabback.web.mapper;

import java.util.List;

public interface EntityMapper<E, REQ, RSP> {
    E toEntity(REQ req);
    RSP toDto(E entity);
    List<E> toEntities(List<REQ> dtos);
    List<RSP> toDtos(List<E> entities);
}
