package ready_to_marry.partnerservice.partner.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ready_to_marry.partnerservice.Reservation.ReservationClient;
import ready_to_marry.partnerservice.Reservation.dto.ContractRequestDto;
import ready_to_marry.partnerservice.Reservation.dto.ContractListResponse;
import ready_to_marry.partnerservice.common.exception.ErrorCode;
import ready_to_marry.partnerservice.common.exception.payment.BusinessException;
import ready_to_marry.partnerservice.common.exception.payment.InfraException;
import ready_to_marry.partnerservice.notification.dto.NotificationRequestDto;
import ready_to_marry.partnerservice.notification.service.NotificationService;
import ready_to_marry.partnerservice.partner.dto.PartnerMapper;
import ready_to_marry.partnerservice.partner.dto.PartnerRequestDto;
import ready_to_marry.partnerservice.partner.dto.PartnerResponseDto;
import ready_to_marry.partnerservice.partner.entity.Partner;
import ready_to_marry.partnerservice.partner.repository.PartnerRepository;
import ready_to_marry.partnerservice.user.UserClient;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;
    private final NotificationService notificationService;
    private final ReservationClient reservationClient;
    private final UserClient userClient;

    @Override
    public PartnerResponseDto findPartnerById(Long partnerId) {
        try {
            Partner partner = partnerRepository.findById(partnerId)
                    .orElseThrow(() -> new EntityNotFoundException("Partner not found"));
            PartnerResponseDto responseDto = partnerMapper.toDto(partner);
            return responseDto; // Entity → DTO 변환
        } catch (EntityNotFoundException e) {
            // Entity가 없을 때는 비즈니스 예외로 던짐
            throw new BusinessException(ErrorCode.PARTNER_NOT_FOUND);
        } catch (DataAccessException e) {
            // DB 조회 실패 시
            throw new InfraException(ErrorCode.DB_RETRIEVE_FAILURE, e);
        }
    }

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
        }catch (ConstraintViolationException e){
            throw new BusinessException(ErrorCode.DATA_FORMAT_ERROR);
        }catch (DataAccessException e){
            throw new InfraException(ErrorCode.POSTGRES_SAVE_FAILURE, e);
        }
    }

    @Override
    public boolean deletePartner(Long partnerId) {
        try {
            boolean exists = partnerRepository.existsById(partnerId);
            if (!exists) {
                throw new EntityNotFoundException("Partner with id " + partnerId + " not found");
            }
            partnerRepository.deleteById(partnerId);
            return true;
        } catch (EntityNotFoundException e) {
            throw new BusinessException(ErrorCode.PARTNER_NOT_FOUND);
        } catch (DataAccessException e) {
            throw new InfraException(ErrorCode.POSTGRES_DELETE_FAILURE, e);
        }
    }

    @Override
    public ContractListResponse createContract(ContractRequestDto contractRequestDto, Long partnerId) {
        try {
            ContractListResponse contract = reservationClient.createContract(contractRequestDto, partnerId);
            System.out.println("저장 완료 및 결제 요청 알림 발송 시작");

            System.out.println("유저 fcm 토큰 호출");

            String targetToken = userClient.getFcmToken(contract.getUserId());

            System.out.println("fcm 토큰 호출 성공" + targetToken);

            NotificationRequestDto notificationRequestDto = NotificationRequestDto.builder()
                    .userId(contract.getUserId().toString())
                    .title("결제 요청")
                    .message(contractRequestDto.getAmount() + "원 결제 요청 도착")
                    .targetToken(targetToken)
                    .contractId(contract.getContractId())
                    .amount(contract.getAmount())
                    .build();

            System.out.println("발송 시작");
            notificationService.sendNotification(notificationRequestDto);
            System.out.println("발송 완료");

            return contract;
        } catch (Exception e) {
            System.err.println("❌ 계약 생성 또는 알림 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
            throw e; // 필요 시 에러 포워딩
        }
    }
}
