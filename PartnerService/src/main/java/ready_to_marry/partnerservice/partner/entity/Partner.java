package ready_to_marry.partnerservice.partner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ready_to_marry.partnerservice.common.util.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Partner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
