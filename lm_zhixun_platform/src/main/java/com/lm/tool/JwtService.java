package com.lm.tool;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lm.common.ex.lthrow.UserExceptionThrow;
import com.lm.common.r.UserResultEnum;
import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 为什么要Springmvc接管呢？
 * 如果后续需要用统一配置文件或者统一配置中心去修改参数，就需要将类交给springmvc接管
 * 否则spring的注解无效
 */
@Component
public class JwtService {
    //jwt私钥
    private static final String KEY = "wenhaoccc";
    //指定作者
    private static final String AUTHOR = "lmm";
    //指定token的key
    private static final String LM_USER_ID = "lm_user_id";
    //1秒
    private static final  Long ONE_SECOND = 1000L;
    //1分钟
    private static final Long ONE_MINUTE = ONE_SECOND * 60;
    //token 30分钟过期
    private static final Long TOKEN_EXPIRE_TIME = ONE_MINUTE * 30;

    /**
     * 生成秘钥
     * @param userId
     * @return
     */
    public String createToken(Long userId){
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        String token = JWT.create()
                .withIssuer(AUTHOR)
                .withClaim(LM_USER_ID,userId)
                .withExpiresAt(new Date(System.currentTimeMillis()+TOKEN_EXPIRE_TIME))
                .sign(algorithm);
        return token;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public boolean verify(String token){
        //等于空的时候
        if (!StringUtils.hasText(token)){
            return false;
        }
        try{
            Algorithm algorithm = Algorithm.HMAC256(KEY); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(AUTHOR)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            System.out.println("token失效了");
            return false;
        }
    }

    public Long getTokenUserId(String token){
        //等于空的时候
        if (!StringUtils.hasText(token)){
            return null;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY); //use more secure key
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(AUTHOR)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(LM_USER_ID).asLong();
        }catch (Exception exception){
            throw new UserExceptionThrow(UserResultEnum.USER_TOKEN_ERROR);
        }
    }
    @Test
    public void testSS(){

        System.out.println( StringUtils.hasText(null));
        System.out.println( StringUtils.hasText(""));
        System.out.println( StringUtils.hasText(" "));
        System.out.println( StringUtils.hasText("  "));
        System.out.println( StringUtils.hasText("  34 "));
        //false
        //false
        //false
        //false
        //true
    }
}
