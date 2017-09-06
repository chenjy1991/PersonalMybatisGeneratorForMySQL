package cn.chenjy.pmg.mysql.common;

import java.io.File;

/**
 * @author 陈俊羽 chenjyfy@vip.qq.com
 * @Description:
 * @date 2017/9/6 下午5:43
 */
public class FileUtils {
    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    public static void createDirectory(String path) {
        String dir = "/";
        String[] paths = path.split("/");
        for (String str : paths) {
            dir = dir + str + "/";
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }
}
