package com.heihei.heiapiclientsdk;

import com.heihei.heiapiclientsdk.client.HeiApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("heiapi.client")
@Data
@ComponentScan
public class HeiApiClientConfig {
    private String accessKey;

    private String secretKey;

    @Bean
    public HeiApiClient heiApiClient() {
        return new HeiApiClient(accessKey, secretKey);
    }


}
