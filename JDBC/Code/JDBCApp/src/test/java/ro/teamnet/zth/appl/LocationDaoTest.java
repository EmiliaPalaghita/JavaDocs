package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Location;

/**
 * Created by Emilia.Palaghita on 14-Jul-17.
 */
public class LocationDaoTest {
    LocationDao locationDao = new LocationDao();

    @Test
    public void testFindById() {
        Location location = locationDao.findById(3000L);
        System.out.println(location.toString());
    }

    @Test
    public void testGetNextIdValMethod() {
        Long nextVal = locationDao.getNextIdVal();
        System.out.println(nextVal);
    }

    @Test
    public void testUpdateMethod() {
        Location location = locationDao.update(3201L, "", "", "La mama acasa", "");
        System.out.println(location.toString());
    }

    @Test
    public void testDeleteMethod() {
        locationDao.delete(3201L);
    }

    @Test
    public void testInsertMethod() {
        Location location = new Location();
        location.setStateProvince("blabla");
        location.setCity("la Bibica");
        location.setPostalCode("6969");
        location.setStreetAddress("strada studiourilor");
        location = locationDao.insert(location);
        System.out.println(location.toString());
    }
}
