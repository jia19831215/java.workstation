package workstation.core.exceptions;

/**
 * Created by Administrator on 2016/11/10.
 */
public class SystemException extends BaseException {
    public SystemException(String msg) {
        super(msg);
    }

    public SystemException(String msg, Throwable inner) {
        super(msg, inner);
    }
}
