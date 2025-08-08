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

