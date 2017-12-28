package cn.chenjy.pmg.mysql;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 陈俊羽 chenjyfy@vip.qq.com
 * @Description:
 * @date 2017/9/7 上午8:43
 */
public class DevelopeTest {

    public static void main(String[] args){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int mins = cal.get(Calendar.MINUTE);
        int m = mins / 10;
        cal.set(Calendar.MINUTE, m * 10);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        date = new Date(date.getTime() - 600000 * 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String picTime = sdf.format(date.getTime() - 3600000 * 8 );
        StringBuffer radarPath = new StringBuffer();
        String mainPath = "http://172.21.129.72/Data/radpt/radar/href/MOSAICHREF000.";
        radarPath.append(mainPath);
        radarPath.append(picTime.substring(0, 8)).append(".").append(picTime.substring(8)).append("00.gif");
        System.out.print(radarPath);
    }
}
