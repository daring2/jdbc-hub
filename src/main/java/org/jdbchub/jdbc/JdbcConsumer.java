package org.jdbchub.jdbc;

import java.sql.SQLException;
import java.util.function.Consumer;
import static org.jdbchub.jdbc.JdbcUtils.*;

@FunctionalInterface
interface JdbcConsumer<T> extends Consumer<T> {

	@Override
	default void accept(T t) {
		try {
			acceptThrows(t);
		} catch (SQLException e) {
			uncheckThrow(e);
		}
	}

	void acceptThrows(T t) throws SQLException;

}
