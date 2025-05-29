package ready_to_marry.partnerservice.partner.service;

import ready_to_marry.partnerservice.partner.dto.PartnerRequestDto;

public interface PartnerService {
    Long registerPartner(PartnerRequestDto partnerRequestDto);
}