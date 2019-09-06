package cn.caohangwei.dmlive.common.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jdbc util
 * @author PinuoC
 */
public class JdbcUtil {

    private Connection conn;

    private PreparedStatement pstm;

    private ResultSet rs;

    public JdbcUtil(String jdbcDriver, String jdbcUrl, String jdbcUsername, String jdbcPassword) {
        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            if(null == conn){
                System.out.println("数据库链接失败");
            }else {
                System.out.println("数据库链接成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Map> selectByParams(String sql, List params) throws SQLException {
        List<Map> list = new ArrayList<>();
        int index = 1;
        pstm = conn.prepareStatement(sql);
        if (null != params && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(index++, params.get(i));
            }
        }
        rs = pstm.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int colsLen = metaData.getColumnCount();
        while (rs.next()) {
            Map map = new HashMap(colsLen);
            for (int i = 0; i < colsLen; i++) {
                String columnName = metaData.getColumnName(i + 1);
                Object columnValue = rs.getObject(columnName);
                if (null == columnValue) {
                    columnValue = "";
                }
                map.put(columnName, columnValue);
            }
            list.add(map);
        }
        return list;
    }

    public void release(){
        try {
            if(null != conn){
                conn.close();
            }
            if(null != pstm){
                pstm.close();
            }
            if(null != rs){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("释放数据库连接");
    }

}
