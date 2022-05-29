package com.lm.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
//Java中通过继承Serializable接口来获得序列化与反序列化的能力,使用ObjectInputStream和ObjectOutputStream来进行具体的对象序列化读写
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "sys_user")
public class User implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class) //get传过来不会丢精度，post等就需要处理一下，或者js转字符串啥的
    private Long userId;

    private String username;
    private String password;

    private String imageName;

    //用户状态 0禁用，1启用
    private Integer state;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime ;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime newTime;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long newId;

}
