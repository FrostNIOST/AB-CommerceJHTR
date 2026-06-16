package shop.abcommerce;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import shop.abcommerce.config.AsyncSyncConfiguration;
import shop.abcommerce.config.JacksonConfiguration;
import shop.abcommerce.config.MongoDbTestContainer;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { AbcommerceApp.class, JacksonConfiguration.class, AsyncSyncConfiguration.class })
@ImportTestcontainers(MongoDbTestContainer.class)
public @interface IntegrationTest {}
