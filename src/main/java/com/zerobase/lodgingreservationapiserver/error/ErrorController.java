package com.zerobase.lodgingreservationapiserver.error;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @RequestMapping("/error/denied")
    public String errorDenied(){
        System.out.println("################");
        System.out.println("접근 권한이 없습니다.");
        System.out.println("################");

        return "접근 권한이 없습니다.";
    }
}
