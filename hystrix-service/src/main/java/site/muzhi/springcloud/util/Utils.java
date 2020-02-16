package site.muzhi.springcloud.util;

import java.util.List;

/**
 * @author: LiChuang
 * @date: 2020/02/15
 * @description:
 */
public class Utils {
    public static String join(List list, String tag) {
        StringBuilder sb = new StringBuilder();
        list.forEach(item -> sb.append(item).append(tag));
        String result = sb.toString();
        return result.substring(0, result.length() - 2);
    }
}
