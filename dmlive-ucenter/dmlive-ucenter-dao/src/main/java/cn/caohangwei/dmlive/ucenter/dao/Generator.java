package cn.caohangwei.dmlive.ucenter.dao;

import cn.caohangwei.dmlive.common.util.MybatisGeneratorUtil;
import cn.caohangwei.dmlive.common.util.PropertiesFileUtil;

import java.util.HashMap;
import java.util.Map;

public class Generator {

    private static final String MODULE = "dmlive-ucenter";
    private static final String DATABASE = "dmlive";
    private static final String TABLES_PREFIX = "ucenter";
    private static final String PACKAGE_NAME = "cn.caohangwei.dmlive.ucenter";
    private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
    private static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
    private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
    private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");
    private static Map<String,String> LAST_INSERT_ID_TABLES = new HashMap<>();

    public static void main(String[] args) throws Exception{
        MybatisGeneratorUtil.generate(JDBC_DRIVER,JDBC_URL,JDBC_USERNAME,JDBC_PASSWORD,MODULE,DATABASE,TABLES_PREFIX,PACKAGE_NAME,LAST_INSERT_ID_TABLES);
    }

}
