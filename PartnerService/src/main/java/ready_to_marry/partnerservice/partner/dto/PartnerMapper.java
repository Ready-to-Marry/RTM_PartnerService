package ready_to_marry.partnerservice.partner.dto;

import ready_to_marry.partnerservice.partner.entity.Partner;

public interface PartnerMapper {
    PartnerResponseDto toDto(Partner partner);
}
