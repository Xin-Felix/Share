package work.huangxin.share.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.UUID;

public class Constants {

    public static final String CLAIMS = "claims";
    /**
     * 删除
     */
    public static final Integer IS_DEL = 1;
    /**
     * 未删除
     */
    public static final Integer IS_NOT_DEL = 0;

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取id，格式：时间yyyyMMdd+六位时间戳
     *
     * @return
     */
    public static String getID() {
        String date = DateFormatUtils.format(new Date(), "yyyyMMdd");
        String timeInMillis = String.valueOf(System.currentTimeMillis()).substring(6);
        return date + timeInMillis;
    }

}
