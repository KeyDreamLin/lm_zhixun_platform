package com.lm.tool.generator;
//package com.lm.mpgenerator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

//mybatis plus 代码生成器
public class mpGenerator {

    public static void main(String[] args) {

        //这里按着给的注解修改参数即可

        //手动配置数据源
        String url="jdbc:mysql://localhost:3306/kss-zixun-db";   //注意修改数据库名
        String sql_name="root";
        String sql_pwd="123456";


        //数据库表的设置
        String TableName = "kss_admin_user";  //设置需要自动代码生成的表名
        String listTablePrefix = "kss_"; //设置 过滤 表的后缀
        // pojo的子包名 过滤前缀然后删除下划线
        String PojoPackageName  = TableName
                .replace(listTablePrefix,"")
                .replace("_","");;
        //基本信息
        String author = "Lm";    //作者
        String parent = "com";   //父包名
        String module = "lm";   //模块包名

        FastAutoGenerator.create(url,sql_name, sql_pwd)
                // 全局配置
                .globalConfig(builder -> {
                    builder.
                            author(author) // 设置作者
                            .fileOverride()   //覆盖之前的文件
//                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(System.getProperty("user.dir") + "/src/main/java");

                        //设置输出路径：项目的 java 目录下【System.getProperty("user.dir")意思是获取到项目所在的绝对路径】
                })
                // 包的配置
                .packageConfig(builder -> {
                    builder.parent("com.lm") // 设置父包名
                            .moduleName("test") // 设置父包模块名
                            .entity("entity.pojo."+PojoPackageName)     //pojo 实体类包名
                            .other("entity.vo")        // 设置vo包名
                            .service("service."+PojoPackageName)        //Service 包名
                            .serviceImpl("service."+PojoPackageName+".impl") // ***ServiceImpl 包名
                            .mapper("mapper")   //Mapper 包名
                            .xml("mapper.xml")  //Mapper XML 包名
                            .controller("controller") //Controller 包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"/src/main/resources/mapper"));    //配置 mapper.xml 路径信息：项目的 resources 目录下
                })
                // 策略配置 配置需要生成的表 然后java的内容
                .strategyConfig(builder -> {
                    builder.addInclude(TableName) // 设置需要生成的表名
                            .addTablePrefix(listTablePrefix) // 设置过滤表前缀
                            //4.1、实体类策略配置
                            .entityBuilder()
                            //.disableSerialVersionUID()  //默认是开启实体类序列化，可以手动disable使它不序列化。由于项目中需要使用序列化就按照默认开启了
                            //开启 Lombok
                            .enableLombok()
                            //添加表字段填充，"create_time"字段自动填充为插入时间，"modify_time"字段自动填充为插入修改时间
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("update_time", FieldFill.INSERT_UPDATE)
                            )
                            .idType(IdType.ASSIGN_ID);    //设置主键雪花
//                            // 数据库中存在逻辑删除 但是不需要在springboot配置
//                            .logicDeleteColumnName("isdelete")   //逻辑删除字段名(数据库)
//                            .logicDeletePropertyName("isdelete");  //逻辑删除属性名(实体)
                })
                .injectionConfig(builder -> {
                    builder.customMap(Collections.singletonMap("VoClassName", "AdminUser"))
                            .customMap(Collections.singletonMap("VoClassName", "AdminUser"))
                            // 文件名 和 模版路径
                            .customFile(Collections.singletonMap("AdminUserVo.java", "/templates/vo.java.ftl"));
                })
                // 配置自定义模版
                .templateConfig(builder->{
                    builder.disable(TemplateType.ENTITY)
                            .entity("/templates/pojo.java");
                })
                // 模版引擎
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板

                .execute();
    }
}