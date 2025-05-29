package ready_to_marry.partnerservice.partner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ready_to_marry.partnerservice.partner.entity.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    boolean existsByBusinessNum(String businessNum);
}
