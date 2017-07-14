package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;

/**
 * Created by Emilia.Palaghita on 14-Jul-17.
 */
public class DepartmentDaoTest {
    DepartmentDao departmentDao = new DepartmentDao();

    @Test
    public void testFindByIdMethod() {

        Department department = departmentDao.findById(10L);
        System.out.println(department.toString());
    }

    @Test
    public void testGetNextIdValMethod() {
        Long nextVal = departmentDao.getNextIdVal();
        System.out.println(nextVal);
    }

    @Test
    public void testUpdateMethod() {
        Department department = departmentDao.update(271L, "Departament de fitosi", null);
        System.out.println(department.toString());
    }

    @Test
    public void testDeleteMethod() {
        departmentDao.delete(271L);
    }

    @Test
    public void testInsertMethod() {
        Department department = new Department();
        department.setLocation(1300L);
        department.setDepartmentName("PR&Media");
        departmentDao.insert(department);
    }
}
