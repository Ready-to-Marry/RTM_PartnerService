package ready_to_marry.partnerservice.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequestDto {
    private String id;
    private Long contractId;
    private String title;
    private String message;
    private int amount;
    private String targetToken;
    private String createdAt;
}
