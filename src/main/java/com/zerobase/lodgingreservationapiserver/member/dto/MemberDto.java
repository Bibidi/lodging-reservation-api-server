package com.zerobase.lodgingreservationapiserver.member.dto;


import com.zerobase.lodgingreservationapiserver.member.entity.Member;
import com.zerobase.lodgingreservationapiserver.member.entity.MemberGrade;
import com.zerobase.lodgingreservationapiserver.member.entity.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {

    String email;
    String userNickName;
    String password;
    LocalDateTime regDt;

    MemberGrade memberGrade;
    MemberStatus memberStatus;

    boolean emailAuthYn;
    LocalDateTime emailAuthDt;

    public static MemberDto of(Member member){
        return MemberDto.builder()
                .email(member.getEmail())
                .userNickName(member.getUserNickName())
                .regDt(member.getRegDt())
                .memberGrade(member.getMemberGrade())
                .memberStatus(member.getMemberStatus())
                .emailAuthYn(member.isEmailAuthYn())
                .emailAuthDt(member.getEmailAuthDt())
                .build();
    }

}
