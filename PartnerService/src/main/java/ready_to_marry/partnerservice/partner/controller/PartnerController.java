package ready_to_marry.partnerservice.partner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/{partnerId}")
    public String test(@PathVariable Long partnerId) {
        System.out.println("partnerId: " + partnerId);
        return "완료";
    }

    @GetMapping()
    public String test2() {
        System.out.println("호출 완료");
        return "완료2";
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
}
