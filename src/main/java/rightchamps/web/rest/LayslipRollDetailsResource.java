package rightchamps.web.rest;

import com.codahale.metrics.annotation.Timed;
import rightchamps.domain.LayslipRollDetails;

import rightchamps.repository.LayslipRollDetailsRepository;
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
 * REST controller for managing LayslipRollDetails.
 */
@RestController
@RequestMapping("/api")
public class LayslipRollDetailsResource {

    private final Logger log = LoggerFactory.getLogger(LayslipRollDetailsResource.class);

    private static final String ENTITY_NAME = "layslipRollDetails";

    private final LayslipRollDetailsRepository layslipRollDetailsRepository;

    public LayslipRollDetailsResource(LayslipRollDetailsRepository layslipRollDetailsRepository) {
        this.layslipRollDetailsRepository = layslipRollDetailsRepository;
    }

    /**
     * POST  /layslip-roll-details : Create a new layslipRollDetails.
     *
     * @param layslipRollDetails the layslipRollDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new layslipRollDetails, or with status 400 (Bad Request) if the layslipRollDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/layslip-roll-details")
    @Timed
    public ResponseEntity<LayslipRollDetails> createLayslipRollDetails(@RequestBody LayslipRollDetails layslipRollDetails) throws URISyntaxException {
        log.debug("REST request to save LayslipRollDetails : {}", layslipRollDetails);
        if (layslipRollDetails.getId() != null) {
            throw new BadRequestAlertException("A new layslipRollDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LayslipRollDetails result = layslipRollDetailsRepository.save(layslipRollDetails);
        return ResponseEntity.created(new URI("/api/layslip-roll-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /layslip-roll-details : Updates an existing layslipRollDetails.
     *
     * @param layslipRollDetails the layslipRollDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated layslipRollDetails,
     * or with status 400 (Bad Request) if the layslipRollDetails is not valid,
     * or with status 500 (Internal Server Error) if the layslipRollDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/layslip-roll-details")
    @Timed
    public ResponseEntity<LayslipRollDetails> updateLayslipRollDetails(@RequestBody LayslipRollDetails layslipRollDetails) throws URISyntaxException {
        log.debug("REST request to update LayslipRollDetails : {}", layslipRollDetails);
        if (layslipRollDetails.getId() == null) {
            return createLayslipRollDetails(layslipRollDetails);
        }
        LayslipRollDetails result = layslipRollDetailsRepository.save(layslipRollDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, layslipRollDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /layslip-roll-details : get all the layslipRollDetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of layslipRollDetails in body
     */
    @GetMapping("/layslip-roll-details")
    @Timed
    public List<LayslipRollDetails> getAllLayslipRollDetails() {
        log.debug("REST request to get all LayslipRollDetails");
        return layslipRollDetailsRepository.findAll();
        }

    /**
     * GET  /layslip-roll-details/:id : get the "id" layslipRollDetails.
     *
     * @param id the id of the layslipRollDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the layslipRollDetails, or with status 404 (Not Found)
     */
    @GetMapping("/layslip-roll-details/{id}")
    @Timed
    public ResponseEntity<LayslipRollDetails> getLayslipRollDetails(@PathVariable Long id) {
        log.debug("REST request to get LayslipRollDetails : {}", id);
        LayslipRollDetails layslipRollDetails = layslipRollDetailsRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(layslipRollDetails));
    }

    /**
     * DELETE  /layslip-roll-details/:id : delete the "id" layslipRollDetails.
     *
     * @param id the id of the layslipRollDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/layslip-roll-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteLayslipRollDetails(@PathVariable Long id) {
        log.debug("REST request to delete LayslipRollDetails : {}", id);
        layslipRollDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
