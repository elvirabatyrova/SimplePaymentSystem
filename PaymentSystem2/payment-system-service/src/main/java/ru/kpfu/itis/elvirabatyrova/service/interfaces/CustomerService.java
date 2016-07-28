package ru.kpfu.itis.elvirabatyrova.service.interfaces;

import ru.kpfu.itis.elvirabatyrova.model.Customer;

import java.sql.Date;
import java.util.List;

/**
 * Created by Pc on 25.07.2016.
 */

public interface CustomerService {

    List<Customer> findAll();
    Customer create(String firstName, String secondName, Date dateOfBirth, String address);
    void deleteById(Integer id);
    void update(Integer id, String firstName, String secondName, Date dateOfBirth, String address);
    Customer getById(Integer id);

}
