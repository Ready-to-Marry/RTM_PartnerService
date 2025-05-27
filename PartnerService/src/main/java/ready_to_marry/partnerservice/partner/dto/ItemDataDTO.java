package ready_to_marry.partnerservice.partner.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemDataDTO {
    private int total;
    private int page;
    private int size;
    private List<ItemDTO> items;
}
