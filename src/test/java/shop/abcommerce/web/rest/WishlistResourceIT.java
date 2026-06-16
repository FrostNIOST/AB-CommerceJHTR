package shop.abcommerce.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static shop.abcommerce.domain.WishlistAsserts.*;
import static shop.abcommerce.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
import shop.abcommerce.domain.Wishlist;
import shop.abcommerce.domain.enumeration.State;
import shop.abcommerce.repository.WishlistRepository;
import shop.abcommerce.service.WishlistService;
import shop.abcommerce.service.dto.WishlistDTO;
import shop.abcommerce.service.mapper.WishlistMapper;

/**
 * Integration tests for the {@link WishlistResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class WishlistResourceIT {

    private static final State DEFAULT_STATE = State.ACTIVE;
    private static final State UPDATED_STATE = State.INACTIVE;

    private static final Instant DEFAULT_UPDATE_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/wishlists";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Mock
    private WishlistRepository wishlistRepositoryMock;

    @Autowired
    private WishlistMapper wishlistMapper;

    @Mock
    private WishlistService wishlistServiceMock;

    @Autowired
    private MockMvc restWishlistMockMvc;

    private Wishlist wishlist;

    private Wishlist insertedWishlist;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wishlist createEntity() {
        Wishlist wishlist = new Wishlist().state(DEFAULT_STATE).updateAt(DEFAULT_UPDATE_AT);
        // Add required entity
        AccountU accountU;
        accountU = AccountUResourceIT.createEntity();
        accountU.setId("fixed-id-for-tests");
        wishlist.setAccount(accountU);
        return wishlist;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wishlist createUpdatedEntity() {
        Wishlist updatedWishlist = new Wishlist().state(UPDATED_STATE).updateAt(UPDATED_UPDATE_AT);
        // Add required entity
        AccountU accountU;
        accountU = AccountUResourceIT.createUpdatedEntity();
        accountU.setId("fixed-id-for-tests");
        updatedWishlist.setAccount(accountU);
        return updatedWishlist;
    }

    @BeforeEach
    void initTest() {
        wishlist = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedWishlist != null) {
            wishlistRepository.delete(insertedWishlist);
            insertedWishlist = null;
        }
    }

    @Test
    void createWishlist() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Wishlist
        WishlistDTO wishlistDTO = wishlistMapper.toDto(wishlist);
        var returnedWishlistDTO = om.readValue(
            restWishlistMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wishlistDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            WishlistDTO.class
        );

        // Validate the Wishlist in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedWishlist = wishlistMapper.toEntity(returnedWishlistDTO);
        assertWishlistUpdatableFieldsEquals(returnedWishlist, getPersistedWishlist(returnedWishlist));

        insertedWishlist = returnedWishlist;
    }

    @Test
    void createWishlistWithExistingId() throws Exception {
        // Create the Wishlist with an existing ID
        wishlist.setId("existing_id");
        WishlistDTO wishlistDTO = wishlistMapper.toDto(wishlist);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWishlistMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wishlistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Wishlist in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void checkStateIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        wishlist.setState(null);

        // Create the Wishlist, which fails.
        WishlistDTO wishlistDTO = wishlistMapper.toDto(wishlist);

        restWishlistMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wishlistDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkUpdateAtIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        wishlist.setUpdateAt(null);

        // Create the Wishlist, which fails.
        WishlistDTO wishlistDTO = wishlistMapper.toDto(wishlist);

        restWishlistMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wishlistDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void getAllWishlists() throws Exception {
        // Initialize the database
        insertedWishlist = wishlistRepository.save(wishlist);

        // Get all the wishlistList
        restWishlistMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wishlist.getId())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].updateAt").value(hasItem(DEFAULT_UPDATE_AT.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllWishlistsWithEagerRelationshipsIsEnabled() throws Exception {
        when(wishlistServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restWishlistMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(wishlistServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllWishlistsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(wishlistServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restWishlistMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(wishlistRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void getWishlist() throws Exception {
        // Initialize the database
        insertedWishlist = wishlistRepository.save(wishlist);

        // Get the wishlist
        restWishlistMockMvc
            .perform(get(ENTITY_API_URL_ID, wishlist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(wishlist.getId()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.updateAt").value(DEFAULT_UPDATE_AT.toString()));
    }

    @Test
    void getNonExistingWishlist() throws Exception {
        // Get the wishlist
        restWishlistMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingWishlist() throws Exception {
        // Initialize the database
        insertedWishlist = wishlistRepository.save(wishlist);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the wishlist
        Wishlist updatedWishlist = wishlistRepository.findById(wishlist.getId()).orElseThrow();
        updatedWishlist.state(UPDATED_STATE).updateAt(UPDATED_UPDATE_AT);
        WishlistDTO wishlistDTO = wishlistMapper.toDto(updatedWishlist);

        restWishlistMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wishlistDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(wishlistDTO))
            )
            .andExpect(status().isOk());

        // Validate the Wishlist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedWishlistToMatchAllProperties(updatedWishlist);
    }

    @Test
    void putNonExistingWishlist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wishlist.setId(UUID.randomUUID().toString());

        // Create the Wishlist
        WishlistDTO wishlistDTO = wishlistMapper.toDto(wishlist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWishlistMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wishlistDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(wishlistDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wishlist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchWishlist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wishlist.setId(UUID.randomUUID().toString());

        // Create the Wishlist
        WishlistDTO wishlistDTO = wishlistMapper.toDto(wishlist);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWishlistMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(wishlistDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wishlist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamWishlist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wishlist.setId(UUID.randomUUID().toString());

        // Create the Wishlist
        WishlistDTO wishlistDTO = wishlistMapper.toDto(wishlist);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWishlistMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wishlistDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Wishlist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateWishlistWithPatch() throws Exception {
        // Initialize the database
        insertedWishlist = wishlistRepository.save(wishlist);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the wishlist using partial update
        Wishlist partialUpdatedWishlist = new Wishlist();
        partialUpdatedWishlist.setId(wishlist.getId());

        partialUpdatedWishlist.updateAt(UPDATED_UPDATE_AT);

        restWishlistMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWishlist.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWishlist))
            )
            .andExpect(status().isOk());

        // Validate the Wishlist in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWishlistUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedWishlist, wishlist), getPersistedWishlist(wishlist));
    }

    @Test
    void fullUpdateWishlistWithPatch() throws Exception {
        // Initialize the database
        insertedWishlist = wishlistRepository.save(wishlist);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the wishlist using partial update
        Wishlist partialUpdatedWishlist = new Wishlist();
        partialUpdatedWishlist.setId(wishlist.getId());

        partialUpdatedWishlist.state(UPDATED_STATE).updateAt(UPDATED_UPDATE_AT);

        restWishlistMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWishlist.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWishlist))
            )
            .andExpect(status().isOk());

        // Validate the Wishlist in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWishlistUpdatableFieldsEquals(partialUpdatedWishlist, getPersistedWishlist(partialUpdatedWishlist));
    }

    @Test
    void patchNonExistingWishlist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wishlist.setId(UUID.randomUUID().toString());

        // Create the Wishlist
        WishlistDTO wishlistDTO = wishlistMapper.toDto(wishlist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWishlistMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, wishlistDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(wishlistDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wishlist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchWishlist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wishlist.setId(UUID.randomUUID().toString());

        // Create the Wishlist
        WishlistDTO wishlistDTO = wishlistMapper.toDto(wishlist);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWishlistMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(wishlistDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wishlist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamWishlist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wishlist.setId(UUID.randomUUID().toString());

        // Create the Wishlist
        WishlistDTO wishlistDTO = wishlistMapper.toDto(wishlist);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWishlistMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(wishlistDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Wishlist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteWishlist() throws Exception {
        // Initialize the database
        insertedWishlist = wishlistRepository.save(wishlist);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the wishlist
        restWishlistMockMvc
            .perform(delete(ENTITY_API_URL_ID, wishlist.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return wishlistRepository.count();
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

    protected Wishlist getPersistedWishlist(Wishlist wishlist) {
        return wishlistRepository.findById(wishlist.getId()).orElseThrow();
    }

    protected void assertPersistedWishlistToMatchAllProperties(Wishlist expectedWishlist) {
        assertWishlistAllPropertiesEquals(expectedWishlist, getPersistedWishlist(expectedWishlist));
    }

    protected void assertPersistedWishlistToMatchUpdatableProperties(Wishlist expectedWishlist) {
        assertWishlistAllUpdatablePropertiesEquals(expectedWishlist, getPersistedWishlist(expectedWishlist));
    }
}
