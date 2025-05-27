package ready_to_marry.partnerservice.partner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ready_to_marry.partnerservice.partner.dto.ItemDetailResponse;
import ready_to_marry.partnerservice.partner.dto.ItemListResponse;
import ready_to_marry.partnerservice.partner.service.PartnerService;

@RestController
@RequiredArgsConstructor
public class PartnerController {
    private final PartnerService partnerService;

    @GetMapping("/items")
    public ResponseEntity<ItemListResponse> getPartnerItemList(
            @RequestHeader("X-Partner-Id") Long partnerId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ItemListResponse response = partnerService.listByPartner(partnerId, page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<ItemDetailResponse> getItemDetail(@PathVariable Long itemId) {
        ItemDetailResponse detail = partnerService.getItemDetail(itemId);
        return ResponseEntity.ok(detail);
    }
}
