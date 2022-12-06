package com.zerobase.lodgingreservationapiserver.member.controller;

import com.zerobase.lodgingreservationapiserver.member.dto.MemberDto;
import com.zerobase.lodgingreservationapiserver.member.model.MemberInput;
import com.zerobase.lodgingreservationapiserver.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @ApiOperation("회원가입")
    @PostMapping("/member/register")
    public void registerSubmit(MemberInput parameter){
        boolean result = memberService.register(parameter);
    }

    /*
    @ApiOperation("로그인 처리")
    @PostMapping("/member/login")
    public String loginWithoutForm(@RequestParam String username, @RequestParam String password){
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/";
    }*/

    @ApiOperation("로그인 결과(성공/실패)를 반환")
    @GetMapping("/member/loginResult")
    public String getLoginResult(@RequestParam String msg){
        return msg;
    }



    @ApiOperation("로그인된 유저의 상세정보 조회")
    @GetMapping("/member/info")
    public MemberDto memberInfo(Principal principal){

        System.out.println(principal.getName());

        String userId = principal.getName();
        MemberDto memberInfo = memberService.getInfo(userId);

        return memberInfo;
    }
}
