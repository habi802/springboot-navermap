package kr.co.navermap.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class GeocodeResponse {
    // 멤버 필드는 Geocoding API를 호출했을 때 나오는 결과값에 맞춰서 만들어야 한다.
    // 그렇지 않으면 모두 null 값으로 옴
    private String status;
    private List<Address> addresses;
    private String errorMesasge;

    @Data
    public static class Address {
        private String x;
        private String y;
    }
}
