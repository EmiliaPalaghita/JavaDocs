package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.api.em.ColumnInfo;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emilia.Palaghita on 14-Jul-17.
 */
public class EmployeeDao {
    private EntityManager manager = new EntityManagerImpl();

    public Employee findById(Long id) {
        return manager.findById(Employee.class, id);
    }

    public Long getNextIdVal() {
        String tableName = EntityUtils.getTableName(Employee.class);
        List<ColumnInfo> columns = EntityUtils.getColumns(Employee.class);
        String idColumn = null;
        for (ColumnInfo c : columns) {
            if (c.isId()) {
                idColumn = c.getDbColumnName();
                break;
            }
        }
        return manager.getNextIdVal(tableName, idColumn);
    }

    public void delete(Long id) {
        Employee employee = findById(id);
        manager.delete(employee);
    }

    public Employee insert(Employee employee) {
        return (Employee) manager.insert(employee);
    }

    private static List<Department> getMatchingDepartments(List<Department> departments, String pattern) {
        List<Department> matchingDepartments = new ArrayList<>();
        for (Department department : departments) {
            if (department.getDepartmentName().toLowerCase().contains(pattern.toLowerCase())) {
                matchingDepartments.add(department);
            }
        }
        return matchingDepartments;
    }

    private static String buildQuery(List<Department> departments) {
        String query = "SELECT * FROM EMPLOYEES E " + "WHERE ";
        for (Department department : departments) {
            query = query + "E.DEPARTMENT_ID = " + department.getId() + " OR ";
        }
        query = query.substring(0, query.length() - 4);
        return query;
    }

    public static List<Employee> getEmployeeByDepartment(String pattern) {
        DepartmentDao departmentDao = new DepartmentDao();
        List<Department> matchingDepartments = getMatchingDepartments(departmentDao.findAll(), pattern);

        String query = buildQuery(matchingDepartments);

        List<ColumnInfo> columnInfoList = EntityUtils.getColumns(Employee.class);
        List<Employee> employees = new ArrayList<>();

        Connection connection = DBManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Employee e = new Employee();
                for (ColumnInfo columnInfo : columnInfoList) {
                    Field f = e.getClass().getDeclaredField(columnInfo.getColumnName());
                    f.setAccessible(true);
                    Object value = resultSet.getObject(columnInfo.getDbColumnName());
                    value = EntityUtils.castFromSqlType(value, columnInfo.getColumnType());
                    f.set(e, value);
                }
                employees.add(e);
            }
            connection.close();
            return employees;
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<Employee> employees = getEmployeeByDepartment("ing");
        System.out.println(employees.size());
    }
}
