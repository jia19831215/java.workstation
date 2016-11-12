package workstation.core.utils;

import com.sun.istack.internal.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WangXiaoJia on 2016/6/15.
 */
public class DateUtil {

    /**
     * 日期对象格式化
     * @param input 日期对象
     * @param pattern 格式化字符串
     * @return
     */
    public static String format(@NotNull Date input, @NotNull String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(input);
    }

    /**
     * 字符串转化成日期对象
     * @param date 日期字符串
     * @param pattern 格式化字符串
     * @return
     * @throws ParseException
     */
    public static Date parase(@NotNull String date, @NotNull String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.parse(date);
    }
}
