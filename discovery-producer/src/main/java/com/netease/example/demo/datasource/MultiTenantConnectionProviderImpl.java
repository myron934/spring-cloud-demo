package com.netease.example.demo.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netease.example.demo.context.TenantContext;

@Component
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public MultiTenantConnectionProviderImpl(){
		System.out.println("init");
	}
	@Override
	public boolean isUnwrappableAs(@SuppressWarnings("rawtypes") Class arg0) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return dataSource.getConnection();
	}

	@Override
	public Connection getConnection(String arg0) throws SQLException {
		String tenantIdentifier = TenantContext.getCurrentTenant();

		final Connection connection = getAnyConnection();
		try {
			if (tenantIdentifier != null) {
				connection.createStatement().execute("USE " + tenantIdentifier);
			} else {
				connection.createStatement().execute("USE " + TenantContext.DEFUALT_TENANT_ID);
			}
		} catch (SQLException e) {
			throw new HibernateException("Problem setting schema to " + tenantIdentifier, e);
		}
		return connection;
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();

	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		try {
			connection.createStatement().execute("USE " + TenantContext.DEFUALT_TENANT_ID);
		} catch (SQLException e) {
			throw new HibernateException("Problem setting schema to " + tenantIdentifier, e);
		}
		connection.close();

	}

	@Override
	public boolean supportsAggressiveRelease() {
		return true;
	}

}
