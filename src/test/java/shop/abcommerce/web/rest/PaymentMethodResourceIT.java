package shop.abcommerce.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static shop.abcommerce.domain.PaymentMethodAsserts.*;
import static shop.abcommerce.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import shop.abcommerce.IntegrationTest;
import shop.abcommerce.domain.PaymentMethod;
import shop.abcommerce.domain.enumeration.State;
import shop.abcommerce.repository.PaymentMethodRepository;
import shop.abcommerce.service.dto.PaymentMethodDTO;
import shop.abcommerce.service.mapper.PaymentMethodMapper;

/**
 * Integration tests for the {@link PaymentMethodResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PaymentMethodResourceIT {

    private static final String DEFAULT_NAME_PAYMENT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_NAME_PAYMENT_METHOD = "BBBBBBBBBB";

    private static final String DEFAULT_COD = "AAAAAAAAAA";
    private static final String UPDATED_COD = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_API_KEY = "AAAAAAAAAA";
    private static final String UPDATED_API_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_ENDPOINT = "AAAAAAAAAA";
    private static final String UPDATED_ENDPOINT = "BBBBBBBBBB";

    private static final State DEFAULT_STATE = State.ACTIVE;
    private static final State UPDATED_STATE = State.INACTIVE;

    private static final String ENTITY_API_URL = "/api/payment-methods";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Autowired
    private MockMvc restPaymentMethodMockMvc;

    private PaymentMethod paymentMethod;

    private PaymentMethod insertedPaymentMethod;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentMethod createEntity() {
        return new PaymentMethod()
            .namePaymentMethod(DEFAULT_NAME_PAYMENT_METHOD)
            .cod(DEFAULT_COD)
            .description(DEFAULT_DESCRIPTION)
            .type(DEFAULT_TYPE)
            .apiKey(DEFAULT_API_KEY)
            .endpoint(DEFAULT_ENDPOINT)
            .state(DEFAULT_STATE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentMethod createUpdatedEntity() {
        return new PaymentMethod()
            .namePaymentMethod(UPDATED_NAME_PAYMENT_METHOD)
            .cod(UPDATED_COD)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .apiKey(UPDATED_API_KEY)
            .endpoint(UPDATED_ENDPOINT)
            .state(UPDATED_STATE);
    }

    @BeforeEach
    void initTest() {
        paymentMethod = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedPaymentMethod != null) {
            paymentMethodRepository.delete(insertedPaymentMethod);
            insertedPaymentMethod = null;
        }
    }

    @Test
    void createPaymentMethod() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PaymentMethod
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);
        var returnedPaymentMethodDTO = om.readValue(
            restPaymentMethodMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentMethodDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PaymentMethodDTO.class
        );

        // Validate the PaymentMethod in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedPaymentMethod = paymentMethodMapper.toEntity(returnedPaymentMethodDTO);
        assertPaymentMethodUpdatableFieldsEquals(returnedPaymentMethod, getPersistedPaymentMethod(returnedPaymentMethod));

        insertedPaymentMethod = returnedPaymentMethod;
    }

    @Test
    void createPaymentMethodWithExistingId() throws Exception {
        // Create the PaymentMethod with an existing ID
        paymentMethod.setId("existing_id");
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentMethodMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentMethodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentMethod in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void checkNamePaymentMethodIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        paymentMethod.setNamePaymentMethod(null);

        // Create the PaymentMethod, which fails.
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        restPaymentMethodMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentMethodDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkCodIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        paymentMethod.setCod(null);

        // Create the PaymentMethod, which fails.
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        restPaymentMethodMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentMethodDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkTypeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        paymentMethod.setType(null);

        // Create the PaymentMethod, which fails.
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        restPaymentMethodMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentMethodDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkStateIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        paymentMethod.setState(null);

        // Create the PaymentMethod, which fails.
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        restPaymentMethodMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentMethodDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void getAllPaymentMethods() throws Exception {
        // Initialize the database
        insertedPaymentMethod = paymentMethodRepository.save(paymentMethod);

        // Get all the paymentMethodList
        restPaymentMethodMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentMethod.getId())))
            .andExpect(jsonPath("$.[*].namePaymentMethod").value(hasItem(DEFAULT_NAME_PAYMENT_METHOD)))
            .andExpect(jsonPath("$.[*].cod").value(hasItem(DEFAULT_COD)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].apiKey").value(hasItem(DEFAULT_API_KEY)))
            .andExpect(jsonPath("$.[*].endpoint").value(hasItem(DEFAULT_ENDPOINT)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));
    }

    @Test
    void getPaymentMethod() throws Exception {
        // Initialize the database
        insertedPaymentMethod = paymentMethodRepository.save(paymentMethod);

        // Get the paymentMethod
        restPaymentMethodMockMvc
            .perform(get(ENTITY_API_URL_ID, paymentMethod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentMethod.getId()))
            .andExpect(jsonPath("$.namePaymentMethod").value(DEFAULT_NAME_PAYMENT_METHOD))
            .andExpect(jsonPath("$.cod").value(DEFAULT_COD))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.apiKey").value(DEFAULT_API_KEY))
            .andExpect(jsonPath("$.endpoint").value(DEFAULT_ENDPOINT))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()));
    }

    @Test
    void getNonExistingPaymentMethod() throws Exception {
        // Get the paymentMethod
        restPaymentMethodMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingPaymentMethod() throws Exception {
        // Initialize the database
        insertedPaymentMethod = paymentMethodRepository.save(paymentMethod);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentMethod
        PaymentMethod updatedPaymentMethod = paymentMethodRepository.findById(paymentMethod.getId()).orElseThrow();
        updatedPaymentMethod
            .namePaymentMethod(UPDATED_NAME_PAYMENT_METHOD)
            .cod(UPDATED_COD)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .apiKey(UPDATED_API_KEY)
            .endpoint(UPDATED_ENDPOINT)
            .state(UPDATED_STATE);
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(updatedPaymentMethod);

        restPaymentMethodMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentMethodDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentMethodDTO))
            )
            .andExpect(status().isOk());

        // Validate the PaymentMethod in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPaymentMethodToMatchAllProperties(updatedPaymentMethod);
    }

    @Test
    void putNonExistingPaymentMethod() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentMethod.setId(UUID.randomUUID().toString());

        // Create the PaymentMethod
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentMethodMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentMethodDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentMethodDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentMethod in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPaymentMethod() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentMethod.setId(UUID.randomUUID().toString());

        // Create the PaymentMethod
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentMethodMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentMethodDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentMethod in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPaymentMethod() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentMethod.setId(UUID.randomUUID().toString());

        // Create the PaymentMethod
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentMethodMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentMethodDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PaymentMethod in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePaymentMethodWithPatch() throws Exception {
        // Initialize the database
        insertedPaymentMethod = paymentMethodRepository.save(paymentMethod);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentMethod using partial update
        PaymentMethod partialUpdatedPaymentMethod = new PaymentMethod();
        partialUpdatedPaymentMethod.setId(paymentMethod.getId());

        partialUpdatedPaymentMethod.namePaymentMethod(UPDATED_NAME_PAYMENT_METHOD).endpoint(UPDATED_ENDPOINT);

        restPaymentMethodMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentMethod.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentMethod))
            )
            .andExpect(status().isOk());

        // Validate the PaymentMethod in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymentMethodUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPaymentMethod, paymentMethod),
            getPersistedPaymentMethod(paymentMethod)
        );
    }

    @Test
    void fullUpdatePaymentMethodWithPatch() throws Exception {
        // Initialize the database
        insertedPaymentMethod = paymentMethodRepository.save(paymentMethod);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentMethod using partial update
        PaymentMethod partialUpdatedPaymentMethod = new PaymentMethod();
        partialUpdatedPaymentMethod.setId(paymentMethod.getId());

        partialUpdatedPaymentMethod
            .namePaymentMethod(UPDATED_NAME_PAYMENT_METHOD)
            .cod(UPDATED_COD)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .apiKey(UPDATED_API_KEY)
            .endpoint(UPDATED_ENDPOINT)
            .state(UPDATED_STATE);

        restPaymentMethodMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentMethod.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentMethod))
            )
            .andExpect(status().isOk());

        // Validate the PaymentMethod in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymentMethodUpdatableFieldsEquals(partialUpdatedPaymentMethod, getPersistedPaymentMethod(partialUpdatedPaymentMethod));
    }

    @Test
    void patchNonExistingPaymentMethod() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentMethod.setId(UUID.randomUUID().toString());

        // Create the PaymentMethod
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentMethodMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, paymentMethodDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentMethodDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentMethod in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPaymentMethod() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentMethod.setId(UUID.randomUUID().toString());

        // Create the PaymentMethod
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentMethodMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentMethodDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentMethod in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPaymentMethod() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentMethod.setId(UUID.randomUUID().toString());

        // Create the PaymentMethod
        PaymentMethodDTO paymentMethodDTO = paymentMethodMapper.toDto(paymentMethod);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentMethodMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(paymentMethodDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PaymentMethod in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePaymentMethod() throws Exception {
        // Initialize the database
        insertedPaymentMethod = paymentMethodRepository.save(paymentMethod);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the paymentMethod
        restPaymentMethodMockMvc
            .perform(delete(ENTITY_API_URL_ID, paymentMethod.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return paymentMethodRepository.count();
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

    protected PaymentMethod getPersistedPaymentMethod(PaymentMethod paymentMethod) {
        return paymentMethodRepository.findById(paymentMethod.getId()).orElseThrow();
    }

    protected void assertPersistedPaymentMethodToMatchAllProperties(PaymentMethod expectedPaymentMethod) {
        assertPaymentMethodAllPropertiesEquals(expectedPaymentMethod, getPersistedPaymentMethod(expectedPaymentMethod));
    }

    protected void assertPersistedPaymentMethodToMatchUpdatableProperties(PaymentMethod expectedPaymentMethod) {
        assertPaymentMethodAllUpdatablePropertiesEquals(expectedPaymentMethod, getPersistedPaymentMethod(expectedPaymentMethod));
    }
}
