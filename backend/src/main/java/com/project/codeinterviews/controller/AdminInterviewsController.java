package com.project.codeinterviews.controller;


import com.project.codeinterviews.DTO.InterviewDetailsDTO;
import com.project.codeinterviews.DTO.NewInterviewDTO;
import com.project.codeinterviews.DTO.NewInterviewResponseDTO;
import com.project.codeinterviews.DTO.InterviewSummaryDTO;
import com.project.codeinterviews.entity.Domanda;
import com.project.codeinterviews.service.InterviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/interviews")
public class AdminInterviewsController {
    @Autowired
    private InterviewService interviewService;

    @PostMapping
    public ResponseEntity<NewInterviewResponseDTO> createInterview(@RequestBody NewInterviewDTO interviewDTO) {
        NewInterviewResponseDTO response = interviewService.createInterview(interviewDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a question by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the question",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Domanda.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Question not found",
                    content = @Content)})

    @GetMapping
    public ResponseEntity<Page<InterviewSummaryDTO>> getInterviews(Pageable pageable) {
        return ResponseEntity.ok(interviewService.getInterviews(pageable));
    }

    @GetMapping("/{interviewId}")
    public ResponseEntity<InterviewDetailsDTO> getInterviewById(@PathVariable Long interviewId) {
        return interviewService.getInterviewById(interviewId);
    }

    @GetMapping("/{interviewId}/questions/{questionId}")
    public ResponseEntity<?> getInterviewAnswer(@Parameter(description = "id of question to be searched")
                                                @PathVariable Long interviewId, @PathVariable Long questionId) {
        return interviewService.getInterviewAnswer(interviewId, questionId);
    }
}