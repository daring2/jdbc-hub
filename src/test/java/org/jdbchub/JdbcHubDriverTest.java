package org.jdbchub;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JdbcHubDriverTest {

    @Test
    public void testAcceptsURL() throws SQLException {
        JdbcHubDriver driver = new JdbcHubDriver();
        assertTrue(driver.acceptsURL("jdbc:jdbchub:props"));
        assertFalse(driver.acceptsURL("jdbc:postgresql:props"));
    }

}