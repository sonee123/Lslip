package rightchamps.repository;

import rightchamps.domain.WorkCode;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the WorkCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkCodeRepository extends JpaRepository<WorkCode, Long> {

    @Query("select work_code from WorkCode work_code where work_code.user.login = ?#{principal.username}")
    List<WorkCode> findByUserIsCurrentUser();

}
