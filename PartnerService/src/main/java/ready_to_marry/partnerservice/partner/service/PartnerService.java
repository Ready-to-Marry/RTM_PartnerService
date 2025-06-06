package ready_to_marry.partnerservice.partner.service;

import ready_to_marry.partnerservice.partner.dto.PartnerRequestDto;
import ready_to_marry.partnerservice.partner.dto.PartnerResponseDto;

public interface PartnerService {
    PartnerResponseDto findPartnerById(Long partnerId);
    Long registerPartner(PartnerRequestDto partnerRequestDto);
    boolean deletePartner(Long partnerId);
}