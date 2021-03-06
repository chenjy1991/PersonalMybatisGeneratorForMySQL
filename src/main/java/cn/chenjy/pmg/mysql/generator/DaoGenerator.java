package cn.chenjy.pmg.mysql.generator;

import cn.chenjy.pmg.mysql.common.Config;
import cn.chenjy.pmg.mysql.common.DateUtils;
import cn.chenjy.pmg.mysql.common.FileUtils;
import cn.chenjy.pmg.mysql.common.StringUtils;

import java.util.*;

/**
 * @author 陈俊羽 chenjyfy@vip.qq.com
 * @Description:
 * @date 2017/9/7 上午8:33
 */
public class DaoGenerator {

    public static void getJavaStr(Map<String, Object> tableMap) {
        String enterKey = StringUtils.getEnterKey();
        for (Map.Entry table : tableMap.entrySet()) {
            try {
                StringBuilder sb = new StringBuilder();
                List<String> typeList = new ArrayList<>();
                String tableName = table.getKey().toString();
                String tableNameUp = StringUtils.getNameUp(tableName);
                String tableNameLo = StringUtils.getNameLo(tableName);
                List<Map<String, String>> tableObj = table.getValue() instanceof LinkedList ? (LinkedList<Map<String, String>>) table.getValue() : null;
                String idType = StringUtils.getJavaType(tableObj.get(0).get("type"));
                sb.append("package " + Config.DAO_PACKAGE + ";" + enterKey + enterKey);
                sb.append("import " + Config.ENTITY_PACKAGE + "." + tableNameUp + ";" + enterKey);
                sb.append("import org.apache.ibatis.annotations.Mapper;" + enterKey);
                sb.append("import org.springframework.stereotype.Component;" + enterKey + enterKey);
                sb.append("import java.util.List;" + enterKey + enterKey);
                sb.append("/**" + enterKey);
                sb.append(" * @author " + Config.AUTHOR + " " + Config.AUTHOR_EMAIL + " " + DateUtils.date2StrDefault(new Date()) + enterKey);
                sb.append(" * @Description:" + enterKey);
                sb.append(" */" + enterKey);
                sb.append("@Component" + enterKey);
                sb.append("@Mapper" + enterKey);
                sb.append("public interface " + tableNameUp + "Dao {" + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 获取总数量" + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    Long count();" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 按条件获取总数量" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    Long countSelective(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 按主键ID获取对象" + enterKey);
                sb.append("     * @param id" + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    " + tableNameUp + " get(" + idType + " id);" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 按条件获取对象" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    " + tableNameUp + " getSelective(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 按条件获取对象组" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    List<" + tableNameUp + "> listSelective(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * Insert(完整对象)" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    int save(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * Insert(非完整对象)" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    int saveSelective(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * Update(完整对象)" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    int update(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * Update(非完整对象)" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    int updateSelective(" + tableNameUp + " " + tableNameLo + ");" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * 标记删除" + enterKey);
                sb.append("     * @param id" + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    int removeSign(" + idType + " id);" + enterKey + enterKey);
                sb.append("    /**" + enterKey);
                sb.append("     * delete" + enterKey);
                sb.append("     * @param id" + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    int removeReal(" + idType + " id);" + enterKey);
                sb.append("}");
                FileUtils.writeJavaFile(sb.toString(), tableNameUp + "Dao.java", Config.DAO_ADDRESS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
