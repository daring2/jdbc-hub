package org.jdbchub.jdbc;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.List;
import static org.jdbchub.jdbc.JdbcUtils.*;

public class JdbcPreparedStatement<T extends PreparedStatement> extends JdbcStatement<T> implements PreparedStatement {

	public JdbcPreparedStatement(JdbcConnection connection, List<T> statements) {
		super(connection, statements);
	}

	@Override
	public ResultSet executeQuery() throws SQLException {
		return new JdbcResultSet(this, mapToList(PreparedStatement::executeQuery));
	}

	@Override
	public int executeUpdate() throws SQLException {
		return mapToInt(PreparedStatement::executeUpdate).sum();
	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		forEach(st -> st.setNull(parameterIndex, sqlType));
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		forEach(st -> st.setBoolean(parameterIndex, x));
	}

	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		forEach(st -> st.setByte(parameterIndex, x));
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		forEach(st -> st.setShort(parameterIndex, x));
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		forEach(st -> st.setInt(parameterIndex, x));
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		forEach(st -> st.setLong(parameterIndex, x));
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		forEach(st -> st.setFloat(parameterIndex, x));
	}

	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		forEach(st -> st.setDouble(parameterIndex, x));
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		forEach(st -> st.setBigDecimal(parameterIndex, x));
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		forEach(st -> st.setString(parameterIndex, x));
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		forEach(st -> st.setBytes(parameterIndex, x));
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		forEach(st -> st.setDate(parameterIndex, x));
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		forEach(st -> st.setTime(parameterIndex, x));
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		forEach(st -> st.setTimestamp(parameterIndex, x));
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setAsciiStream(parameterIndex, new ByteArrayInputStream(bytes), length));
	}

	@Deprecated
	@Override
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setUnicodeStream(parameterIndex, new ByteArrayInputStream(bytes), length));
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setBinaryStream(parameterIndex, new ByteArrayInputStream(bytes), length));
	}

	@Override
	public void clearParameters() throws SQLException {
		forEach(PreparedStatement::clearParameters);
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		forEach(st -> st.setObject(parameterIndex, x, targetSqlType));
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		forEach(st -> st.setObject(parameterIndex, x));
	}

	@Override
	public boolean execute() throws SQLException {
		return mapToList(PreparedStatement::execute).get(0);
	}

	@Override
	public void addBatch() throws SQLException {
		forEach(PreparedStatement::addBatch);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setCharacterStream(parameterIndex, new StringReader(str), length));
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		forEach(st -> st.setRef(parameterIndex, x));
	}

	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		forEach(st -> st.setBlob(parameterIndex, x));
	}

	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		forEach(st -> st.setClob(parameterIndex, x));
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		forEach(st -> st.setArray(parameterIndex, x));
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		return mainEntity().getMetaData();
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		forEach(st -> st.setDate(parameterIndex, x, cal));
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		forEach(st -> st.setTime(parameterIndex, x, cal));
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		forEach(st -> st.setTimestamp(parameterIndex, x, cal));
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		forEach(st -> st.setNull(parameterIndex, sqlType, typeName));
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		forEach(st -> st.setURL(parameterIndex, x));
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		return mainEntity().getParameterMetaData();
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		forEach(st -> st.setRowId(parameterIndex, x));
	}

	@Override
	public void setNString(int parameterIndex, String value) throws SQLException {
		forEach(st -> st.setNString(parameterIndex, value));
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setNCharacterStream(parameterIndex, new StringReader(str), length));
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		forEach(st -> st.setNClob(parameterIndex, value));
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setClob(parameterIndex, new StringReader(str), length));
	}

	@Override
	public void setBlob(int parameterIndex, InputStream x, long length) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setBlob(parameterIndex, new ByteArrayInputStream(bytes), length));
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setNClob(parameterIndex, new StringReader(str), length));
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		forEach(st -> st.setSQLXML(parameterIndex, xmlObject));
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		forEach(st -> st.setObject(parameterIndex, x, targetSqlType, scaleOrLength));
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setAsciiStream(parameterIndex, new ByteArrayInputStream(bytes), length));
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setBinaryStream(parameterIndex, new ByteArrayInputStream(bytes), length));
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setCharacterStream(parameterIndex, new StringReader(str), length));
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setAsciiStream(parameterIndex, new ByteArrayInputStream(bytes)));
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setBinaryStream(parameterIndex, new ByteArrayInputStream(bytes)));
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setCharacterStream(parameterIndex, new StringReader(str)));
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setNCharacterStream(parameterIndex, new StringReader(str)));
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setClob(parameterIndex, new StringReader(str)));
	}

	@Override
	public void setBlob(int parameterIndex, InputStream x) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setBlob(parameterIndex, new ByteArrayInputStream(bytes)));
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setNClob(parameterIndex, new StringReader(str)));
	}

}
