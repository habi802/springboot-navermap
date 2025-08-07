package kr.co.navermap.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class DirectionResponse {
    private Integer code;
    private String message;
    private Route route;

    @Data
    public static class Route {
        private List<Trafast> trafast;
    }

    @Data
    public static class Trafast {
        private Summary summary;
    }

    @Data
    public static class Summary {
        private int distance;
    }
}
