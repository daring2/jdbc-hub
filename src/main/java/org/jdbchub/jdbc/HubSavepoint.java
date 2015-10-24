package org.jdbchub.jdbc;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

public class HubSavepoint extends EntityList<Savepoint> implements Savepoint {

	public HubSavepoint(List<Savepoint> savepoints) {
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
