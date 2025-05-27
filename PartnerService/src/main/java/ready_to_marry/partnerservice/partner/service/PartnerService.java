package ready_to_marry.partnerservice.partner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ready_to_marry.partnerservice.common.dto.ApiResponse;
import ready_to_marry.partnerservice.partner.dto.ItemDTO;
import ready_to_marry.partnerservice.partner.dto.ItemDetailResponse;
import ready_to_marry.partnerservice.partner.dto.ItemListResponse;


@Service
@RequiredArgsConstructor
public class PartnerService {

    private final WebClient.Builder webClientBuilder;

    public ItemListResponse listByPartner(Long partnerId, int page, int size) {
        ApiResponse<ItemListResponse> apiResponse = webClientBuilder.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("catalog-service")
                        .path("/items/partners")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .header("X-Partner-Id", String.valueOf(partnerId))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<ItemListResponse>>() {})
                .block();

        return apiResponse != null ? apiResponse.getData() : null;
    }

    public ItemDetailResponse getItemDetail(Long itemId) {
        ApiResponse<ItemDetailResponse> response = webClientBuilder.build()
                .get()
                .uri("http://catalog-service/items/partners/{itemId}", itemId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<ItemDetailResponse>>() {})
                .block();

        return response != null ? response.getData() : null;
    }
}