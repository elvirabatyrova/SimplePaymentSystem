package ru.kpfu.itis.elvirabatyrova.dao.impls;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.elvirabatyrova.dao.HibernateUtil;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.CustomerDAO;
import ru.kpfu.itis.elvirabatyrova.model.Customer;

import java.sql.Date;
import java.util.List;

/**
 * Created by Pc on 25.07.2016.
 */

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Customer> findAll() {
        Query q = session.createQuery("from Customer");
        return q.list();
    }

    @Override
    public Customer create(String firstName, String secondName, Date dateOfBirth, String address) {
        Transaction t = session.beginTransaction();
        Customer customer = new Customer(firstName, secondName, dateOfBirth, address);
        session.save(customer);
        t.commit();
        session.flush();
        session.clear();
        return customer;
    }

    @Override
    public void deleteById(Integer id) {
        Transaction t = session.beginTransaction();
        Query q = session.createQuery("delete Customer where id = " + id);
        q.executeUpdate();
        t.commit();
        session.flush();
        session.clear();
    }

    @Override
    public void update(Integer id, String firstName, String secondName, Date dateOfBirth, String address) {
        Transaction t = session.beginTransaction();
        Query q = session.createQuery("update Customer set firstName = :firstName, secondName = :secondName, " +
                "dateOfBirth = :dateOfBirth, address = :address where id = " + id);
        q.setParameter("firstName", firstName);
        q.setParameter("secondName", secondName);
        q.setParameter("dateOfBirth", dateOfBirth);
        q.setParameter("address", address);
        q.executeUpdate();
        t.commit();
        session.flush();
        session.clear();
    }

    @Override
    public Customer getById(Integer id) {
        Query q = session.createQuery("from Customer where id = " + id + "");
        if (q.list().size() != 0) {
            Customer customer = (Customer) q.list().get(0);
            return customer;
        }
        else {
            return null;
        }
    }

}
