package kr.co.navermap;

import kr.co.navermap.model.location.LatLng;
import kr.co.navermap.model.location.LocationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MapService {
    @Value("${constants.naver-map.app-key}")
    private String appKey;

    @Value("${constants.naver-map.secret-key}")
    private String secretKey;

    private final List<LocationInfo> locations = List.of(
        new LocationInfo("세연콩국", "대구 중구 명덕로 173", 15000),
        new LocationInfo("한식예찬안동찜닭", "대구 중구 중앙대로61길 33-7", 20000),
        new LocationInfo("에이타운피자", "대구 중구 봉산문화2길 42-24", 17000),
        new LocationInfo("나우이피자", "대구 중구 국채보상로142길 40", 18000),
        new LocationInfo("도우스탠드", "대구 중구 동성로2길 21-5", 25000),
        new LocationInfo("교촌치킨", "대구 중구 삼덕동3가 255-4", 19000),
        new LocationInfo("여우굴", "대구 중구 공평동 89-1", 9000),
        new LocationInfo("에이바이트키친", "대구 중구 봉산동 35-95", 12000),
        new LocationInfo("댄하우스", "대구 중구 남산동 938-8", 14000),
        new LocationInfo("크라운감자탕", "대구 중구 대봉동 25-7", 16000)
    );

    // 주소 정렬하는 메소드
    public List<LocationInfo> sortDistance(String myAddress) {
        return null;
    }
}
