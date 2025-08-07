package kr.co.navermap;

import kr.co.navermap.model.DirectionResponse;
import kr.co.navermap.model.GeocodeResponse;
import kr.co.navermap.model.LocationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MapService {
    private final GeocodeFeignClient geocodeFeignClient;
    private final DirectionFeignClient directionFeignClient;

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
    public List<LocationInfo> sortDistance(String address) {
        // 내가 입력한 주소의 좌표를 구함
        GeocodeResponse myGeocode = geocodeFeignClient.getGeocode(address);
        log.info("myGeocode: {}", myGeocode);

        // 내 주소와 locations의 주소 사이의 거리를 구하는 작업
        for (LocationInfo location : locations) {
            GeocodeResponse goalGeocode = geocodeFeignClient.getGeocode(location.getAddress());

            String myLatLng = myGeocode.getAddresses().get(0).getX() + "," + myGeocode.getAddresses().get(0).getY();
            String goalLatLng = goalGeocode.getAddresses().get(0).getX() + "," + goalGeocode.getAddresses().get(0).getY();

            DirectionResponse direction = directionFeignClient.getDirection(goalLatLng, myLatLng, "trafast");
            location.setDistance(direction.getRoute().getTrafast().get(0).getSummary().getDistance());
        }

        // locations는 final 타입이라 새로운 List를 선언한 뒤,
        // 그 List를 distance가 작은 순으로 정렬함
        List<LocationInfo> result = new ArrayList<>(locations);
        result.sort(Comparator.comparingInt(LocationInfo::getDistance));

        return result;
    }
}
