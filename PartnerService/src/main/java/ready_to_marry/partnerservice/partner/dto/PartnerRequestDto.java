package ready_to_marry.partnerservice.partner.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PartnerRequestDto {
    @Column(length = 20)
    private String name;

    @Column(length = 255)
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
