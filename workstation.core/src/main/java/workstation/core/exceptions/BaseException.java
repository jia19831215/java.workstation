package workstation.core.exceptions;

/**
 * Created by Administrator on 2016/11/10.
 */
public abstract class BaseException extends RuntimeException {

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, Throwable inner) {
        super(msg, inner);
    }
}
