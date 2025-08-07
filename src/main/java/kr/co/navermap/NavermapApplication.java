package kr.co.navermap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class NavermapApplication {

    public static void main(String[] args) {
        SpringApplication.run(NavermapApplication.class, args);
    }

}
