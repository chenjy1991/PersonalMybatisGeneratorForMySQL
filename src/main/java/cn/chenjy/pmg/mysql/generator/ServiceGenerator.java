package cn.chenjy.pmg.mysql.generator;

import cn.chenjy.pmg.mysql.common.Config;
import cn.chenjy.pmg.mysql.common.DateUtils;
import cn.chenjy.pmg.mysql.common.FileUtils;
import cn.chenjy.pmg.mysql.common.StringUtils;

import java.util.*;

/**
 * @author chenjy chenjy@chenjy.cn 2017/12/27 上午11:08
 * @Description:
 */
public class ServiceGenerator {
    public static void getJavaStr(Map<String, Object> tableMap) {
        String enterKey = StringUtils.getEnterKey();
        for (Map.Entry table : tableMap.entrySet()) {
            try {
                StringBuilder sb = new StringBuilder();
                String tableName = table.getKey().toString();
                String tableNameUp = StringUtils.getNameUp(tableName);
                String tableNameLo = StringUtils.getNameLo(tableName);
                List<Map<String, String>> tableObj = table.getValue() instanceof LinkedList ? (LinkedList<Map<String, String>>) table.getValue() : null;
                String idType = StringUtils.getJavaType(tableObj.get(0).get("type"));
                sb.append("package " + Config.SERVICE_PACKAGE + ";" + enterKey + enterKey);
                sb.append("import com.nbmssoft.patrol.weather.common.page.PageInfo;" + enterKey);
                sb.append("import " + Config.ENTITY_PACKAGE + "." + tableNameUp + ";" + enterKey + enterKey);
                sb.append("import java.util.List;" + enterKey + enterKey);
                sb.append("/**" + enterKey);
                sb.append(" * @author " + Config.AUTHOR + " " + Config.AUTHOR_EMAIL + " " + DateUtils.date2StrDefault(new Date()) + enterKey);
                sb.append(" * @Description:" + enterKey);
                sb.append(" */" + enterKey);
                sb.append("public interface " + tableNameUp + "Service {" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 分页查询" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @param pageInfo" + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    PageInfo<" + tableNameUp + "> queryPage(" + tableNameUp + " " + tableNameLo + ", PageInfo<" + tableNameUp + "> pageInfo);" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 查询总数" + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    Long count();" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 查询总数(按需)" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    Long countSelective(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 获取对象" + enterKey);
                sb.append("     * @param id" + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    " + tableNameUp + " get(" + idType + " id);" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 获取对象(按需)" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    " + tableNameUp + " getSelective(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 获取对象组(按需)" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    List<" + tableNameUp + "> listSelective(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * Insert" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    int save(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * Insert(按需)" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    int saveSelective(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * Update" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    int update(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * Update(按需)" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    int updateSelective(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 标记删除" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    int removeSign(" + idType + " id);" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 真实删除" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    int removeReal(" + idType + " id);" + enterKey);
                sb.append("}");
                FileUtils.writeJavaFile(sb.toString(), tableNameUp + "Service.java", Config.SERVICE_ADDRESS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
