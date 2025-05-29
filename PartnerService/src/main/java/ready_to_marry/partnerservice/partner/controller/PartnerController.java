package ready_to_marry.partnerservice.partner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ready_to_marry.partnerservice.common.dto.ApiResponse;
import ready_to_marry.partnerservice.partner.dto.PartnerRequestDto;
import ready_to_marry.partnerservice.partner.entity.Partner;
import ready_to_marry.partnerservice.partner.service.PartnerService;

@RestController
@RequiredArgsConstructor
public class PartnerController {
    private final PartnerService partnerService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Long>> register(@RequestBody PartnerRequestDto partnerRequestDto) {
        Long partnerId = partnerService.registerPartner(partnerRequestDto);
        return ResponseEntity.ok(ApiResponse.<Long>builder()
                        .code(0)
                        .message("Partner register success")
                        .data(partnerId)
                        .build());
    }
}
