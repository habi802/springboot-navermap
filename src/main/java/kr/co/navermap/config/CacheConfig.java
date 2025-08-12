package kr.co.navermap.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//
//        CaffeineCache geocodeCache = new CaffeineCache("geocodeCache",
//                Caffeine.newBuilder()
//                        .expireAfterWrite(1, TimeUnit.DAYS)
//                        .maximumSize(1000)
//                        .build()
//        );
//
//        CaffeineCache directionCache = new CaffeineCache("directionCache",
//                Caffeine.newBuilder()
//                        .expireAfterWrite(1, TimeUnit.DAYS)
//                        .maximumSize(1000)
//                        .build()
//        );
//        cacheManager.setCaches(Arrays.asList(geocodeCache, directionCache));

        // 캐시 최대 보관 기간: 1일
        // 캐시 최대 보관 개수: 1,000개
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("geocodeCache", "directionCache");
        cacheManager.setCaffeine(
            Caffeine.newBuilder()
                    .expireAfterWrite(1, TimeUnit.DAYS)
                    .maximumSize(1_000)
        );
        return cacheManager;
    }
}
