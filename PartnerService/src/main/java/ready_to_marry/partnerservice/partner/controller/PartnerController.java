package ready_to_marry.partnerservice.partner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ready_to_marry.partnerservice.Reservation.dto.ContractRequestDto;
import ready_to_marry.partnerservice.Reservation.dto.ContractListResponse;
import ready_to_marry.partnerservice.common.dto.ApiResponse;
import ready_to_marry.partnerservice.partner.dto.PartnerRequestDto;
import ready_to_marry.partnerservice.partner.dto.PartnerResponseDto;
import ready_to_marry.partnerservice.partner.service.PartnerService;

@RestController
@RequestMapping("/partner")
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

    @GetMapping("/profile/{partnerId}")
    public ResponseEntity<ApiResponse<PartnerResponseDto>> getProfile(@PathVariable Long partnerId) {
        System.out.println(partnerId);
        System.out.println("호출 완료");
        PartnerResponseDto result = partnerService.findPartnerById(partnerId);
        return ResponseEntity.ok(
                ApiResponse.<PartnerResponseDto>builder()
                        .code(0)
                        .message("success")
                        .data(result)
                        .build()
        );
    }

    @DeleteMapping("/delete/{partnerId}")
    public ResponseEntity<ApiResponse<Boolean>> deletePartner(@PathVariable Long partnerId) {
        boolean result = partnerService.deletePartner(partnerId);
        return ResponseEntity.ok(ApiResponse.<Boolean>builder()
                        .code(0)
                        .message("Partner delete sucess")
                        .data(result)
                        .build());
    }

    @PostMapping("/create/contract")
    public ResponseEntity<ApiResponse<ContractListResponse>> createContract(@RequestBody ContractRequestDto contractRequestDto, @RequestHeader("X-Partner-Id") Long partnerId) {
        System.out.println("호출 완료");
        ContractListResponse result = partnerService.createContract(contractRequestDto, partnerId);
        return ResponseEntity.ok(
                ApiResponse.<ContractListResponse>builder()
                        .code(0)
                        .message("success")
                        .data(result)
                        .build()
        );
    }
}
