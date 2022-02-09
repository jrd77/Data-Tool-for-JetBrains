package top.devinstall.sql.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhen.wang
 * @description
 * @date 2022/2/8 14:22
 */
public class SingleTonUtil {

    private static Map<String, Object> map = new ConcurrentHashMap<>();

    private SingleTonUtil() {

    }

    public static <T> T get(Class<T> tClass) {

        return (T) map.get(tClass.getName());
    }

    public static <T> void put(T t) {

        String className = t.getClass().getName();
        map.put(className, t);
    }

}
