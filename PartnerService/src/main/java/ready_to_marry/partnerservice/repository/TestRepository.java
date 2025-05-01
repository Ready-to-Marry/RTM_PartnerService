package ready_to_marry.partnerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ready_to_marry.partnerservice.entity.user.Users;

@Repository
public interface TestRepository extends JpaRepository<Users, Long> {
}
