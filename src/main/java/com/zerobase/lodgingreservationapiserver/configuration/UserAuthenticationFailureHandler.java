package com.zerobase.lodgingreservationapiserver.configuration;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException, RuntimeException {

        //setUseForward(true);

        //request.setAttribute("errorMessage", msg);

        String msg = "로그인에 실패하였습니다.";

        System.out.println("################");
        System.out.println(msg);
        System.out.println("################");

        msg = URLEncoder.encode(msg, "UTF-8"); // 한글 인코딩 깨지는 문제 방지
        setDefaultFailureUrl("/member/loginResult?msg="+msg);
        super.onAuthenticationFailure(request, response, exception);
    }
}