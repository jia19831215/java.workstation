package workstation.core;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/11/11.
 */
public class ApplicationContext {
    private static ConcurrentHashMap CONFIG = new ConcurrentHashMap();
    private static ApplicationContext CONTEXT;

    public static <T> T getValue(String key, Class<T> clazz) {
        return (T) CONFIG.get(key);
    }

    public static void setValue(String key, Object value) {
        CONFIG.put(key, value);
    }
}
