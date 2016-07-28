package ru.kpfu.itis.elvirabatyrova.dao.impls;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.elvirabatyrova.dao.HibernateUtil;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.PaymentDAO;
import ru.kpfu.itis.elvirabatyrova.model.Biller;
import ru.kpfu.itis.elvirabatyrova.model.Customer;
import ru.kpfu.itis.elvirabatyrova.model.Payment;

import java.sql.Date;
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

    @Override
    public Payment create(Customer customer, Biller biller, Long account, double amount, Date paymentDate) {
        Transaction t = session.beginTransaction();
        Payment payment = new Payment(customer, biller, account, amount, paymentDate);
        session.save(payment);
        t.commit();
        session.flush();
        session.clear();
        return payment;
    }

}
