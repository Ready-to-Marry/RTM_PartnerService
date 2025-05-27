package ready_to_marry.partnerservice.common.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestTemplateConfig {

    // eureka 연동용
    @Bean
    @LoadBalanced  // Eureka 서비스명 사용을 위한 설정
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}