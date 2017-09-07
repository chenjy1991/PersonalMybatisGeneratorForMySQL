package cn.chenjy.pmg.mysql.generator;

import cn.chenjy.pmg.mysql.common.Config;
import cn.chenjy.pmg.mysql.common.FileUtils;
import cn.chenjy.pmg.mysql.common.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapperGenerator {

    public static void getJavaStr(Map<String, Object> tableMap) {
        String enterKey = StringUtils.getEnterKey();
        for (Map.Entry table : tableMap.entrySet()) {
            StringBuilder sb = new StringBuilder();
            List<String> typeList = new ArrayList<>();
            String tableName = table.getKey().toString();
            String tableNameUp = StringUtils.getNameUp(tableName);
            String tableNameLo = StringUtils.getNameLo(tableName);
            List<Map<String, String>> tableObj = table.getValue() instanceof LinkedList ? (LinkedList<Map<String, String>>) table.getValue() : null;
            String idSqlType = StringUtils.getSQLType(tableObj.get(0).get("type"));
            String idJavaType = StringUtils.getJavaType(idSqlType);
            String idName = tableObj.get(0).get("name");
            String idNameLo = StringUtils.getNameLo(idName);

            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + enterKey);
            sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">" + enterKey);
            sb.append("<mapper namespace=\"" + Config.DAO_PACKAGE + "." + tableNameUp + "Dao\">" + enterKey);
            sb.append("    <resultMap id=\"BaseResultMap\" type=\"" + Config.ENTITY_PACKAGE + "." + tableNameUp + "\">" + enterKey);
            for (Map<String, String> column : tableObj) {
                String sqlType = StringUtils.getSQLType(column.get("type"));
                String name = column.get("name");
                if (name.toLowerCase().equals("id")) {
                    sb.append("        <id column=\"id\" jdbcType=\"" + idSqlType + "\" property=\"id\" />" + enterKey);
                } else {
                    sb.append("        <result column=\"" + name + "\" jdbcType=\"" + sqlType + "\" property=\"" + name + "\" />" + enterKey);
                }
            }
            sb.append("    </resultMap>" + enterKey + enterKey);
            // Long count();
            sb.append("    <select id=\"count\" resultType=\"java.lang.Long\">"+enterKey);
            sb.append("        SELECT count(0) FROM "+tableName+enterKey);
            sb.append("    </select>"+enterKey+enterKey);
            //Long countSelective(Object object);
            sb.append("    <select id=\"countSelective\" parameterType=\""+Config.ENTITY_PACKAGE+"."+tableNameUp+"\" resultType=\"java.lang.Long\">"+enterKey);
            sb.append("        SELECT count(0) FROM "+tableName+enterKey);
            sb.append("        <where>"+enterKey);
            for (Map<String, String> column : tableObj){
                String sqlType = StringUtils.getSQLType(column.get("type"));
                String name = column.get("name");
                String nameLo = StringUtils.getNameLo(name);
                if(sqlType.equals("VARCHAR")){
                    sb.append("            <if test=\" "+nameLo+" != null and "+nameLo+" != '' \">"+enterKey);
                    sb.append("                AND "+name+"=#{"+nameLo+",jdbcType="+sqlType+"}"+enterKey);
                    sb.append("            </if>"+enterKey);
                } else {
                    sb.append("            <if test=\" "+nameLo+" != null \">"+enterKey);
                    sb.append("                AND "+name+"=#{"+nameLo+",jdbcType=\""+sqlType+"\"}"+enterKey);
                    sb.append("            </if>"+enterKey);
                }
            }
            sb.append("        </where>"+enterKey);
            sb.append("    </select>"+enterKey+enterKey);
            // Object get(Integer id);
            sb.append("    <select id=\"get\" parameterType=\"java.lang."+idJavaType+"\" resultMap=\"BaseResultMap\">"+enterKey);
            sb.append("        SELECT"+enterKey);
            sb.append("            ");
            for (Map<String, String> column : tableObj){
                sb.append(column.get("name")+",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(enterKey);
            sb.append("        FROM "+tableName+enterKey);
            sb.append("        WHERE "+idName+"#{"+idNameLo+",jdbcType="+idSqlType+"}"+enterKey);
            sb.append("    </select>"+enterKey+enterKey);
            // Object getSelective(Object object);
            sb.append("    <select id=\"getSelective\" resultMap=\"BaseResultMap\">"+enterKey);
            sb.append("        SELECT"+enterKey);
            sb.append("            ");
            for (Map<String, String> column : tableObj){
                sb.append(column.get("name")+",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(enterKey);
            sb.append("        FROM "+tableName+enterKey);
            sb.append("        <where>"+enterKey);
            for (Map<String, String> column : tableObj){
                String sqlType = StringUtils.getSQLType(column.get("type"));
                String name = column.get("name");
                String nameLo = StringUtils.getNameLo(name);
                if(sqlType.equals("VARCHAR")){
                    sb.append("            <if test=\" "+nameLo+" != null and "+nameLo+" != '' \">"+enterKey);
                    sb.append("                AND "+name+"=#{"+nameLo+",jdbcType="+sqlType+"}"+enterKey);
                    sb.append("            </if>"+enterKey);
                } else {
                    sb.append("            <if test=\" "+nameLo+" != null \">"+enterKey);
                    sb.append("                AND "+name+"=#{"+nameLo+",jdbcType=\""+sqlType+"\"}"+enterKey);
                    sb.append("            </if>"+enterKey);
                }
            }
            sb.append("        </where>"+enterKey);
            sb.append("    </select>"+enterKey+enterKey);













            sb.append("</mapper>");
            FileUtils.writeJavaFile(sb.toString(), tableNameUp + "Mapper.xml", Config.XML_ADDRESS);
        }
    }
}
