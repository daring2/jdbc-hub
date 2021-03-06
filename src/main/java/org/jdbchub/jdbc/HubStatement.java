package org.jdbchub.jdbc;

import java.sql.*;
import java.util.List;

public class HubStatement<T extends Statement> extends EntityList<T> implements Statement {

	final HubConnection connection;

	public HubStatement(HubConnection connection, List<T> statements) {
		super(statements);
		this.connection = connection;
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		return new HubResultSet(this, mapToList(st -> st.executeQuery(sql)));
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		return mapToInt(st -> st.executeUpdate(sql)).sum();
	}

	@Override
	public void close() throws SQLException {
		forEach(Statement::close);
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		return mainEntity().getMaxFieldSize();
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		forEach(st -> st.setMaxFieldSize(max));
	}

	@Override
	public int getMaxRows() throws SQLException {
		return mainEntity().getMaxRows();
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		forEach(st -> st.setMaxRows(max));
	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		forEach(st -> st.setEscapeProcessing(enable));
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		return mainEntity().getQueryTimeout();
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		forEach(st -> st.setQueryTimeout(seconds));
	}

	@Override
	public void cancel() throws SQLException {
		forEach(Statement::cancel);
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return mainEntity().getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		forEach(Statement::clearWarnings);
	}

	@Override
	public void setCursorName(String name) throws SQLException {
		forEach(st -> st.setCursorName(name));
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		return mapToList(st -> st.execute(sql)).get(0);
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		List<ResultSet> rs = mapToList(Statement::getResultSet);
		return rs.get(0) != null ? new HubResultSet(this, rs) : null;
	}

	@Override
	public int getUpdateCount() throws SQLException {
		int c = mainEntity().getUpdateCount();
		return c != -1 ? mapToInt(Statement::getUpdateCount).sum() : -1;
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		return mapToList(Statement::getMoreResults).get(0);
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		forEach(st -> st.setFetchDirection(direction));
	}

	@Override
	public int getFetchDirection() throws SQLException {
		return mainEntity().getFetchDirection();
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		forEach(st -> st.setFetchSize(rows));
	}

	@Override
	public int getFetchSize() throws SQLException {
		return mainEntity().getFetchSize();
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		return mainEntity().getResultSetConcurrency();
	}

	@Override
	public int getResultSetType() throws SQLException {
		return mainEntity().getResultSetType();
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		forEach(st -> st.addBatch(sql));
	}

	@Override
	public void clearBatch() throws SQLException {
		forEach(Statement::clearBatch);
	}

	@Override
	public int[] executeBatch() throws SQLException {
		List<int[]> rs = mapToList(Statement::executeBatch);
		if (rs.size() > 0) {
			int[] r0 = rs.get(0);
			int[] sr = new int[r0.length];
			for (int i = 0; i < r0.length; i++) {
				for (int[] r : rs) {
					if (sr[i] < 0 || r[i] < 0) {
						sr[i] = Integer.min(sr[i], r[i]);
					} else {
						sr[i] += r[i];
					}
				}
			}
			return sr;
		} else {
			return new int[0];
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		return connection;
	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		return mapToList(st -> st.getMoreResults(current)).get(0);
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		return new HubResultSet(this, mapToList(Statement::getGeneratedKeys));
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		return mapToInt(st -> st.executeUpdate(sql, autoGeneratedKeys)).sum();
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		return mapToInt(st -> st.executeUpdate(sql, columnIndexes)).sum();
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		return mapToInt(st -> st.executeUpdate(sql, columnNames)).sum();
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		return mapToList(st -> st.execute(sql, autoGeneratedKeys)).get(0);
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		return mapToList(st -> st.execute(sql, columnIndexes)).get(0);
	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		return mapToList(st -> st.execute(sql, columnNames)).get(0);
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		return mainEntity().getResultSetHoldability();
	}

	@Override
	public boolean isClosed() throws SQLException {
		return map(Statement::isClosed).anyMatch(r -> r);
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		forEach(st -> st.setPoolable(poolable));
	}

	@Override
	public boolean isPoolable() throws SQLException {
		return mainEntity().isPoolable();
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		forEach(Statement::closeOnCompletion);
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		return mainEntity().isCloseOnCompletion();
	}

	@Override
	public <A> A unwrap(Class<A> iface) throws SQLException {
		return mainEntity().unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return mainEntity().isWrapperFor(iface);
	}
}
