package cn.stanoswald.eestore.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help);
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    private static final String URl = "jdbc:mysql://localhost/ee_store?useUnicode=true&useSSL=false&characterEncoding=utf-8";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    /**
     * 自动生成代码输出目录。
     */
    private static final String OUTPUT_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        FastAutoGenerator.create(URl,USERNAME,PASSWORD)
                .globalConfig(builder ->
                    builder.author("yjw")// 设置作者
//                            .enableSwagger()// 开启 swagger 模式
                            .fileOverride()// 覆盖已生成文件
                            .disableOpenDir()//禁止打开输出目录
                            .outputDir(OUTPUT_DIR + "/src/main/java")// 指定输出目录
                )
                .packageConfig(builder -> builder
                        .parent("cn.stanoswald.eestore")// parent指定生成的代码在哪个包下
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .mapper("mapper")
                        .pathInfo(Collections.singletonMap(OutputFile.xml,OUTPUT_DIR + "/src/main/resources/cn/stanoswald/eestore/mapper"))// 设置mapperXml生成路径
                )
                .strategyConfig(builder -> builder
                        .enableCapitalMode()
                        .enableSkipView()
                        .addInclude(getTables(scanner("请输入表名，多个英文逗号分隔？all"))).addTablePrefix("tbl_")
                        .entityBuilder().disableSerialVersionUID()
                        .controllerBuilder().formatFileName("%sController")
                        .mapperBuilder().formatMapperFileName("%sMapper").formatXmlFileName("%sMapper")
                        .serviceBuilder().formatServiceFileName("%sService").formatServiceImplFileName("%sServiceImpl")
                        .build()
                )
                // MyBatis-Plus代码生成器是通过模板引擎来渲染文件的，默认模板引擎是Velocity，根据依赖我们使用Freemarker
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
