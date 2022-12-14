package com.example.jobis.domain.company.domain;

import com.example.jobis.domain.company.domain.enums.CompanyType;
import com.example.jobis.domain.recruit.domain.Recruit;
import com.example.jobis.domain.user.domain.User;
import com.example.jobis.domain.user.domain.enums.Authority;
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
public class Company extends User {

    @Column(length = 40, nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String companyProfileUrl;

    @Column(length = 10, nullable = false, unique = true)
    private String businessNumber;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @OneToMany(mappedBy = "company")
    private List<Recruit> recruitList = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Attachment> attachments = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_details_id", nullable = false)
    private CompanyDetails companyDetails;

    @Builder
    public Company(String companyName, String businessNumber, String password,
                   String accountId, String companyProfileUrl,
                   CompanyDetails companyDetails) {
        super(accountId, password, Authority.COMPANY);
        this.companyName = companyName;
        this.businessNumber = businessNumber;
        this.companyType = CompanyType.DEFAULT;
        this.companyProfileUrl = companyProfileUrl;
        this.companyDetails = companyDetails;
    }
}
