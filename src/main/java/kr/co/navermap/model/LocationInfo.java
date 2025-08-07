package kr.co.navermap.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationInfo {
    private String name;
    private String address;
    private int price;
    private int distance;

    public LocationInfo(String name, String address, int price) {
        this.name = name;
        this.address = address;
        this.price = price;
    }
}
