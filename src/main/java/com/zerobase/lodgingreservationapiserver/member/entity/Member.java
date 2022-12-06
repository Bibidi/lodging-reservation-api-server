package com.zerobase.lodgingreservationapiserver.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Member {

    @Id
    private String email;
    private String userNickName;
    private String password;
    private LocalDateTime regDt;

    private MemberGrade memberGrade;
    private MemberStatus memberStatus;

    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;
}
