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

    // feign client 요청할 때마다 실행됨
    @Bean
    public RequestInterceptor requestInterceptor() {
        // 동적으로 헤더를 설정함
        return template -> {
            template.header("X-NCP-APIGW-API-KEY-ID", appKey);
            template.header("X-NCP-APIGW-API-KEY", secretKey);
        };
    }
}
