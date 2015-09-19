package org.jdbchub.jdbc;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

class JdbcUtils {

	@SuppressWarnings("unchecked")
	static <E extends Exception, T> T uncheckThrow(Exception e) throws E {
		throw (E) e;
	}

	static byte[] readBytes(InputStream in) throws SQLException {
		try {
			return ByteStreams.toByteArray(in);
		} catch (IOException e) {
			throw new SQLException(e);
		}
	}

	static String readString(Readable r) throws SQLException {
		try {
			return CharStreams.toString(r);
		} catch (IOException e) {
			throw new SQLException(e);
		}
	}

	private JdbcUtils() {
	}
}
