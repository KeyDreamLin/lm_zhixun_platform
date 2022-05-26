package com.lm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lm.common.Constants;
import com.lm.common.ex.handler.UserExceptionHandler;
import com.lm.common.r.UserResultEnum;
import com.lm.pojo.User;
import com.lm.pojo.UserToken;
import com.lm.service.user.UserService;
import com.lm.service.userpermission.UserPermissionService;
import com.lm.service.usertoken.UserTokenService;
import com.lm.tool.Base64Tool;
import com.lm.tool.CookieTool;
import com.lm.vo.user.UserLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    //用户秘钥
    private UserTokenService userTokenService;

    @Autowired
    private UserPermissionService userPermissionService;

    @PostMapping("/login")
    public String Login(@RequestBody UserLogin userLogin, HttpServletRequest httpServletRequest , HttpServletResponse response) {
        log.info("UserController_Login--->用户登录-->账号：{}--密码：{}",userLogin.getUsername(),userLogin.getPassword());

        //账号为空
        if (StringUtils.isEmpty(userLogin.getUsername())){
            throw new UserExceptionHandler(UserResultEnum.USER_INPUT_NULL_ERROR);
        }

        //用户表条件构造器
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加条件==用户账号
        userLambdaQueryWrapper.eq(StringUtils.isNotEmpty(userLogin.getUsername()),User::getUsername,userLogin.getUsername());
        //添加条件==用户密码
        userLambdaQueryWrapper.eq(StringUtils.isNotEmpty(userLogin.getPassword()),User::getPassword,userLogin.getPassword());

        User userServiceOne = userService.getOne(userLambdaQueryWrapper);
//        System.out.println(1/0);
//        System.out.println(0/1);
        if (userServiceOne == null){
            throw new UserExceptionHandler(UserResultEnum.USER_LOGIN_NO_EXIST);
        }

        if (userServiceOne.getState() == 0){
            throw new UserExceptionHandler(UserResultEnum.USER_LOGIN_ACCOUNT_STATE_STOP_USE);
        }

        //登录成功存入session
        httpServletRequest.getSession().setAttribute(Constants.USERID_SESSION,userServiceOne.getUserId());//传入id

        //登录成功，并将cookie传入SQL和返回到前端
        Long new_timestamp = System.currentTimeMillis();//获取当前时间戳
        String token =  Base64Tool.Encode(userServiceOne.getUserId()+"&"+ new_timestamp);

        //token实体类
        UserToken userToken = new UserToken();
        userToken.setUserIdToken(token);
        userToken.setNewTimestamp(new_timestamp);

        // 创建一个 cookie对象
        Cookie cookie = new Cookie(Constants.USER_TOKEN,token);
        cookie.setPath("/");
        //将cookie对象加入response响应
        response.addCookie(cookie);
        //将token存入数据表
        userTokenService.save(userToken);

        log.info("UserController_Login--->用户登录-->cookie--{}",token);
        return"登录成功！";
    }

    /**
     * 登录注销功能，token过期也会被拦截到！
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String Logout(HttpServletRequest request,HttpServletResponse response){

        //删除session
        request.getSession().removeAttribute(Constants.USERID_SESSION);
        //查询cookie对应的userToken
        Cookie cookie = CookieTool.GetCookieByName(request.getCookies(), Constants.USER_TOKEN);
        if (cookie != null) {
            //删除前端的cookie
            CookieTool.DeleteCookie(response,Constants.USER_TOKEN);
            //顺便删除数据库的token
            LambdaQueryWrapper<UserToken> userTokenLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userTokenLambdaQueryWrapper.eq(UserToken::getUserIdToken,cookie.getValue());
            userTokenService.remove(userTokenLambdaQueryWrapper);
            log.info("删除成功！");
        }

        return "退出成功!";
    }

    /**
     * 根据用户id查询用户信息
     * @param id    user_id 用户id
     * @return R< User > 用户对象
     */
    @GetMapping("/{id}")
    public User queryUserById(@PathVariable Long id){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserId,id);
        User user = userService.getOne(userLambdaQueryWrapper);
        log.info("User_queryUserById:{}-->{}",id,user);
        return user;
    }

    /**
     * 获取用户分页信息 restful风格
     * @param pages
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{pages}/{pageSize}")
    public Page page(@PathVariable Integer pages,@PathVariable Integer pageSize ){
        log.info("第{}页，一共{}条",pages,pageSize);
        Page<User> userPage = new Page<>(pages,pageSize);
        userService.page(userPage);
        return userPage;
    }

    /**
     * 用户注册 post
     * @param user
     * @return
     */
    @PostMapping
    public String addUser(@RequestBody User user){

        log.info("UserController_addUser--->用户注册-->{}",user);
        userService.save(user);
        return "添加用户成功！";
    }


    /**
     * 删除用户信息 根据user_id
     * @param id user_id
     * @return 给前端回传信息
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        log.info("");
        return "删除成功！";
    }

    @PutMapping("")
    public String update(@RequestBody User user){
        log.info("修改用户信息：{}",user);
        userService.updateById(user);
        return null;
    }
    @GetMapping("test")
    public String tests(){
        return "测试";
    }
}
