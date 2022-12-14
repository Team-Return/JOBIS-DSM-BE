package com.example.jobis.domain.recruit.domain;

import com.example.jobis.domain.code.domain.RecruitAreaCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecruitArea {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_area_id")
    private Long id;

    private Integer hiredCount;

    private String majorTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id", nullable = false)
    private Recruit recruit;

    @OneToMany(mappedBy = "recruitAreaId")
    private List<RecruitAreaCode> codeList = new ArrayList<>();

    @Builder
    public RecruitArea(Integer hiredCount, String majorTask, Recruit recruit) {
        this.hiredCount = hiredCount;
        this.majorTask = majorTask;
        this.recruit = recruit;
    }

}
