package service;

import connection.SessionUtil;
import dao.AddressDAO;
import entity.Address;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;


public class AddressService extends SessionUtil implements AddressDAO {

    public void add(Address address) throws SQLException {
        //открываем сессию с транзакцией
        openTransactionSession();
            Session session = getSession();
            session.save(address);
        //закрываем сессию с транзакцией
        closeTransactionSesstion();
    }

    public List<Address> getAll() throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM ADDRESS";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Address.class);
        List<Address> addressList = query.list();

        closeTransactionSesstion();

        return addressList;
    }

    public Address getById(Long id) throws SQLException {
        //open session with a transaction

        String sql = "SELECT * FROM ADDRESS WHERE ID = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Address.class);
        query.setParameter("id", id);

        Address address = (Address) query.getSingleResult();

        closeTransactionSesstion();

        return address;
    }

    public void update(Address address) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.update(address);

        closeTransactionSesstion();
    }

    public void remove(Address address) throws SQLException {
        openTransactionSession();

        Session session = getSession();
        session.remove(address);

        closeTransactionSesstion();
    }

}