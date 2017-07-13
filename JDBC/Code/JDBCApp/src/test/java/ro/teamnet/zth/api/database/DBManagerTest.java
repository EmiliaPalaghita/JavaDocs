package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Emilia.Palaghita on 13-Jul-17.
 */
public class DBManagerTest {
    @Test
    public void testGetConnectionMethod() {
        Connection connection = DBManager.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void testCheckConnectionMethod() {
        ResultSet result = DBManager.checkConnection(DBManager.getConnection());
        assertNotNull(result);
    }
}
