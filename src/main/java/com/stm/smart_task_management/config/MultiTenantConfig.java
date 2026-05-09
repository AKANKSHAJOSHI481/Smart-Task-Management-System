package com.stm.smart_task_management.config;

import com.stm.smart_task_management.tenant.SchemaMultiTenantConnectionProvider;
import com.stm.smart_task_management.tenant.TenantIdentifierResolver;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class MultiTenantConfig implements HibernatePropertiesCustomizer {
    private final SchemaMultiTenantConnectionProvider schemaMultiTenantConnectionProvider;
    private final TenantIdentifierResolver tenantIdentifierResolver;
    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(
                AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER,
                schemaMultiTenantConnectionProvider
        );
        hibernateProperties.put(
                AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER,
                tenantIdentifierResolver
        );
    }
}
