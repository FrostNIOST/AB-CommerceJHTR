package shop.abcommerce.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.abcommerce.domain.AccountU;
import shop.abcommerce.repository.AccountURepository;
import shop.abcommerce.service.AccountUService;
import shop.abcommerce.service.dto.AccountUDTO;
import shop.abcommerce.service.mapper.AccountUMapper;

/**
 * Service Implementation for managing {@link shop.abcommerce.domain.AccountU}.
 */
@Service
public class AccountUServiceImpl implements AccountUService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountUServiceImpl.class);

    private final AccountURepository accountURepository;

    private final AccountUMapper accountUMapper;

    public AccountUServiceImpl(AccountURepository accountURepository, AccountUMapper accountUMapper) {
        this.accountURepository = accountURepository;
        this.accountUMapper = accountUMapper;
    }

    @Override
    public AccountUDTO save(AccountUDTO accountUDTO) {
        LOG.debug("Request to save AccountU : {}", accountUDTO);
        AccountU accountU = accountUMapper.toEntity(accountUDTO);
        accountU = accountURepository.save(accountU);
        return accountUMapper.toDto(accountU);
    }

    @Override
    public AccountUDTO update(AccountUDTO accountUDTO) {
        LOG.debug("Request to update AccountU : {}", accountUDTO);
        AccountU accountU = accountUMapper.toEntity(accountUDTO);
        accountU = accountURepository.save(accountU);
        return accountUMapper.toDto(accountU);
    }

    @Override
    public Optional<AccountUDTO> partialUpdate(AccountUDTO accountUDTO) {
        LOG.debug("Request to partially update AccountU : {}", accountUDTO);

        return accountURepository
            .findById(accountUDTO.getId())
            .map(existingAccountU -> {
                accountUMapper.partialUpdate(existingAccountU, accountUDTO);

                return existingAccountU;
            })
            .map(accountURepository::save)
            .map(accountUMapper::toDto);
    }

    @Override
    public Page<AccountUDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all AccountUS");
        return accountURepository.findAll(pageable).map(accountUMapper::toDto);
    }

    public Page<AccountUDTO> findAllWithEagerRelationships(Pageable pageable) {
        return accountURepository.findAllWithEagerRelationships(pageable).map(accountUMapper::toDto);
    }

    @Override
    public Optional<AccountUDTO> findOne(String id) {
        LOG.debug("Request to get AccountU : {}", id);
        return accountURepository.findOneWithEagerRelationships(id).map(accountUMapper::toDto);
    }

    @Override
    public void delete(String id) {
        LOG.debug("Request to delete AccountU : {}", id);
        accountURepository.deleteById(id);
    }
}
