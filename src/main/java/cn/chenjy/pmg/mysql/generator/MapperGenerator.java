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
            //start content
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + enterKey);
            sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">" + enterKey);
            sb.append("<mapper namespace=\"" + Config.DAO_PACKAGE + "." + tableNameUp + "Dao\">" + enterKey);
            sb.append("    <resultMap id=\"BaseResultMap\" type=\"" + Config.ENTITY_PACKAGE + "." + tableNameUp + "\">" + enterKey);
            for (Map<String, String> column : tableObj) {
                String sqlType = StringUtils.getSQLType(column.get("type"));
                if(sqlType.equals("TEXT")){
                    sqlType = "VARCHAR";
                }
                String name = column.get("name");
                String nameLo = StringUtils.getNameLo(name);
                if (name.toLowerCase().equals(idName)) {
                    sb.append("        <id column=\"id\" jdbcType=\"" + idSqlType + "\" property=\""+idNameLo+"\" />" + enterKey);
                } else {
                    sb.append("        <result column=\"" + name + "\" jdbcType=\"" + sqlType + "\" property=\"" + nameLo + "\" />" + enterKey);
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
                if(sqlType.equals("TEXT")){
                    sqlType = "VARCHAR";
                }
                String name = column.get("name");
                String nameLo = StringUtils.getNameLo(name);
                if(sqlType.equals("VARCHAR")){
                    sb.append("            <if test=\" "+nameLo+" != null and "+nameLo+" != '' \">"+enterKey);
                    sb.append("                AND "+name+"=#{"+nameLo+",jdbcType="+sqlType+"}"+enterKey);
                    sb.append("            </if>"+enterKey);
                } else {
                    sb.append("            <if test=\" "+nameLo+" != null \">"+enterKey);
                    sb.append("                AND "+name+"=#{"+nameLo+",jdbcType="+sqlType+"}"+enterKey);
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
            sb.append("        WHERE "+idName+" = #{"+idNameLo+",jdbcType="+idSqlType+"}"+enterKey);
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
                if(sqlType.equals("TEXT")){
                    sqlType = "VARCHAR";
                }
                String name = column.get("name");
                String nameLo = StringUtils.getNameLo(name);
                if(sqlType.equals("VARCHAR")){
                    sb.append("            <if test=\" "+nameLo+" != null and "+nameLo+" != '' \">"+enterKey);
                    sb.append("                AND "+name+"=#{"+nameLo+",jdbcType="+sqlType+"}"+enterKey);
                    sb.append("            </if>"+enterKey);
                } else {
                    sb.append("            <if test=\" "+nameLo+" != null \">"+enterKey);
                    sb.append("                AND "+name+"=#{"+nameLo+",jdbcType="+sqlType+"}"+enterKey);
                    sb.append("            </if>"+enterKey);
                }
            }
            sb.append("        </where>"+enterKey);
            sb.append("    </select>"+enterKey+enterKey);
            // List<Object> listSelective(Object object);
            sb.append("    <select id=\"listSelective\" parameterType=\""+Config.ENTITY_PACKAGE+"."+tableNameUp+"\" resultMap=\"BaseResultMap\">"+enterKey);
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
                if(sqlType.equals("TEXT")){
                    sqlType = "VARCHAR";
                }
                String name = column.get("name");
                String nameLo = StringUtils.getNameLo(name);
                if(sqlType.equals("VARCHAR")){
                    sb.append("            <if test=\" "+nameLo+" != null and "+nameLo+" != '' \">"+enterKey);
                    sb.append("                AND "+name+"=#{"+nameLo+",jdbcType="+sqlType+"}"+enterKey);
                    sb.append("            </if>"+enterKey);
                } else {
                    sb.append("            <if test=\" "+nameLo+" != null \">"+enterKey);
                    sb.append("                AND "+name+"=#{"+nameLo+",jdbcType="+sqlType+"}"+enterKey);
                    sb.append("            </if>"+enterKey);
                }
            }
            sb.append("        </where>"+enterKey);
            sb.append("    </select>"+enterKey+enterKey);
            // int save(Object object);
            sb.append("    <insert id=\"save\" parameterType=\""+Config.ENTITY_PACKAGE+"."+tableNameUp+"\" keyProperty=\""+idNameLo+"\" useGeneratedKeys=\"true\">"+enterKey);
            sb.append("        INSERT INTO "+tableName+"(");
            for (Map<String, String> column : tableObj){
                sb.append(enterKey+"            "+column.get("name")+",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(enterKey);
            sb.append("        ) VALUES (");
            for (Map<String, String> column : tableObj){
                String sqlType = StringUtils.getSQLType(column.get("type"));
                if(sqlType.equals("TEXT")){
                    sqlType = "VARCHAR";
                }
                sb.append(enterKey+"            #{"+StringUtils.getNameLo(column.get("name"))+",jdbcType="+sqlType+"},");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(enterKey);
            sb.append("        )"+enterKey);
            sb.append("    </insert>"+enterKey+enterKey);
            // int saveSelective(Object object);
            sb.append("    <insert id=\"saveSelective\" parameterType=\""+Config.ENTITY_PACKAGE+"."+tableNameUp+"\" keyProperty=\""+idNameLo+"\" useGeneratedKeys=\"true\">"+enterKey);
            sb.append("        INSERT INTO "+tableName+enterKey);
            sb.append("            <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >"+enterKey);
            for (Map<String, String> column : tableObj){
                String sqlType = StringUtils.getSQLType(column.get("type"));
                if(sqlType.equals("TEXT")){
                    sqlType = "VARCHAR";
                }
                String name = column.get("name");
                String nameLo = StringUtils.getNameLo(name);
                if(sqlType.equals("VARCHAR")){
                    sb.append("                <if test=\" "+nameLo+" != null and "+nameLo+" != '' \">"+enterKey);
                    sb.append("                    "+name+","+enterKey);
                    sb.append("                </if>"+enterKey);
                } else {
                    sb.append("                <if test=\" "+nameLo+" != null \">"+enterKey);
                    sb.append("                    "+name+","+enterKey);
                    sb.append("                </if>"+enterKey);
                }
            }
            sb.append("            </trim>"+enterKey);
            sb.append("            <trim prefix=\" VALUES (\" suffix=\")\" suffixOverrides=\",\" >"+enterKey);
            for (Map<String, String> column : tableObj){
                String sqlType = StringUtils.getSQLType(column.get("type"));
                if(sqlType.equals("TEXT")){
                    sqlType = "VARCHAR";
                }
                String name = column.get("name");
                String nameLo = StringUtils.getNameLo(name);
                if(sqlType.equals("VARCHAR")){
                    sb.append("                <if test=\" "+nameLo+" != null and "+nameLo+" != '' \">"+enterKey);
                    sb.append("                    #{"+nameLo+",jdbcType="+sqlType+"},"+enterKey);
                    sb.append("                </if>"+enterKey);
                } else {
                    sb.append("                <if test=\" "+nameLo+" != null \">"+enterKey);
                    sb.append("                    #{"+nameLo+",jdbcType="+sqlType+"},"+enterKey);
                    sb.append("                </if>"+enterKey);
                }
            }
            sb.append("            </trim>"+enterKey);
            sb.append("    </insert>"+enterKey+enterKey);
            // int update(Object object);
            sb.append("    <update id=\"update\" parameterType=\""+Config.ENTITY_PACKAGE+"."+tableNameUp+"\">"+enterKey);
            sb.append("        UPDATE "+tableName+" SET");
            for (Map<String, String> column : tableObj){
                String sqlType = StringUtils.getSQLType(column.get("type"));
                if(sqlType.equals("TEXT")){
                    sqlType = "VARCHAR";
                }
                String name = column.get("name");
                String nameLo = StringUtils.getNameLo(name);
                if(!name.equals(idName)){
                    sb.append(enterKey+"            "+name+" = #{"+nameLo+",jdbcType="+sqlType+"},");
                }
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(enterKey+"        WHERE "+idName+" = #{"+idNameLo+",jdbcType="+idSqlType+"}"+enterKey);
            sb.append("    </update>"+enterKey+enterKey);
            // int updateSelective(Object object);
            sb.append("    <update id=\"updateSelective\" parameterType=\""+Config.ENTITY_PACKAGE+"."+tableNameUp+"\">"+enterKey);
            sb.append("        UPDATE "+tableName+enterKey);
            sb.append("            <set>"+enterKey);
            for (Map<String, String> column : tableObj){
                String sqlType = StringUtils.getSQLType(column.get("type"));
                if(sqlType.equals("TEXT")){
                    sqlType = "VARCHAR";
                }
                String name = column.get("name");
                String nameLo = StringUtils.getNameLo(name);
                if(!name.equals(idName)){
                    if(sqlType.equals("VARCHAR")){
                        sb.append("                <if test=\" "+nameLo+" != null and "+nameLo+" != '' \">"+enterKey);
                        sb.append("                    "+name+" = #{"+nameLo+",jdbcType="+sqlType+"},"+enterKey);
                        sb.append("                </if>"+enterKey);
                    } else {
                        sb.append("                <if test=\" "+nameLo+" != null \">"+enterKey);
                        sb.append("                    "+name+" = #{"+nameLo+",jdbcType="+sqlType+"},"+enterKey);
                        sb.append("                </if>"+enterKey);
                    }

                }
            }
            sb.append("            </set>"+enterKey);
            sb.append("        WHERE "+idName+" = #{"+idNameLo+",jdbcType="+idSqlType+"}"+enterKey);
            sb.append("    </update>"+enterKey+enterKey);
            // int removeSign(Integer id);
            sb.append("    <update id=\"removeSign\" parameterType=\"java.lang."+idJavaType+"\">"+enterKey);
            sb.append("        UPDATE "+tableName+" SET time_delete = (SELECT unix_timestamp() * 1000) WHERE "+idName+" = #{"+idNameLo+",jdbcType="+idSqlType+"}"+enterKey);
            sb.append("    </update>"+enterKey+enterKey);
            // int removeReal(Integer id);
            sb.append("    <delete id=\"removeReal\" parameterType=\"java.lang."+idJavaType+"\">"+enterKey);
            sb.append("        DELETE FROM "+tableName+" WHERE "+idName+" = #{"+idNameLo+",jdbcType="+idSqlType+"}"+enterKey);
            sb.append("    </delete>"+enterKey);
            sb.append("</mapper>");
            //end content
            FileUtils.writeJavaFile(sb.toString(), tableNameUp + "Mapper.xml", Config.XML_ADDRESS);
        }
    }
}
