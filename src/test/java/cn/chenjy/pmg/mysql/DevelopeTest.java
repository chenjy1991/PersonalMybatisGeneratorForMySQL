package cn.chenjy.pmg.mysql;

import java.io.File;

/**
 * @author 陈俊羽 chenjyfy@vip.qq.com
 * @Description:
 * @date 2017/9/7 上午8:43
 */
public class DevelopeTest {

    public static void main(String[] args){
//        System.out.println(System.getProperties().getProperty("os.name").toString().toLowerCase());
//        System.out.println("fhuds_afhlkjas".contains("_"));
//        System.out.println("_".contains("sdfhalsk_sdkjfhalks"));
            File file = new File("C:/Users/peach/code/patrol");
            if(!file.exists()){
                file.mkdir();
            }
    }
}
