package ready_to_marry.partnerservice.Reservation.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContractListResponse {

    private Long contractId;
    private Long userId;
    private Long partnerId;
    private Long itemId;
    private int amount;
    private String contractContent;
    private String status;
    private LocalDateTime createdAt;
}
