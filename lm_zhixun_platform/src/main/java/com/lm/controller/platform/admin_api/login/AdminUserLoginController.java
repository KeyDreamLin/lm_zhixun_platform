package com.lm.controller.platform.admin_api.login;

import com.lm.common.r.UserResultEnum;
import com.lm.config.redis.JwtBlackSetService;
import com.lm.config.redis.key.RedisAndHeaderKey;
import com.lm.controller.platform.admin_api.BaseController;
import com.lm.entity.bo.adminuser.AdminUserTokenBo;
import com.lm.entity.pojo.adminuser.AdminUser;
import com.lm.entity.vo.adminuser.AdminUserLoginVo;
import com.lm.service.adminuser.AdminUserServiceImpl;
import com.lm.tool.JwtService;
import com.lm.tool.LmAssert;
import com.lm.tool.pwd.DesUtils;
import com.lm.tool.pwd.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@RestController
public class AdminUserLoginController extends BaseController implements RedisAndHeaderKey {

    @Resource
    private AdminUserServiceImpl adminUserService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private StringRedisTemplate redisTemplate;  // 操作Redis

    @Autowired
    private JwtBlackSetService jwtBlackSetService;

    @PostMapping("/login/toLogin")
    public AdminUserTokenBo Login(@RequestBody AdminUserLoginVo adminUserLoginVo) {
        log.info("UserController_Login--->用户登录-->账号：{}--密码：{}  验证码：{} 验证码UUID：{}"
                , adminUserLoginVo.getUsername()
                , adminUserLoginVo.getPassword()
                , adminUserLoginVo.getCode()
                , adminUserLoginVo.getCodeuuid()
        );
        // 断言类写，就是里面可以判断空、是否true、是否为false等等
        // 这个类可以抛出还可以顺便抛出异常，传入枚举，方便
        //------------------------------------------------------------
        // 校验回传过来数据是否为空，抛出异常
        LmAssert.isEmptyEx(adminUserLoginVo.getUsername(), UserResultEnum.USER_INPUT_NULL_ERROR);
        LmAssert.isEmptyEx(adminUserLoginVo.getPassword(), UserResultEnum.USER_INPUT_NULL_ERROR);
        LmAssert.isEmptyEx(adminUserLoginVo.getCode(), UserResultEnum.USER_INPUT_NULL_ERROR);
        LmAssert.isEmptyEx(adminUserLoginVo.getCodeuuid(), UserResultEnum.USER_INPUT_NULL_ERROR);

        // 根据验证码UUID codeuuid查询Redis对应的验证码
        // 取出对应的验证码
        String db_codePwd = redisTemplate.opsForValue().get(adminUserLoginVo.getCodeuuid());
        // 前端传过来的验证码和Redis的对比
        boolean flagCheckCodePwd = adminUserLoginVo.getCode().equals(db_codePwd);
        // 对比不上 抛出异常 验证码错误
        LmAssert.isFalseEx(flagCheckCodePwd,UserResultEnum.USER_INPUT_CODE_ERROR);

        // 解密传过来的用户信息
        adminUserLoginVo.setUsername(DesUtils.decrypt(adminUserLoginVo.getUsername()));

        // 根据用户名称查询用户信息
        AdminUser adminUserDb = adminUserService.login(adminUserLoginVo.getUsername());
        LmAssert.isNotNull(adminUserDb,UserResultEnum.USER_LOGIN_NO_EXIST);//为空抛出异常，用户不存在

        // 用户输入的密码 前端传输Des密文  然后后台解密Des后再转换为双重md5加前后掩码
        String inputPwd = DesUtils.decrypt(adminUserLoginVo.getPassword());
        // md5加密 再传入解密后的密码加密
        String inputMd5= MD5Util.strToMd5s(inputPwd);

        // 判断输入的密码和数据库的密码是否一致 数据库存放的是双重md5加前后掩码的
        boolean flagCheckPwd = adminUserDb.getPassword().equals(inputMd5);
        // 如果输入的密码和数据库密码不一致，抛出异常
        LmAssert.isFalseEx(flagCheckPwd,UserResultEnum.USER_LOGIN_NO_EXIST);
        // 根据用户id生成token
        AdminUserTokenBo adminUserTokenBo = new AdminUserTokenBo();
        adminUserTokenBo.setTokenJj(jwtService.createToken(adminUserDb.getId()));

        // 往redis传tokenUuid
        String tokenUuid_key = REDIS_LOGIN_UUID_KEY + adminUserDb.getId();
        String tokenUuid_Val = UUID.randomUUID().toString();
        // 把键和值传进去
        redisTemplate.opsForValue().set(tokenUuid_key,tokenUuid_Val);

        // 屏蔽一些敏感的信息
        adminUserDb.setPassword(null);
        adminUserTokenBo.setUser(adminUserDb);
        // 设置jwt_token
        adminUserTokenBo.setTokenUuid(tokenUuid_Val);

        // 返回前台状态
        return adminUserTokenBo;
    }

    /**
     * 登录注销功能，token过期也会被拦截到！
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/login/logout")
    public String Logout(HttpServletRequest request){
        String token_jj = request.getHeader(HEADER_TOKEN_JJ);
        String token_uuid = request.getHeader(HEADER_TOKEN_UUID);
        String token_user_id = request.getHeader(HEADER_TOKEN_USER_ID);
//        log.info("---{}---  {} --- {}",token_jj,token_uuid,token_user_id);
        LmAssert.isEmptyEx(token_jj,UserResultEnum.USER_TOKEN_NOT_FOUND);
        LmAssert.isEmptyEx(token_uuid,UserResultEnum.USER_NO_LOGIN);
        LmAssert.isEmptyEx(token_user_id,UserResultEnum.USER_NO_LOGIN);
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
