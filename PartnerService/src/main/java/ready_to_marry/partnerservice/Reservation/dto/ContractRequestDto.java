package ready_to_marry.partnerservice.Reservation.dto;

import lombok.Getter;

@Getter
public class ContractRequestDto {
    private Long reservationId;
    private int amount;
    private String contractContent;
    private String targetToken;
    private Long userId;
}
