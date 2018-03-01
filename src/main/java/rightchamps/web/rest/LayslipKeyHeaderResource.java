package rightchamps.web.rest;

import com.codahale.metrics.annotation.Timed;
import rightchamps.domain.LayslipKeyHeader;

import rightchamps.repository.LayslipKeyHeaderRepository;
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
 * REST controller for managing LayslipKeyHeader.
 */
@RestController
@RequestMapping("/api")
public class LayslipKeyHeaderResource {

    private final Logger log = LoggerFactory.getLogger(LayslipKeyHeaderResource.class);

    private static final String ENTITY_NAME = "layslipKeyHeader";

    private final LayslipKeyHeaderRepository layslipKeyHeaderRepository;

    public LayslipKeyHeaderResource(LayslipKeyHeaderRepository layslipKeyHeaderRepository) {
        this.layslipKeyHeaderRepository = layslipKeyHeaderRepository;
    }

    /**
     * POST  /layslip-key-headers : Create a new layslipKeyHeader.
     *
     * @param layslipKeyHeader the layslipKeyHeader to create
     * @return the ResponseEntity with status 201 (Created) and with body the new layslipKeyHeader, or with status 400 (Bad Request) if the layslipKeyHeader has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/layslip-key-headers")
    @Timed
    public ResponseEntity<LayslipKeyHeader> createLayslipKeyHeader(@RequestBody LayslipKeyHeader layslipKeyHeader) throws URISyntaxException {
        log.debug("REST request to save LayslipKeyHeader : {}", layslipKeyHeader);
        if (layslipKeyHeader.getId() != null) {
            throw new BadRequestAlertException("A new layslipKeyHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LayslipKeyHeader result = layslipKeyHeaderRepository.save(layslipKeyHeader);
        return ResponseEntity.created(new URI("/api/layslip-key-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /layslip-key-headers : Updates an existing layslipKeyHeader.
     *
     * @param layslipKeyHeader the layslipKeyHeader to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated layslipKeyHeader,
     * or with status 400 (Bad Request) if the layslipKeyHeader is not valid,
     * or with status 500 (Internal Server Error) if the layslipKeyHeader couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/layslip-key-headers")
    @Timed
    public ResponseEntity<LayslipKeyHeader> updateLayslipKeyHeader(@RequestBody LayslipKeyHeader layslipKeyHeader) throws URISyntaxException {
        log.debug("REST request to update LayslipKeyHeader : {}", layslipKeyHeader);
        if (layslipKeyHeader.getId() == null) {
            return createLayslipKeyHeader(layslipKeyHeader);
        }
        LayslipKeyHeader result = layslipKeyHeaderRepository.save(layslipKeyHeader);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, layslipKeyHeader.getId().toString()))
            .body(result);
    }

    /**
     * GET  /layslip-key-headers : get all the layslipKeyHeaders.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of layslipKeyHeaders in body
     */
    @GetMapping("/layslip-key-headers")
    @Timed
    public List<LayslipKeyHeader> getAllLayslipKeyHeaders() {
        log.debug("REST request to get all LayslipKeyHeaders");
        return layslipKeyHeaderRepository.findAll();
        }

    /**
     * GET  /layslip-key-headers/:id : get the "id" layslipKeyHeader.
     *
     * @param id the id of the layslipKeyHeader to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the layslipKeyHeader, or with status 404 (Not Found)
     */
    @GetMapping("/layslip-key-headers/{id}")
    @Timed
    public ResponseEntity<LayslipKeyHeader> getLayslipKeyHeader(@PathVariable Long id) {
        log.debug("REST request to get LayslipKeyHeader : {}", id);
        LayslipKeyHeader layslipKeyHeader = layslipKeyHeaderRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(layslipKeyHeader));
    }

    /**
     * DELETE  /layslip-key-headers/:id : delete the "id" layslipKeyHeader.
     *
     * @param id the id of the layslipKeyHeader to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/layslip-key-headers/{id}")
    @Timed
    public ResponseEntity<Void> deleteLayslipKeyHeader(@PathVariable Long id) {
        log.debug("REST request to delete LayslipKeyHeader : {}", id);
        layslipKeyHeaderRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
