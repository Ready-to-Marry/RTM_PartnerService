package ready_to_marry.partnerservice.partner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ItemListResponse {
    private long total;
    private int page;
    private int size;
    private List<ItemDTO> items;

}