package kr.co.navermap;

import kr.co.navermap.model.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/maps")
@RequiredArgsConstructor
public class MapController {
    private final MapService mapService;

    @GetMapping
    public ResultResponse<?> maps() {
        List<String> result = mapService.getDistance();
        return new ResultResponse<List<String>>("", result);
    }
}
