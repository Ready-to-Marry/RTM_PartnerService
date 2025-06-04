package ready_to_marry.partnerservice.Reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ready_to_marry.partnerservice.Reservation.dto.ContractRequestDto;
import ready_to_marry.partnerservice.Reservation.dto.ContractListResponse;
import ready_to_marry.partnerservice.common.dto.ApiResponse;
import ready_to_marry.partnerservice.common.exception.ErrorCode;
import ready_to_marry.partnerservice.common.exception.payment.BusinessException;

@Component
@RequiredArgsConstructor
public class ReservationClient {

    private final WebClient.Builder webClientBuilder;

    private static final String BASE_URL = "http://reservation-service";

    public ContractListResponse createContract(ContractRequestDto contractRequestDto, Long partnerId) {
        System.out.println("http://reservation-service");
        System.out.println(contractRequestDto.getReservationId());
        System.out.println(contractRequestDto.getAmount());
        System.out.println(partnerId);
        return webClientBuilder.build()
                .post()
                .uri(BASE_URL + "/contracts/request") // 예시 URI, 실제 경로에 맞게 수정
                .header("X-Partner-Id", String.valueOf(partnerId))
                .bodyValue(contractRequestDto)
                .retrieve()
                .onStatus(status -> status.isError(), response ->
                        response.bodyToMono(new ParameterizedTypeReference<ApiResponse<Void>>() {})
                                .flatMap(body -> {
                                    int code = body.getCode();
                                    String message = body.getMessage();

                                    if (code == 1701) {
                                        return Mono.error(new BusinessException(ErrorCode.NOT_FOUND_RESERVATION));
                                    } else if (code == 1703) {
                                        return Mono.error(new BusinessException(ErrorCode.RESERVATION_STATUS_NOT_ANSWERED));
                                    } else if (code == 1704) {
                                        return Mono.error(new BusinessException(ErrorCode.UNAUTHORIZED_RESERVATION_ACCESS));
                                    } else {
                                        return Mono.error(new RuntimeException("Unknown error: " + message));
                                    }
                                })
                )
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<ContractListResponse>>() {})
                .map(ApiResponse::getData)
                .block();
    }
}
