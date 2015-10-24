package org.jdbchub.jdbc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import static org.jdbchub.jdbc.JdbcUtils.readBytes;
import static org.jdbchub.jdbc.JdbcUtils.readString;

public class HubCallableStatement extends HubPreparedStatement<CallableStatement> implements CallableStatement {

	public HubCallableStatement(HubConnection connection, List<CallableStatement> statements) {
		super(connection, statements);
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
		forEach(st -> st.registerOutParameter(parameterIndex, sqlType));
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
		forEach(st -> st.registerOutParameter(parameterIndex, sqlType, scale));
	}

	@Override
	public boolean wasNull() throws SQLException {
		return mainEntity().wasNull();
	}

	@Override
	public String getString(int parameterIndex) throws SQLException {
		return mainEntity().getString(parameterIndex);
	}

	@Override
	public boolean getBoolean(int parameterIndex) throws SQLException {
		return mainEntity().getBoolean(parameterIndex);
	}

	@Override
	public byte getByte(int parameterIndex) throws SQLException {
		return mainEntity().getByte(parameterIndex);
	}

	@Override
	public short getShort(int parameterIndex) throws SQLException {
		return mainEntity().getShort(parameterIndex);
	}

	@Override
	public int getInt(int parameterIndex) throws SQLException {
		return mainEntity().getInt(parameterIndex);
	}

	@Override
	public long getLong(int parameterIndex) throws SQLException {
		return mainEntity().getLong(parameterIndex);
	}

	@Override
	public float getFloat(int parameterIndex) throws SQLException {
		return mainEntity().getFloat(parameterIndex);
	}

	@Override
	public double getDouble(int parameterIndex) throws SQLException {
		return mainEntity().getDouble(parameterIndex);
	}

	@Deprecated
	@Override
	public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
		return mainEntity().getBigDecimal(parameterIndex, scale);
	}

	@Override
	public byte[] getBytes(int parameterIndex) throws SQLException {
		return mainEntity().getBytes(parameterIndex);
	}

	@Override
	public Date getDate(int parameterIndex) throws SQLException {
		return mainEntity().getDate(parameterIndex);
	}

	@Override
	public Time getTime(int parameterIndex) throws SQLException {
		return mainEntity().getTime(parameterIndex);
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex) throws SQLException {
		return mainEntity().getTimestamp(parameterIndex);
	}

	@Override
	public Object getObject(int parameterIndex) throws SQLException {
		return mainEntity().getObject(parameterIndex);
	}

	@Override
	public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
		return mainEntity().getBigDecimal(parameterIndex);
	}

	@Override
	public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
		return mainEntity().getObject(parameterIndex, map);
	}

	@Override
	public Ref getRef(int parameterIndex) throws SQLException {
		return mainEntity().getRef(parameterIndex);
	}

	@Override
	public Blob getBlob(int parameterIndex) throws SQLException {
		return mainEntity().getBlob(parameterIndex);
	}

	@Override
	public Clob getClob(int parameterIndex) throws SQLException {
		return mainEntity().getClob(parameterIndex);
	}

	@Override
	public Array getArray(int parameterIndex) throws SQLException {
		return mainEntity().getArray(parameterIndex);
	}

	@Override
	public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
		return mainEntity().getDate(parameterIndex, cal);
	}

	@Override
	public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
		return mainEntity().getTime(parameterIndex, cal);
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
		return mainEntity().getTimestamp(parameterIndex, cal);
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
		forEach(st -> st.registerOutParameter(parameterIndex, sqlType, typeName));
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
		forEach(st -> st.registerOutParameter(parameterName, sqlType));
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
		forEach(st -> st.registerOutParameter(parameterName, sqlType, scale));
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
		forEach(st -> st.registerOutParameter(parameterName, sqlType, typeName));
	}

	@Override
	public URL getURL(int parameterIndex) throws SQLException {
		return mainEntity().getURL(parameterIndex);
	}

	@Override
	public void setURL(String parameterName, URL val) throws SQLException {
		forEach(st -> st.setURL(parameterName, val));
	}

	@Override
	public void setNull(String parameterName, int sqlType) throws SQLException {
		forEach(st -> st.setNull(parameterName, sqlType));
	}

	@Override
	public void setBoolean(String parameterName, boolean x) throws SQLException {
		forEach(st -> st.setBoolean(parameterName, x));
	}

	@Override
	public void setByte(String parameterName, byte x) throws SQLException {
		forEach(st -> st.setByte(parameterName, x));
	}

	@Override
	public void setShort(String parameterName, short x) throws SQLException {
		forEach(st -> st.setShort(parameterName, x));
	}

	@Override
	public void setInt(String parameterName, int x) throws SQLException {
		forEach(st -> st.setInt(parameterName, x));
	}

	@Override
	public void setLong(String parameterName, long x) throws SQLException {
		forEach(st -> st.setLong(parameterName, x));
	}

	@Override
	public void setFloat(String parameterName, float x) throws SQLException {
		forEach(st -> st.setFloat(parameterName, x));
	}

	@Override
	public void setDouble(String parameterName, double x) throws SQLException {
		forEach(st -> st.setDouble(parameterName, x));
	}

	@Override
	public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
		forEach(st -> st.setBigDecimal(parameterName, x));
	}

	@Override
	public void setString(String parameterName, String x) throws SQLException {
		forEach(st -> st.setString(parameterName, x));
	}

	@Override
	public void setBytes(String parameterName, byte[] x) throws SQLException {
		forEach(st -> st.setBytes(parameterName, x));
	}

	@Override
	public void setDate(String parameterName, Date x) throws SQLException {
		forEach(st -> st.setDate(parameterName, x));
	}

	@Override
	public void setTime(String parameterName, Time x) throws SQLException {
		forEach(st -> st.setTime(parameterName, x));
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
		forEach(st -> st.setTimestamp(parameterName, x));
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setAsciiStream(parameterName, new ByteArrayInputStream(bytes), length));
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setBinaryStream(parameterName, new ByteArrayInputStream(bytes), length));
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
		forEach(st -> st.setObject(parameterName, x, targetSqlType, scale));
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
		forEach(st -> st.setObject(parameterName, x, targetSqlType));
	}

	@Override
	public void setObject(String parameterName, Object x) throws SQLException {
		forEach(st -> st.setObject(parameterName, x));
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setCharacterStream(parameterName, new StringReader(str), length));
	}

	@Override
	public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
		forEach(st -> st.setDate(parameterName, x, cal));
	}

	@Override
	public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
		forEach(st -> st.setTime(parameterName, x, cal));
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
		forEach(st -> st.setTimestamp(parameterName, x, cal));
	}

	@Override
	public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
		forEach(st -> st.setNull(parameterName, sqlType, typeName));
	}

	@Override
	public String getString(String parameterName) throws SQLException {
		return mainEntity().getString(parameterName);
	}

	@Override
	public boolean getBoolean(String parameterName) throws SQLException {
		return mainEntity().getBoolean(parameterName);
	}

	@Override
	public byte getByte(String parameterName) throws SQLException {
		return mainEntity().getByte(parameterName);
	}

	@Override
	public short getShort(String parameterName) throws SQLException {
		return mainEntity().getShort(parameterName);
	}

	@Override
	public int getInt(String parameterName) throws SQLException {
		return mainEntity().getInt(parameterName);
	}

	@Override
	public long getLong(String parameterName) throws SQLException {
		return mainEntity().getLong(parameterName);
	}

	@Override
	public float getFloat(String parameterName) throws SQLException {
		return mainEntity().getFloat(parameterName);
	}

	@Override
	public double getDouble(String parameterName) throws SQLException {
		return mainEntity().getDouble(parameterName);
	}

	@Override
	public byte[] getBytes(String parameterName) throws SQLException {
		return mainEntity().getBytes(parameterName);
	}

	@Override
	public Date getDate(String parameterName) throws SQLException {
		return mainEntity().getDate(parameterName);
	}

	@Override
	public Time getTime(String parameterName) throws SQLException {
		return mainEntity().getTime(parameterName);
	}

	@Override
	public Timestamp getTimestamp(String parameterName) throws SQLException {
		return mainEntity().getTimestamp(parameterName);
	}

	@Override
	public Object getObject(String parameterName) throws SQLException {
		return mainEntity().getObject(parameterName);
	}

	@Override
	public BigDecimal getBigDecimal(String parameterName) throws SQLException {
		return mainEntity().getBigDecimal(parameterName);
	}

	@Override
	public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
		return mainEntity().getObject(parameterName, map);
	}

	@Override
	public Ref getRef(String parameterName) throws SQLException {
		return mainEntity().getRef(parameterName);
	}

	@Override
	public Blob getBlob(String parameterName) throws SQLException {
		return mainEntity().getBlob(parameterName);
	}

	@Override
	public Clob getClob(String parameterName) throws SQLException {
		return mainEntity().getClob(parameterName);
	}

	@Override
	public Array getArray(String parameterName) throws SQLException {
		return mainEntity().getArray(parameterName);
	}

	@Override
	public Date getDate(String parameterName, Calendar cal) throws SQLException {
		return mainEntity().getDate(parameterName, cal);
	}

	@Override
	public Time getTime(String parameterName, Calendar cal) throws SQLException {
		return mainEntity().getTime(parameterName, cal);
	}

	@Override
	public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
		return mainEntity().getTimestamp(parameterName, cal);
	}

	@Override
	public URL getURL(String parameterName) throws SQLException {
		return mainEntity().getURL(parameterName);
	}

	@Override
	public RowId getRowId(int parameterIndex) throws SQLException {
		return mainEntity().getRowId(parameterIndex);
	}

	@Override
	public RowId getRowId(String parameterName) throws SQLException {
		return mainEntity().getRowId(parameterName);
	}

	@Override
	public void setRowId(String parameterName, RowId x) throws SQLException {
		forEach(st -> st.setRowId(parameterName, x));
	}

	@Override
	public void setNString(String parameterName, String value) throws SQLException {
		forEach(st -> st.setNString(parameterName, value));
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setNCharacterStream(parameterName, new StringReader(str), length));
	}

	@Override
	public void setNClob(String parameterName, NClob value) throws SQLException {
		forEach(st -> st.setNClob(parameterName, value));
	}

	@Override
	public void setClob(String parameterName, Reader reader, long length) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setClob(parameterName, new StringReader(str), length));
	}

	@Override
	public void setBlob(String parameterName, InputStream x, long length) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setBlob(parameterName, new ByteArrayInputStream(bytes), length));
	}

	@Override
	public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setNClob(parameterName, new StringReader(str), length));
	}

	@Override
	public NClob getNClob(int parameterIndex) throws SQLException {
		return mainEntity().getNClob(parameterIndex);
	}

	@Override
	public NClob getNClob(String parameterName) throws SQLException {
		return mainEntity().getNClob(parameterName);
	}

	@Override
	public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
		forEach(st -> st.setSQLXML(parameterName, xmlObject));
	}

	@Override
	public SQLXML getSQLXML(int parameterIndex) throws SQLException {
		return mainEntity().getSQLXML(parameterIndex);
	}

	@Override
	public SQLXML getSQLXML(String parameterName) throws SQLException {
		return mainEntity().getSQLXML(parameterName);
	}

	@Override
	public String getNString(int parameterIndex) throws SQLException {
		return mainEntity().getNString(parameterIndex);
	}

	@Override
	public String getNString(String parameterName) throws SQLException {
		return mainEntity().getNString(parameterName);
	}

	@Override
	public Reader getNCharacterStream(int parameterIndex) throws SQLException {
		return mainEntity().getNCharacterStream(parameterIndex);
	}

	@Override
	public Reader getNCharacterStream(String parameterName) throws SQLException {
		return mainEntity().getNCharacterStream(parameterName);
	}

	@Override
	public Reader getCharacterStream(int parameterIndex) throws SQLException {
		return mainEntity().getCharacterStream(parameterIndex);
	}

	@Override
	public Reader getCharacterStream(String parameterName) throws SQLException {
		return mainEntity().getCharacterStream(parameterName);
	}

	@Override
	public void setBlob(String parameterName, Blob x) throws SQLException {
		forEach(st -> st.setBlob(parameterName, x));
	}

	@Override
	public void setClob(String parameterName, Clob x) throws SQLException {
		forEach(st -> st.setClob(parameterName, x));
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setAsciiStream(parameterName, new ByteArrayInputStream(bytes), length));
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setBinaryStream(parameterName, new ByteArrayInputStream(bytes), length));
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setCharacterStream(parameterName, new StringReader(str), length));
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setAsciiStream(parameterName, new ByteArrayInputStream(bytes)));
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setBinaryStream(parameterName, new ByteArrayInputStream(bytes)));
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setCharacterStream(parameterName, new StringReader(str)));
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader reader) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setNCharacterStream(parameterName, new StringReader(str)));
	}

	@Override
	public void setClob(String parameterName, Reader reader) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setClob(parameterName, new StringReader(str)));
	}

	@Override
	public void setBlob(String parameterName, InputStream x) throws SQLException {
		byte[] bytes = readBytes(x);
		forEach(st -> st.setBlob(parameterName, new ByteArrayInputStream(bytes)));
	}

	@Override
	public void setNClob(String parameterName, Reader reader) throws SQLException {
		String str = readString(reader);
		forEach(st -> st.setNClob(parameterName, new StringReader(str)));
	}

	@Override
	public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
		return mainEntity().getObject(parameterIndex, type);
	}

	@Override
	public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
		return mainEntity().getObject(parameterName, type);
	}
}
