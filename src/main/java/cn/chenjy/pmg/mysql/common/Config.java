package cn.chenjy.pmg.mysql.common;

/**
 * @author 陈俊羽 chenjyfy@vip.qq.com
 * @Description:
 * @date 2017/9/6 下午5:46
 */
public class Config {
    //database properties
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = "rds3135d68dk69915sz5.mysql.rds.aliyuncs.com";
    public static final String JDBC_DATABASE = "weather_patrol";
    public static final String JDBC_USERNAME = "nbms_chenjy";
    public static final String JDBC_PASSWORD = "Nbms_chenjy";
    public static final String[] TABLE_NAMES = {"company_maintain", "data_ctrl_event", "device_base_info", "device_property"};

    //file properties mac
//    public static final String ENTITY_ADDRESS = "/Users/chenjy/Documents/patrol/src/main/java/com/nbmssoft/patrol/pojo/entity/";
//    public static final String ENTITY_PACKAGE = "com.nbmssoft.patrol.pojo.entity";
//    public static final String DAO_ADDRESS = "/Users/chenjy/Documents/patrol/src/main/java/com/nbmssoft/patrol/dao/";
//    public static final String DAO_PACKAGE = "com.nbmssoft.patrol.dao";
//    public static final String XML_ADDRESS = "/Users/chenjy/Documents/patrol/src/main/resources/mapper/";

    //file properties mac
    public static final String ENTITY_ADDRESS = "C:/Users/peach/code/patrol/pojo/entity/";
    public static final String ENTITY_PACKAGE = "com.nbmssoft.patrol.pojo.entity";
    public static final String DAO_ADDRESS = "C:/Users/peach/code/patrol/dao/";
    public static final String DAO_PACKAGE = "com.nbmssoft.patrol.dao";
    public static final String XML_ADDRESS = "C:/Users/peach/code/patrol/mapper/";

    //String properties
    public static final String ENTER_KEY = "\r";
}
