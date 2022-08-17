package com.lm.entity.vo.banner;

import lombok.Data;

import java.util.Date;

@Data
public class BannerUserVo {
    private Long bannerId;
    private String title;
    private Date createTime;
    private Long userId;
    private Date ctime;
    private String username;
}