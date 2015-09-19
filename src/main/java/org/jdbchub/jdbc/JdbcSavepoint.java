package org.jdbchub.jdbc;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

public class JdbcSavepoint extends EntityList<Savepoint> implements Savepoint {

	public JdbcSavepoint(List<Savepoint> savepoints) {
		super(savepoints);
	}

	@Override
	public int getSavepointId() throws SQLException {
		return mainEntity().getSavepointId();
	}

	@Override
	public String getSavepointName() throws SQLException {
		return mainEntity().getSavepointName();
	}

}
