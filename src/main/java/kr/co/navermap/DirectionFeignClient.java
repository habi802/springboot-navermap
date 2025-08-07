package kr.co.navermap;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "direction", url = "${constants.naver-map.direction-url}")
public class DirectionFeignClient {
}
