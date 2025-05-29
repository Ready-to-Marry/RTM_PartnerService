package ready_to_marry.partnerservice.partner.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ready_to_marry.partnerservice.partner.entity.Partner;

@Component
@RequiredArgsConstructor
public class PartnerMapperImpl implements PartnerMapper{

    @Override
    public PartnerResponseDto toDto(Partner partner) {

        PartnerResponseDto partnerResponseDto = PartnerResponseDto.builder()
                .name(partner.getName())
                .phone(partner.getPhone())
                .companyName(partner.getCompanyName())
                .address(partner.getAddress())
                .companyNum(partner.getCompanyNum())
                .businessNum(partner.getBusinessNum())
                .build();

        return partnerResponseDto;
    }
}
