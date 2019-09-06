package cn.caohangwei.dmlive.common.util;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Velocity util.
 * @author PinuoC
 */
public class VelocityUtil {

    public static void generate(String inputFilePath, String outputFilePath, VelocityContext context){
        try {
            Properties p = new Properties();
            p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,getPath(inputFilePath));
            Velocity.init(p);
            Template template = Velocity.getTemplate(getFile(inputFilePath),"utf-8");
            File outputFile = new File(outputFilePath);
            FileWriterWithEncoding writer = new FileWriterWithEncoding(outputFile,"utf-8");
            template.merge(context,writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPath(String filePath){
        String path = "";
        if (StringUtils.isNotBlank(filePath)) {
            path = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }
        return path;
    }

    public static String getFile(String filePath){
        String file = "";
        if(StringUtils.isNotBlank(filePath)){
            file = filePath.substring(filePath.lastIndexOf("/") + 1);
        }
        return file;
    }

}
