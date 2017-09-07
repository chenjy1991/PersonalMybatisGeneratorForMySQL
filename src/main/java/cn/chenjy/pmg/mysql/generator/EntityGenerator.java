package cn.chenjy.pmg.mysql.generator;

import cn.chenjy.pmg.mysql.common.Config;
import cn.chenjy.pmg.mysql.common.FileUtils;
import cn.chenjy.pmg.mysql.common.StringUtils;

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
    public static final char UNDERLINE = '_';

    public static void generatorEntity(Map<String, Object> tableMap) {
        getJavaStr(tableMap);
    }

    private static void getJavaStr(Map<String, Object> tableMap) {
        String enterKey = StringUtils.getEnterKey();
        for (Map.Entry table : tableMap.entrySet()) {
            StringBuilder sb = new StringBuilder();
            List<String> typeList = new ArrayList<>();
            String tableName = table.getKey().toString();
            String tableNameUp = StringUtils.getNameUp(tableName);
            String tableNameLo = StringUtils.getNameLo(tableName);
            List<Map<String, String>> tableObj = table.getValue() instanceof LinkedList ? (List<Map<String, String>>) table.getValue() : null;
            sb.append("package " + Config.ENTITY_PACKAGE + ";" + enterKey + enterKey);
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
                    sb.append("import java.sql.Timestamp;" + enterKey);
                }
            }
            sb.append("public class " + tableNameUp + " {" + enterKey);
            for (Map<String, String> coloum : tableObj) {
                String typeStr = StringUtils.getJavaType(coloum.get("type"));

                sb.append("    private " + typeStr + " " + StringUtils.getNameLo(coloum.get("name")) + ";" + enterKey + enterKey);
            }
            for (Map<String, String> coloum : tableObj) {
                String typeStr = StringUtils.getJavaType(coloum.get("type"));
                String nameStrUp = StringUtils.getNameUp(coloum.get("name"));
                String nameStrLo = StringUtils.getNameLo(coloum.get("name"));
                sb.append("    public " + typeStr + " get" + nameStrUp + "() {" + enterKey);
                sb.append("        return " + nameStrLo + ";" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                sb.append("    public void set" + nameStrUp + "(" + typeStr + " " + nameStrLo + ") {" + enterKey);
                sb.append("        this." + nameStrLo + " = " + nameStrLo + ";" + enterKey);
                sb.append("    }" + enterKey);
            }
            sb.append("}");
            FileUtils.writeJavaFile(sb.toString(), tableNameUp + ".java", Config.ENTITY_ADDRESS);
        }
    }
}
