package rightchamps.web.rest;

import rightchamps.LayslipApp;

import rightchamps.domain.WorkCenterMaster;
import rightchamps.repository.WorkCenterMasterRepository;
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
 * Test class for the WorkCenterMasterResource REST controller.
 *
 * @see WorkCenterMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LayslipApp.class)
public class WorkCenterMasterResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private WorkCenterMasterRepository workCenterMasterRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWorkCenterMasterMockMvc;

    private WorkCenterMaster workCenterMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WorkCenterMasterResource workCenterMasterResource = new WorkCenterMasterResource(workCenterMasterRepository);
        this.restWorkCenterMasterMockMvc = MockMvcBuilders.standaloneSetup(workCenterMasterResource)
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
    public static WorkCenterMaster createEntity(EntityManager em) {
        WorkCenterMaster workCenterMaster = new WorkCenterMaster()
            .title(DEFAULT_TITLE);
        return workCenterMaster;
    }

    @Before
    public void initTest() {
        workCenterMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createWorkCenterMaster() throws Exception {
        int databaseSizeBeforeCreate = workCenterMasterRepository.findAll().size();

        // Create the WorkCenterMaster
        restWorkCenterMasterMockMvc.perform(post("/api/work-center-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workCenterMaster)))
            .andExpect(status().isCreated());

        // Validate the WorkCenterMaster in the database
        List<WorkCenterMaster> workCenterMasterList = workCenterMasterRepository.findAll();
        assertThat(workCenterMasterList).hasSize(databaseSizeBeforeCreate + 1);
        WorkCenterMaster testWorkCenterMaster = workCenterMasterList.get(workCenterMasterList.size() - 1);
        assertThat(testWorkCenterMaster.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void createWorkCenterMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = workCenterMasterRepository.findAll().size();

        // Create the WorkCenterMaster with an existing ID
        workCenterMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkCenterMasterMockMvc.perform(post("/api/work-center-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workCenterMaster)))
            .andExpect(status().isBadRequest());

        // Validate the WorkCenterMaster in the database
        List<WorkCenterMaster> workCenterMasterList = workCenterMasterRepository.findAll();
        assertThat(workCenterMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllWorkCenterMasters() throws Exception {
        // Initialize the database
        workCenterMasterRepository.saveAndFlush(workCenterMaster);

        // Get all the workCenterMasterList
        restWorkCenterMasterMockMvc.perform(get("/api/work-center-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workCenterMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())));
    }

    @Test
    @Transactional
    public void getWorkCenterMaster() throws Exception {
        // Initialize the database
        workCenterMasterRepository.saveAndFlush(workCenterMaster);

        // Get the workCenterMaster
        restWorkCenterMasterMockMvc.perform(get("/api/work-center-masters/{id}", workCenterMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(workCenterMaster.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWorkCenterMaster() throws Exception {
        // Get the workCenterMaster
        restWorkCenterMasterMockMvc.perform(get("/api/work-center-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkCenterMaster() throws Exception {
        // Initialize the database
        workCenterMasterRepository.saveAndFlush(workCenterMaster);
        int databaseSizeBeforeUpdate = workCenterMasterRepository.findAll().size();

        // Update the workCenterMaster
        WorkCenterMaster updatedWorkCenterMaster = workCenterMasterRepository.findOne(workCenterMaster.getId());
        // Disconnect from session so that the updates on updatedWorkCenterMaster are not directly saved in db
        em.detach(updatedWorkCenterMaster);
        updatedWorkCenterMaster
            .title(UPDATED_TITLE);

        restWorkCenterMasterMockMvc.perform(put("/api/work-center-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWorkCenterMaster)))
            .andExpect(status().isOk());

        // Validate the WorkCenterMaster in the database
        List<WorkCenterMaster> workCenterMasterList = workCenterMasterRepository.findAll();
        assertThat(workCenterMasterList).hasSize(databaseSizeBeforeUpdate);
        WorkCenterMaster testWorkCenterMaster = workCenterMasterList.get(workCenterMasterList.size() - 1);
        assertThat(testWorkCenterMaster.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void updateNonExistingWorkCenterMaster() throws Exception {
        int databaseSizeBeforeUpdate = workCenterMasterRepository.findAll().size();

        // Create the WorkCenterMaster

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWorkCenterMasterMockMvc.perform(put("/api/work-center-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(workCenterMaster)))
            .andExpect(status().isCreated());

        // Validate the WorkCenterMaster in the database
        List<WorkCenterMaster> workCenterMasterList = workCenterMasterRepository.findAll();
        assertThat(workCenterMasterList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteWorkCenterMaster() throws Exception {
        // Initialize the database
        workCenterMasterRepository.saveAndFlush(workCenterMaster);
        int databaseSizeBeforeDelete = workCenterMasterRepository.findAll().size();

        // Get the workCenterMaster
        restWorkCenterMasterMockMvc.perform(delete("/api/work-center-masters/{id}", workCenterMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<WorkCenterMaster> workCenterMasterList = workCenterMasterRepository.findAll();
        assertThat(workCenterMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkCenterMaster.class);
        WorkCenterMaster workCenterMaster1 = new WorkCenterMaster();
        workCenterMaster1.setId(1L);
        WorkCenterMaster workCenterMaster2 = new WorkCenterMaster();
        workCenterMaster2.setId(workCenterMaster1.getId());
        assertThat(workCenterMaster1).isEqualTo(workCenterMaster2);
        workCenterMaster2.setId(2L);
        assertThat(workCenterMaster1).isNotEqualTo(workCenterMaster2);
        workCenterMaster1.setId(null);
        assertThat(workCenterMaster1).isNotEqualTo(workCenterMaster2);
    }
}
