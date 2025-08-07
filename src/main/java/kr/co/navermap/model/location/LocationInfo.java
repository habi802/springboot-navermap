package kr.co.navermap.model.location;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationInfo {
    private String name;
    private String address;
    private int price;
}
