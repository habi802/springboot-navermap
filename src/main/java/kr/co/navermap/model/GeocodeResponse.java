package kr.co.navermap.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class GeocodeResponse {
    private String status;
    private List<Address> addresses;
    private String errorMesasge;

    @Data
    public static class Address {
        private String x;
        private String y;
    }
}
