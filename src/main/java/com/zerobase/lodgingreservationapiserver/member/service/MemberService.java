package com.zerobase.lodgingreservationapiserver.member.service;

import com.zerobase.lodgingreservationapiserver.member.dto.MemberDto;
import com.zerobase.lodgingreservationapiserver.member.model.MemberInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {


    /**
     * 회원가입 진행
     */
    boolean register(MemberInput parameter);

    /**
     * 회원 상세정보 조회
     */
    MemberDto getInfo(String userId);
}
