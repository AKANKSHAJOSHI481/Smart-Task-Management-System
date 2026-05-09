package com.stm.smart_task_management.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {
    @Override
    public Object resolveCurrentTenantIdentifier() {
        String tenant = TenantContext.getTenant();
        return tenant != null ? tenant : "public";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
