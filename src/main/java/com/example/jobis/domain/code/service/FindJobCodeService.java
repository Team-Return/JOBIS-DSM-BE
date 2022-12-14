package com.example.jobis.domain.code.service;

import com.example.jobis.domain.code.controller.dto.response.JobCodeResponse;
import com.example.jobis.domain.code.domain.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindJobCodeService {
    private final CodeRepository codeRepository;

    @Transactional(readOnly = true)
    public List<JobCodeResponse> execute() {
        return codeRepository.findAllJobCode().stream()
                .map(c-> JobCodeResponse.builder()
                        .code(c.getCode())
                        .keyword(c.getKeyword())
                        .jobType(c.getJobType())
                        .build())
                .collect(Collectors.toList());
    }
}
