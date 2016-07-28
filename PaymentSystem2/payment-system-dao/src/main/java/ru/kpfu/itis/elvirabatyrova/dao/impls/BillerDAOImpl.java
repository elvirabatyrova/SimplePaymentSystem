package ru.kpfu.itis.elvirabatyrova.dao.impls;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.elvirabatyrova.dao.HibernateUtil;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.BillerDAO;
import ru.kpfu.itis.elvirabatyrova.model.Biller;

import java.util.List;

/**
 * Created by Pc on 26.07.2016.
 */

@Repository
public class BillerDAOImpl implements BillerDAO {

    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<Biller> findAll() {
        Query q = session.createQuery("from Biller");
        return q.list();
    }

    @Override
    public Biller create(String companyName) {
        Transaction t = session.beginTransaction();
        Biller biller = new Biller(companyName);
        session.save(biller);
        t.commit();
        session.flush();
        session.clear();
        return biller;
    }

    @Override
    public void deleteById(Integer id) {
        Transaction t = session.beginTransaction();
        Query q = session.createQuery("delete Biller where id = " + id);
        q.executeUpdate();
        t.commit();
        session.flush();
        session.clear();
    }

    @Override
    public void update(Integer id, String companyName) {
        Transaction t = session.beginTransaction();
        Query q = session.createQuery("update Biller set companyName = :companyName where id = " + id);
        q.setParameter("companyName", companyName);
        q.executeUpdate();
        t.commit();
        session.flush();
        session.clear();
    }

    @Override
    public Biller getById(Integer id) {
        Query q = session.createQuery("from Biller where id = " + id + "");
        if (q.list().size() != 0) {
            Biller biller = (Biller) q.list().get(0);
            return biller;
        }
        else {
            return null;
        }
    }
}
