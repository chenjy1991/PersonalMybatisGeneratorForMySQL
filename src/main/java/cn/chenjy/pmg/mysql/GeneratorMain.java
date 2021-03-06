package cn.chenjy.pmg.mysql;

import cn.chenjy.pmg.mysql.common.Config;
import cn.chenjy.pmg.mysql.generator.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 陈俊羽 chenjyfy@vip.qq.com
 * @Description:
 * @date 2017/9/6 下午5:42
 */
public class GeneratorMain {

    public static void main(String[] args) {
        List<List<Map<String, String>>> listList = new LinkedList<>();
        List<Map<String, String>> mapList = new LinkedList<>();
        Map<String, Object> mapTable = new LinkedHashMap<>();
        Map<String, String> map;
        try {
            Class.forName(Config.JDBC_DRIVER).newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://" + Config.JDBC_URL + "/" + Config.JDBC_DATABASE + "?user=" + Config.JDBC_USERNAME + "&password=" + Config.JDBC_PASSWORD + "");
//            Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.199.71:1433;databaseName=XUNJIAN","sa","yx@2015");
            DatabaseMetaData mDBMetaData = con.getMetaData();
            for (String tableName : Config.TABLE_NAMES) {
                System.out.println("Table:------->" + tableName);
                String columnName;
                String columnType;
                ResultSet colRet = mDBMetaData.getColumns(null, "%", tableName, "%");
                while (colRet.next()) {
                    columnName = colRet.getString("COLUMN_NAME");
                    columnType = colRet.getString("TYPE_NAME");
                    int datasize = colRet.getInt("COLUMN_SIZE");
                    map = new LinkedHashMap<>();
                    map.put("name", columnName);
                    map.put("type", columnType);
                    map.put("size", datasize + "");
                    mapList.add(map);
                }
                listList.add(mapList);
                mapTable.put(tableName, mapList);
                mapList = new LinkedList<>();
            }
            EntityGenerator.generatorEntity(mapTable);
            DaoGenerator.getJavaStr(mapTable);
            MapperGenerator.getJavaStr(mapTable);
            ServiceGenerator.getJavaStr(mapTable);
            ServiceImplGenerator.getJavaStr(mapTable);
            ControllerGenerator.getJavaStr(mapTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
