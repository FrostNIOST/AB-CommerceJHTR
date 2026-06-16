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
import shop.abcommerce.repository.SubCategoryRepository;
import shop.abcommerce.service.SubCategoryService;
import shop.abcommerce.service.dto.SubCategoryDTO;
import shop.abcommerce.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link shop.abcommerce.domain.SubCategory}.
 */
@RestController
@RequestMapping("/api/sub-categories")
public class SubCategoryResource {

    private static final Logger LOG = LoggerFactory.getLogger(SubCategoryResource.class);

    private static final String ENTITY_NAME = "subCategory";

    @Value("${jhipster.clientApp.name:abcommerce}")
    private String applicationName;

    private final SubCategoryService subCategoryService;

    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryResource(SubCategoryService subCategoryService, SubCategoryRepository subCategoryRepository) {
        this.subCategoryService = subCategoryService;
        this.subCategoryRepository = subCategoryRepository;
    }

    /**
     * {@code POST  /sub-categories} : Create a new subCategory.
     *
     * @param subCategoryDTO the subCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subCategoryDTO, or with status {@code 400 (Bad Request)} if the subCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SubCategoryDTO> createSubCategory(@Valid @RequestBody SubCategoryDTO subCategoryDTO) throws URISyntaxException {
        LOG.debug("REST request to save SubCategory : {}", subCategoryDTO);
        if (subCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new subCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        subCategoryDTO = subCategoryService.save(subCategoryDTO);
        return ResponseEntity.created(new URI("/api/sub-categories/" + subCategoryDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, subCategoryDTO.getId()))
            .body(subCategoryDTO);
    }

    /**
     * {@code PUT  /sub-categories/:id} : Updates an existing subCategory.
     *
     * @param id the id of the subCategoryDTO to save.
     * @param subCategoryDTO the subCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the subCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SubCategoryDTO> updateSubCategory(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody SubCategoryDTO subCategoryDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update SubCategory : {}, {}", id, subCategoryDTO);
        if (subCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, subCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!subCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        subCategoryDTO = subCategoryService.update(subCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, subCategoryDTO.getId()))
            .body(subCategoryDTO);
    }

    /**
     * {@code PATCH  /sub-categories/:id} : Partial updates given fields of an existing subCategory, field will ignore if it is null
     *
     * @param id the id of the subCategoryDTO to save.
     * @param subCategoryDTO the subCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the subCategoryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the subCategoryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the subCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SubCategoryDTO> partialUpdateSubCategory(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody SubCategoryDTO subCategoryDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update SubCategory partially : {}, {}", id, subCategoryDTO);
        if (subCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, subCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!subCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SubCategoryDTO> result = subCategoryService.partialUpdate(subCategoryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, subCategoryDTO.getId())
        );
    }

    /**
     * {@code GET  /sub-categories} : get all the Sub Categories.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Sub Categories in body.
     */
    @GetMapping("")
    public ResponseEntity<List<SubCategoryDTO>> getAllSubCategories(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        LOG.debug("REST request to get a page of SubCategories");
        Page<SubCategoryDTO> page;
        if (eagerload) {
            page = subCategoryService.findAllWithEagerRelationships(pageable);
        } else {
            page = subCategoryService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sub-categories/:id} : get the "id" subCategory.
     *
     * @param id the id of the subCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SubCategoryDTO> getSubCategory(@PathVariable("id") String id) {
        LOG.debug("REST request to get SubCategory : {}", id);
        Optional<SubCategoryDTO> subCategoryDTO = subCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subCategoryDTO);
    }

    /**
     * {@code DELETE  /sub-categories/:id} : delete the "id" subCategory.
     *
     * @param id the id of the subCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable("id") String id) {
        LOG.debug("REST request to delete SubCategory : {}", id);
        subCategoryService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id))
            .build();
    }
}
