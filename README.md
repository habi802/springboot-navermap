### 네이버 지도의 Geocoding, Direction 5 API를 이용해 10개의 장소 중 내 주소와 가까운 순으로 정렬하는 코드
- OpenFeign을 이용하여 네이버 지도의 Geocoding, Direction 5 API를 호출함
- build.gradle에 maven openfeign을 검색해서 나온 걸 복붙함

```groovy
implementation 'org.springframework.cloud:spring-cloud-starter-openfeign:4.3.0'
```

- application.yml
```yaml
spring:
  application:
    name: navermap
  datasource:
    url: jdbc:mariadb://localhost/gallery
    username: root
    password: green502
  config:
    import: optional:classpath:application-secret.yml
```

- appication-secret.yml
```yaml
constants:
  naver-map:
    geocode-url: https://maps.apigw.ntruss.com/map-geocode/v2/geocode
    direction-url: https://maps.apigw.ntruss.com/map-direction/v1/driving
    app-key: # 네이버 지도 API 키
    secret-key: # 네이버 지도 API 시크릿 키
    data-type: json
```

- 메인 application에 @EnableFeignClients 추가해야 함
```java
@EnableFeignClients
@SpringBootApplication
public class NavermapApplication {

    public static void main(String[] args) {
        SpringApplication.run(NavermapApplication.class, args);
    }

}
```

- API 호출 후 결과값을 캐시에 저장하여 똑같은 입력값이 들어간 API를 또 한 번 호출하려고 할 때,  
  API를 호출하지 않고 캐시에 저장된 결과값을 불러와 return 하게 함
- build.gradle에 maven caffeine 검색해서 나온 걸 복붙함
- spring-boot-starter-cache를 가져와야 캐시 관련 애노테이션을 쓸 수 있으나  
  이 코드에서는 사용하지 않았고, 직접 캐시에 결과값을 집어넣고, 꺼내게 하였음
```groovy
implementation 'com.github.ben-manes.caffeine:caffeine:3.2.2'
implementation 'org.springframework.boot:spring-boot-starter-cache'
```

- config/CacheConfig 클래스 생성
```java
@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        // 캐시 최대 보관 기간: 1일
        // 캐시 최대 보관 개수: 1,000개
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("geocodeCache", "directionCache");
        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.DAYS)
                        .maximumSize(1_000)
        );
        return cacheManager;
    }
}
```

