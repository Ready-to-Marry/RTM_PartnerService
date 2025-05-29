package ready_to_marry.partnerservice.partner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ready_to_marry.partnerservice.common.exception.ErrorCode;
import ready_to_marry.partnerservice.common.exception.payment.BusinessException;
import ready_to_marry.partnerservice.common.exception.payment.InfraException;
import ready_to_marry.partnerservice.partner.dto.PartnerRequestDto;
import ready_to_marry.partnerservice.partner.entity.Partner;
import ready_to_marry.partnerservice.partner.repository.PartnerRepository;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;

    @Override
    public Long registerPartner(PartnerRequestDto dto) {
        // 중복 사업자등록번호 체크
        if (partnerRepository.existsByBusinessNum(dto.getBusinessNum())) {
            throw new BusinessException(ErrorCode.DUPLICATE_BUSINESS_NUM);
        }

        Partner partner = Partner.builder()
                .name(dto.getName())
                .companyName(dto.getCompanyName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .companyNum(dto.getCompanyNum())
                .businessNum(dto.getBusinessNum())
                .build();
        try{
            partnerRepository.save(partner);
            return partner.getId();
        }catch (DataAccessException e){
            throw new InfraException(ErrorCode.POSTGRES_SAVE_FAILURE);
        }
    }
}
