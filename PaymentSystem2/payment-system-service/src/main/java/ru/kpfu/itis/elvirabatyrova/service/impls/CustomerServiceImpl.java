package ru.kpfu.itis.elvirabatyrova.service.impls;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.elvirabatyrova.dao.HibernateUtil;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.CustomerDAO;
import ru.kpfu.itis.elvirabatyrova.model.Customer;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.CustomerService;

import java.sql.Date;
import java.util.List;

/**
 * Created by Pc on 25.07.2016.
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public Customer create(String firstName, String secondName, Date dateOfBirth, String address) {
        return customerDAO.create(firstName, secondName, dateOfBirth, address);
    }

    @Override
    public void deleteById(Integer id) {
        customerDAO.deleteById(id);
    }

    @Override
    public void update(Integer id, String firstName, String secondName, Date dateOfBirth, String address) {
        customerDAO.update(id, firstName, secondName, dateOfBirth, address);
    }

    @Override
    public Customer getById(Integer id) {
        return customerDAO.getById(id);
    }

}
