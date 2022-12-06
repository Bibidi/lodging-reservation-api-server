package com.zerobase.lodgingreservationapiserver.member.service.impl;

import com.zerobase.lodgingreservationapiserver.member.entity.Member;
import com.zerobase.lodgingreservationapiserver.member.exception.DuplicateMemberException;
import com.zerobase.lodgingreservationapiserver.member.model.MemberInput;
import com.zerobase.lodgingreservationapiserver.member.repository.MemberRepository;
import com.zerobase.lodgingreservationapiserver.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;


    @Test
    // 단순 회원가입 테스트
    void register() {
        // given
        MemberInput memberInput = new MemberInput();

        memberInput.setEmail("forTestMail@naver.com");
        memberInput.setPassword("forTestPassword");

        // when
        memberService.register(memberInput);

        // then
        Optional<Member> optionalMember = memberRepository.findById(memberInput.getEmail());
        assertEquals(optionalMember.get().getEmail(), memberInput.getEmail());
    }

    @Test
    // 중복 회원가입 테스트
    void register_duplication_check(){

        boolean passRegister = true;

        // given
        MemberInput memberInput = new MemberInput();
        memberInput.setEmail("forTestMail@naver.com");
        memberInput.setPassword("forTestPassword");

        // when
        memberService.register(memberInput);

        try{
            memberService.register(memberInput);
        }
        catch(DuplicateMemberException e){
            passRegister = false;
        }

        assertEquals(passRegister, false);
    }
}