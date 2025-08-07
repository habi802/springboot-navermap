package kr.co.navermap;

import kr.co.navermap.model.DirectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "direction", url = "${constants.naver-map.direction-url}")
public interface DirectionFeignClient {
    @GetMapping
    DirectionResponse getDirection(@RequestParam String goal,
                                   @RequestParam String start,
                                   @RequestParam String option);
}
