package cn.chenjy.pmg.mysql.generator;

import cn.chenjy.pmg.mysql.common.Config;
import cn.chenjy.pmg.mysql.common.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 陈俊羽 chenjyfy@vip.qq.com
 * @Description:
 * @date 2017/9/6 下午5:46
 */
public class EntityGenerator {
    private static final String ENTER_KEY = "\r";
    public static final char UNDERLINE = '_';

    public static void generatorEntity(Map<String,Object> tableMap) {
        getJavaStr(tableMap);
    }

    private static void getJavaStr(Map<String, Object> tableMap) {
        for (Map.Entry table : tableMap.entrySet()) {
            StringBuilder sb = new StringBuilder();
            List<String> typeList = new ArrayList<>();
            String tableName = table.getKey().toString();
            List<Map<String, String>> tableObj = table.getValue() instanceof LinkedList ? (List<Map<String, String>>) table.getValue() : null;
            sb.append("package " + Config.ENTITY_PACKAGE + ";" + ENTER_KEY + ENTER_KEY);
            //添加字段类型
            for (Map<String, String> coloum : tableObj) {
                for (String type : typeList) {
                    if (!type.equals(coloum.get("type"))) {
                        typeList.add(coloum.get("type"));
                    }
                }
            }
            for (String type : typeList) {
                if ("TIMESTAMP".equals(type)) {
                    sb.append("import java.sql.Timestamp;" + ENTER_KEY);
                }
            }
            sb.append("public class " + getNameUp(tableName) + " {" + ENTER_KEY);
            for (Map<String, String> coloum : tableObj) {
                String typeStr = getJavaType(coloum.get("type"));

                sb.append("    private " + typeStr + " " + getNameLo(coloum.get("name")) + ";" + ENTER_KEY + ENTER_KEY);
            }
            for (Map<String, String> coloum : tableObj) {
                String typeStr = getJavaType(coloum.get("type"));
                String nameStrUp = getNameUp(coloum.get("name"));
                String nameStrLo = getNameLo(coloum.get("name"));
                sb.append("    public " + typeStr + " get" + nameStrUp + "() {" + ENTER_KEY);
                sb.append("        return " + nameStrLo + ";" + ENTER_KEY);
                sb.append("    }" + ENTER_KEY + ENTER_KEY);
                sb.append("    public void set" + nameStrUp + "(" + typeStr + " " + nameStrLo + ") {" + ENTER_KEY);
                sb.append("        this." + nameStrLo + " = " + nameStrLo + ";" + ENTER_KEY);
                sb.append("    }" + ENTER_KEY);
            }
            sb.append("}");
            writeJavaFile(sb.toString(), getNameUp(tableName));
        }
    }

    private static String getNameUp(String tableName) {
        String tableStr = getNameLo(tableName);
        tableStr = tableStr.substring(0, 1).toUpperCase() + tableStr.substring(1);
        return tableStr;
    }

    private static String getNameLo(String tableName) {
        if (tableName == null || "".equals(tableName.trim())) {
            return "";
        }
        int len = tableName.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = tableName.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(tableName.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static String getJavaType(String sqlType) {
        String javatype = "";
        switch (sqlType) {
            case "INT":
                javatype = "Integer";
                break;
            case "VARCHAR":
                javatype = "String";
                break;
            case "BIGINT":
                javatype = "Long";
            default:
                javatype = "String";
                break;

        }
        return javatype;
    }

    private static void writeJavaFile(String content, String filename) {
        String javaFileName = Config.ENTITY_ADDRESS + filename + ".java";
        File javaFile = new File(javaFileName);
        try {
            FileUtils.createDirectory(Config.ENTITY_ADDRESS);
            if (!javaFile.exists()) {
                javaFile.createNewFile();
            } else {
                FileUtils.deleteFile(javaFileName);
                javaFile.createNewFile();
            }
            FileWriter resultFile = new FileWriter(javaFile);
            PrintWriter myNewFile = new PrintWriter(resultFile);
            myNewFile.println(content);
            resultFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
