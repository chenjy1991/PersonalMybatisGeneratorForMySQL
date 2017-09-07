package cn.chenjy.pmg.mysql.common;

/**
 * @author 陈俊羽 chenjyfy@vip.qq.com
 * @Description:
 * @date 2017/9/7 上午8:34
 */
public class StringUtils {

    public static final char UNDERLINE = '_';

    /**
     * "_"命名改为首字母小写的驼峰命名
     * @param tableName
     * @return
     */
    public static String getNameLo(String tableName){
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

    /**
     * "_"命名改为首字母大写的驼峰命名
     * @param tableName
     * @return
     */
    public static String getNameUp(String tableName) {
        String tableStr = getNameLo(tableName);
        tableStr = tableStr.substring(0, 1).toUpperCase() + tableStr.substring(1);
        return tableStr;
    }

    /**
     * 数据库类型转java类型
     * @param sqlType
     * @return
     */
    public static String getJavaType(String sqlType) {
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

    /**
     * 根据操作系统获取换行符
     * @return
     */
    public static String getEnterKey(){
        String enterKey = "";
        String osname = System.getProperties().getProperty("os.name").toLowerCase();
        if(osname.contains("mac")){
            enterKey = "\r";
        } else if (osname.contains("linux")){
            enterKey = "\n";
        } else if(osname.contains("windows")){
            enterKey = "\r\n";
        }
        return enterKey;
    }
}
