package com.netease.example.demo.datasource;


import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import com.netease.example.demo.context.TenantContext;

@Component
public class TenantResolver implements CurrentTenantIdentifierResolver {
	@Override
	public String resolveCurrentTenantIdentifier() {
		String tenantId = TenantContext.getCurrentTenant();
		if (tenantId != null) {
            return tenantId;
        }
		return TenantContext.DEFUALT_TENANT_ID;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
