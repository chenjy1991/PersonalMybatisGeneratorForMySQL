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
 * @date 2017/9/7 上午8:33
 */
public class DaoGenerator {

    public static void getJavaStr(Map<String, Object> tableMap){
        String enterKey = StringUtils.getEnterKey();
        for(Map.Entry table : tableMap.entrySet()){
            StringBuilder sb = new StringBuilder();
            List<String> typeList = new ArrayList<>();
            String tableName = table.getKey().toString();
            String tableNameUp = StringUtils.getNameUp(tableName);
            String tableNameLo = StringUtils.getNameLo(tableName);
            List<Map<String, String>> tableObj = table.getValue() instanceof LinkedList ? (LinkedList<Map<String, String>>) table.getValue() : null;
            String idType = StringUtils.getJavaType(tableObj.get(0).get("type"));
            sb.append("package "+ Config.DAO_PACKAGE +";"+enterKey+enterKey);
            sb.append("import "+Config.ENTITY_PACKAGE+"."+ tableNameUp+";"+enterKey);
            sb.append("import org.apache.ibatis.annotations.Mapper;"+enterKey);
            sb.append("import org.springframework.stereotype.Component;"+enterKey+enterKey);
            sb.append("import java.util.List;"+enterKey+enterKey);
            sb.append("@Component"+enterKey);
            sb.append("@Mapper"+enterKey);
            sb.append("public interface "+tableNameUp+"Dao {"+enterKey);
            sb.append("    Long count();"+enterKey+enterKey);
            sb.append("    Long countSelective("+tableNameUp+" "+tableNameLo+");"+enterKey+enterKey);
            sb.append("    "+tableNameUp+" get("+idType+" id);"+enterKey+enterKey);
            sb.append("    "+tableNameUp+" getSelective("+tableNameUp+" "+tableNameLo+");"+enterKey+enterKey);
            sb.append("    List<"+tableNameUp+"> listSelective("+tableNameUp+ " "+tableNameLo+");"+enterKey+enterKey);
            sb.append("    int save("+tableNameUp+" "+tableNameLo+");"+enterKey+enterKey);
            sb.append("    int saveSelective("+tableNameUp+" "+tableNameLo+");"+enterKey+enterKey);
            sb.append("    int update("+tableNameUp+" "+tableNameLo+");"+enterKey+enterKey);
            sb.append("    int updateSelective("+tableNameUp+ " "+tableNameLo+");"+enterKey+enterKey);
            sb.append("    int removeSign("+idType+" id);"+enterKey+enterKey);
            sb.append("    int removeReal("+idType+" id);"+enterKey);
            sb.append("}");
            FileUtils.writeJavaFile(sb.toString(), tableNameUp+"Dao", Config.DAO_ADDRESS);

        }
    }
}
