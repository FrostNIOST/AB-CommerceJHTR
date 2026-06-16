package shop.abcommerce.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shop.abcommerce.repository.AccountURepository;
import shop.abcommerce.service.AccountUService;
import shop.abcommerce.service.dto.AccountUDTO;
import shop.abcommerce.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link shop.abcommerce.domain.AccountU}.
 */
@RestController
@RequestMapping("/api/account-us")
public class AccountUResource {

    private static final Logger LOG = LoggerFactory.getLogger(AccountUResource.class);

    private static final String ENTITY_NAME = "accountU";

    @Value("${jhipster.clientApp.name:abcommerce}")
    private String applicationName;

    private final AccountUService accountUService;

    private final AccountURepository accountURepository;

    public AccountUResource(AccountUService accountUService, AccountURepository accountURepository) {
        this.accountUService = accountUService;
        this.accountURepository = accountURepository;
    }

    /**
     * {@code POST  /account-us} : Create a new accountU.
     *
     * @param accountUDTO the accountUDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountUDTO, or with status {@code 400 (Bad Request)} if the accountU has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AccountUDTO> createAccountU(@Valid @RequestBody AccountUDTO accountUDTO) throws URISyntaxException {
        LOG.debug("REST request to save AccountU : {}", accountUDTO);
        if (accountUDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountU cannot already have an ID", ENTITY_NAME, "idexists");
        }
        accountUDTO = accountUService.save(accountUDTO);
        return ResponseEntity.created(new URI("/api/account-us/" + accountUDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, accountUDTO.getId()))
            .body(accountUDTO);
    }

    /**
     * {@code PUT  /account-us/:id} : Updates an existing accountU.
     *
     * @param id the id of the accountUDTO to save.
     * @param accountUDTO the accountUDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountUDTO,
     * or with status {@code 400 (Bad Request)} if the accountUDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountUDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AccountUDTO> updateAccountU(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody AccountUDTO accountUDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update AccountU : {}, {}", id, accountUDTO);
        if (accountUDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, accountUDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!accountURepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        accountUDTO = accountUService.update(accountUDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, accountUDTO.getId()))
            .body(accountUDTO);
    }

    /**
     * {@code PATCH  /account-us/:id} : Partial updates given fields of an existing accountU, field will ignore if it is null
     *
     * @param id the id of the accountUDTO to save.
     * @param accountUDTO the accountUDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountUDTO,
     * or with status {@code 400 (Bad Request)} if the accountUDTO is not valid,
     * or with status {@code 404 (Not Found)} if the accountUDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the accountUDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AccountUDTO> partialUpdateAccountU(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody AccountUDTO accountUDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update AccountU partially : {}, {}", id, accountUDTO);
        if (accountUDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, accountUDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!accountURepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AccountUDTO> result = accountUService.partialUpdate(accountUDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, accountUDTO.getId())
        );
    }

    /**
     * {@code GET  /account-us} : get all the Account US.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Account US in body.
     */
    @GetMapping("")
    public ResponseEntity<List<AccountUDTO>> getAllAccountUS(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        LOG.debug("REST request to get a page of AccountUS");
        Page<AccountUDTO> page;
        if (eagerload) {
            page = accountUService.findAllWithEagerRelationships(pageable);
        } else {
            page = accountUService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /account-us/:id} : get the "id" accountU.
     *
     * @param id the id of the accountUDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountUDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountUDTO> getAccountU(@PathVariable("id") String id) {
        LOG.debug("REST request to get AccountU : {}", id);
        Optional<AccountUDTO> accountUDTO = accountUService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountUDTO);
    }

    /**
     * {@code DELETE  /account-us/:id} : delete the "id" accountU.
     *
     * @param id the id of the accountUDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountU(@PathVariable("id") String id) {
        LOG.debug("REST request to delete AccountU : {}", id);
        accountUService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id))
            .build();
    }
}
