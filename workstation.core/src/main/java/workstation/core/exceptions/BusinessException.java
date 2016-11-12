package workstation.core.exceptions;

/**
 * Created by Administrator on 2016/11/10.
 */
public class BusinessException extends BaseException {
    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable inner) {
        super(msg, inner);
    }
}
