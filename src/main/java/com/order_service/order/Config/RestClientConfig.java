package com.order_service.order.Config;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.order_service.order.Client.InventoryClient;

@Configuration
public class RestClientConfig {

    @Value("${inventory.service.url}")
    private String inventoryUrl;
    
    @Bean
    public InventoryClient inventoryClient(){

        RestClient restClient = RestClient.builder()
                                        .baseUrl(inventoryUrl)
                                        .requestFactory(getClientRequestFactory())
                                        .build();
        
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }

    private ClientHttpRequestFactory getClientRequestFactory() {

    RequestConfig config = RequestConfig.custom()
            .setConnectTimeout(Timeout.ofSeconds(3))
            .setResponseTimeout(Timeout.ofSeconds(3))
            .build();

    CloseableHttpClient client = HttpClients.custom()
            .setDefaultRequestConfig(config)
            .build();

    return new HttpComponentsClientHttpRequestFactory(client);
}   
}
