package ready_to_marry.partnerservice.partner.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDetailResponse {
    private Long itemId;
    private String category;
    private String field;
    private String name;
    private String region;
    private Long price;
    private String thumbnailUrl;
    private List<String> tags;
    private List<String> styles;

    private String address;
    private String description;
    private String descriptionImageUrl;

    private WeddingHallDetail weddingHallDetail;
    private VideoOrInvitationDetail videoOrInvitationDetail;

    @Getter
    @Builder
    public static class WeddingHallDetail {
        private Integer mealPrice;
        private Integer capacity;
        private Integer parkingCapacity;
    }

    @Getter
    @Builder
    public static class VideoOrInvitationDetail {
        private Integer duration;
    }
}