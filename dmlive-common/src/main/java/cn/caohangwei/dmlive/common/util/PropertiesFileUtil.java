package cn.caohangwei.dmlive.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PropertiesFileUtil {

    public static HashMap<String,PropertiesFileUtil> configMap = new HashMap<>();

    private ResourceBundle resourceBundle = null;

    private PropertiesFileUtil(String name){
        this.resourceBundle = ResourceBundle.getBundle(name);
    }

    public static PropertiesFileUtil getInstance(String name){
        PropertiesFileUtil conf = configMap.get(name);
        if(null == conf){
            synchronized (PropertiesFileUtil.class){
                conf = configMap.get(name);
                if(null == conf){
                    conf = new PropertiesFileUtil(name);
                    configMap.put(name,conf);
                }
            }
        }
        return conf;
    }

    public String get(String key){
        try {
            String value = resourceBundle.getString(key);
            return value;
        } catch (Exception e) {
            return "";
        }
    }
}
