package rightchamps.web.rest;

import rightchamps.LayslipApp;

import rightchamps.domain.LayslipRollDetails;
import rightchamps.repository.LayslipRollDetailsRepository;
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
 * Test class for the LayslipRollDetailsResource REST controller.
 *
 * @see LayslipRollDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LayslipApp.class)
public class LayslipRollDetailsResourceIntTest {

    private static final String DEFAULT_PO_NO = "AAAAAAAAAA";
    private static final String UPDATED_PO_NO = "BBBBBBBBBB";

    private static final String DEFAULT_COM_MATERIAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COM_MATERIAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ROLL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ROLL_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_ROLL_QTY = 1;
    private static final Integer UPDATED_ROLL_QTY = 2;

    private static final String DEFAULT_GRADE = "AAAAAAAAAA";
    private static final String UPDATED_GRADE = "BBBBBBBBBB";

    private static final String DEFAULT_SHADE = "AAAAAAAAAA";
    private static final String UPDATED_SHADE = "BBBBBBBBBB";

    private static final Integer DEFAULT_FLAT_START = 1;
    private static final Integer UPDATED_FLAT_START = 2;

    private static final Integer DEFAULT_FITTED_START = 1;
    private static final Integer UPDATED_FITTED_START = 2;

    private static final Integer DEFAULT_PILLOW_START = 1;
    private static final Integer UPDATED_PILLOW_START = 2;

    private static final Integer DEFAULT_FLAT_END = 1;
    private static final Integer UPDATED_FLAT_END = 2;

    private static final Integer DEFAULT_FITTED_END = 1;
    private static final Integer UPDATED_FITTED_END = 2;

    private static final Integer DEFAULT_PILLOW_END = 1;
    private static final Integer UPDATED_PILLOW_END = 2;

    private static final Integer DEFAULT_FULL_LENGTH = 1;
    private static final Integer UPDATED_FULL_LENGTH = 2;

    private static final Integer DEFAULT_HALF_LENGTH = 1;
    private static final Integer UPDATED_HALF_LENGTH = 2;

    private static final Integer DEFAULT_ENDBIT_PCS = 1;
    private static final Integer UPDATED_ENDBIT_PCS = 2;

    private static final Integer DEFAULT_EST_PILLOWS = 1;
    private static final Integer UPDATED_EST_PILLOWS = 2;

    private static final Integer DEFAULT_PILLOW_PRORATA = 1;
    private static final Integer UPDATED_PILLOW_PRORATA = 2;

    private static final Integer DEFAULT_REJECTED_FABRIC = 1;
    private static final Integer UPDATED_REJECTED_FABRIC = 2;

    @Autowired
    private LayslipRollDetailsRepository layslipRollDetailsRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLayslipRollDetailsMockMvc;

    private LayslipRollDetails layslipRollDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LayslipRollDetailsResource layslipRollDetailsResource = new LayslipRollDetailsResource(layslipRollDetailsRepository);
        this.restLayslipRollDetailsMockMvc = MockMvcBuilders.standaloneSetup(layslipRollDetailsResource)
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
    public static LayslipRollDetails createEntity(EntityManager em) {
        LayslipRollDetails layslipRollDetails = new LayslipRollDetails()
            .poNo(DEFAULT_PO_NO)
            .comMaterialCode(DEFAULT_COM_MATERIAL_CODE)
            .rollNumber(DEFAULT_ROLL_NUMBER)
            .rollQty(DEFAULT_ROLL_QTY)
            .grade(DEFAULT_GRADE)
            .shade(DEFAULT_SHADE)
            .flatStart(DEFAULT_FLAT_START)
            .fittedStart(DEFAULT_FITTED_START)
            .pillowStart(DEFAULT_PILLOW_START)
            .flatEnd(DEFAULT_FLAT_END)
            .fittedEnd(DEFAULT_FITTED_END)
            .pillowEnd(DEFAULT_PILLOW_END)
            .fullLength(DEFAULT_FULL_LENGTH)
            .halfLength(DEFAULT_HALF_LENGTH)
            .endbitPcs(DEFAULT_ENDBIT_PCS)
            .estPillows(DEFAULT_EST_PILLOWS)
            .pillowProrata(DEFAULT_PILLOW_PRORATA)
            .rejectedFabric(DEFAULT_REJECTED_FABRIC);
        return layslipRollDetails;
    }

    @Before
    public void initTest() {
        layslipRollDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createLayslipRollDetails() throws Exception {
        int databaseSizeBeforeCreate = layslipRollDetailsRepository.findAll().size();

        // Create the LayslipRollDetails
        restLayslipRollDetailsMockMvc.perform(post("/api/layslip-roll-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(layslipRollDetails)))
            .andExpect(status().isCreated());

        // Validate the LayslipRollDetails in the database
        List<LayslipRollDetails> layslipRollDetailsList = layslipRollDetailsRepository.findAll();
        assertThat(layslipRollDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        LayslipRollDetails testLayslipRollDetails = layslipRollDetailsList.get(layslipRollDetailsList.size() - 1);
        assertThat(testLayslipRollDetails.getPoNo()).isEqualTo(DEFAULT_PO_NO);
        assertThat(testLayslipRollDetails.getComMaterialCode()).isEqualTo(DEFAULT_COM_MATERIAL_CODE);
        assertThat(testLayslipRollDetails.getRollNumber()).isEqualTo(DEFAULT_ROLL_NUMBER);
        assertThat(testLayslipRollDetails.getRollQty()).isEqualTo(DEFAULT_ROLL_QTY);
        assertThat(testLayslipRollDetails.getGrade()).isEqualTo(DEFAULT_GRADE);
        assertThat(testLayslipRollDetails.getShade()).isEqualTo(DEFAULT_SHADE);
        assertThat(testLayslipRollDetails.getFlatStart()).isEqualTo(DEFAULT_FLAT_START);
        assertThat(testLayslipRollDetails.getFittedStart()).isEqualTo(DEFAULT_FITTED_START);
        assertThat(testLayslipRollDetails.getPillowStart()).isEqualTo(DEFAULT_PILLOW_START);
        assertThat(testLayslipRollDetails.getFlatEnd()).isEqualTo(DEFAULT_FLAT_END);
        assertThat(testLayslipRollDetails.getFittedEnd()).isEqualTo(DEFAULT_FITTED_END);
        assertThat(testLayslipRollDetails.getPillowEnd()).isEqualTo(DEFAULT_PILLOW_END);
        assertThat(testLayslipRollDetails.getFullLength()).isEqualTo(DEFAULT_FULL_LENGTH);
        assertThat(testLayslipRollDetails.getHalfLength()).isEqualTo(DEFAULT_HALF_LENGTH);
        assertThat(testLayslipRollDetails.getEndbitPcs()).isEqualTo(DEFAULT_ENDBIT_PCS);
        assertThat(testLayslipRollDetails.getEstPillows()).isEqualTo(DEFAULT_EST_PILLOWS);
        assertThat(testLayslipRollDetails.getPillowProrata()).isEqualTo(DEFAULT_PILLOW_PRORATA);
        assertThat(testLayslipRollDetails.getRejectedFabric()).isEqualTo(DEFAULT_REJECTED_FABRIC);
    }

    @Test
    @Transactional
    public void createLayslipRollDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = layslipRollDetailsRepository.findAll().size();

        // Create the LayslipRollDetails with an existing ID
        layslipRollDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLayslipRollDetailsMockMvc.perform(post("/api/layslip-roll-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(layslipRollDetails)))
            .andExpect(status().isBadRequest());

        // Validate the LayslipRollDetails in the database
        List<LayslipRollDetails> layslipRollDetailsList = layslipRollDetailsRepository.findAll();
        assertThat(layslipRollDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLayslipRollDetails() throws Exception {
        // Initialize the database
        layslipRollDetailsRepository.saveAndFlush(layslipRollDetails);

        // Get all the layslipRollDetailsList
        restLayslipRollDetailsMockMvc.perform(get("/api/layslip-roll-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(layslipRollDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].poNo").value(hasItem(DEFAULT_PO_NO.toString())))
            .andExpect(jsonPath("$.[*].comMaterialCode").value(hasItem(DEFAULT_COM_MATERIAL_CODE.toString())))
            .andExpect(jsonPath("$.[*].rollNumber").value(hasItem(DEFAULT_ROLL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].rollQty").value(hasItem(DEFAULT_ROLL_QTY)))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE.toString())))
            .andExpect(jsonPath("$.[*].shade").value(hasItem(DEFAULT_SHADE.toString())))
            .andExpect(jsonPath("$.[*].flatStart").value(hasItem(DEFAULT_FLAT_START)))
            .andExpect(jsonPath("$.[*].fittedStart").value(hasItem(DEFAULT_FITTED_START)))
            .andExpect(jsonPath("$.[*].pillowStart").value(hasItem(DEFAULT_PILLOW_START)))
            .andExpect(jsonPath("$.[*].flatEnd").value(hasItem(DEFAULT_FLAT_END)))
            .andExpect(jsonPath("$.[*].fittedEnd").value(hasItem(DEFAULT_FITTED_END)))
            .andExpect(jsonPath("$.[*].pillowEnd").value(hasItem(DEFAULT_PILLOW_END)))
            .andExpect(jsonPath("$.[*].fullLength").value(hasItem(DEFAULT_FULL_LENGTH)))
            .andExpect(jsonPath("$.[*].halfLength").value(hasItem(DEFAULT_HALF_LENGTH)))
            .andExpect(jsonPath("$.[*].endbitPcs").value(hasItem(DEFAULT_ENDBIT_PCS)))
            .andExpect(jsonPath("$.[*].estPillows").value(hasItem(DEFAULT_EST_PILLOWS)))
            .andExpect(jsonPath("$.[*].pillowProrata").value(hasItem(DEFAULT_PILLOW_PRORATA)))
            .andExpect(jsonPath("$.[*].rejectedFabric").value(hasItem(DEFAULT_REJECTED_FABRIC)));
    }

    @Test
    @Transactional
    public void getLayslipRollDetails() throws Exception {
        // Initialize the database
        layslipRollDetailsRepository.saveAndFlush(layslipRollDetails);

        // Get the layslipRollDetails
        restLayslipRollDetailsMockMvc.perform(get("/api/layslip-roll-details/{id}", layslipRollDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(layslipRollDetails.getId().intValue()))
            .andExpect(jsonPath("$.poNo").value(DEFAULT_PO_NO.toString()))
            .andExpect(jsonPath("$.comMaterialCode").value(DEFAULT_COM_MATERIAL_CODE.toString()))
            .andExpect(jsonPath("$.rollNumber").value(DEFAULT_ROLL_NUMBER.toString()))
            .andExpect(jsonPath("$.rollQty").value(DEFAULT_ROLL_QTY))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE.toString()))
            .andExpect(jsonPath("$.shade").value(DEFAULT_SHADE.toString()))
            .andExpect(jsonPath("$.flatStart").value(DEFAULT_FLAT_START))
            .andExpect(jsonPath("$.fittedStart").value(DEFAULT_FITTED_START))
            .andExpect(jsonPath("$.pillowStart").value(DEFAULT_PILLOW_START))
            .andExpect(jsonPath("$.flatEnd").value(DEFAULT_FLAT_END))
            .andExpect(jsonPath("$.fittedEnd").value(DEFAULT_FITTED_END))
            .andExpect(jsonPath("$.pillowEnd").value(DEFAULT_PILLOW_END))
            .andExpect(jsonPath("$.fullLength").value(DEFAULT_FULL_LENGTH))
            .andExpect(jsonPath("$.halfLength").value(DEFAULT_HALF_LENGTH))
            .andExpect(jsonPath("$.endbitPcs").value(DEFAULT_ENDBIT_PCS))
            .andExpect(jsonPath("$.estPillows").value(DEFAULT_EST_PILLOWS))
            .andExpect(jsonPath("$.pillowProrata").value(DEFAULT_PILLOW_PRORATA))
            .andExpect(jsonPath("$.rejectedFabric").value(DEFAULT_REJECTED_FABRIC));
    }

    @Test
    @Transactional
    public void getNonExistingLayslipRollDetails() throws Exception {
        // Get the layslipRollDetails
        restLayslipRollDetailsMockMvc.perform(get("/api/layslip-roll-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLayslipRollDetails() throws Exception {
        // Initialize the database
        layslipRollDetailsRepository.saveAndFlush(layslipRollDetails);
        int databaseSizeBeforeUpdate = layslipRollDetailsRepository.findAll().size();

        // Update the layslipRollDetails
        LayslipRollDetails updatedLayslipRollDetails = layslipRollDetailsRepository.findOne(layslipRollDetails.getId());
        // Disconnect from session so that the updates on updatedLayslipRollDetails are not directly saved in db
        em.detach(updatedLayslipRollDetails);
        updatedLayslipRollDetails
            .poNo(UPDATED_PO_NO)
            .comMaterialCode(UPDATED_COM_MATERIAL_CODE)
            .rollNumber(UPDATED_ROLL_NUMBER)
            .rollQty(UPDATED_ROLL_QTY)
            .grade(UPDATED_GRADE)
            .shade(UPDATED_SHADE)
            .flatStart(UPDATED_FLAT_START)
            .fittedStart(UPDATED_FITTED_START)
            .pillowStart(UPDATED_PILLOW_START)
            .flatEnd(UPDATED_FLAT_END)
            .fittedEnd(UPDATED_FITTED_END)
            .pillowEnd(UPDATED_PILLOW_END)
            .fullLength(UPDATED_FULL_LENGTH)
            .halfLength(UPDATED_HALF_LENGTH)
            .endbitPcs(UPDATED_ENDBIT_PCS)
            .estPillows(UPDATED_EST_PILLOWS)
            .pillowProrata(UPDATED_PILLOW_PRORATA)
            .rejectedFabric(UPDATED_REJECTED_FABRIC);

        restLayslipRollDetailsMockMvc.perform(put("/api/layslip-roll-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLayslipRollDetails)))
            .andExpect(status().isOk());

        // Validate the LayslipRollDetails in the database
        List<LayslipRollDetails> layslipRollDetailsList = layslipRollDetailsRepository.findAll();
        assertThat(layslipRollDetailsList).hasSize(databaseSizeBeforeUpdate);
        LayslipRollDetails testLayslipRollDetails = layslipRollDetailsList.get(layslipRollDetailsList.size() - 1);
        assertThat(testLayslipRollDetails.getPoNo()).isEqualTo(UPDATED_PO_NO);
        assertThat(testLayslipRollDetails.getComMaterialCode()).isEqualTo(UPDATED_COM_MATERIAL_CODE);
        assertThat(testLayslipRollDetails.getRollNumber()).isEqualTo(UPDATED_ROLL_NUMBER);
        assertThat(testLayslipRollDetails.getRollQty()).isEqualTo(UPDATED_ROLL_QTY);
        assertThat(testLayslipRollDetails.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testLayslipRollDetails.getShade()).isEqualTo(UPDATED_SHADE);
        assertThat(testLayslipRollDetails.getFlatStart()).isEqualTo(UPDATED_FLAT_START);
        assertThat(testLayslipRollDetails.getFittedStart()).isEqualTo(UPDATED_FITTED_START);
        assertThat(testLayslipRollDetails.getPillowStart()).isEqualTo(UPDATED_PILLOW_START);
        assertThat(testLayslipRollDetails.getFlatEnd()).isEqualTo(UPDATED_FLAT_END);
        assertThat(testLayslipRollDetails.getFittedEnd()).isEqualTo(UPDATED_FITTED_END);
        assertThat(testLayslipRollDetails.getPillowEnd()).isEqualTo(UPDATED_PILLOW_END);
        assertThat(testLayslipRollDetails.getFullLength()).isEqualTo(UPDATED_FULL_LENGTH);
        assertThat(testLayslipRollDetails.getHalfLength()).isEqualTo(UPDATED_HALF_LENGTH);
        assertThat(testLayslipRollDetails.getEndbitPcs()).isEqualTo(UPDATED_ENDBIT_PCS);
        assertThat(testLayslipRollDetails.getEstPillows()).isEqualTo(UPDATED_EST_PILLOWS);
        assertThat(testLayslipRollDetails.getPillowProrata()).isEqualTo(UPDATED_PILLOW_PRORATA);
        assertThat(testLayslipRollDetails.getRejectedFabric()).isEqualTo(UPDATED_REJECTED_FABRIC);
    }

    @Test
    @Transactional
    public void updateNonExistingLayslipRollDetails() throws Exception {
        int databaseSizeBeforeUpdate = layslipRollDetailsRepository.findAll().size();

        // Create the LayslipRollDetails

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLayslipRollDetailsMockMvc.perform(put("/api/layslip-roll-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(layslipRollDetails)))
            .andExpect(status().isCreated());

        // Validate the LayslipRollDetails in the database
        List<LayslipRollDetails> layslipRollDetailsList = layslipRollDetailsRepository.findAll();
        assertThat(layslipRollDetailsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLayslipRollDetails() throws Exception {
        // Initialize the database
        layslipRollDetailsRepository.saveAndFlush(layslipRollDetails);
        int databaseSizeBeforeDelete = layslipRollDetailsRepository.findAll().size();

        // Get the layslipRollDetails
        restLayslipRollDetailsMockMvc.perform(delete("/api/layslip-roll-details/{id}", layslipRollDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LayslipRollDetails> layslipRollDetailsList = layslipRollDetailsRepository.findAll();
        assertThat(layslipRollDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LayslipRollDetails.class);
        LayslipRollDetails layslipRollDetails1 = new LayslipRollDetails();
        layslipRollDetails1.setId(1L);
        LayslipRollDetails layslipRollDetails2 = new LayslipRollDetails();
        layslipRollDetails2.setId(layslipRollDetails1.getId());
        assertThat(layslipRollDetails1).isEqualTo(layslipRollDetails2);
        layslipRollDetails2.setId(2L);
        assertThat(layslipRollDetails1).isNotEqualTo(layslipRollDetails2);
        layslipRollDetails1.setId(null);
        assertThat(layslipRollDetails1).isNotEqualTo(layslipRollDetails2);
    }
}
