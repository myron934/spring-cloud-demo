package com.netease.example.demo.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantContext {
	private static Logger logger = LoggerFactory.getLogger(TenantContext.class);
	private static ThreadLocal<String> currentTenant = new ThreadLocal<>();
	public final static String DEFUALT_TENANT_ID="test";
	public static void setCurrentTenant(String tenant) {
		logger.debug("Setting tenant to " + tenant);
		currentTenant.set(tenant);
	}

	public static String getCurrentTenant() {
		return currentTenant.get();
	}

	public static void clear() {
		currentTenant.set(null);
	}
}
