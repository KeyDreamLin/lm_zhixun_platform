package com.lm.config.mybatisconfig;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MyBatis公共字段处理
 */
@Component
public class MyBatisCommonFieldHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime" , new Date(),metaObject);
        this.setFieldValByName("createTime" ,new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime" ,new Date(),metaObject);
    }
}
