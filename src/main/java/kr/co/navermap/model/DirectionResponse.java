package kr.co.navermap.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class DirectionResponse {
    // 멤버 필드는 Direction 5 API를 호출했을 때 나오는 결과값에 맞춰서 만들어야 한다.
    // 그렇지 않으면 모두 null 값으로 옴
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
