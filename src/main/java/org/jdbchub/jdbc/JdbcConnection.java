package org.jdbchub.jdbc;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

public class JdbcConnection implements Connection {

	private final List<Connection> connections;

	public JdbcConnection(List<Connection> connections) {
		this.connections = connections;
	}

	private <T> List<T> map(JdbcFunction<Connection, T> mapper) {
		return connections.parallelStream().map(mapper).collect(Collectors.toList());
	}

	private void forEach(JdbcConsumer<Connection> consumer) {
		connections.parallelStream().forEach(consumer);
	}

	private Connection mainConnection() {
		return connections.get(0);
	}

	@Override
	public Statement createStatement() throws SQLException {
		return new JdbcStatement<>(map(Connection::createStatement));
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return new JdbcPreparedStatement<>(map(c -> c.prepareStatement(sql)));
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return new JdbcCallableStatement(map(c -> c.prepareCall(sql)));
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return sql;
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		forEach(c -> c.setAutoCommit(autoCommit));
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return mainConnection().getAutoCommit();
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
		return mainConnection().isClosed();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return mainConnection().getMetaData();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		forEach(c -> c.setReadOnly(readOnly));
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return mainConnection().isReadOnly();
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		forEach(c -> c.setCatalog(catalog));
	}

	@Override
	public String getCatalog() throws SQLException {
		return mainConnection().getCatalog();
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		forEach(c -> c.setTransactionIsolation(level));
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return mainConnection().getTransactionIsolation();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return null; // TODO implement
	}

	@Override
	public void clearWarnings() throws SQLException {
		forEach(Connection::clearWarnings);
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return new JdbcStatement<>(map(c -> c.createStatement(resultSetType, resultSetConcurrency)));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return new JdbcPreparedStatement<>(map(c -> c.prepareStatement(sql, resultSetType, resultSetConcurrency)));
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return new JdbcCallableStatement(map(c -> c.prepareCall(sql, resultSetType, resultSetConcurrency)));
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return mainConnection().getTypeMap();
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
		return mainConnection().getHoldability();
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return null; // TODO implement
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return null; // TODO implement
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		// TODO implement
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		// TODO implement
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return new JdbcStatement<>(map(c -> c.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability)));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return new JdbcPreparedStatement<>(map(c -> c.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability)));
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return new JdbcCallableStatement(map(c -> c.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability)));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		return new JdbcPreparedStatement<>(map(c -> c.prepareStatement(sql, autoGeneratedKeys)));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		return new JdbcPreparedStatement<>(map(c -> c.prepareStatement(sql, columnIndexes)));
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		return new JdbcPreparedStatement<>(map(c -> c.prepareStatement(sql, columnNames)));
	}

	@Override
	public Clob createClob() throws SQLException {
		return null; // TODO implement
	}

	@Override
	public Blob createBlob() throws SQLException {
		return null; // TODO implement
	}

	@Override
	public NClob createNClob() throws SQLException {
		return null; // TODO implement
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return null; // TODO implement
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return false;
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
		return mainConnection().getClientInfo(name);
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return mainConnection().getClientInfo();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return null; // TODO implement
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return null; // TODO implement
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		forEach(c -> c.setSchema(schema));
	}

	@Override
	public String getSchema() throws SQLException {
		return mainConnection().getSchema();
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
		return mainConnection().getNetworkTimeout();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return mainConnection().unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return  mainConnection().isWrapperFor(iface);
	}
}
