package workstation.apiclient.annotations;

import workstation.apiclient.HttpMethodEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2016/11/11.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestDto {
    public String Url();

    public HttpMethodEnum Method();

    public String ClientType();

    public String ServiceName();
}
