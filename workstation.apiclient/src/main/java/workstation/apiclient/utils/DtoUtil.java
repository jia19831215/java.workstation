package workstation.apiclient.utils;

import workstation.apiclient.annotations.RequestDto;

import java.util.Hashtable;

/**
 * Created by Administrator on 2016/11/11.
 */
public class DtoUtil {

    private static Hashtable<Class, RequestDto> CACHE = new Hashtable<Class, RequestDto>();
    private static Object LOCK = new Object();

    public static RequestDto getRequestDtoAnnotation(Object dto) {

        Class clazz = dto.getClass();

        if (!CACHE.containsKey(clazz)) {
            synchronized (LOCK) {
                if (!CACHE.containsKey(clazz)) {
                    RequestDto annotation = (RequestDto) clazz.getAnnotation(RequestDto.class);

                    if (annotation != null)
                        CACHE.put(clazz, annotation);
                }
            }
        }

        return CACHE.get(clazz);
    }
}
