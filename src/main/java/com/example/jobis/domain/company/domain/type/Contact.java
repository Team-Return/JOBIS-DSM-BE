package com.example.jobis.domain.company.domain.type;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Contact {

    @Column(length = 11)
    private String fax;

    @Column(length = 60, nullable = false)
    private String email;

    @Builder
    public Contact(String fax, String email) {
        this.fax = fax;
        this.email = email;
    }
}
