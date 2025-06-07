package ready_to_marry.partnerservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ready_to_marry.partnerservice.common.dto.ApiResponse;
import ready_to_marry.partnerservice.common.exception.ErrorCode;
import ready_to_marry.partnerservice.common.exception.payment.BusinessException;

@Component
@RequiredArgsConstructor
public class UserClient {

    private final WebClient.Builder webClientBuilder;

    private static final String BASE_URL = "http://user-service";

    public String getFcmToken(Long userId) {
        return webClientBuilder.build()
                .get()
                .uri(BASE_URL + "/internal/user-fcm-tokens")
                .header("X-User-Id", String.valueOf(userId))
                .retrieve()
                .onStatus(status -> status.isError(), response ->
                        response.bodyToMono(new ParameterizedTypeReference<ApiResponse<Void>>() {})
                                .flatMap(body -> {
                                    int code = body.getCode();
                                    String message = body.getMessage();

                                    if (code == 1504) {
                                        return Mono.error(new BusinessException(ErrorCode.USER_NOT_FOUND));
                                    } else {
                                        return Mono.error(new RuntimeException("Unknown error: " + message));
                                    }
                                })
                )
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<String>>() {})
                .map(ApiResponse::getData)
                .block();
    }

}
