package com.lm;

import com.lm.pojo.UserToken;
import com.lm.service.usertoken.UserTokenService;
import com.lm.tool.Base64Tool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class LoginDemoApplicationTests {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private UserTokenService userTokenService;
    @Test
    void contextLoads() {
        //登录成功，并将cookie传入SQL和返回到前端
        Long new_timestamp = System.currentTimeMillis();//获取当前时间戳
        String token =  Base64Tool.Encode("123852753"+"&"+ new_timestamp);

        //token实体类
        UserToken userToken = new UserToken();
        userToken.setUserIdToken(token);
        userToken.setNewTimestamp(new_timestamp);
        //存入数据库
        userTokenService.save(userToken);
    }
    @Test
    void testC(){
        String str = "123";
        System.out.println(str.hashCode());
        str = "23";
        System.out.println(str.hashCode());
        int a = 1;
    }

}
