package ru.kpfu.itis.elvirabatyrova.service.impls;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.elvirabatyrova.dao.HibernateUtil;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.CustomerDAO;
import ru.kpfu.itis.elvirabatyrova.model.Customer;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.CustomerService;

import java.util.List;

/**
 * Created by Pc on 25.07.2016.
 */

@Repository
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

}
