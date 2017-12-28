package cn.chenjy.pmg.mysql.generator;

import cn.chenjy.pmg.mysql.common.Config;
import cn.chenjy.pmg.mysql.common.DateUtils;
import cn.chenjy.pmg.mysql.common.FileUtils;
import cn.chenjy.pmg.mysql.common.StringUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author chenjy chenjy@chenjy.cn 2017/12/27 下午2:35
 * @Description:
 */
public class ServiceImplGenerator {
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
                sb.append("package " + Config.SERVICE_PACKAGE + ".impl" + ";" + enterKey + enterKey);
                sb.append("import com.github.pagehelper.PageHelper;" + enterKey);
                sb.append("import com.nbmssoft.patrol.weather.common.page.PageInfo;" + enterKey);
                sb.append("import " + Config.DAO_PACKAGE + "." + tableNameUp + "Dao;" + enterKey);
                sb.append("import " + Config.ENTITY_PACKAGE + tableNameUp + ";" + enterKey);
                sb.append("import " + Config.SERVICE_PACKAGE + "." + tableNameUp + "Service;" + enterKey);
                sb.append("import org.springframework.beans.factory.annotation.Autowired;" + enterKey);
                sb.append("import org.springframework.stereotype.Service;" + enterKey + enterKey);
                sb.append("import java.util.List;" + enterKey + enterKey);
                sb.append("/**" + enterKey);
                sb.append(" * @author " + Config.AUTHOR + " " + Config.AUTHOR_EMAIL + DateUtils.date2StrDefault(new Date()) + enterKey);
                sb.append(" * @Description:" + enterKey);
                sb.append(" */" + enterKey);
                sb.append("@Service" + enterKey);
                sb.append("public class " + tableNameUp + "ServiceImpl implements " + tableNameUp + "Service {" + enterKey);
                sb.append("    @Autowired" + enterKey);
                sb.append("    " + tableNameUp + "Dao " + tableNameLo + "Dao;" + enterKey + enterKey);
                //PageInfo queryPage()
                sb.append("    @Override" + enterKey);
                sb.append("    public PageInfo<" + tableNameUp + "> queryPage(" + tableNameUp + " " + tableNameLo + ", PageInfo<" + tableNameUp + "> pageInfo) {" + enterKey);
                sb.append("        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), \"id desc\");" + enterKey);
                sb.append("        List<" + tableNameUp + "> pageList = " + tableNameLo + "Dao.listSelective(" + tableNameLo + ");" + enterKey);
                sb.append("        return new PageInfo<" + tableNameUp + ">(pageList);" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //Long count()
                sb.append("    @Override" + enterKey);
                sb.append("    public Long count() {" + enterKey);
                sb.append("        return " + tableNameLo + "Dao.count();" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //Long countSelective(Object object)
                sb.append("    @Override" + enterKey);
                sb.append("    public Long countSelective(" + tableNameUp + " " + tableNameLo + ") {" + enterKey);
                sb.append("        return " + tableNameLo + "Dao.countSelective(" + tableNameLo + ");" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //Object get(Number id)
                sb.append("    @Override" + enterKey);
                sb.append("    public " + tableNameUp + " get(" + idType + " id) {" + enterKey);
                sb.append("        return " + tableNameLo + "Dao.get(id);" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //Object getSelective(Object object)
                sb.append("    @Override" + enterKey);
                sb.append("    public " + tableNameUp + " getSelective(" + tableNameUp + " " + tableNameLo + ") {" + enterKey);
                sb.append("        return " + tableNameLo + "Dao.getSelective(" + tableNameLo + ");" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //List<Object> listSelective(Object object)
                sb.append("    @Override" + enterKey);
                sb.append("    public List<" + tableNameUp + "> listSelective(" + tableNameUp + " " + tableNameLo + ") {" + enterKey);
                sb.append("        return " + tableNameLo + "Dao.listSelective(" + tableNameLo + ");" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //int save(Object object)
                sb.append("    @Override" + enterKey);
                sb.append("    public int save(" + tableNameUp + " " + tableNameLo + ") {" + enterKey);
                sb.append("        return " + tableNameLo + "Dao.save(" + tableNameLo + ");" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //saveSelective(Object object)
                sb.append("    @Override" + enterKey);
                sb.append("    public int saveSelective(" + tableNameUp + " " + tableNameLo + ") {" + enterKey);
                sb.append("        return " + tableNameLo + "Dao.saveSelective(" + tableNameLo + ");" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //int update(Object object)
                sb.append("    @Override" + enterKey);
                sb.append("    public int update(" + tableNameUp + " " + tableNameLo + ") {" + enterKey);
                sb.append("        return " + tableNameLo + "Dao.update(" + tableNameLo + ");" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //int updateSelective(Object object)
                sb.append("    @Override" + enterKey);
                sb.append("    public int updateSelective(" + tableNameUp + " " + tableNameLo + ") {" + enterKey);
                sb.append("        return " + tableNameLo + "Dao.updateSelective(" + tableNameLo + ");" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //int removeSign(Number id)
                sb.append("    @Override" + enterKey);
                sb.append("    public int removeSign(" + idType + " id) {" + enterKey);
                sb.append("        return " + tableNameLo + "Dao.removeSign(id);" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //int removeReal(Number id)
                sb.append("    @Override" + enterKey);
                sb.append("    public int removeReal(" + idType + " id) {" + enterKey);
                sb.append("        return " + tableNameLo + "Dao.removeReal(id);" + enterKey);
                sb.append("    }" + enterKey);
                sb.append("}");
                FileUtils.writeJavaFile(sb.toString(), tableNameUp + "ServiceImpl.java", Config.SERVICE_ADDRESS + "impl/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
