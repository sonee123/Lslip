package rightchamps.web.rest;

import rightchamps.LayslipApp;

import rightchamps.domain.Layslipheader;
import rightchamps.repository.LayslipheaderRepository;
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
 * Test class for the LayslipheaderResource REST controller.
 *
 * @see LayslipheaderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LayslipApp.class)
public class LayslipheaderResourceIntTest {

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    private static final String DEFAULT_PO_NO = "AAAAAAAAAA";
    private static final String UPDATED_PO_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MATERIAL_NO = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MATERIAL_DESC = "AAAAAAAAAA";
    private static final String UPDATED_MATERIAL_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_MAIN_GRID = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_GRID = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDER_QTY = 1;
    private static final Integer UPDATED_ORDER_QTY = 2;

    private static final Integer DEFAULT_REMAINING_QTY = 1;
    private static final Integer UPDATED_REMAINING_QTY = 2;

    private static final Integer DEFAULT_PLANNED_QTY = 1;
    private static final Integer UPDATED_PLANNED_QTY = 2;

    private static final String DEFAULT_LAY_COMPONENT = "AAAAAAAAAA";
    private static final String UPDATED_LAY_COMPONENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_FLAT = 1;
    private static final Integer UPDATED_FLAT = 2;

    private static final Integer DEFAULT_FITTED = 1;
    private static final Integer UPDATED_FITTED = 2;

    private static final Integer DEFAULT_PILLOW = 1;
    private static final Integer UPDATED_PILLOW = 2;

    private static final String DEFAULT_FLAT_MAT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FLAT_MAT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FITTED_MAT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FITTED_MAT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PILLOW_MAT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PILLOW_MAT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PILLOW_GRID = "AAAAAAAAAA";
    private static final String UPDATED_PILLOW_GRID = "BBBBBBBBBB";

    private static final String DEFAULT_FLAT_TO_BE_MADE = "AAAAAAAAAA";
    private static final String UPDATED_FLAT_TO_BE_MADE = "BBBBBBBBBB";

    private static final String DEFAULT_FITTED_TO_BE_MADE = "AAAAAAAAAA";
    private static final String UPDATED_FITTED_TO_BE_MADE = "BBBBBBBBBB";

    private static final String DEFAULT_PILLOW_TO_BE_MADE = "AAAAAAAAAA";
    private static final String UPDATED_PILLOW_TO_BE_MADE = "BBBBBBBBBB";

    private static final Integer DEFAULT_FLAT_WAYS = 1;
    private static final Integer UPDATED_FLAT_WAYS = 2;

    private static final Integer DEFAULT_FITTED_WAYS = 1;
    private static final Integer UPDATED_FITTED_WAYS = 2;

    private static final Integer DEFAULT_PILLOW_WAYS = 1;
    private static final Integer UPDATED_PILLOW_WAYS = 2;

    private static final Integer DEFAULT_FLAT_PIECES_PER_WAY = 1;
    private static final Integer UPDATED_FLAT_PIECES_PER_WAY = 2;

    private static final Integer DEFAULT_FITTED_PIECES_PER_WAY = 1;
    private static final Integer UPDATED_FITTED_PIECES_PER_WAY = 2;

    private static final Integer DEFAULT_PILLOW_PIECES_PER_WAY = 1;
    private static final Integer UPDATED_PILLOW_PIECES_PER_WAY = 2;

    @Autowired
    private LayslipheaderRepository layslipheaderRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLayslipheaderMockMvc;

    private Layslipheader layslipheader;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LayslipheaderResource layslipheaderResource = new LayslipheaderResource(layslipheaderRepository);
        this.restLayslipheaderMockMvc = MockMvcBuilders.standaloneSetup(layslipheaderResource)
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
    public static Layslipheader createEntity(EntityManager em) {
        Layslipheader layslipheader = new Layslipheader()
            .priority(DEFAULT_PRIORITY)
            .poNo(DEFAULT_PO_NO)
            .materialNo(DEFAULT_MATERIAL_NO)
            .materialDesc(DEFAULT_MATERIAL_DESC)
            .mainGrid(DEFAULT_MAIN_GRID)
            .orderQty(DEFAULT_ORDER_QTY)
            .remainingQty(DEFAULT_REMAINING_QTY)
            .plannedQty(DEFAULT_PLANNED_QTY)
            .layComponent(DEFAULT_LAY_COMPONENT)
            .flat(DEFAULT_FLAT)
            .fitted(DEFAULT_FITTED)
            .pillow(DEFAULT_PILLOW)
            .flatMatCode(DEFAULT_FLAT_MAT_CODE)
            .fittedMatCode(DEFAULT_FITTED_MAT_CODE)
            .pillowMatCode(DEFAULT_PILLOW_MAT_CODE)
            .pillowGrid(DEFAULT_PILLOW_GRID)
            .flatToBeMade(DEFAULT_FLAT_TO_BE_MADE)
            .fittedToBeMade(DEFAULT_FITTED_TO_BE_MADE)
            .pillowToBeMade(DEFAULT_PILLOW_TO_BE_MADE)
            .flatWays(DEFAULT_FLAT_WAYS)
            .fittedWays(DEFAULT_FITTED_WAYS)
            .pillowWays(DEFAULT_PILLOW_WAYS)
            .flatPiecesPerWay(DEFAULT_FLAT_PIECES_PER_WAY)
            .fittedPiecesPerWay(DEFAULT_FITTED_PIECES_PER_WAY)
            .pillowPiecesPerWay(DEFAULT_PILLOW_PIECES_PER_WAY);
        return layslipheader;
    }

    @Before
    public void initTest() {
        layslipheader = createEntity(em);
    }

    @Test
    @Transactional
    public void createLayslipheader() throws Exception {
        int databaseSizeBeforeCreate = layslipheaderRepository.findAll().size();

        // Create the Layslipheader
        restLayslipheaderMockMvc.perform(post("/api/layslipheaders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(layslipheader)))
            .andExpect(status().isCreated());

        // Validate the Layslipheader in the database
        List<Layslipheader> layslipheaderList = layslipheaderRepository.findAll();
        assertThat(layslipheaderList).hasSize(databaseSizeBeforeCreate + 1);
        Layslipheader testLayslipheader = layslipheaderList.get(layslipheaderList.size() - 1);
        assertThat(testLayslipheader.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testLayslipheader.getPoNo()).isEqualTo(DEFAULT_PO_NO);
        assertThat(testLayslipheader.getMaterialNo()).isEqualTo(DEFAULT_MATERIAL_NO);
        assertThat(testLayslipheader.getMaterialDesc()).isEqualTo(DEFAULT_MATERIAL_DESC);
        assertThat(testLayslipheader.getMainGrid()).isEqualTo(DEFAULT_MAIN_GRID);
        assertThat(testLayslipheader.getOrderQty()).isEqualTo(DEFAULT_ORDER_QTY);
        assertThat(testLayslipheader.getRemainingQty()).isEqualTo(DEFAULT_REMAINING_QTY);
        assertThat(testLayslipheader.getPlannedQty()).isEqualTo(DEFAULT_PLANNED_QTY);
        assertThat(testLayslipheader.getLayComponent()).isEqualTo(DEFAULT_LAY_COMPONENT);
        assertThat(testLayslipheader.getFlat()).isEqualTo(DEFAULT_FLAT);
        assertThat(testLayslipheader.getFitted()).isEqualTo(DEFAULT_FITTED);
        assertThat(testLayslipheader.getPillow()).isEqualTo(DEFAULT_PILLOW);
        assertThat(testLayslipheader.getFlatMatCode()).isEqualTo(DEFAULT_FLAT_MAT_CODE);
        assertThat(testLayslipheader.getFittedMatCode()).isEqualTo(DEFAULT_FITTED_MAT_CODE);
        assertThat(testLayslipheader.getPillowMatCode()).isEqualTo(DEFAULT_PILLOW_MAT_CODE);
        assertThat(testLayslipheader.getPillowGrid()).isEqualTo(DEFAULT_PILLOW_GRID);
        assertThat(testLayslipheader.getFlatToBeMade()).isEqualTo(DEFAULT_FLAT_TO_BE_MADE);
        assertThat(testLayslipheader.getFittedToBeMade()).isEqualTo(DEFAULT_FITTED_TO_BE_MADE);
        assertThat(testLayslipheader.getPillowToBeMade()).isEqualTo(DEFAULT_PILLOW_TO_BE_MADE);
        assertThat(testLayslipheader.getFlatWays()).isEqualTo(DEFAULT_FLAT_WAYS);
        assertThat(testLayslipheader.getFittedWays()).isEqualTo(DEFAULT_FITTED_WAYS);
        assertThat(testLayslipheader.getPillowWays()).isEqualTo(DEFAULT_PILLOW_WAYS);
        assertThat(testLayslipheader.getFlatPiecesPerWay()).isEqualTo(DEFAULT_FLAT_PIECES_PER_WAY);
        assertThat(testLayslipheader.getFittedPiecesPerWay()).isEqualTo(DEFAULT_FITTED_PIECES_PER_WAY);
        assertThat(testLayslipheader.getPillowPiecesPerWay()).isEqualTo(DEFAULT_PILLOW_PIECES_PER_WAY);
    }

    @Test
    @Transactional
    public void createLayslipheaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = layslipheaderRepository.findAll().size();

        // Create the Layslipheader with an existing ID
        layslipheader.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLayslipheaderMockMvc.perform(post("/api/layslipheaders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(layslipheader)))
            .andExpect(status().isBadRequest());

        // Validate the Layslipheader in the database
        List<Layslipheader> layslipheaderList = layslipheaderRepository.findAll();
        assertThat(layslipheaderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLayslipheaders() throws Exception {
        // Initialize the database
        layslipheaderRepository.saveAndFlush(layslipheader);

        // Get all the layslipheaderList
        restLayslipheaderMockMvc.perform(get("/api/layslipheaders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(layslipheader.getId().intValue())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].poNo").value(hasItem(DEFAULT_PO_NO.toString())))
            .andExpect(jsonPath("$.[*].materialNo").value(hasItem(DEFAULT_MATERIAL_NO.toString())))
            .andExpect(jsonPath("$.[*].materialDesc").value(hasItem(DEFAULT_MATERIAL_DESC.toString())))
            .andExpect(jsonPath("$.[*].mainGrid").value(hasItem(DEFAULT_MAIN_GRID.toString())))
            .andExpect(jsonPath("$.[*].orderQty").value(hasItem(DEFAULT_ORDER_QTY)))
            .andExpect(jsonPath("$.[*].remainingQty").value(hasItem(DEFAULT_REMAINING_QTY)))
            .andExpect(jsonPath("$.[*].plannedQty").value(hasItem(DEFAULT_PLANNED_QTY)))
            .andExpect(jsonPath("$.[*].layComponent").value(hasItem(DEFAULT_LAY_COMPONENT.toString())))
            .andExpect(jsonPath("$.[*].flat").value(hasItem(DEFAULT_FLAT)))
            .andExpect(jsonPath("$.[*].fitted").value(hasItem(DEFAULT_FITTED)))
            .andExpect(jsonPath("$.[*].pillow").value(hasItem(DEFAULT_PILLOW)))
            .andExpect(jsonPath("$.[*].flatMatCode").value(hasItem(DEFAULT_FLAT_MAT_CODE.toString())))
            .andExpect(jsonPath("$.[*].fittedMatCode").value(hasItem(DEFAULT_FITTED_MAT_CODE.toString())))
            .andExpect(jsonPath("$.[*].pillowMatCode").value(hasItem(DEFAULT_PILLOW_MAT_CODE.toString())))
            .andExpect(jsonPath("$.[*].pillowGrid").value(hasItem(DEFAULT_PILLOW_GRID.toString())))
            .andExpect(jsonPath("$.[*].flatToBeMade").value(hasItem(DEFAULT_FLAT_TO_BE_MADE.toString())))
            .andExpect(jsonPath("$.[*].fittedToBeMade").value(hasItem(DEFAULT_FITTED_TO_BE_MADE.toString())))
            .andExpect(jsonPath("$.[*].pillowToBeMade").value(hasItem(DEFAULT_PILLOW_TO_BE_MADE.toString())))
            .andExpect(jsonPath("$.[*].flatWays").value(hasItem(DEFAULT_FLAT_WAYS)))
            .andExpect(jsonPath("$.[*].fittedWays").value(hasItem(DEFAULT_FITTED_WAYS)))
            .andExpect(jsonPath("$.[*].pillowWays").value(hasItem(DEFAULT_PILLOW_WAYS)))
            .andExpect(jsonPath("$.[*].flatPiecesPerWay").value(hasItem(DEFAULT_FLAT_PIECES_PER_WAY)))
            .andExpect(jsonPath("$.[*].fittedPiecesPerWay").value(hasItem(DEFAULT_FITTED_PIECES_PER_WAY)))
            .andExpect(jsonPath("$.[*].pillowPiecesPerWay").value(hasItem(DEFAULT_PILLOW_PIECES_PER_WAY)));
    }

    @Test
    @Transactional
    public void getLayslipheader() throws Exception {
        // Initialize the database
        layslipheaderRepository.saveAndFlush(layslipheader);

        // Get the layslipheader
        restLayslipheaderMockMvc.perform(get("/api/layslipheaders/{id}", layslipheader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(layslipheader.getId().intValue()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.poNo").value(DEFAULT_PO_NO.toString()))
            .andExpect(jsonPath("$.materialNo").value(DEFAULT_MATERIAL_NO.toString()))
            .andExpect(jsonPath("$.materialDesc").value(DEFAULT_MATERIAL_DESC.toString()))
            .andExpect(jsonPath("$.mainGrid").value(DEFAULT_MAIN_GRID.toString()))
            .andExpect(jsonPath("$.orderQty").value(DEFAULT_ORDER_QTY))
            .andExpect(jsonPath("$.remainingQty").value(DEFAULT_REMAINING_QTY))
            .andExpect(jsonPath("$.plannedQty").value(DEFAULT_PLANNED_QTY))
            .andExpect(jsonPath("$.layComponent").value(DEFAULT_LAY_COMPONENT.toString()))
            .andExpect(jsonPath("$.flat").value(DEFAULT_FLAT))
            .andExpect(jsonPath("$.fitted").value(DEFAULT_FITTED))
            .andExpect(jsonPath("$.pillow").value(DEFAULT_PILLOW))
            .andExpect(jsonPath("$.flatMatCode").value(DEFAULT_FLAT_MAT_CODE.toString()))
            .andExpect(jsonPath("$.fittedMatCode").value(DEFAULT_FITTED_MAT_CODE.toString()))
            .andExpect(jsonPath("$.pillowMatCode").value(DEFAULT_PILLOW_MAT_CODE.toString()))
            .andExpect(jsonPath("$.pillowGrid").value(DEFAULT_PILLOW_GRID.toString()))
            .andExpect(jsonPath("$.flatToBeMade").value(DEFAULT_FLAT_TO_BE_MADE.toString()))
            .andExpect(jsonPath("$.fittedToBeMade").value(DEFAULT_FITTED_TO_BE_MADE.toString()))
            .andExpect(jsonPath("$.pillowToBeMade").value(DEFAULT_PILLOW_TO_BE_MADE.toString()))
            .andExpect(jsonPath("$.flatWays").value(DEFAULT_FLAT_WAYS))
            .andExpect(jsonPath("$.fittedWays").value(DEFAULT_FITTED_WAYS))
            .andExpect(jsonPath("$.pillowWays").value(DEFAULT_PILLOW_WAYS))
            .andExpect(jsonPath("$.flatPiecesPerWay").value(DEFAULT_FLAT_PIECES_PER_WAY))
            .andExpect(jsonPath("$.fittedPiecesPerWay").value(DEFAULT_FITTED_PIECES_PER_WAY))
            .andExpect(jsonPath("$.pillowPiecesPerWay").value(DEFAULT_PILLOW_PIECES_PER_WAY));
    }

    @Test
    @Transactional
    public void getNonExistingLayslipheader() throws Exception {
        // Get the layslipheader
        restLayslipheaderMockMvc.perform(get("/api/layslipheaders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLayslipheader() throws Exception {
        // Initialize the database
        layslipheaderRepository.saveAndFlush(layslipheader);
        int databaseSizeBeforeUpdate = layslipheaderRepository.findAll().size();

        // Update the layslipheader
        Layslipheader updatedLayslipheader = layslipheaderRepository.findOne(layslipheader.getId());
        // Disconnect from session so that the updates on updatedLayslipheader are not directly saved in db
        em.detach(updatedLayslipheader);
        updatedLayslipheader
            .priority(UPDATED_PRIORITY)
            .poNo(UPDATED_PO_NO)
            .materialNo(UPDATED_MATERIAL_NO)
            .materialDesc(UPDATED_MATERIAL_DESC)
            .mainGrid(UPDATED_MAIN_GRID)
            .orderQty(UPDATED_ORDER_QTY)
            .remainingQty(UPDATED_REMAINING_QTY)
            .plannedQty(UPDATED_PLANNED_QTY)
            .layComponent(UPDATED_LAY_COMPONENT)
            .flat(UPDATED_FLAT)
            .fitted(UPDATED_FITTED)
            .pillow(UPDATED_PILLOW)
            .flatMatCode(UPDATED_FLAT_MAT_CODE)
            .fittedMatCode(UPDATED_FITTED_MAT_CODE)
            .pillowMatCode(UPDATED_PILLOW_MAT_CODE)
            .pillowGrid(UPDATED_PILLOW_GRID)
            .flatToBeMade(UPDATED_FLAT_TO_BE_MADE)
            .fittedToBeMade(UPDATED_FITTED_TO_BE_MADE)
            .pillowToBeMade(UPDATED_PILLOW_TO_BE_MADE)
            .flatWays(UPDATED_FLAT_WAYS)
            .fittedWays(UPDATED_FITTED_WAYS)
            .pillowWays(UPDATED_PILLOW_WAYS)
            .flatPiecesPerWay(UPDATED_FLAT_PIECES_PER_WAY)
            .fittedPiecesPerWay(UPDATED_FITTED_PIECES_PER_WAY)
            .pillowPiecesPerWay(UPDATED_PILLOW_PIECES_PER_WAY);

        restLayslipheaderMockMvc.perform(put("/api/layslipheaders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLayslipheader)))
            .andExpect(status().isOk());

        // Validate the Layslipheader in the database
        List<Layslipheader> layslipheaderList = layslipheaderRepository.findAll();
        assertThat(layslipheaderList).hasSize(databaseSizeBeforeUpdate);
        Layslipheader testLayslipheader = layslipheaderList.get(layslipheaderList.size() - 1);
        assertThat(testLayslipheader.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testLayslipheader.getPoNo()).isEqualTo(UPDATED_PO_NO);
        assertThat(testLayslipheader.getMaterialNo()).isEqualTo(UPDATED_MATERIAL_NO);
        assertThat(testLayslipheader.getMaterialDesc()).isEqualTo(UPDATED_MATERIAL_DESC);
        assertThat(testLayslipheader.getMainGrid()).isEqualTo(UPDATED_MAIN_GRID);
        assertThat(testLayslipheader.getOrderQty()).isEqualTo(UPDATED_ORDER_QTY);
        assertThat(testLayslipheader.getRemainingQty()).isEqualTo(UPDATED_REMAINING_QTY);
        assertThat(testLayslipheader.getPlannedQty()).isEqualTo(UPDATED_PLANNED_QTY);
        assertThat(testLayslipheader.getLayComponent()).isEqualTo(UPDATED_LAY_COMPONENT);
        assertThat(testLayslipheader.getFlat()).isEqualTo(UPDATED_FLAT);
        assertThat(testLayslipheader.getFitted()).isEqualTo(UPDATED_FITTED);
        assertThat(testLayslipheader.getPillow()).isEqualTo(UPDATED_PILLOW);
        assertThat(testLayslipheader.getFlatMatCode()).isEqualTo(UPDATED_FLAT_MAT_CODE);
        assertThat(testLayslipheader.getFittedMatCode()).isEqualTo(UPDATED_FITTED_MAT_CODE);
        assertThat(testLayslipheader.getPillowMatCode()).isEqualTo(UPDATED_PILLOW_MAT_CODE);
        assertThat(testLayslipheader.getPillowGrid()).isEqualTo(UPDATED_PILLOW_GRID);
        assertThat(testLayslipheader.getFlatToBeMade()).isEqualTo(UPDATED_FLAT_TO_BE_MADE);
        assertThat(testLayslipheader.getFittedToBeMade()).isEqualTo(UPDATED_FITTED_TO_BE_MADE);
        assertThat(testLayslipheader.getPillowToBeMade()).isEqualTo(UPDATED_PILLOW_TO_BE_MADE);
        assertThat(testLayslipheader.getFlatWays()).isEqualTo(UPDATED_FLAT_WAYS);
        assertThat(testLayslipheader.getFittedWays()).isEqualTo(UPDATED_FITTED_WAYS);
        assertThat(testLayslipheader.getPillowWays()).isEqualTo(UPDATED_PILLOW_WAYS);
        assertThat(testLayslipheader.getFlatPiecesPerWay()).isEqualTo(UPDATED_FLAT_PIECES_PER_WAY);
        assertThat(testLayslipheader.getFittedPiecesPerWay()).isEqualTo(UPDATED_FITTED_PIECES_PER_WAY);
        assertThat(testLayslipheader.getPillowPiecesPerWay()).isEqualTo(UPDATED_PILLOW_PIECES_PER_WAY);
    }

    @Test
    @Transactional
    public void updateNonExistingLayslipheader() throws Exception {
        int databaseSizeBeforeUpdate = layslipheaderRepository.findAll().size();

        // Create the Layslipheader

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLayslipheaderMockMvc.perform(put("/api/layslipheaders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(layslipheader)))
            .andExpect(status().isCreated());

        // Validate the Layslipheader in the database
        List<Layslipheader> layslipheaderList = layslipheaderRepository.findAll();
        assertThat(layslipheaderList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLayslipheader() throws Exception {
        // Initialize the database
        layslipheaderRepository.saveAndFlush(layslipheader);
        int databaseSizeBeforeDelete = layslipheaderRepository.findAll().size();

        // Get the layslipheader
        restLayslipheaderMockMvc.perform(delete("/api/layslipheaders/{id}", layslipheader.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Layslipheader> layslipheaderList = layslipheaderRepository.findAll();
        assertThat(layslipheaderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Layslipheader.class);
        Layslipheader layslipheader1 = new Layslipheader();
        layslipheader1.setId(1L);
        Layslipheader layslipheader2 = new Layslipheader();
        layslipheader2.setId(layslipheader1.getId());
        assertThat(layslipheader1).isEqualTo(layslipheader2);
        layslipheader2.setId(2L);
        assertThat(layslipheader1).isNotEqualTo(layslipheader2);
        layslipheader1.setId(null);
        assertThat(layslipheader1).isNotEqualTo(layslipheader2);
    }
}
