package ru.kpfu.itis.elvirabatyrova.dao.impls;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.elvirabatyrova.dao.HibernateUtil;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.PaymentDAO;
import ru.kpfu.itis.elvirabatyrova.model.Payment;

import java.util.List;

/**
 * Created by Pc on 26.07.2016.
 */

@Repository
public class PaymentDAOImpl implements PaymentDAO {

    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Payment> findAll() {
        Query q = session.createQuery("from Payment");
        return q.list();
    }

}
