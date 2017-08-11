package service;

import connection.SessionUtil;
import dao.EmployeeDAO;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService extends SessionUtil implements EmployeeDAO {

    public void add(Employee employee) throws SQLException {
        //открываем сессию с транзакцией
        openTransactionSession();
            Session session = getSession();
            session.save(employee);
        //закрываем сессию с транзакцией
        closeTransactionSesstion();
    }

    public List<Employee> getAll() throws SQLException {
        openTransactionSession();
            String sql = "SELECT * FROM EMPLOYEE";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Employee.class);
        List<Employee> employeeList = query.list();

            closeTransactionSesstion();
        return employeeList;
    }

    public Employee getById(Long id) throws SQLException {
        openTransactionSession();
            String sql = "SELECT * FROM EMPLOYEE WHERE ID = :id";
        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Employee.class);
        query.setParameter("id", id);

        Employee employee = (Employee) query.getSingleResult();

            closeTransactionSesstion();
        return employee;
    }

    public void update(Employee employee) throws SQLException {
        openTransactionSession();
            Session session = getSession();
            session.update(employee);
        closeTransactionSesstion();
    }

    public void remove(Employee employee) throws SQLException {
        openTransactionSession();
            Session session = getSession();
            session.remove(employee);
        closeTransactionSesstion();
    }
}