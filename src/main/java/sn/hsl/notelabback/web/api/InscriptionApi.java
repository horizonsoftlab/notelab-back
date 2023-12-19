package sn.hsl.notelabback.web.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.hsl.notelabback.service.InscriptionService;
import sn.hsl.notelabback.web.dto.request.InscriptionReqDto;
import sn.hsl.notelabback.web.tools.response.ApiSuccess;

import static sn.hsl.notelabback.web.tools.constant.ApiConstant.CLASSROOM_ID_API;
import static sn.hsl.notelabback.web.tools.constant.ApiConstant.INSCRIPTION_API_PREFIX;

@RestController
@RequestMapping(INSCRIPTION_API_PREFIX)
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class InscriptionApi {
    final InscriptionService inscriptionService;

    @Operation(summary = "Register a student")
    @PostMapping(CLASSROOM_ID_API)
    public ResponseEntity<ApiSuccess> createClassroom(
            @Parameter(name = "id classroom") @PathVariable Long classroomId,
            @RequestBody @Valid InscriptionReqDto inscriptionReqDto) {
        var body = inscriptionService.registerStudent(classroomId, inscriptionReqDto);
        return new ResponseEntity<>(ApiSuccess.build(HttpStatus.CREATED, body), HttpStatus.CREATED);
    }
}
