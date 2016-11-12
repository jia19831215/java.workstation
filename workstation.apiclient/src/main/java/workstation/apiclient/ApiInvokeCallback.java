package workstation.apiclient;

/**
 * Created by Administrator on 2016/11/11.
 */
public interface ApiInvokeCallback<TResponse> {
    void process(TResponse response);
}

