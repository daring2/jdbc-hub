package org.jdbchub.jdbc;

import java.util.concurrent.Callable;

class JdbcUtils {

	static <T> T uncheck(Callable<T> c) {
		try {
			return c.call();
		} catch (Exception e) {
			return uncheckThrow(e);
		}
	}

	@SuppressWarnings("unchecked")
	static <E extends Exception, T> T uncheckThrow(Exception e) throws E {
		throw (E) e;
	}

	private JdbcUtils() {
	}
}
