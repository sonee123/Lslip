package rightchamps.web.rest;

import rightchamps.LayslipApp;

import rightchamps.domain.WorkCode;
import rightchamps.repository.WorkCodeRepository;
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
 * Test class for the WorkCodeResource REST controller.
 *
 * @see WorkCodeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LayslipApp.class)
public class WorkCodeResourceIntTest {

    @Autowired
    private WorkCodeRepository workCodeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWorkCodeMockMvc;

    private WorkCode workCode;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WorkCodeResource workCodeResource = new WorkCodeResource(workCodeRepository);
        this.restWorkCodeMockMvc = MockMvcBuilders.standaloneSetup(workCodeResource)
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
    public static WorkCode createEntity(EntityManager em) {
        WorkCode workCode = new WorkCode();
        return workCode;
    }

    @Before
    public void initTest() {
        workCode = createEntity(em);
    }

    @Test
    @Transactional
    public void createWorkCode() throws Exception {
        int databaseSizeBeforeCreate = workCodeRepository.findAll().size();

        // Create the WorkCode
        restWorkCodeMockMvc.perform(post("/api/work-codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workCode)))
            .andExpect(status().isCreated());

        // Validate the WorkCode in the database
        List<WorkCode> workCodeList = workCodeRepository.findAll();
        assertThat(workCodeList).hasSize(databaseSizeBeforeCreate + 1);
        WorkCode testWorkCode = workCodeList.get(workCodeList.size() - 1);
    }

    @Test
    @Transactional
    public void createWorkCodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = workCodeRepository.findAll().size();

        // Create the WorkCode with an existing ID
        workCode.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkCodeMockMvc.perform(post("/api/work-codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workCode)))
            .andExpect(status().isBadRequest());

        // Validate the WorkCode in the database
        List<WorkCode> workCodeList = workCodeRepository.findAll();
        assertThat(workCodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllWorkCodes() throws Exception {
        // Initialize the database
        workCodeRepository.saveAndFlush(workCode);

        // Get all the workCodeList
        restWorkCodeMockMvc.perform(get("/api/work-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workCode.getId().intValue())));
    }

    @Test
    @Transactional
    public void getWorkCode() throws Exception {
        // Initialize the database
        workCodeRepository.saveAndFlush(workCode);

        // Get the workCode
        restWorkCodeMockMvc.perform(get("/api/work-codes/{id}", workCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(workCode.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingWorkCode() throws Exception {
        // Get the workCode
        restWorkCodeMockMvc.perform(get("/api/work-codes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkCode() throws Exception {
        // Initialize the database
        workCodeRepository.saveAndFlush(workCode);
        int databaseSizeBeforeUpdate = workCodeRepository.findAll().size();

        // Update the workCode
        WorkCode updatedWorkCode = workCodeRepository.findOne(workCode.getId());
        // Disconnect from session so that the updates on updatedWorkCode are not directly saved in db
        em.detach(updatedWorkCode);

        restWorkCodeMockMvc.perform(put("/api/work-codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWorkCode)))
            .andExpect(status().isOk());

        // Validate the WorkCode in the database
        List<WorkCode> workCodeList = workCodeRepository.findAll();
        assertThat(workCodeList).hasSize(databaseSizeBeforeUpdate);
        WorkCode testWorkCode = workCodeList.get(workCodeList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingWorkCode() throws Exception {
        int databaseSizeBeforeUpdate = workCodeRepository.findAll().size();

        // Create the WorkCode

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWorkCodeMockMvc.perform(put("/api/work-codes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workCode)))
            .andExpect(status().isCreated());

        // Validate the WorkCode in the database
        List<WorkCode> workCodeList = workCodeRepository.findAll();
        assertThat(workCodeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteWorkCode() throws Exception {
        // Initialize the database
        workCodeRepository.saveAndFlush(workCode);
        int databaseSizeBeforeDelete = workCodeRepository.findAll().size();

        // Get the workCode
        restWorkCodeMockMvc.perform(delete("/api/work-codes/{id}", workCode.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<WorkCode> workCodeList = workCodeRepository.findAll();
        assertThat(workCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkCode.class);
        WorkCode workCode1 = new WorkCode();
        workCode1.setId(1L);
        WorkCode workCode2 = new WorkCode();
        workCode2.setId(workCode1.getId());
        assertThat(workCode1).isEqualTo(workCode2);
        workCode2.setId(2L);
        assertThat(workCode1).isNotEqualTo(workCode2);
        workCode1.setId(null);
        assertThat(workCode1).isNotEqualTo(workCode2);
    }
}
