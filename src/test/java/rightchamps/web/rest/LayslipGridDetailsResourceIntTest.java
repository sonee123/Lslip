package rightchamps.web.rest;

import rightchamps.LayslipApp;

import rightchamps.domain.LayslipGridDetails;
import rightchamps.repository.LayslipGridDetailsRepository;
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
 * Test class for the LayslipGridDetailsResource REST controller.
 *
 * @see LayslipGridDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LayslipApp.class)
public class LayslipGridDetailsResourceIntTest {

    private static final String DEFAULT_LAY_SLIP_NO = "AAAAAAAAAA";
    private static final String UPDATED_LAY_SLIP_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_LAYSLIP_NO = "AAAAAAAAAA";
    private static final String UPDATED_SUB_LAYSLIP_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MAIN_GRID = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_GRID = "BBBBBBBBBB";

    private static final Integer DEFAULT_PILLOWS = 1;
    private static final Integer UPDATED_PILLOWS = 2;

    private static final Integer DEFAULT_WAST_FABRIC = 1;
    private static final Integer UPDATED_WAST_FABRIC = 2;

    @Autowired
    private LayslipGridDetailsRepository layslipGridDetailsRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLayslipGridDetailsMockMvc;

    private LayslipGridDetails layslipGridDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LayslipGridDetailsResource layslipGridDetailsResource = new LayslipGridDetailsResource(layslipGridDetailsRepository);
        this.restLayslipGridDetailsMockMvc = MockMvcBuilders.standaloneSetup(layslipGridDetailsResource)
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
    public static LayslipGridDetails createEntity(EntityManager em) {
        LayslipGridDetails layslipGridDetails = new LayslipGridDetails()
            .laySlipNo(DEFAULT_LAY_SLIP_NO)
            .subLayslipNo(DEFAULT_SUB_LAYSLIP_NO)
            .mainGrid(DEFAULT_MAIN_GRID)
            .pillows(DEFAULT_PILLOWS)
            .wastFabric(DEFAULT_WAST_FABRIC);
        return layslipGridDetails;
    }

    @Before
    public void initTest() {
        layslipGridDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createLayslipGridDetails() throws Exception {
        int databaseSizeBeforeCreate = layslipGridDetailsRepository.findAll().size();

        // Create the LayslipGridDetails
        restLayslipGridDetailsMockMvc.perform(post("/api/layslip-grid-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(layslipGridDetails)))
            .andExpect(status().isCreated());

        // Validate the LayslipGridDetails in the database
        List<LayslipGridDetails> layslipGridDetailsList = layslipGridDetailsRepository.findAll();
        assertThat(layslipGridDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        LayslipGridDetails testLayslipGridDetails = layslipGridDetailsList.get(layslipGridDetailsList.size() - 1);
        assertThat(testLayslipGridDetails.getLaySlipNo()).isEqualTo(DEFAULT_LAY_SLIP_NO);
        assertThat(testLayslipGridDetails.getSubLayslipNo()).isEqualTo(DEFAULT_SUB_LAYSLIP_NO);
        assertThat(testLayslipGridDetails.getMainGrid()).isEqualTo(DEFAULT_MAIN_GRID);
        assertThat(testLayslipGridDetails.getPillows()).isEqualTo(DEFAULT_PILLOWS);
        assertThat(testLayslipGridDetails.getWastFabric()).isEqualTo(DEFAULT_WAST_FABRIC);
    }

    @Test
    @Transactional
    public void createLayslipGridDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = layslipGridDetailsRepository.findAll().size();

        // Create the LayslipGridDetails with an existing ID
        layslipGridDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLayslipGridDetailsMockMvc.perform(post("/api/layslip-grid-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(layslipGridDetails)))
            .andExpect(status().isBadRequest());

        // Validate the LayslipGridDetails in the database
        List<LayslipGridDetails> layslipGridDetailsList = layslipGridDetailsRepository.findAll();
        assertThat(layslipGridDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLayslipGridDetails() throws Exception {
        // Initialize the database
        layslipGridDetailsRepository.saveAndFlush(layslipGridDetails);

        // Get all the layslipGridDetailsList
        restLayslipGridDetailsMockMvc.perform(get("/api/layslip-grid-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(layslipGridDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].laySlipNo").value(hasItem(DEFAULT_LAY_SLIP_NO.toString())))
            .andExpect(jsonPath("$.[*].subLayslipNo").value(hasItem(DEFAULT_SUB_LAYSLIP_NO.toString())))
            .andExpect(jsonPath("$.[*].mainGrid").value(hasItem(DEFAULT_MAIN_GRID.toString())))
            .andExpect(jsonPath("$.[*].pillows").value(hasItem(DEFAULT_PILLOWS)))
            .andExpect(jsonPath("$.[*].wastFabric").value(hasItem(DEFAULT_WAST_FABRIC)));
    }

    @Test
    @Transactional
    public void getLayslipGridDetails() throws Exception {
        // Initialize the database
        layslipGridDetailsRepository.saveAndFlush(layslipGridDetails);

        // Get the layslipGridDetails
        restLayslipGridDetailsMockMvc.perform(get("/api/layslip-grid-details/{id}", layslipGridDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(layslipGridDetails.getId().intValue()))
            .andExpect(jsonPath("$.laySlipNo").value(DEFAULT_LAY_SLIP_NO.toString()))
            .andExpect(jsonPath("$.subLayslipNo").value(DEFAULT_SUB_LAYSLIP_NO.toString()))
            .andExpect(jsonPath("$.mainGrid").value(DEFAULT_MAIN_GRID.toString()))
            .andExpect(jsonPath("$.pillows").value(DEFAULT_PILLOWS))
            .andExpect(jsonPath("$.wastFabric").value(DEFAULT_WAST_FABRIC));
    }

    @Test
    @Transactional
    public void getNonExistingLayslipGridDetails() throws Exception {
        // Get the layslipGridDetails
        restLayslipGridDetailsMockMvc.perform(get("/api/layslip-grid-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLayslipGridDetails() throws Exception {
        // Initialize the database
        layslipGridDetailsRepository.saveAndFlush(layslipGridDetails);
        int databaseSizeBeforeUpdate = layslipGridDetailsRepository.findAll().size();

        // Update the layslipGridDetails
        LayslipGridDetails updatedLayslipGridDetails = layslipGridDetailsRepository.findOne(layslipGridDetails.getId());
        // Disconnect from session so that the updates on updatedLayslipGridDetails are not directly saved in db
        em.detach(updatedLayslipGridDetails);
        updatedLayslipGridDetails
            .laySlipNo(UPDATED_LAY_SLIP_NO)
            .subLayslipNo(UPDATED_SUB_LAYSLIP_NO)
            .mainGrid(UPDATED_MAIN_GRID)
            .pillows(UPDATED_PILLOWS)
            .wastFabric(UPDATED_WAST_FABRIC);

        restLayslipGridDetailsMockMvc.perform(put("/api/layslip-grid-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLayslipGridDetails)))
            .andExpect(status().isOk());

        // Validate the LayslipGridDetails in the database
        List<LayslipGridDetails> layslipGridDetailsList = layslipGridDetailsRepository.findAll();
        assertThat(layslipGridDetailsList).hasSize(databaseSizeBeforeUpdate);
        LayslipGridDetails testLayslipGridDetails = layslipGridDetailsList.get(layslipGridDetailsList.size() - 1);
        assertThat(testLayslipGridDetails.getLaySlipNo()).isEqualTo(UPDATED_LAY_SLIP_NO);
        assertThat(testLayslipGridDetails.getSubLayslipNo()).isEqualTo(UPDATED_SUB_LAYSLIP_NO);
        assertThat(testLayslipGridDetails.getMainGrid()).isEqualTo(UPDATED_MAIN_GRID);
        assertThat(testLayslipGridDetails.getPillows()).isEqualTo(UPDATED_PILLOWS);
        assertThat(testLayslipGridDetails.getWastFabric()).isEqualTo(UPDATED_WAST_FABRIC);
    }

    @Test
    @Transactional
    public void updateNonExistingLayslipGridDetails() throws Exception {
        int databaseSizeBeforeUpdate = layslipGridDetailsRepository.findAll().size();

        // Create the LayslipGridDetails

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLayslipGridDetailsMockMvc.perform(put("/api/layslip-grid-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(layslipGridDetails)))
            .andExpect(status().isCreated());

        // Validate the LayslipGridDetails in the database
        List<LayslipGridDetails> layslipGridDetailsList = layslipGridDetailsRepository.findAll();
        assertThat(layslipGridDetailsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLayslipGridDetails() throws Exception {
        // Initialize the database
        layslipGridDetailsRepository.saveAndFlush(layslipGridDetails);
        int databaseSizeBeforeDelete = layslipGridDetailsRepository.findAll().size();

        // Get the layslipGridDetails
        restLayslipGridDetailsMockMvc.perform(delete("/api/layslip-grid-details/{id}", layslipGridDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LayslipGridDetails> layslipGridDetailsList = layslipGridDetailsRepository.findAll();
        assertThat(layslipGridDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LayslipGridDetails.class);
        LayslipGridDetails layslipGridDetails1 = new LayslipGridDetails();
        layslipGridDetails1.setId(1L);
        LayslipGridDetails layslipGridDetails2 = new LayslipGridDetails();
        layslipGridDetails2.setId(layslipGridDetails1.getId());
        assertThat(layslipGridDetails1).isEqualTo(layslipGridDetails2);
        layslipGridDetails2.setId(2L);
        assertThat(layslipGridDetails1).isNotEqualTo(layslipGridDetails2);
        layslipGridDetails1.setId(null);
        assertThat(layslipGridDetails1).isNotEqualTo(layslipGridDetails2);
    }
}
