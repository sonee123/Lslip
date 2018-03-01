package rightchamps.repository;

import rightchamps.domain.WorkCenterMaster;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the WorkCenterMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkCenterMasterRepository extends JpaRepository<WorkCenterMaster, Long> {
    @Query("select distinct work_center_master from WorkCenterMaster work_center_master left join fetch work_center_master.layslips")
    List<WorkCenterMaster> findAllWithEagerRelationships();

    @Query("select work_center_master from WorkCenterMaster work_center_master left join fetch work_center_master.layslips where work_center_master.id =:id")
    WorkCenterMaster findOneWithEagerRelationships(@Param("id") Long id);

}
