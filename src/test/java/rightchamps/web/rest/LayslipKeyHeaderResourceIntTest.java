package rightchamps.web.rest;

import rightchamps.LayslipApp;

import rightchamps.domain.LayslipKeyHeader;
import rightchamps.repository.LayslipKeyHeaderRepository;
import rightchamps.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static rightchamps.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LayslipKeyHeaderResource REST controller.
 *
 * @see LayslipKeyHeaderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LayslipApp.class)
public class LayslipKeyHeaderResourceIntTest {

    @Autowired
    private LayslipKeyHeaderRepository layslipKeyHeaderRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLayslipKeyHeaderMockMvc;

    private LayslipKeyHeader layslipKeyHeader;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LayslipKeyHeaderResource layslipKeyHeaderResource = new LayslipKeyHeaderResource(layslipKeyHeaderRepository);
        this.restLayslipKeyHeaderMockMvc = MockMvcBuilders.standaloneSetup(layslipKeyHeaderResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LayslipKeyHeader createEntity(EntityManager em) {
        LayslipKeyHeader layslipKeyHeader = new LayslipKeyHeader();
        return layslipKeyHeader;
    }

    @Before
    public void initTest() {
        layslipKeyHeader = createEntity(em);
    }

    @Test
    @Transactional
    public void createLayslipKeyHeader() throws Exception {
        int databaseSizeBeforeCreate = layslipKeyHeaderRepository.findAll().size();

        // Create the LayslipKeyHeader
        restLayslipKeyHeaderMockMvc.perform(post("/api/layslip-key-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(layslipKeyHeader)))
            .andExpect(status().isCreated());

        // Validate the LayslipKeyHeader in the database
        List<LayslipKeyHeader> layslipKeyHeaderList = layslipKeyHeaderRepository.findAll();
        assertThat(layslipKeyHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        LayslipKeyHeader testLayslipKeyHeader = layslipKeyHeaderList.get(layslipKeyHeaderList.size() - 1);
    }

    @Test
    @Transactional
    public void createLayslipKeyHeaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = layslipKeyHeaderRepository.findAll().size();

        // Create the LayslipKeyHeader with an existing ID
        layslipKeyHeader.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLayslipKeyHeaderMockMvc.perform(post("/api/layslip-key-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(layslipKeyHeader)))
            .andExpect(status().isBadRequest());

        // Validate the LayslipKeyHeader in the database
        List<LayslipKeyHeader> layslipKeyHeaderList = layslipKeyHeaderRepository.findAll();
        assertThat(layslipKeyHeaderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLayslipKeyHeaders() throws Exception {
        // Initialize the database
        layslipKeyHeaderRepository.saveAndFlush(layslipKeyHeader);

        // Get all the layslipKeyHeaderList
        restLayslipKeyHeaderMockMvc.perform(get("/api/layslip-key-headers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(layslipKeyHeader.getId().intValue())));
    }

    @Test
    @Transactional
    public void getLayslipKeyHeader() throws Exception {
        // Initialize the database
        layslipKeyHeaderRepository.saveAndFlush(layslipKeyHeader);

        // Get the layslipKeyHeader
        restLayslipKeyHeaderMockMvc.perform(get("/api/layslip-key-headers/{id}", layslipKeyHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(layslipKeyHeader.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLayslipKeyHeader() throws Exception {
        // Get the layslipKeyHeader
        restLayslipKeyHeaderMockMvc.perform(get("/api/layslip-key-headers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLayslipKeyHeader() throws Exception {
        // Initialize the database
        layslipKeyHeaderRepository.saveAndFlush(layslipKeyHeader);
        int databaseSizeBeforeUpdate = layslipKeyHeaderRepository.findAll().size();

        // Update the layslipKeyHeader
        LayslipKeyHeader updatedLayslipKeyHeader = layslipKeyHeaderRepository.findOne(layslipKeyHeader.getId());
        // Disconnect from session so that the updates on updatedLayslipKeyHeader are not directly saved in db
        em.detach(updatedLayslipKeyHeader);

        restLayslipKeyHeaderMockMvc.perform(put("/api/layslip-key-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLayslipKeyHeader)))
            .andExpect(status().isOk());

        // Validate the LayslipKeyHeader in the database
        List<LayslipKeyHeader> layslipKeyHeaderList = layslipKeyHeaderRepository.findAll();
        assertThat(layslipKeyHeaderList).hasSize(databaseSizeBeforeUpdate);
        LayslipKeyHeader testLayslipKeyHeader = layslipKeyHeaderList.get(layslipKeyHeaderList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingLayslipKeyHeader() throws Exception {
        int databaseSizeBeforeUpdate = layslipKeyHeaderRepository.findAll().size();

        // Create the LayslipKeyHeader

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLayslipKeyHeaderMockMvc.perform(put("/api/layslip-key-headers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(layslipKeyHeader)))
            .andExpect(status().isCreated());

        // Validate the LayslipKeyHeader in the database
        List<LayslipKeyHeader> layslipKeyHeaderList = layslipKeyHeaderRepository.findAll();
        assertThat(layslipKeyHeaderList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLayslipKeyHeader() throws Exception {
        // Initialize the database
        layslipKeyHeaderRepository.saveAndFlush(layslipKeyHeader);
        int databaseSizeBeforeDelete = layslipKeyHeaderRepository.findAll().size();

        // Get the layslipKeyHeader
        restLayslipKeyHeaderMockMvc.perform(delete("/api/layslip-key-headers/{id}", layslipKeyHeader.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LayslipKeyHeader> layslipKeyHeaderList = layslipKeyHeaderRepository.findAll();
        assertThat(layslipKeyHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LayslipKeyHeader.class);
        LayslipKeyHeader layslipKeyHeader1 = new LayslipKeyHeader();
        layslipKeyHeader1.setId(1L);
        LayslipKeyHeader layslipKeyHeader2 = new LayslipKeyHeader();
        layslipKeyHeader2.setId(layslipKeyHeader1.getId());
        assertThat(layslipKeyHeader1).isEqualTo(layslipKeyHeader2);
        layslipKeyHeader2.setId(2L);
        assertThat(layslipKeyHeader1).isNotEqualTo(layslipKeyHeader2);
        layslipKeyHeader1.setId(null);
        assertThat(layslipKeyHeader1).isNotEqualTo(layslipKeyHeader2);
    }
}
