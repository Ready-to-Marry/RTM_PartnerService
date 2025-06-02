package ready_to_marry.partnerservice.partner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PartnerRequestDto {

    private String name;

    private String companyName;

    private String address;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9\\-]{1,20}$")
    private String phone;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9\\-]{1,20}$")
    private String companyNum;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String businessNum;
}
