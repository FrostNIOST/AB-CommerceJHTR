package shop.abcommerce.service.mapper;

import static shop.abcommerce.domain.AccountUAsserts.*;
import static shop.abcommerce.domain.AccountUTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountUMapperTest {

    private AccountUMapper accountUMapper;

    @BeforeEach
    void setUp() {
        accountUMapper = new AccountUMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getAccountUSample1();
        var actual = accountUMapper.toEntity(accountUMapper.toDto(expected));
        assertAccountUAllPropertiesEquals(expected, actual);
    }
}
