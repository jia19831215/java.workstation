package workstation.apiclient;

import workstation.apiclient.annotations.RequestDto;
import workstation.apiclient.configurations.ApiConfiguration;
import workstation.apiclient.configurations.ClientConfiguration;
import workstation.apiclient.utils.DtoUtil;
import workstation.core.ApplicationContext;
import workstation.core.ServiceContainer;

import java.util.Hashtable;

/**
 * Created by Administrator on 2016/11/11.
 */
public class ApiInvoker {
    public static ApiConfiguration CONFIG;
    public static Hashtable<String, IApiClient> CLIENTS = new Hashtable<String, IApiClient>();

    private static Object LOCK = new Object();

    private static IApiClient getClient(String clientType) {
        if (!CLIENTS.containsKey(clientType)) {
            synchronized (LOCK) {
                if (!CLIENTS.containsKey(clientType)) {
                    String tokenUrl = null;

                    for (ClientConfiguration clientConfig : CONFIG.getClientConfig()) {
                        if (clientConfig.getClientType().equals(clientType)) {
                            tokenUrl = clientConfig.getTokenUrl();
                            break;
                        }
                    }

                    CLIENTS.put(clientType, ServiceContainer.resolve(clientType, IApiClient.class).init(CONFIG.getApiServiceConfig().getBaseAddress(), ApplicationContext.getValue(ApiClientConstant.API_USER_ID, String.class), ApplicationContext.getValue(ApiClientConstant.API_PASSWORD, String.class), tokenUrl));
                }
            }
        }

        return CLIENTS.get(clientType);
    }

    public static <TRequest, TResponse> TResponse invokeService(TRequest request, boolean isEncrypt, String serviceName) {
        RequestDto annotation = DtoUtil.getRequestDtoAnnotation(request);

        if (annotation != null) {
            IApiClient client = getClient(annotation.ClientType());

            return client.invoke(request, annotation.Url().toUpperCase().startsWith("HTTP") ? annotation.Url() : String.format("%s%s", CONFIG.getApiServiceConfig().getBaseAddress(), annotation.Url()), annotation.Method(), annotation.ServiceName(), isEncrypt);
        } else {
            // TODO：配置文件方式调用
            return null;
        }
    }

    public static <TRequest, TResponse> void asyncInvokeService(TRequest request, boolean isEncrypt, String serviceName, ApiInvokeCallback<TResponse> callback){
        RequestDto annotation = DtoUtil.getRequestDtoAnnotation(request);

        if (annotation != null) {
            IApiClient client = getClient(annotation.ClientType());

            client.asyncInvoke(request, annotation.Url().toUpperCase().startsWith("HTTP") ? annotation.Url() : String.format("%s%s", CONFIG.getApiServiceConfig().getBaseAddress(), annotation.Url()), annotation.Method(), annotation.ServiceName(), callback, isEncrypt);
        } else {
            // TODO：配置文件方式调用
        }
    }
}
