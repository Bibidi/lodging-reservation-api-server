package com.zerobase.lodgingreservationapiserver.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


@Component
@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String msg = "로그인에 성공하였습니다.";
        System.out.println("###############");
        System.out.println(msg);
        System.out.println("###############");

        msg = URLEncoder.encode(msg, "UTF-8"); // 한글 인코딩 깨지는 문제 방지
        setDefaultTargetUrl("/member/loginResult?msg="+msg);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
