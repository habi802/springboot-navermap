package kr.co.navermap;

import kr.co.navermap.config.FeignConfig;
import kr.co.navermap.model.GeocodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "geocode",
    url = "${constants.naver-map.geocode-url}",
    configuration = FeignConfig.class
)
public interface GeocodeFeignClient {
    @GetMapping(produces = "application/json")
    GeocodeResponse getGeocode(@RequestParam("query") String address);
}
