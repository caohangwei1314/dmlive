package cn.caohangwei.dmlive.common.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dynamic switching of data source.
 * @author PinuoC
 */
public class DynamicDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

    public static String getDataSource() {
        String dataSource = CONTEXT_HOLDER.get();
        if (null == dataSource) {
            CONTEXT_HOLDER.set(DataSourceEnum.MASTER.getDefault());
        }
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSource(){
        CONTEXT_HOLDER.remove();
    }

}
