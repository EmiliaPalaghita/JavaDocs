package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.api.em.ColumnInfo;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by Emilia.Palaghita on 14-Jul-17.
 */
public class DepartmentDao {
    private EntityManager manager = new EntityManagerImpl();

    public Department findById(Long id) {
        return manager.findById(Department.class, id);
    }

    public Long getNextIdVal() {
        String tableName = EntityUtils.getTableName(Department.class);
        List<ColumnInfo> columns = EntityUtils.getColumns(Department.class);
        String idColumn = null;
        for (ColumnInfo c : columns) {
            if (c.isId()) {
                idColumn = c.getDbColumnName();
                break;
            }
        }
        return manager.getNextIdVal(tableName, idColumn);
    }

    public Department update(Long id, String departmentName, Long locationId) {
        Department department = findById(id);
        if (!departmentName.equals("")) {
            department.setDepartmentName(departmentName);
        }
        if (locationId != null) {
            department.setLocation(locationId);
        }
        return manager.update(department);
    }

    public void delete(Long id) {
        Department department = manager.findById(Department.class, id);
        manager.delete(department);
    }

    public Department insert(Department department) {
        return (Department) manager.insert(department);
    }

    public List<Department> findAll() {
        return manager.findAll(Department.class);
    }
}
