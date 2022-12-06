package com.zerobase.lodgingreservationapiserver.member.service.impl;

import com.zerobase.lodgingreservationapiserver.member.dto.MemberDto;
import com.zerobase.lodgingreservationapiserver.member.entity.Member;
import com.zerobase.lodgingreservationapiserver.member.entity.MemberGrade;
import com.zerobase.lodgingreservationapiserver.member.entity.MemberStatus;
import com.zerobase.lodgingreservationapiserver.member.exception.DuplicateMemberException;
import com.zerobase.lodgingreservationapiserver.member.model.MemberInput;
import com.zerobase.lodgingreservationapiserver.member.repository.MemberRepository;
import com.zerobase.lodgingreservationapiserver.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public boolean register(MemberInput parameter){

        // 이미 가입되어 있는지 확인
        Optional<Member> optionalMember = memberRepository.findById(parameter.getEmail());

        // 현재 해당하는 email에 해당하는 데이터가 존재
        if(optionalMember.isPresent()){
            throw new DuplicateMemberException();
            //throw new RuntimeException("해당 이메일을 사용중인 회원이 있습니다.");
        }


        // 스프링 시큐리티
        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());

        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .email(parameter.getEmail())
                .userNickName(parameter.getUserNickName())
                .password(encPassword)
                .regDt(LocalDateTime.now())
                .emailAuthYn(false)
                .memberGrade(MemberGrade.SILVER)
                .memberStatus(MemberStatus.REQ)
                .emailAuthKey(uuid)
                .build();

        memberRepository.save(member);

        // TODO: email 인증 추가해야함


        return true;
    }

    @Override
    public MemberDto getInfo(String userId) {

        Optional<Member> optionalMember = memberRepository.findById(userId);

        Member member = optionalMember.get();

        return MemberDto.of(member);
    }


    /**
     * SpringSecurity 사용을 위한 메서드
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 현재 시스템에서 username 은 email 의미
        Optional<Member> optionalMember = memberRepository.findById(username);

        if(!optionalMember.isPresent()){
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(member.getEmail(), member.getPassword(), grantedAuthorities);
    }
}
