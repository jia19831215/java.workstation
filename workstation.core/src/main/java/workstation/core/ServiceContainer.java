package workstation.core;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ServiceContainer 
 */
public class ServiceContainer {
    private static ConcurrentHashMap<String, Object> CACHE = new ConcurrentHashMap<String, Object>();

    public static <T> T resolve(String key, Class<T> interfaceClass) {
        return (T) CACHE.get(key);
    }

    public static <T> T resolve(Class<T> interfaceClass) {
        return (T) CACHE.get(interfaceClass.getName());
    }

    public static <T> void registerInstance(Class<T> interfaceClass, Object service) {
        CACHE.put(interfaceClass.getName(), service);
    }

    public static void registerInstance(String key, Object service) {
        CACHE.put(key, service);
    }
}
