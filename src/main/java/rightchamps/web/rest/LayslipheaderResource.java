package rightchamps.web.rest;

import com.codahale.metrics.annotation.Timed;
import rightchamps.domain.Layslipheader;

import rightchamps.repository.LayslipheaderRepository;
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
 * REST controller for managing Layslipheader.
 */
@RestController
@RequestMapping("/api")
public class LayslipheaderResource {

    private final Logger log = LoggerFactory.getLogger(LayslipheaderResource.class);

    private static final String ENTITY_NAME = "layslipheader";

    private final LayslipheaderRepository layslipheaderRepository;

    public LayslipheaderResource(LayslipheaderRepository layslipheaderRepository) {
        this.layslipheaderRepository = layslipheaderRepository;
    }

    /**
     * POST  /layslipheaders : Create a new layslipheader.
     *
     * @param layslipheader the layslipheader to create
     * @return the ResponseEntity with status 201 (Created) and with body the new layslipheader, or with status 400 (Bad Request) if the layslipheader has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/layslipheaders")
    @Timed
    public ResponseEntity<Layslipheader> createLayslipheader(@RequestBody Layslipheader layslipheader) throws URISyntaxException {
        log.debug("REST request to save Layslipheader : {}", layslipheader);
        if (layslipheader.getId() != null) {
            throw new BadRequestAlertException("A new layslipheader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Layslipheader result = layslipheaderRepository.save(layslipheader);
        return ResponseEntity.created(new URI("/api/layslipheaders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /layslipheaders : Updates an existing layslipheader.
     *
     * @param layslipheader the layslipheader to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated layslipheader,
     * or with status 400 (Bad Request) if the layslipheader is not valid,
     * or with status 500 (Internal Server Error) if the layslipheader couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/layslipheaders")
    @Timed
    public ResponseEntity<Layslipheader> updateLayslipheader(@RequestBody Layslipheader layslipheader) throws URISyntaxException {
        log.debug("REST request to update Layslipheader : {}", layslipheader);
        if (layslipheader.getId() == null) {
            return createLayslipheader(layslipheader);
        }
        Layslipheader result = layslipheaderRepository.save(layslipheader);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, layslipheader.getId().toString()))
            .body(result);
    }

    /**
     * GET  /layslipheaders : get all the layslipheaders.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of layslipheaders in body
     */
    @GetMapping("/layslipheaders")
    @Timed
    public List<Layslipheader> getAllLayslipheaders() {
        log.debug("REST request to get all Layslipheaders");
        return layslipheaderRepository.findAll();
        }

    /**
     * GET  /layslipheaders/:id : get the "id" layslipheader.
     *
     * @param id the id of the layslipheader to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the layslipheader, or with status 404 (Not Found)
     */
    @GetMapping("/layslipheaders/{id}")
    @Timed
    public ResponseEntity<Layslipheader> getLayslipheader(@PathVariable Long id) {
        log.debug("REST request to get Layslipheader : {}", id);
        Layslipheader layslipheader = layslipheaderRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(layslipheader));
    }

    /**
     * DELETE  /layslipheaders/:id : delete the "id" layslipheader.
     *
     * @param id the id of the layslipheader to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/layslipheaders/{id}")
    @Timed
    public ResponseEntity<Void> deleteLayslipheader(@PathVariable Long id) {
        log.debug("REST request to delete Layslipheader : {}", id);
        layslipheaderRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
