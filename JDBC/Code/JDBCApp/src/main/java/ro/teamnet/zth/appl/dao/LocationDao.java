package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.ColumnInfo;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by Emilia.Palaghita on 14-Jul-17.
 */
public class LocationDao {
    private EntityManager manager = new EntityManagerImpl();

    public Location findById(Long id) {
        return manager.findById(Location.class, id);
    }

    public Long getNextIdVal() {
        String tableName = EntityUtils.getTableName(Location.class);
        List<ColumnInfo> columns = EntityUtils.getColumns(Location.class);
        String idColumn = null;
        for (ColumnInfo c : columns) {
            if (c.isId()) {
                idColumn = c.getDbColumnName();
                break;
            }
        }
        return manager.getNextIdVal(tableName, idColumn);
    }

    public Location update(Long id, String streetAdress, String postalCode, String city, String stateProvince) {
        Location location = manager.findById(Location.class, id);
        if (!streetAdress.equals("")) {
            location.setStreetAddress(streetAdress);
        }
        if (!postalCode.equals("")) {
            location.setPostalCode(postalCode);
        }
        if (!city.equals("")) {
            location.setCity(city);
        }
        if (!stateProvince.equals("")) {
            location.setStateProvince("");
        }

        return manager.update(location);
    }

    public void delete(Long id) {
        Location location = manager.findById(Location.class, id);
        manager.delete(location);
    }

    public Location insert(Location location) {
        return (Location) manager.insert(location);
    }
}
