package workstation.apiclient.configurations;

/**
 * Created by Administrator on 2016/11/11.
 */
public class ClientConfiguration {
    private String clientType;
    private String tokenUrl;

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }
}

