package org.jdbchub.jdbc;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class HubConnection extends EntityList<Connection> implements Connection {

	public HubConnection(List<Connection> connections) {
		super(connections);
	}

	@Override
	public Statement createStatement() throws SQLException {
		return new HubStatement<>(this, mapToList(Connection::createStatement));
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return new HubPreparedStatement<>(this, mapToList(c -> c.prepareStatement(sql)));
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return new HubCallableStatement(this, mapToList(c -> c.prepareCall(sql)));
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return mainEntity().nativeSQL(sql);
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		forEach(c -> c.setAutoCommit(autoCommit));
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return mainEntity().getAutoCommit();
	}

	@Override
	public void commit() throws SQLException {
		forEach(Connection::commit);
	}

	@Override
	public void rollback() throws SQLException {
		forEach(Connection::rollback);
	}

	@Override
	public void close() throws SQLException {
		forEach(Connection::close);
	}

	@Override
	public boolean isClosed() throws SQLException {
		return map(Connection::isClosed).anyMatch(r -> r);
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return mainEntity().getMetaData();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		forEach(c -> c.setReadOnly(readOnly));
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return mainEntity().isReadOnly();
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		forEach(c -> c.setCatalog(catalog));
	}

	@Override
	public String getCatalog() throws SQLException {
		return mainEntity().getCatalog();
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		forEach(c -> c.setTransactionIsolation(level));
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return mainEntity().getTransactionIsolation();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return mainEntity().getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		forEach(Connection::clearWarnings);
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return new HubStatement<>(this, mapToList(c -> c.createStatement(resultSetType, resultSetConcurrency)));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return new HubPreparedStatement<>(this, mapToList(c -> c.prepareStatement(sql, resultSetType, resultSetConcurrency)));
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return new HubCallableStatement(this, mapToList(c -> c.prepareCall(sql, resultSetType, resultSetConcurrency)));
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return mainEntity().getTypeMap();
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		forEach(c -> c.setTypeMap(map));
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		forEach(c -> c.setHoldability(holdability));
	}

	@Override
	public int getHoldability() throws SQLException {
		return mainEntity().getHoldability();
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return new HubSavepoint(mapToList(Connection::setSavepoint));
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return new HubSavepoint(mapToList(c -> c.setSavepoint(name)));
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		HubSavepoint sp = (HubSavepoint) savepoint;
		for (int i = 0; i < size(); i++) {
			entity(i).rollback(sp.entity(i));
		}
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		HubSavepoint sp = (HubSavepoint) savepoint;
		for (int i = 0; i < size(); i++) {
			entity(i).releaseSavepoint(sp.entity(i));
		}
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return new HubStatement<>(this, mapToList(c -> c.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability)));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return new HubPreparedStatement<>(this, mapToList(c -> c.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability)));
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return new HubCallableStatement(this, mapToList(c -> c.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability)));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		return new HubPreparedStatement<>(this, mapToList(c -> c.prepareStatement(sql, autoGeneratedKeys)));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		return new HubPreparedStatement<>(this, mapToList(c -> c.prepareStatement(sql, columnIndexes)));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		return new HubPreparedStatement<>(this, mapToList(c -> c.prepareStatement(sql, columnNames)));
	}

	@Override
	public Clob createClob() throws SQLException {
		return mainEntity().createClob();
	}

	@Override
	public Blob createBlob() throws SQLException {
		return mainEntity().createBlob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		return mainEntity().createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return mainEntity().createSQLXML();
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return allMatch(c -> c.isValid(timeout));
	}

	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		forEach(c -> c.setClientInfo(name, value));
	}

	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		forEach(c -> c.setClientInfo(properties));
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return mainEntity().getClientInfo(name);
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return mainEntity().getClientInfo();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return mainEntity().createArrayOf(typeName, elements);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return mainEntity().createStruct(typeName, attributes);
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		forEach(c -> c.setSchema(schema));
	}

	@Override
	public String getSchema() throws SQLException {
		return mainEntity().getSchema();
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		forEach(c -> c.abort(executor));
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		forEach(c -> c.setNetworkTimeout(executor, milliseconds));
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		return mainEntity().getNetworkTimeout();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return mainEntity().unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return mainEntity().isWrapperFor(iface);
	}
}
