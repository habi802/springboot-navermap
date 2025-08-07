package kr.co.navermap;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "geocode", url = "${constants.naver-map.geocode-url}")
public class GeocodeFeignClient {
}
