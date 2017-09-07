package cn.chenjy.pmg.mysql.common;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

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



    public static void writeJavaFile(String content, String filename, String fileAddress){
        String javaFileName = fileAddress + filename +".java";
        File javaFile = new File(javaFileName);
        try {
            FileUtils.createDirectory(fileAddress);
            if(!javaFile.exists()){
                javaFile.createNewFile();

            } else {
                FileUtils.deleteFile(javaFileName);
                javaFile.createNewFile();
            }
            FileWriter resultFile = new FileWriter(javaFile);
            PrintWriter myNewFile = new PrintWriter(resultFile);
            myNewFile.println(content);
            resultFile.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
