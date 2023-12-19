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
import sn.hsl.notelabback.service.ClassroomService;
import sn.hsl.notelabback.web.dto.request.ClassroomReqDto;
import sn.hsl.notelabback.web.tools.response.ApiSuccess;

import static sn.hsl.notelabback.web.tools.constant.ApiConstant.CLASSROOM_API_PREFIX;
import static sn.hsl.notelabback.web.tools.constant.ApiConstant.SCHOOL_ID_API;

@RestController
@RequestMapping(CLASSROOM_API_PREFIX)
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ClassroomApi {
    final ClassroomService classroomService;

    @Operation(summary = "Create a new classroom to a specific school")
    @PostMapping(SCHOOL_ID_API)
    public ResponseEntity<ApiSuccess> createClassroom(
            @Parameter(name = "id school to create a classroom") @PathVariable Long schoolId,
            @RequestBody @Valid ClassroomReqDto classroomReqDto) {
        var body = classroomService.createClassroom(schoolId, classroomReqDto);
        return new ResponseEntity<>(ApiSuccess.build(HttpStatus.CREATED, body), HttpStatus.CREATED);
    }
}
