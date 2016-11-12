package workstation.apiclient;

import workstation.core.exceptions.SystemException;

/**
 * Created by Administrator on 2016/11/11.
 */
public class ApiClientException extends SystemException {

    public ApiClientException(String msg) {
        super(msg);
    }

    public ApiClientException(String msg, Throwable ce) {
        super(msg, ce);
    }
}
