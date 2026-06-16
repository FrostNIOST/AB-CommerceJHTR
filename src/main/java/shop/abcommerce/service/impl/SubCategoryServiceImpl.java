package shop.abcommerce.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.abcommerce.domain.SubCategory;
import shop.abcommerce.repository.SubCategoryRepository;
import shop.abcommerce.service.SubCategoryService;
import shop.abcommerce.service.dto.SubCategoryDTO;
import shop.abcommerce.service.mapper.SubCategoryMapper;

/**
 * Service Implementation for managing {@link shop.abcommerce.domain.SubCategory}.
 */
@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(SubCategoryServiceImpl.class);

    private final SubCategoryRepository subCategoryRepository;

    private final SubCategoryMapper subCategoryMapper;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, SubCategoryMapper subCategoryMapper) {
        this.subCategoryRepository = subCategoryRepository;
        this.subCategoryMapper = subCategoryMapper;
    }

    @Override
    public SubCategoryDTO save(SubCategoryDTO subCategoryDTO) {
        LOG.debug("Request to save SubCategory : {}", subCategoryDTO);
        SubCategory subCategory = subCategoryMapper.toEntity(subCategoryDTO);
        subCategory = subCategoryRepository.save(subCategory);
        return subCategoryMapper.toDto(subCategory);
    }

    @Override
    public SubCategoryDTO update(SubCategoryDTO subCategoryDTO) {
        LOG.debug("Request to update SubCategory : {}", subCategoryDTO);
        SubCategory subCategory = subCategoryMapper.toEntity(subCategoryDTO);
        subCategory = subCategoryRepository.save(subCategory);
        return subCategoryMapper.toDto(subCategory);
    }

    @Override
    public Optional<SubCategoryDTO> partialUpdate(SubCategoryDTO subCategoryDTO) {
        LOG.debug("Request to partially update SubCategory : {}", subCategoryDTO);

        return subCategoryRepository
            .findById(subCategoryDTO.getId())
            .map(existingSubCategory -> {
                subCategoryMapper.partialUpdate(existingSubCategory, subCategoryDTO);

                return existingSubCategory;
            })
            .map(subCategoryRepository::save)
            .map(subCategoryMapper::toDto);
    }

    @Override
    public Page<SubCategoryDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all SubCategories");
        return subCategoryRepository.findAll(pageable).map(subCategoryMapper::toDto);
    }

    public Page<SubCategoryDTO> findAllWithEagerRelationships(Pageable pageable) {
        return subCategoryRepository.findAllWithEagerRelationships(pageable).map(subCategoryMapper::toDto);
    }

    @Override
    public Optional<SubCategoryDTO> findOne(String id) {
        LOG.debug("Request to get SubCategory : {}", id);
        return subCategoryRepository.findOneWithEagerRelationships(id).map(subCategoryMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete SubCategory : {}", id);
        subCategoryRepository.deleteById(id);
    }
}
