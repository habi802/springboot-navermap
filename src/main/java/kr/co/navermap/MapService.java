package kr.co.navermap;

import kr.co.navermap.model.DirectionResponse;
import kr.co.navermap.model.GeocodeResponse;
import kr.co.navermap.model.LocationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
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

    private final CacheManager cacheManager;

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

    // 10개의 주소를 내가 입력한 주소와 가까운 순으로 정렬하는 메소드
    public List<LocationInfo> sortDistance(String address) {
        // 내가 입력한 주소의 좌표를 구함
        GeocodeResponse myGeocode = getGeocode(address);
        log.info("myGeocode: {}", myGeocode);

        // 내 주소와 locations의 주소 사이의 거리를 구하는 작업
        for (LocationInfo location : locations) {
            GeocodeResponse goalGeocode = getGeocode(location.getAddress());

            String myLatLng = myGeocode.getAddresses().get(0).getX() + "," + myGeocode.getAddresses().get(0).getY();
            String goalLatLng = goalGeocode.getAddresses().get(0).getX() + "," + goalGeocode.getAddresses().get(0).getY();

            DirectionResponse direction = getDirection(myLatLng, goalLatLng);
            location.setDistance(direction.getRoute().getTrafast().get(0).getSummary().getDistance());
        }

        // locations는 final 타입이라 새로운 List를 선언한 뒤,
        // 그 List를 distance가 작은 순으로 정렬함
        List<LocationInfo> result = new ArrayList<>(locations);
        result.sort(Comparator.comparingInt(LocationInfo::getDistance));

        return result;
    }

    // 좌표를 구하는 메소드
    public GeocodeResponse getGeocode(String address) {
        Cache cache = cacheManager.getCache("geocodeCache");

        if (cache != null && cache.get(address) != null) {
            // 캐시에 입력한 주소가 이미 있을 경우,
            // 즉 입력한 주소를 좌표로 변환하는 API를 호출한 적 있을 경우
            // API를 호출하지 않고 캐시에 저장된 결과값을 return
            log.info("geocode 캐시에 있음: {}", cache.get(address, GeocodeResponse.class));
            return cache.get(address, GeocodeResponse.class);
        }

        GeocodeResponse result = geocodeFeignClient.getGeocode(address);
        log.info("geocoding API 호출: {}", result);

        // API를 호출한 뒤 결과값을 캐시에 저장
        if (cache != null) {
            cache.put(address, result);
        }
        return result;
    }

    // 거리를 계산하는 메소드
    public DirectionResponse getDirection(String start, String goal) {
        Cache cache = cacheManager.getCache("directionCache");
        String key = start + '_' + goal;

        if (cache != null && cache.get(key) != null) {
            // 캐시에 입력한 좌표들이 이미 있을 경우,
            // 즉 좌표 사이의 거리를 계산하는 API를 호출한 적 있을 경우
            // API를 호출하지 않고 캐시에 저장된 결과값을 return
            log.info("direction 캐시에 있음: {}", cache.get(key, DirectionResponse.class));
            return cache.get(key, DirectionResponse.class);
        }

        DirectionResponse result = directionFeignClient.getDirection(goal, start, "trafast");
        log.info("direction 5 API 호출: {}", result);

        // API를 호출한 뒤 결과값을 캐시에 저장
        if (cache != null) {
            cache.put(key, result);
        }
        return result;
    }
}
