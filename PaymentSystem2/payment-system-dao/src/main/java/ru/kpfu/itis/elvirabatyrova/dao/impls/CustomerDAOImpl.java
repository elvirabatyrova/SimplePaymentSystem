package ru.kpfu.itis.elvirabatyrova.dao.impls;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.elvirabatyrova.dao.HibernateUtil;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.CustomerDAO;
import ru.kpfu.itis.elvirabatyrova.model.Customer;

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

}
