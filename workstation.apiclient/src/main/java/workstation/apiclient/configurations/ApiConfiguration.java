package workstation.apiclient.configurations;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
public class ApiConfiguration {
    private ApiServiceConfiguration apiServiceConfig;
    private List<ClientConfiguration> clientConfig;

    public List<ClientConfiguration> getClientConfig() {
        return clientConfig;
    }

    public void setClientConfig(List<ClientConfiguration> clientConfig) {
        this.clientConfig = clientConfig;
    }

    public ApiServiceConfiguration getApiServiceConfig() {
        return apiServiceConfig;
    }

    public void setApiServiceConfig(ApiServiceConfiguration apiServiceConfig) {
        this.apiServiceConfig = apiServiceConfig;
    }
}
