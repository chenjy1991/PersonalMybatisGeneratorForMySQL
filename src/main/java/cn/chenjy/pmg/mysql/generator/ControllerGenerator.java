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
 * @author chenjy chenjy@chenjy.cn 2017/12/28 上午8:50
 * @Description:
 */
public class ControllerGenerator {
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
                sb.append("package " + Config.CONTROLLER_PACKAGE + ";" + enterKey + enterKey);
                sb.append("import com.nbmssoft.patrol.weather.common.page.PageInfo;" + enterKey);
                sb.append("import com.nbmssoft.patrol.weather.common.version.ApiVersion;" + enterKey);
                sb.append("import " + Config.SERVICE_PACKAGE + "." + tableNameUp + "Service;" + enterKey);
                sb.append("import com.nbmssoft.patrol.weather.pojo.dto.ApiResult;" + enterKey);
                sb.append("import com.nbmssoft.patrol.weather.pojo.dto.HttpStatus;" + enterKey);
                sb.append("import " + Config.ENTITY_PACKAGE + "." + tableNameUp + ";" + enterKey);
                sb.append("import org.springframework.beans.factory.annotation.Autowired;" + enterKey);
                sb.append("import org.springframework.web.bind.annotation.PathVariable;" + enterKey);
                sb.append("import org.springframework.web.bind.annotation.RequestMapping;" + enterKey);
                sb.append("import org.springframework.web.bind.annotation.RequestMethod;" + enterKey);
                sb.append("import org.springframework.web.bind.annotation.RestController;" + enterKey + enterKey);
                sb.append("import java.util.List;" + enterKey + enterKey);
                sb.append("/**" + enterKey);
                sb.append(" * @author " + Config.AUTHOR + " " + Config.AUTHOR_EMAIL + " " + DateUtils.date2StrDefault(new Date()) + enterKey);
                sb.append(" * @Description:" + enterKey);
                sb.append(" */" + enterKey);
                sb.append("@RestController" + enterKey);
                sb.append("@RequestMapping(\"api/{version}/" + tableNameLo + "s\")" + enterKey);
                sb.append("public class " + tableNameUp + "Controller {" + enterKey);
                sb.append("    @Autowired" + enterKey);
                sb.append("    " + tableNameUp + "Service " + tableNameLo + "Service;" + enterKey + enterKey);
                //分页查询
                sb.append("    /**" + enterKey);
                sb.append("     * 分页查询" + enterKey);
                sb.append("     *" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @param pageInfo" + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    @ApiVersion(1)" + enterKey);
                sb.append("    @RequestMapping(value = \"page\", method = RequestMethod.POST)" + enterKey);
                sb.append("    public ApiResult page(" + tableNameUp + " " + tableNameLo + ", PageInfo pageInfo) {" + enterKey);
                sb.append("        pageInfo = " + tableNameLo + "Service.queryPage(" + tableNameLo + ", pageInfo);" + enterKey);
                sb.append("        return ApiResult.success(pageInfo);" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //获取对象集合
                sb.append("    /**" + enterKey);
                sb.append("     * 获取对象集合" + enterKey);
                sb.append("     *" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    @ApiVersion(1)" + enterKey);
                sb.append("    @RequestMapping(method = RequestMethod.GET)" + enterKey);
                sb.append("    public ApiResult list(" + tableNameUp + " " + tableNameLo + ") {" + enterKey);
                sb.append("        List<" + tableNameUp + "> list = " + tableNameLo + "Service.listSelective(" + tableNameLo + ");" + enterKey);
                sb.append("        return ApiResult.success(list);" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //获取指定对象
                sb.append("    /**" + enterKey);
                sb.append("     * 获取对象集合" + enterKey);
                sb.append("     *" + enterKey);
                sb.append("     * @param id" + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    @ApiVersion(1)" + enterKey);
                sb.append("    @RequestMapping(value = \"{id}\", method = RequestMethod.GET)" + enterKey);
                sb.append("    public ApiResult get(@PathVariable Integer id) {" + enterKey);
                sb.append("        " + tableNameUp + " " + tableNameLo + " = " + tableNameLo + "Service.get(id);" + enterKey);
                sb.append("        return ApiResult.success(" + tableNameLo + ");" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //保存
                sb.append("    /**" + enterKey);
                sb.append("     * 保存" + enterKey);
                sb.append("     *" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    @ApiVersion(1)" + enterKey);
                sb.append("    @RequestMapping(method = RequestMethod.POST)" + enterKey);
                sb.append("    public ApiResult save(" + tableNameUp + " " + tableNameLo + ") {" + enterKey);
                sb.append("        int flag = " + tableNameLo + "Service.saveSelective(" + tableNameLo + ");" + enterKey);
                sb.append("        if (flag > 0) {" + enterKey);
                sb.append("            return ApiResult.success();" + enterKey);
                sb.append("        } else {" + enterKey);
                sb.append("            return ApiResult.error(HttpStatus.HTTP_400, HttpStatus.HTTP_400_INFO);" + enterKey);
                sb.append("        }" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //修改，全字段
                sb.append("    /**" + enterKey);
                sb.append("     * 修改，全字段" + enterKey);
                sb.append("     *" + enterKey);
                sb.append("     * @param id" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    @ApiVersion(1)" + enterKey);
                sb.append("    @RequestMapping(value = \"{id}\", method = RequestMethod.PUT)" + enterKey);
                sb.append("    public ApiResult update(@PathVariable Integer id, " + tableNameUp + " " + tableNameLo + ") {" + enterKey);
                sb.append("        " + tableNameLo + ".setId(id);" + enterKey);
                sb.append("        int flag = " + tableNameLo + "Service.update(" + tableNameLo + ");" + enterKey);
                sb.append("        if (flag > 0) {" + enterKey);
                sb.append("            return ApiResult.success();" + enterKey);
                sb.append("        } else {" + enterKey);
                sb.append("            return ApiResult.error(HttpStatus.HTTP_400, HttpStatus.HTTP_400_INFO);" + enterKey);
                sb.append("        }" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //按需修改
                sb.append("    /**" + enterKey);
                sb.append("     * 按需修改" + enterKey);
                sb.append("     *" + enterKey);
                sb.append("     * @param id" + enterKey);
                sb.append("     * @param " + tableNameLo + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    @ApiVersion(1)" + enterKey);
                sb.append("    @RequestMapping(value = \"{id}\", method = RequestMethod.PATCH)" + enterKey);
                sb.append("    public ApiResult updateSelective(@PathVariable Integer id, " + tableNameUp + " " + tableNameLo + ") {" + enterKey);
                sb.append("        " + tableNameLo + ".setId(id);" + enterKey);
                sb.append("        int flag = " + tableNameLo + "Service.updateSelective(" + tableNameLo + ");" + enterKey);
                sb.append("        if (flag > 0) {" + enterKey);
                sb.append("            return ApiResult.success();" + enterKey);
                sb.append("        } else {" + enterKey);
                sb.append("            return ApiResult.error(HttpStatus.HTTP_400, HttpStatus.HTTP_400_INFO);" + enterKey);
                sb.append("        }" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //标记删除
                sb.append("    /**" + enterKey);
                sb.append("     * 标记删除" + enterKey);
                sb.append("     *" + enterKey);
                sb.append("     * @param id" + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    @ApiVersion(1)" + enterKey);
                sb.append("    @RequestMapping(value = \"{id}\", method = RequestMethod.DELETE)" + enterKey);
                sb.append("    public ApiResult removeSign(@PathVariable Integer id) {" + enterKey);
                sb.append("        int flag = " + tableNameLo + "Service.removeSign(id);" + enterKey);
                sb.append("        if (flag > 0) {" + enterKey);
                sb.append("            return ApiResult.success();" + enterKey);
                sb.append("        } else {" + enterKey);
                sb.append("            return ApiResult.error(HttpStatus.HTTP_400, HttpStatus.HTTP_400_INFO);" + enterKey);
                sb.append("        }" + enterKey);
                sb.append("    }" + enterKey + enterKey);
                //真实删除
                sb.append("    /**" + enterKey);
                sb.append("     * 真实删除" + enterKey);
                sb.append("     *" + enterKey);
                sb.append("     * @param id" + enterKey);
                sb.append("     * @return" + enterKey);
                sb.append("     */" + enterKey);
                sb.append("    @ApiVersion(1)" + enterKey);
                sb.append("    @RequestMapping(value = \"{id}/real\", method = RequestMethod.DELETE)" + enterKey);
                sb.append("    public ApiResult removeReal(@PathVariable Integer id) {" + enterKey);
                sb.append("        int flag = " + tableNameLo + "Service.removeReal(id);" + enterKey);
                sb.append("        if (flag > 0) {" + enterKey);
                sb.append("            return ApiResult.success();" + enterKey);
                sb.append("        } else {" + enterKey);
                sb.append("            return ApiResult.error(HttpStatus.HTTP_400, HttpStatus.HTTP_400_INFO);" + enterKey);
                sb.append("        }" + enterKey);
                sb.append("    }" + enterKey);
                sb.append("}");
                FileUtils.writeJavaFile(sb.toString(), tableNameUp + "Controller.java", Config.CONTROLLER_ADDRESS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
