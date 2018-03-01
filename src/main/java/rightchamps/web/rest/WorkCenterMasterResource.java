package rightchamps.web.rest;

import com.codahale.metrics.annotation.Timed;
import rightchamps.domain.WorkCenterMaster;

import rightchamps.repository.WorkCenterMasterRepository;
import rightchamps.web.rest.errors.BadRequestAlertException;
import rightchamps.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WorkCenterMaster.
 */
@RestController
@RequestMapping("/api")
public class WorkCenterMasterResource {

    private final Logger log = LoggerFactory.getLogger(WorkCenterMasterResource.class);

    private static final String ENTITY_NAME = "workCenterMaster";

    private final WorkCenterMasterRepository workCenterMasterRepository;

    public WorkCenterMasterResource(WorkCenterMasterRepository workCenterMasterRepository) {
        this.workCenterMasterRepository = workCenterMasterRepository;
    }

    /**
     * POST  /work-center-masters : Create a new workCenterMaster.
     *
     * @param workCenterMaster the workCenterMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workCenterMaster, or with status 400 (Bad Request) if the workCenterMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-center-masters")
    @Timed
    public ResponseEntity<WorkCenterMaster> createWorkCenterMaster(@RequestBody WorkCenterMaster workCenterMaster) throws URISyntaxException {
        log.debug("REST request to save WorkCenterMaster : {}", workCenterMaster);
        if (workCenterMaster.getId() != null) {
            throw new BadRequestAlertException("A new workCenterMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkCenterMaster result = workCenterMasterRepository.save(workCenterMaster);
        return ResponseEntity.created(new URI("/api/work-center-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /work-center-masters : Updates an existing workCenterMaster.
     *
     * @param workCenterMaster the workCenterMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workCenterMaster,
     * or with status 400 (Bad Request) if the workCenterMaster is not valid,
     * or with status 500 (Internal Server Error) if the workCenterMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/work-center-masters")
    @Timed
    public ResponseEntity<WorkCenterMaster> updateWorkCenterMaster(@RequestBody WorkCenterMaster workCenterMaster) throws URISyntaxException {
        log.debug("REST request to update WorkCenterMaster : {}", workCenterMaster);
        if (workCenterMaster.getId() == null) {
            return createWorkCenterMaster(workCenterMaster);
        }
        WorkCenterMaster result = workCenterMasterRepository.save(workCenterMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workCenterMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /work-center-masters : get all the workCenterMasters.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of workCenterMasters in body
     */
    @GetMapping("/work-center-masters")
    @Timed
    public List<WorkCenterMaster> getAllWorkCenterMasters() {
        log.debug("REST request to get all WorkCenterMasters");
        return workCenterMasterRepository.findAllWithEagerRelationships();
        }

    /**
     * GET  /work-center-masters/:id : get the "id" workCenterMaster.
     *
     * @param id the id of the workCenterMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workCenterMaster, or with status 404 (Not Found)
     */
    @GetMapping("/work-center-masters/{id}")
    @Timed
    public ResponseEntity<WorkCenterMaster> getWorkCenterMaster(@PathVariable Long id) {
        log.debug("REST request to get WorkCenterMaster : {}", id);
        WorkCenterMaster workCenterMaster = workCenterMasterRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(workCenterMaster));
    }

    /**
     * DELETE  /work-center-masters/:id : delete the "id" workCenterMaster.
     *
     * @param id the id of the workCenterMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/work-center-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkCenterMaster(@PathVariable Long id) {
        log.debug("REST request to delete WorkCenterMaster : {}", id);
        workCenterMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
