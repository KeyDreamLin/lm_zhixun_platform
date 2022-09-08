package com.lm.generator;
//package com.lm.mpgenerator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.lm.generator.utils.CamelCaseUtil;
import com.sun.istack.internal.NotNull;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


//mybatis plus 代码生成器
public class mpGenerator_ {

    //这里按着给的注解修改参数即可

    //手动配置数据源
    static String url="jdbc:mysql://localhost:3306/kss-zixun-db";   //注意修改数据库名
    static String username="root";
    static String password="123456";

    //数据库表的设置
    static String TableName = "kss_admin_user";  //设置需要自动代码生成的表名
    static String listTablePrefix = "kss_"; //设置 过滤 表的后缀
    static String Tabletitle = "后台用户管理"; // 注释里面的标题
    // 包路径
    // 父路径 entity.pojo  entity.vo
    static String EntityPackagePath = "entity";
    static String EntityPojoPackagePath = "pojo";
    static String EntityVoPackagePath = "vo";
    static String EntityBoPackagePath = "bo";
    // 子路径 entity.pojo.xxx  entity.vo.xxx entity.bo.xxx
    static String EntityPackageClassPath  = TableName
            .replace(listTablePrefix,"")
            .replace("_","");
    // class路径 entity.pojo.xxx.Xxx.java  entity.vo.xxx.XxxVo.java
    // 将class类名转换为大驼峰
    static String EntityClassName =CamelCaseUtil.toCapitalizeCamelCase(
            TableName.replace(listTablePrefix,""));

    //基本信息
    static String author = "Lm";    //作者
    static String parent = "com";   //父包名
    static String module = "lm";   //模块包名
    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder(url, username, password)
            //数据库查询
            .dbQuery(new MySqlQuery())
            //数据库类型转换器
            .typeConvert(new MySqlTypeConvert())
            //数据库关键字处理器
            .keyWordsHandler(new MySqlKeyWordsHandler());
    // 构建一个快速生成器
    private static FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(DATA_SOURCE_CONFIG);

    public static void main(String[] args) {
        genCode();
    }

    public static void genCode() {
        // 全局配置
        globalConfig();
        // 包配置
        packageConfig();
        // 基本模板配置
        templateConfig();
        // 策略配置
        strategyConfig();
        // 自定义模板配置  这里设置vo和bo
        injectionConfig();
        // 配置模板引擎
        templateEngine();
        fastAutoGenerator.execute();
    }

    /**
     * 全局配置
     */
    public static void globalConfig() {
        fastAutoGenerator.globalConfig(builder -> {
            builder.
                    // 设置作者
                    author(author)
                    //覆盖之前的文件
                    .fileOverride()
                    //禁止生成代码后自动弹出输出目录
                    .disableOpenDir()
                    //设置输出路径：项目的 java 目录下【System.getProperty("user.dir")意思是获取到项目所在的绝对路径】
                    .outputDir(System.getProperty("user.dir") + "/src/main/java");
        });
    }

    /**
     * 包配置
     */
    public static void packageConfig() {
        fastAutoGenerator
                .packageConfig(builder -> {
                    builder
                            // 设置父包名
                            .parent("com.lm")
                            // 设置父包模块名
                            .moduleName("test")
                            //pojo 实体类包名
                            .entity(
                                    EntityPackagePath
                                    +"."
                                    +EntityPojoPackagePath
                                    +"."
                                    +EntityPackageClassPath)
                            //Service 包名
                            .service("service."+EntityPackageClassPath)
                            // ***ServiceImpl 包名
                            .serviceImpl("service."+EntityPackageClassPath+".impl")
                            //Mapper 包名
                            .mapper("mapper")
                            //Mapper XML 包名
                            .xml("mapper.xml")
                            //Controller 包名
                            .controller("controller."+EntityPackageClassPath)
                            // 重写输出自定义文件方法，自定义文件输出路径 所以这里不需要设置
                            .other("")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"/src/main/resources/mapper"));    //配置 mapper.xml 路径信息：项目的 resources 目录下
                });
    }

    /**
     * 基本模板配置
     */
    public static void templateConfig() {
        fastAutoGenerator // 基本模板配置
                .templateConfig(builder->{
                    builder.disable(TemplateType.ENTITY)
                            .entity("/templates/pojo.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
                            .controller("/templates/controller.java");
                });
    }

    /**
     * 自定义模板配置
     */
    public static void injectionConfig() {
        Map<String, Object> customMap = new HashMap<>();

        Map<String, String> customFile = new HashMap<>();
        fastAutoGenerator
                .injectionConfig(builder -> {
                    // 在输出文件之前
                    builder.beforeOutputFile((tableInfo, objectMap) -> {
                        // 拿到package的map 取出父路径
                        Map<String, Object> aPackageMap = (Map) objectMap.get("package");
                        // 设置模版属性
                        // vo的包路径
                        objectMap.put("voPackage", aPackageMap.get("Other").toString()+EntityPackagePath+"."+EntityVoPackagePath+"."+EntityPackageClassPath);
                        objectMap.put("title", Tabletitle);
                        objectMap.put("voLowerClassName",EntityPackageClassPath);
                        objectMap.put("boPackage", aPackageMap.get("Other").toString()+EntityPackagePath+"."+EntityBoPackagePath+"."+EntityPackageClassPath);
                        customFile.put(
                                EntityPackagePath+"//"
                                +EntityVoPackagePath+"//"
                                +EntityPackageClassPath+"//"
                                +EntityClassName+"Vo.java", "/templates/vo.java.ftl"
                        );
                        customFile.put(
                                EntityPackagePath+"//"
                                        +EntityBoPackagePath+"//"
                                        +EntityPackageClassPath+"//"
                                        +EntityClassName+"Bo.java", "/templates/bo.java.ftl"
                        );
                    })
                    // 自定义属性，模板变量
                    .customMap(customMap)
                    .customFile(customFile);
                });
    }


    /**
     * 策略配置
     *
     * @return
     */
    public static void strategyConfig() {
        // 策略配置 配置需要生成的表 然后java的内容
        fastAutoGenerator
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
                });
    }

    /**
     * 配置模板引擎
     */
    public static void templateEngine() {


        fastAutoGenerator.templateEngine(new FreemarkerTemplateEngine(){
            @Override
            protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
                String entityName = tableInfo.getEntityName();
                String otherPath = this.getPathInfo(OutputFile.other);
                customFile.forEach((key, value) -> {
                    // 关注key是什么东西 明天 估计是在injectionConfig的文件模版设置的
                    String fileName = String.format(otherPath + File.separator + "%s"  , key);
                    this.outputFile(new File(fileName), objectMap, value);
                });
            }
        }); // 使用Freemarker引擎模板，默认的是Velocity引擎模板
    }



}