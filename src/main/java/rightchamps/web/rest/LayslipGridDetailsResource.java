package rightchamps.web.rest;

import com.codahale.metrics.annotation.Timed;
import rightchamps.domain.LayslipGridDetails;

import rightchamps.repository.LayslipGridDetailsRepository;
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
 * REST controller for managing LayslipGridDetails.
 */
@RestController
@RequestMapping("/api")
public class LayslipGridDetailsResource {

    private final Logger log = LoggerFactory.getLogger(LayslipGridDetailsResource.class);

    private static final String ENTITY_NAME = "layslipGridDetails";

    private final LayslipGridDetailsRepository layslipGridDetailsRepository;

    public LayslipGridDetailsResource(LayslipGridDetailsRepository layslipGridDetailsRepository) {
        this.layslipGridDetailsRepository = layslipGridDetailsRepository;
    }

    /**
     * POST  /layslip-grid-details : Create a new layslipGridDetails.
     *
     * @param layslipGridDetails the layslipGridDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new layslipGridDetails, or with status 400 (Bad Request) if the layslipGridDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/layslip-grid-details")
    @Timed
    public ResponseEntity<LayslipGridDetails> createLayslipGridDetails(@RequestBody LayslipGridDetails layslipGridDetails) throws URISyntaxException {
        log.debug("REST request to save LayslipGridDetails : {}", layslipGridDetails);
        if (layslipGridDetails.getId() != null) {
            throw new BadRequestAlertException("A new layslipGridDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LayslipGridDetails result = layslipGridDetailsRepository.save(layslipGridDetails);
        return ResponseEntity.created(new URI("/api/layslip-grid-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /layslip-grid-details : Updates an existing layslipGridDetails.
     *
     * @param layslipGridDetails the layslipGridDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated layslipGridDetails,
     * or with status 400 (Bad Request) if the layslipGridDetails is not valid,
     * or with status 500 (Internal Server Error) if the layslipGridDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/layslip-grid-details")
    @Timed
    public ResponseEntity<LayslipGridDetails> updateLayslipGridDetails(@RequestBody LayslipGridDetails layslipGridDetails) throws URISyntaxException {
        log.debug("REST request to update LayslipGridDetails : {}", layslipGridDetails);
        if (layslipGridDetails.getId() == null) {
            return createLayslipGridDetails(layslipGridDetails);
        }
        LayslipGridDetails result = layslipGridDetailsRepository.save(layslipGridDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, layslipGridDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /layslip-grid-details : get all the layslipGridDetails.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of layslipGridDetails in body
     */
    @GetMapping("/layslip-grid-details")
    @Timed
    public List<LayslipGridDetails> getAllLayslipGridDetails() {
        log.debug("REST request to get all LayslipGridDetails");
        return layslipGridDetailsRepository.findAll();
        }

    /**
     * GET  /layslip-grid-details/:id : get the "id" layslipGridDetails.
     *
     * @param id the id of the layslipGridDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the layslipGridDetails, or with status 404 (Not Found)
     */
    @GetMapping("/layslip-grid-details/{id}")
    @Timed
    public ResponseEntity<LayslipGridDetails> getLayslipGridDetails(@PathVariable Long id) {
        log.debug("REST request to get LayslipGridDetails : {}", id);
        LayslipGridDetails layslipGridDetails = layslipGridDetailsRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(layslipGridDetails));
    }

    /**
     * DELETE  /layslip-grid-details/:id : delete the "id" layslipGridDetails.
     *
     * @param id the id of the layslipGridDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/layslip-grid-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteLayslipGridDetails(@PathVariable Long id) {
        log.debug("REST request to delete LayslipGridDetails : {}", id);
        layslipGridDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
