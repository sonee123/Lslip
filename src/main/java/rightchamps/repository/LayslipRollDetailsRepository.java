package rightchamps.repository;

import rightchamps.domain.LayslipRollDetails;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the LayslipRollDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LayslipRollDetailsRepository extends JpaRepository<LayslipRollDetails, Long> {

}
