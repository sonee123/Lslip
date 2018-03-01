package rightchamps.repository;

import rightchamps.domain.Layslipheader;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Layslipheader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LayslipheaderRepository extends JpaRepository<Layslipheader, Long> {

}
