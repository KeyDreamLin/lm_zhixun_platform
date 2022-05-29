package com.lm.controller;

import com.lm.entity.vo.user.UserLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/jwt")
public class JwtController {
    private String key = "wenhao123ccc";
    @PostMapping("/login")
    public UserLogin loginTwt(@RequestBody(required = true) UserLogin user){
//        String token = JwtService.createToken(123123123L);
//        log.info(token);
        return user;
    }

    @PostMapping("get/")
    public boolean getJwt(@RequestBody String jwtToken){
//        return JwtService.verify(jwtToken);
        return false;
    }
}
