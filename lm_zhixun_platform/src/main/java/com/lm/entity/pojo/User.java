package com.lm.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

//Java中通过继承Serializable接口来获得序列化与反序列化的能力,使用ObjectInputStream和ObjectOutputStream来进行具体的对象序列化读写
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "kss_user")
public class User implements Serializable {
    // 主键
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    // 昵称
    private String nickname;
    // 真实姓名
    private String username;
    // 年龄
    private Integer age;
    // 签名
    private String sign;
    // 性别
    private Integer sex;
    // 浏览数
    private Integer views;
    // 职业
    private String job;
    // 工作年限
    private String jobyear;
    // 地址
    private String address;
    // 国家
    private String country;
    // 省份
    private String province;
    // 城市
    private String city;
    // 电话号码
    private String telephone;
    // 微信
    private String weixincode;
    // 学历
    private String education;
    // 生日
    private String birthday;
    // 头像
    private String avatar;
    // 背景图
    private String bgimg;
    // 1 普通 2 月VIP 3季度会员 4 年VIP 5超级VIP 6报名VIP 0过期
    private Integer vip;
    // vip时间
    private Date vipTime;
    // 学习积分
    private Integer cron;
    // 学习天数
    private Integer studydays;
    // 是否删除
    private Integer isDelete;
    // 是否禁止 1 拉黑 0 未拉黑
    private Integer  forbbiden;
    // qq
    private String qqcode;
    // 序号
    private String code;
    //角色
    private String role;
    //密码
    private String password;
    //微信登录的唯一id
    private String openid;
    //微信登录的唯一id
    private String unionid;
    //粉丝数
    private Integer fans;
    //关注数
    private Integer gznums;
    //个人博客地址
    private String bloglink;
    //gitee地址
    private String giteelink;
    // bibibi个人地址
    private String bilibililink;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)//在新增的时候填充
    private Date createTime;
    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)//在新增的时候填充
    private Date updateTime;

}
