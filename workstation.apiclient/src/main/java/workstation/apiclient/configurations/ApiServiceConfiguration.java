package workstation.apiclient.configurations;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
public class ApiServiceConfiguration {
    private List<ServiceConfiguration> services;
    private String baseAddress;

    public List<ServiceConfiguration> getServices() {
        return services;
    }

    public void setServices(List<ServiceConfiguration> services) {
        this.services = services;
    }

    public String getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress;
    }
}
