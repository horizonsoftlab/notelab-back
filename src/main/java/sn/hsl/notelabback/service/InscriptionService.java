package sn.hsl.notelabback.service;

import sn.hsl.notelabback.web.dto.request.InscriptionReqDto;
import sn.hsl.notelabback.web.dto.response.InscriptionRspDto;

public interface InscriptionService {
    InscriptionRspDto registerStudent(Long classroomId, InscriptionReqDto inscriptionReqDto);
}
