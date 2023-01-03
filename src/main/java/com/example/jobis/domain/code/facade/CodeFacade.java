package com.example.jobis.domain.code.facade;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.domain.repository.CodeRepository;
import com.example.jobis.domain.code.exception.InvalidCodeException;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CodeFacade {
    private final CodeRepository codeRepository;

    public List<Code> findCodesWithValidation(List<Long> codes, CodeType codeType) {
        List<Code> resCodes = new ArrayList<>();
        codes.forEach(
                c -> resCodes.add(codeRepository.findByCodeTypeAndCode(codeType, c)
                        .orElseThrow(()->InvalidCodeException.EXCEPTION))
        );
        return resCodes;
    }

    public List<String> getKeywordByRecruitArea(RecruitArea recruitArea, CodeType codeType) {
        return recruitArea.getCodeList().stream()
                .map(RecruitAreaCode::getCodeId)
                .toList().stream()
                .filter(code -> code.getCodeType().equals(CodeType.JOB))
                .map(Code::getKeyword)
                .toList();
    }
}
