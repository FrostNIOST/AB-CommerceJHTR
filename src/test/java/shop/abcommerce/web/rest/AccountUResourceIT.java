package shop.abcommerce.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static shop.abcommerce.domain.AccountUAsserts.*;
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
import shop.abcommerce.domain.AccountU;
import shop.abcommerce.domain.DocumentType;
import shop.abcommerce.domain.User;
import shop.abcommerce.repository.AccountURepository;
import shop.abcommerce.repository.UserRepository;
import shop.abcommerce.service.AccountUService;
import shop.abcommerce.service.dto.AccountUDTO;
import shop.abcommerce.service.mapper.AccountUMapper;

/**
 * Integration tests for the {@link AccountUResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class AccountUResourceIT {

    private static final String DEFAULT_DOCUMENT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SECOND_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SECOND_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SECOND_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SECOND_LAST_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/account-us";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AccountURepository accountURepository;

    @Autowired
    private UserRepository userRepository;

    @Mock
    private AccountURepository accountURepositoryMock;

    @Autowired
    private AccountUMapper accountUMapper;

    @Mock
    private AccountUService accountUServiceMock;

    @Autowired
    private MockMvc restAccountUMockMvc;

    private AccountU accountU;

    private AccountU insertedAccountU;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountU createEntity() {
        AccountU accountU = new AccountU()
            .documentNumber(DEFAULT_DOCUMENT_NUMBER)
            .firstName(DEFAULT_FIRST_NAME)
            .secondName(DEFAULT_SECOND_NAME)
            .firstLastName(DEFAULT_FIRST_LAST_NAME)
            .secondLastName(DEFAULT_SECOND_LAST_NAME);
        // Add required entity
        User user = UserResourceIT.createEntity();
        user.setId("fixed-id-for-tests");
        accountU.setUser(user);
        // Add required entity
        DocumentType documentType;
        documentType = DocumentTypeResourceIT.createEntity();
        documentType.setId("fixed-id-for-tests");
        accountU.setDocumentType(documentType);
        return accountU;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountU createUpdatedEntity() {
        AccountU updatedAccountU = new AccountU()
            .documentNumber(UPDATED_DOCUMENT_NUMBER)
            .firstName(UPDATED_FIRST_NAME)
            .secondName(UPDATED_SECOND_NAME)
            .firstLastName(UPDATED_FIRST_LAST_NAME)
            .secondLastName(UPDATED_SECOND_LAST_NAME);
        // Add required entity
        User user = UserResourceIT.createEntity();
        user.setId("fixed-id-for-tests");
        updatedAccountU.setUser(user);
        // Add required entity
        DocumentType documentType;
        documentType = DocumentTypeResourceIT.createUpdatedEntity();
        documentType.setId("fixed-id-for-tests");
        updatedAccountU.setDocumentType(documentType);
        return updatedAccountU;
    }

    @BeforeEach
    void initTest() {
        accountU = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedAccountU != null) {
            accountURepository.delete(insertedAccountU);
            insertedAccountU = null;
        }
        userRepository.deleteAll();
    }

    @Test
    void createAccountU() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AccountU
        AccountUDTO accountUDTO = accountUMapper.toDto(accountU);
        var returnedAccountUDTO = om.readValue(
            restAccountUMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(accountUDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AccountUDTO.class
        );

        // Validate the AccountU in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedAccountU = accountUMapper.toEntity(returnedAccountUDTO);
        assertAccountUUpdatableFieldsEquals(returnedAccountU, getPersistedAccountU(returnedAccountU));

        insertedAccountU = returnedAccountU;
    }

    @Test
    void createAccountUWithExistingId() throws Exception {
        // Create the AccountU with an existing ID
        accountU.setId("existing_id");
        AccountUDTO accountUDTO = accountUMapper.toDto(accountU);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountUMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(accountUDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountU in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void checkDocumentNumberIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accountU.setDocumentNumber(null);

        // Create the AccountU, which fails.
        AccountUDTO accountUDTO = accountUMapper.toDto(accountU);

        restAccountUMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(accountUDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkFirstNameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accountU.setFirstName(null);

        // Create the AccountU, which fails.
        AccountUDTO accountUDTO = accountUMapper.toDto(accountU);

        restAccountUMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(accountUDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkFirstLastNameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        accountU.setFirstLastName(null);

        // Create the AccountU, which fails.
        AccountUDTO accountUDTO = accountUMapper.toDto(accountU);

        restAccountUMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(accountUDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void getAllAccountUS() throws Exception {
        // Initialize the database
        insertedAccountU = accountURepository.save(accountU);

        // Get all the accountUList
        restAccountUMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountU.getId())))
            .andExpect(jsonPath("$.[*].documentNumber").value(hasItem(DEFAULT_DOCUMENT_NUMBER)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].secondName").value(hasItem(DEFAULT_SECOND_NAME)))
            .andExpect(jsonPath("$.[*].firstLastName").value(hasItem(DEFAULT_FIRST_LAST_NAME)))
            .andExpect(jsonPath("$.[*].secondLastName").value(hasItem(DEFAULT_SECOND_LAST_NAME)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllAccountUSWithEagerRelationshipsIsEnabled() throws Exception {
        when(accountUServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restAccountUMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(accountUServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllAccountUSWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(accountUServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restAccountUMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(accountURepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void getAccountU() throws Exception {
        // Initialize the database
        insertedAccountU = accountURepository.save(accountU);

        // Get the accountU
        restAccountUMockMvc
            .perform(get(ENTITY_API_URL_ID, accountU.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(accountU.getId()))
            .andExpect(jsonPath("$.documentNumber").value(DEFAULT_DOCUMENT_NUMBER))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.secondName").value(DEFAULT_SECOND_NAME))
            .andExpect(jsonPath("$.firstLastName").value(DEFAULT_FIRST_LAST_NAME))
            .andExpect(jsonPath("$.secondLastName").value(DEFAULT_SECOND_LAST_NAME));
    }

    @Test
    void getNonExistingAccountU() throws Exception {
        // Get the accountU
        restAccountUMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingAccountU() throws Exception {
        // Initialize the database
        insertedAccountU = accountURepository.save(accountU);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the accountU
        AccountU updatedAccountU = accountURepository.findById(accountU.getId()).orElseThrow();
        updatedAccountU
            .documentNumber(UPDATED_DOCUMENT_NUMBER)
            .firstName(UPDATED_FIRST_NAME)
            .secondName(UPDATED_SECOND_NAME)
            .firstLastName(UPDATED_FIRST_LAST_NAME)
            .secondLastName(UPDATED_SECOND_LAST_NAME);
        AccountUDTO accountUDTO = accountUMapper.toDto(updatedAccountU);

        restAccountUMockMvc
            .perform(
                put(ENTITY_API_URL_ID, accountUDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(accountUDTO))
            )
            .andExpect(status().isOk());

        // Validate the AccountU in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAccountUToMatchAllProperties(updatedAccountU);
    }

    @Test
    void putNonExistingAccountU() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accountU.setId(UUID.randomUUID().toString());

        // Create the AccountU
        AccountUDTO accountUDTO = accountUMapper.toDto(accountU);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountUMockMvc
            .perform(
                put(ENTITY_API_URL_ID, accountUDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(accountUDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AccountU in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAccountU() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accountU.setId(UUID.randomUUID().toString());

        // Create the AccountU
        AccountUDTO accountUDTO = accountUMapper.toDto(accountU);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccountUMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(accountUDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AccountU in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAccountU() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accountU.setId(UUID.randomUUID().toString());

        // Create the AccountU
        AccountUDTO accountUDTO = accountUMapper.toDto(accountU);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccountUMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(accountUDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AccountU in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAccountUWithPatch() throws Exception {
        // Initialize the database
        insertedAccountU = accountURepository.save(accountU);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the accountU using partial update
        AccountU partialUpdatedAccountU = new AccountU();
        partialUpdatedAccountU.setId(accountU.getId());

        partialUpdatedAccountU.firstName(UPDATED_FIRST_NAME).firstLastName(UPDATED_FIRST_LAST_NAME);

        restAccountUMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAccountU.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAccountU))
            )
            .andExpect(status().isOk());

        // Validate the AccountU in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAccountUUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedAccountU, accountU), getPersistedAccountU(accountU));
    }

    @Test
    void fullUpdateAccountUWithPatch() throws Exception {
        // Initialize the database
        insertedAccountU = accountURepository.save(accountU);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the accountU using partial update
        AccountU partialUpdatedAccountU = new AccountU();
        partialUpdatedAccountU.setId(accountU.getId());

        partialUpdatedAccountU
            .documentNumber(UPDATED_DOCUMENT_NUMBER)
            .firstName(UPDATED_FIRST_NAME)
            .secondName(UPDATED_SECOND_NAME)
            .firstLastName(UPDATED_FIRST_LAST_NAME)
            .secondLastName(UPDATED_SECOND_LAST_NAME);

        restAccountUMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAccountU.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAccountU))
            )
            .andExpect(status().isOk());

        // Validate the AccountU in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAccountUUpdatableFieldsEquals(partialUpdatedAccountU, getPersistedAccountU(partialUpdatedAccountU));
    }

    @Test
    void patchNonExistingAccountU() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accountU.setId(UUID.randomUUID().toString());

        // Create the AccountU
        AccountUDTO accountUDTO = accountUMapper.toDto(accountU);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountUMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, accountUDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(accountUDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AccountU in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAccountU() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accountU.setId(UUID.randomUUID().toString());

        // Create the AccountU
        AccountUDTO accountUDTO = accountUMapper.toDto(accountU);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccountUMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(accountUDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AccountU in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAccountU() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accountU.setId(UUID.randomUUID().toString());

        // Create the AccountU
        AccountUDTO accountUDTO = accountUMapper.toDto(accountU);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccountUMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(accountUDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AccountU in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAccountU() throws Exception {
        // Initialize the database
        insertedAccountU = accountURepository.save(accountU);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the accountU
        restAccountUMockMvc
            .perform(delete(ENTITY_API_URL_ID, accountU.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return accountURepository.count();
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

    protected AccountU getPersistedAccountU(AccountU accountU) {
        return accountURepository.findById(accountU.getId()).orElseThrow();
    }

    protected void assertPersistedAccountUToMatchAllProperties(AccountU expectedAccountU) {
        assertAccountUAllPropertiesEquals(expectedAccountU, getPersistedAccountU(expectedAccountU));
    }

    protected void assertPersistedAccountUToMatchUpdatableProperties(AccountU expectedAccountU) {
        assertAccountUAllUpdatablePropertiesEquals(expectedAccountU, getPersistedAccountU(expectedAccountU));
    }
}
