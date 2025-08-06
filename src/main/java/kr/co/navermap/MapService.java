package kr.co.navermap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MapService {
    private String myAddress = "대구 중구 중앙대로 394";

    private List<String> addresses = List.of(
        "대구 중구 명덕로 173",
        "대구 중구 중앙대로61길 33-7",
        "대구 중구 봉산문화2길 42-24",
        "대구 중구 국채보상로142길 40",
        "대구 중구 동성로2길 21-5",
        "대구 중구 삼덕동3가 255-4",
        "대구 중구 공평동 89-1",
        "대구 중구 봉산동 35-95",
        "대구 중구 남산동 938-8",
        "대구 중구 대봉동 25-7"
    );

    public List<String> getDistance() {
        return null;
    }
}
