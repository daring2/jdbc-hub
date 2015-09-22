package org.jdbchub.jdbc;

import java.sql.SQLException;
import java.util.function.Predicate;
import static org.jdbchub.jdbc.JdbcUtils.uncheckThrow;

@FunctionalInterface
interface JdbcPredicate<T> extends Predicate<T> {

	@Override
	default boolean test(T t) {
		try {
			return testTrows(t);
		} catch (SQLException e) {
			return uncheckThrow(e);
		}
	}

	boolean testTrows(T t) throws SQLException;

}
