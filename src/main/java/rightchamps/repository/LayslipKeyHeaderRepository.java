package rightchamps.repository;

import rightchamps.domain.LayslipKeyHeader;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the LayslipKeyHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LayslipKeyHeaderRepository extends JpaRepository<LayslipKeyHeader, Long> {

}
