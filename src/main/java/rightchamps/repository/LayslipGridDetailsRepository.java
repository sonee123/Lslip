package rightchamps.repository;

import rightchamps.domain.LayslipGridDetails;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the LayslipGridDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LayslipGridDetailsRepository extends JpaRepository<LayslipGridDetails, Long> {

}
