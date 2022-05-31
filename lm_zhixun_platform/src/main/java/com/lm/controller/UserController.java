package com.lm.controller;

import com.lm.common.r.UserResultEnum;
import com.lm.config.redis.JwtBlackSetService;
import com.lm.config.redis.key.RedisAndHeaderKey;
import com.lm.entity.bo.UserBo;
import com.lm.entity.pojo.User;
import com.lm.entity.vo.user.UserLogin;
import com.lm.service.user.UserService;
import com.lm.tool.JwtService;
import com.lm.tool.LmAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController implements RedisAndHeaderKey {

    @Resource
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JwtBlackSetService jwtBlackSetService;

    @PostMapping("/login")
    public UserBo Login(@RequestBody UserLogin userLogin, HttpServletRequest httpServletRequest , HttpServletResponse response) {
        log.info("UserController_Login--->用户登录-->账号：{}--密码：{}",userLogin.getUsername(),userLogin.getPassword());
        // 今晚把断言类写好，就是里面可以判断空、是否true、是否为false等等
        // 这个类可以抛出还可以顺便抛出异常，传入枚举，方便
        //------------------------------------------------------------
        // 校验回传过来数据是否为空，抛出异常
        LmAssert.isEmptyEx(userLogin.getUsername(), UserResultEnum.USER_INPUT_NULL_ERROR);
        LmAssert.isEmptyEx(userLogin.getPassword(), UserResultEnum.USER_INPUT_NULL_ERROR);
        // 根据用户名称查询用户信息
        User userDb = userService.login(userLogin.getUsername());
        LmAssert.isNotNull(userDb,UserResultEnum.USER_LOGIN_NO_EXIST);//为空抛出异常，用户不存在
        // 用户输入的密码（后续带上加密）
        String inputPwd = userLogin.getPassword();
        // 判断输入的密码和数据库的密码是否一致
        boolean flagCheckPwd = userDb.getPassword().equals(inputPwd);
        // 如果输入的密码和数据库密码不一致，抛出异常
        LmAssert.isFalseEx(flagCheckPwd,UserResultEnum.USER_LOGIN_NO_EXIST);
        // 根据用户id生成token
        UserBo userBo = new UserBo();
        userBo.setTokenJj(jwtService.createToken(userDb.getUserId()));

        // 往redis传tokenUuid
        String tokenUuid_key = REDIS_LOGIN_UUID_KEY + userDb.getUserId();
        String tokenUuid_Val = UUID.randomUUID().toString();
        // 把键和值传进去
        redisTemplate.opsForValue().set(tokenUuid_key,tokenUuid_Val);

        // 屏蔽一些敏感的信息
        userDb.setPassword(null);
        userBo.setUser(userDb);

        userBo.setTokenUuid(tokenUuid_Val);

        // 返回前台状态
        return userBo;
    }

    /**
     * 登录注销功能，token过期也会被拦截到！
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String Logout(HttpServletRequest request){
        String token_jj = request.getHeader(HEADER_TOKEN_JJ);
        String token_uuid = request.getHeader(HEADER_TOKEN_UUID);
        String token_user_id = request.getHeader(HEADER_TOKEN_USER_ID);

        LmAssert.isNotNull(token_jj,UserResultEnum.USER_TOKEN_NOT_FOUND);
        LmAssert.isNotNull(token_uuid,UserResultEnum.USER_NO_LOGIN);
        LmAssert.isNotNull(token_user_id,UserResultEnum.USER_NO_LOGIN);
        // 删除redis下线的UUID
        // 先拼接Redis的key
        String tokenUuidKey = REDIS_LOGIN_UUID_KEY + token_user_id;
        log.info("拼接后的UuidKey--->{}",tokenUuidKey);
        redisTemplate.delete(tokenUuidKey);
        // 把jwt添加到Redis的黑名单
        jwtBlackSetService.addBlackList(token_jj);
        return "退出成功!";
    }


    @GetMapping("/testt")
//    @IgnoreToken
    public String testt(){
        return "success";
    }

}
