package com.zerobase.lodgingreservationapiserver.member.model;

import lombok.Data;

@Data
// 회원가입시 넘어오는 InputParam들
public class MemberInput {
    private String userNickName;
    private String email;
    private String password;
}
