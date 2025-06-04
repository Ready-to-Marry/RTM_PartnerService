package ready_to_marry.partnerservice.notification.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ready_to_marry.partnerservice.notification.dto.NotificationRequestDto;

@Service
public class NotificationService {
    private final WebClient webClient;

    public NotificationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://zbfu2a37ol.execute-api.ap-northeast-2.amazonaws.com").build();
    }

    public void sendNotification(NotificationRequestDto requestDto) {
        webClient.post()
                .uri("/send-notification")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestDto)
                .retrieve()
                .onStatus(status -> status.isError(), response ->
                        response.bodyToMono(String.class).flatMap(errorBody ->
                                Mono.error(new RuntimeException("Notification API 오류: " + errorBody))
                        )
                )
                .bodyToMono(Void.class)
                .block();
    }
}
