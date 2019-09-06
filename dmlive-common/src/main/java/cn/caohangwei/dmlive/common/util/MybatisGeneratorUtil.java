package cn.caohangwei.dmlive.common.util;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.sql.SQLException;
import java.util.*;

import static cn.caohangwei.dmlive.common.util.StringUtil.lineToHump;

/**
 * Mybatis generator util
 *
 * @author PinuoC
 */
public class MybatisGeneratorUtil {

    private static String generatorConfig_vm = "/template/generatorConfig.vm";

    public static void generate(
            String jdbcDriver,
            String jdbcUrl,
            String jdbcUsername,
            String jdbcPassword,
            String module,
            String database,
            String tablePrefix,
            String packageName,
            Map<String, String> lastInsertIdTables) throws Exception{
        String os = System.getProperty("os.name");
        String targetProject = module + "/" + module + "-dao";
        String basePath = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "").replace(targetProject, "");
        if (os.toLowerCase().startsWith("win")) {
            generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath().replaceFirst("/", "");
            basePath = basePath.replaceFirst("/", "");
        } else {
            generatorConfig_vm = MybatisGeneratorUtil.class.getResource("/").getPath();
        }
        String generatorConfigXml = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "") + "/src/main/resources/generatorConfig.xml";

        String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "' AND table_name LIKE '" + tablePrefix + "_%';";

        System.out.println("========== 开始生成 generatorConfig.xml 文件 ==========");
        List<Map<String, Object>> tables = new ArrayList<>();
        try {
            VelocityContext context = new VelocityContext();
            JdbcUtil jdbcUtil = new JdbcUtil(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
            Map<String, Object> table;
            List<Map> result = jdbcUtil.selectByParams(sql, null);
            for (Map map : result) {
                System.out.println(map.get("TABLE_NAME"));
                table = new HashMap<>(2);
                table.put("table_name", map.get("TABLE_NAME"));
                table.put("model_name", lineToHump(ObjectUtils.toString(map.get("TABLE_NAME"))));
                tables.add(table);
            }
            jdbcUtil.release();
            String targetProjectSqlMap = basePath + module + "/" + module + "-rpc-service";
            context.put("tables", tables);
            context.put("generator_javaModelGenerator_targetPackage", packageName + ".dao.model");
            context.put("generator_javaClientGenerator_targetPackage", packageName + ".dao.mapper");
            context.put("targetProject", targetProject);
            context.put("targetProject_sqlMap", targetProjectSqlMap);
            context.put("last_insert_id_tables", lastInsertIdTables);
            VelocityUtil.generate(generatorConfig_vm, generatorConfigXml, context);
            deleteDir(new File((targetProject + "/src/main/java/" + packageName).replaceAll("\\.","/") + "/dao/model"));
            deleteDir(new File((targetProject + "/src/main/java/" + packageName).replaceAll("\\.","/") + "/dao/mapper"));
            deleteDir(new File(targetProjectSqlMap + "/src/main/resources/mapper"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("========== 结束生成 generatorConfig.xml 文件 ==========");

        System.out.println("========== 开始运行 MybatisGenerator ==========");
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        File file = new File(generatorConfigXml);
        Configuration configuration = cp.parseConfiguration(file);
        DefaultShellCallback shellCallback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration,shellCallback,warnings);
        myBatisGenerator.generate(null);
        for(int i=0;i<warnings.size();i++){
            System.out.println(warnings.get(i));
        }
        System.out.println("========== 结束运行 MybatisGenerator ==========");
    }

    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteDir(files[i]);
            }
        }
        System.out.print("delete " + dir.getName() + " file ");
        if(dir.delete()){
            System.out.println("success");
        }else {
            System.out.println("failed");
        }
    }
}
