package workstation.apiclient;

/**
 * Created by Administrator on 2016/11/11.
 */
public interface IApiClient {
    String getBaseAddress();
    void setBaseAddress(String baseAddress);

    String getToken();
    void setToken(String token);

    IApiClient init(String baseAddress) throws ApiClientException;
    IApiClient init(String baseAddress, String user, String password, final String tokenUrl) throws ApiClientException;

    <TResponse, TRequest> TResponse invoke(TRequest request, String url, HttpMethodEnum method, String serviceName, boolean isEncrypt);
    <TResponse, TRequest> void asyncInvoke(TRequest request, String url, HttpMethodEnum method, String serviceName, ApiInvokeCallback callback, boolean isEncrypt);
}
