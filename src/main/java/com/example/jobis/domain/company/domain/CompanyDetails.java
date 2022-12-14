package com.example.jobis.domain.company.domain;

import com.example.jobis.domain.company.controller.dto.request.UpdateCompanyDetailsRequest;
import com.example.jobis.domain.company.domain.type.Address;
import com.example.jobis.domain.company.domain.type.CompanyInfo;
import com.example.jobis.domain.company.domain.type.Contact;
import com.example.jobis.domain.company.domain.type.Manager;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CompanyDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_details_id")
    private Long id;

    @Column(length = 4000, nullable = false)
    private String companyIntroduce;

    @Embedded
    private Address address;

    @Embedded
    private Manager manager;

    @Embedded
    private Contact contact;

    @Embedded
    private CompanyInfo companyInfo;

    @Builder
    public CompanyDetails(String companyIntroduce, String zipCode1, String address1,
                          String zipCode2, String address2, String manager1, String phoneNumber1, String manager2,
                          String phoneNumber2, String fax, String email, String representativeName, String foundedAt,
                          Long workerNumber, Long take) {
        this.companyIntroduce = companyIntroduce;
        this.address = new Address(zipCode1, address1, zipCode2, address2);
        this.manager = new Manager(manager1, phoneNumber1, manager2, phoneNumber2);
        this.contact = new Contact(fax, email);
        this.companyInfo = new CompanyInfo(representativeName, foundedAt, workerNumber, take);
    }

    public void update(UpdateCompanyDetailsRequest request) {
        this.companyIntroduce = request.getCompanyIntroduce();
        this.address = new Address(request.getZipCode1(), request.getAddress1(), request.getZipCode2(), request.getAddress2());
        this.manager = new Manager(request.getManager1(), request.getPhoneNumber1(), request.getManager2(), request.getPhoneNumber2());
        this.contact = new Contact(request.getFax(), request.getEmail());
        this.companyInfo = new CompanyInfo(request.getRepresentativeName(), this.companyInfo.getFoundedAt(), request.getWorkerNumber(), request.getTake());
    }
}
