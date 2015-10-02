package org.jdbchub.jdbc;

import java.sql.SQLException;
import java.util.function.Function;
import static org.jdbchub.jdbc.JdbcUtils.*;

@FunctionalInterface
public interface JdbcFunction<T, R> extends Function<T, R> {

	@Override
	default R apply(T t) {
		try {
			return applyThrows(t);
		} catch (SQLException e) {
			return uncheckThrow(e);
		}
	}

	R applyThrows(T t) throws SQLException;

	static <T, R> JdbcFunction<T, R> jdbcFunc(JdbcFunction<T, R> func) {
		return func;
	}

}
