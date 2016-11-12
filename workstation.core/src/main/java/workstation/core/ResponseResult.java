package workstation.core;

import workstation.core.utils.StringUtil;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by Administrator on 2016/11/10.
 */
public class ResponseResult<T> implements Serializable {

    private T result;
    private String message;
    private String systemError;
    private String businessError;
    private Hashtable<String, Object> extensions;

    public Hashtable<String, Object> getExtensions() {
        return extensions;
    }

    public void setExtensions(Hashtable<String, Object> extensions) {
        this.extensions = extensions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getSystemError() {
        return systemError;
    }

    public void setSystemError(String systemError) {
        this.systemError = systemError;
    }

    public String getBusinessError() {
        return businessError;
    }

    public void setBusinessError(String businessError) {
        this.businessError = businessError;
    }

    /**
     * 是否执行成功
     *
     * @return
     */
    public boolean isSuccess() {
        if (this.systemError == null && this.businessError == null) {
            return true;
        } else {
            return false;
        }
    }

    private ResponseResult() {
        super();

        this.extensions = new Hashtable<String, Object>();
    }

    public static <T> ResponseResult success(T res, String msg) {
        ResponseResult<T> result = new ResponseResult<T>();

        result.setMessage(msg);
        result.setResult(res);

        return result;
    }

    public static <T> ResponseResult systemError(Throwable e, String msg) {
        ResponseResult<T> result = new ResponseResult<T>();

        result.setMessage(msg);
        result.setSystemError(StringUtil.getStackTrace(e));

        return result;
    }

    public static <T> ResponseResult systemError(String error, String msg) {
        ResponseResult<T> result = new ResponseResult<T>();

        result.setMessage(msg);
        result.setSystemError(error);

        return result;
    }

    public static <T> ResponseResult businessError(Throwable e, String msg) {
        ResponseResult<T> result = new ResponseResult<T>();

        result.setMessage(msg);
        result.setBusinessError(StringUtil.getStackTrace(e));

        return result;
    }

    public static <T> ResponseResult businessError(String error, String msg) {
        ResponseResult<T> result = new ResponseResult<T>();

        result.setMessage(msg);
        result.setBusinessError(error);

        return result;
    }
}
