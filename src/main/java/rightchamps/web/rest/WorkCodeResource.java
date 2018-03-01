package rightchamps.web.rest;

import com.codahale.metrics.annotation.Timed;
import rightchamps.domain.WorkCode;

import rightchamps.repository.WorkCodeRepository;
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
 * REST controller for managing WorkCode.
 */
@RestController
@RequestMapping("/api")
public class WorkCodeResource {

    private final Logger log = LoggerFactory.getLogger(WorkCodeResource.class);

    private static final String ENTITY_NAME = "workCode";

    private final WorkCodeRepository workCodeRepository;

    public WorkCodeResource(WorkCodeRepository workCodeRepository) {
        this.workCodeRepository = workCodeRepository;
    }

    /**
     * POST  /work-codes : Create a new workCode.
     *
     * @param workCode the workCode to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workCode, or with status 400 (Bad Request) if the workCode has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-codes")
    @Timed
    public ResponseEntity<WorkCode> createWorkCode(@RequestBody WorkCode workCode) throws URISyntaxException {
        log.debug("REST request to save WorkCode : {}", workCode);
        if (workCode.getId() != null) {
            throw new BadRequestAlertException("A new workCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkCode result = workCodeRepository.save(workCode);
        return ResponseEntity.created(new URI("/api/work-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /work-codes : Updates an existing workCode.
     *
     * @param workCode the workCode to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workCode,
     * or with status 400 (Bad Request) if the workCode is not valid,
     * or with status 500 (Internal Server Error) if the workCode couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/work-codes")
    @Timed
    public ResponseEntity<WorkCode> updateWorkCode(@RequestBody WorkCode workCode) throws URISyntaxException {
        log.debug("REST request to update WorkCode : {}", workCode);
        if (workCode.getId() == null) {
            return createWorkCode(workCode);
        }
        WorkCode result = workCodeRepository.save(workCode);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workCode.getId().toString()))
            .body(result);
    }

    /**
     * GET  /work-codes : get all the workCodes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of workCodes in body
     */
    @GetMapping("/work-codes")
    @Timed
    public List<WorkCode> getAllWorkCodes() {
        log.debug("REST request to get all WorkCodes");
        return workCodeRepository.findAll();
        }

    /**
     * GET  /work-codes/:id : get the "id" workCode.
     *
     * @param id the id of the workCode to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workCode, or with status 404 (Not Found)
     */
    @GetMapping("/work-codes/{id}")
    @Timed
    public ResponseEntity<WorkCode> getWorkCode(@PathVariable Long id) {
        log.debug("REST request to get WorkCode : {}", id);
        WorkCode workCode = workCodeRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(workCode));
    }

    /**
     * DELETE  /work-codes/:id : delete the "id" workCode.
     *
     * @param id the id of the workCode to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/work-codes/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkCode(@PathVariable Long id) {
        log.debug("REST request to delete WorkCode : {}", id);
        workCodeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
