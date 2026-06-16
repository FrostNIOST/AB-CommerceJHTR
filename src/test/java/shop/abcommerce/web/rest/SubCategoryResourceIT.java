package shop.abcommerce.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static shop.abcommerce.domain.SubCategoryAsserts.*;
import static shop.abcommerce.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import shop.abcommerce.IntegrationTest;
import shop.abcommerce.domain.Category;
import shop.abcommerce.domain.SubCategory;
import shop.abcommerce.domain.enumeration.State;
import shop.abcommerce.repository.SubCategoryRepository;
import shop.abcommerce.service.SubCategoryService;
import shop.abcommerce.service.dto.SubCategoryDTO;
import shop.abcommerce.service.mapper.SubCategoryMapper;

/**
 * Integration tests for the {@link SubCategoryResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class SubCategoryResourceIT {

    private static final String DEFAULT_SUB_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUB_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final State DEFAULT_STATE = State.ACTIVE;
    private static final State UPDATED_STATE = State.INACTIVE;

    private static final String ENTITY_API_URL = "/api/sub-categories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Mock
    private SubCategoryRepository subCategoryRepositoryMock;

    @Autowired
    private SubCategoryMapper subCategoryMapper;

    @Mock
    private SubCategoryService subCategoryServiceMock;

    @Autowired
    private MockMvc restSubCategoryMockMvc;

    private SubCategory subCategory;

    private SubCategory insertedSubCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubCategory createEntity() {
        SubCategory subCategory = new SubCategory()
            .subCategoryName(DEFAULT_SUB_CATEGORY_NAME)
            .description(DEFAULT_DESCRIPTION)
            .state(DEFAULT_STATE);
        // Add required entity
        Category category;
        category = CategoryResourceIT.createEntity();
        category.setId("fixed-id-for-tests");
        subCategory.setCategory(category);
        return subCategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubCategory createUpdatedEntity() {
        SubCategory updatedSubCategory = new SubCategory()
            .subCategoryName(UPDATED_SUB_CATEGORY_NAME)
            .description(UPDATED_DESCRIPTION)
            .state(UPDATED_STATE);
        // Add required entity
        Category category;
        category = CategoryResourceIT.createUpdatedEntity();
        category.setId("fixed-id-for-tests");
        updatedSubCategory.setCategory(category);
        return updatedSubCategory;
    }

    @BeforeEach
    void initTest() {
        subCategory = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedSubCategory != null) {
            subCategoryRepository.delete(insertedSubCategory);
            insertedSubCategory = null;
        }
    }

    @Test
    void createSubCategory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SubCategory
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(subCategory);
        var returnedSubCategoryDTO = om.readValue(
            restSubCategoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subCategoryDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SubCategoryDTO.class
        );

        // Validate the SubCategory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedSubCategory = subCategoryMapper.toEntity(returnedSubCategoryDTO);
        assertSubCategoryUpdatableFieldsEquals(returnedSubCategory, getPersistedSubCategory(returnedSubCategory));

        insertedSubCategory = returnedSubCategory;
    }

    @Test
    void createSubCategoryWithExistingId() throws Exception {
        // Create the SubCategory with an existing ID
        subCategory.setId("existing_id");
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(subCategory);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubCategoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SubCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void checkSubCategoryNameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        subCategory.setSubCategoryName(null);

        // Create the SubCategory, which fails.
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(subCategory);

        restSubCategoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subCategoryDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkStateIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        subCategory.setState(null);

        // Create the SubCategory, which fails.
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(subCategory);

        restSubCategoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subCategoryDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void getAllSubCategories() throws Exception {
        // Initialize the database
        insertedSubCategory = subCategoryRepository.save(subCategory);

        // Get all the subCategoryList
        restSubCategoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subCategory.getId())))
            .andExpect(jsonPath("$.[*].subCategoryName").value(hasItem(DEFAULT_SUB_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllSubCategoriesWithEagerRelationshipsIsEnabled() throws Exception {
        when(subCategoryServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restSubCategoryMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(subCategoryServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllSubCategoriesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(subCategoryServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restSubCategoryMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(subCategoryRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void getSubCategory() throws Exception {
        // Initialize the database
        insertedSubCategory = subCategoryRepository.save(subCategory);

        // Get the subCategory
        restSubCategoryMockMvc
            .perform(get(ENTITY_API_URL_ID, subCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subCategory.getId()))
            .andExpect(jsonPath("$.subCategoryName").value(DEFAULT_SUB_CATEGORY_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()));
    }

    @Test
    void getNonExistingSubCategory() throws Exception {
        // Get the subCategory
        restSubCategoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingSubCategory() throws Exception {
        // Initialize the database
        insertedSubCategory = subCategoryRepository.save(subCategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the subCategory
        SubCategory updatedSubCategory = subCategoryRepository.findById(subCategory.getId()).orElseThrow();
        updatedSubCategory.subCategoryName(UPDATED_SUB_CATEGORY_NAME).description(UPDATED_DESCRIPTION).state(UPDATED_STATE);
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(updatedSubCategory);

        restSubCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, subCategoryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(subCategoryDTO))
            )
            .andExpect(status().isOk());

        // Validate the SubCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSubCategoryToMatchAllProperties(updatedSubCategory);
    }

    @Test
    void putNonExistingSubCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subCategory.setId(UUID.randomUUID().toString());

        // Create the SubCategory
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(subCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, subCategoryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(subCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SubCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSubCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subCategory.setId(UUID.randomUUID().toString());

        // Create the SubCategory
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(subCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(subCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SubCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSubCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subCategory.setId(UUID.randomUUID().toString());

        // Create the SubCategory
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(subCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubCategoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subCategoryDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SubCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSubCategoryWithPatch() throws Exception {
        // Initialize the database
        insertedSubCategory = subCategoryRepository.save(subCategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the subCategory using partial update
        SubCategory partialUpdatedSubCategory = new SubCategory();
        partialUpdatedSubCategory.setId(subCategory.getId());

        partialUpdatedSubCategory.subCategoryName(UPDATED_SUB_CATEGORY_NAME).description(UPDATED_DESCRIPTION);

        restSubCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSubCategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSubCategory))
            )
            .andExpect(status().isOk());

        // Validate the SubCategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSubCategoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSubCategory, subCategory),
            getPersistedSubCategory(subCategory)
        );
    }

    @Test
    void fullUpdateSubCategoryWithPatch() throws Exception {
        // Initialize the database
        insertedSubCategory = subCategoryRepository.save(subCategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the subCategory using partial update
        SubCategory partialUpdatedSubCategory = new SubCategory();
        partialUpdatedSubCategory.setId(subCategory.getId());

        partialUpdatedSubCategory.subCategoryName(UPDATED_SUB_CATEGORY_NAME).description(UPDATED_DESCRIPTION).state(UPDATED_STATE);

        restSubCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSubCategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSubCategory))
            )
            .andExpect(status().isOk());

        // Validate the SubCategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSubCategoryUpdatableFieldsEquals(partialUpdatedSubCategory, getPersistedSubCategory(partialUpdatedSubCategory));
    }

    @Test
    void patchNonExistingSubCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subCategory.setId(UUID.randomUUID().toString());

        // Create the SubCategory
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(subCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, subCategoryDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(subCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SubCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSubCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subCategory.setId(UUID.randomUUID().toString());

        // Create the SubCategory
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(subCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(subCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SubCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSubCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subCategory.setId(UUID.randomUUID().toString());

        // Create the SubCategory
        SubCategoryDTO subCategoryDTO = subCategoryMapper.toDto(subCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubCategoryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(subCategoryDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SubCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSubCategory() throws Exception {
        // Initialize the database
        insertedSubCategory = subCategoryRepository.save(subCategory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the subCategory
        restSubCategoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, subCategory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return subCategoryRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected SubCategory getPersistedSubCategory(SubCategory subCategory) {
        return subCategoryRepository.findById(subCategory.getId()).orElseThrow();
    }

    protected void assertPersistedSubCategoryToMatchAllProperties(SubCategory expectedSubCategory) {
        assertSubCategoryAllPropertiesEquals(expectedSubCategory, getPersistedSubCategory(expectedSubCategory));
    }

    protected void assertPersistedSubCategoryToMatchUpdatableProperties(SubCategory expectedSubCategory) {
        assertSubCategoryAllUpdatablePropertiesEquals(expectedSubCategory, getPersistedSubCategory(expectedSubCategory));
    }
}
