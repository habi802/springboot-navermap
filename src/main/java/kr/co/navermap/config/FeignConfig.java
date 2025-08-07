package kr.co.navermap.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Value("${constants.naver-map.app-key}")
    private String appKey;

    @Value("${constants.naver-map.secret-key}")
    private String secretKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("X-NCP-APIGW-API-KEY-ID", appKey);
            template.header("X-NCP-APIGW-API-KEY", secretKey);
        };
    }
}
