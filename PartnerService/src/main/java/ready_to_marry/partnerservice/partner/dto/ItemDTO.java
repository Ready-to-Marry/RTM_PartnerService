package ready_to_marry.partnerservice.partner.dto;

import lombok.Data;
import java.util.List;

@Data
public class ItemDTO {
    private Long itemId;
    private String category;
    private String field;
    private String name;
    private String region;
    private Long price;
    private String thumbnailUrl;
    private List<String> tags;
    private List<String> styles;
}

