package ready_to_marry.partnerservice.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequestDto {
    private Long userId;
    private Long contractId;
    private String title;
    private String message;
    private int amount;
    private String targetToken;
}
