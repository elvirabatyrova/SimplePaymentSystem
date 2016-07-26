package ru.kpfu.itis.elvirabatyrova.dao.interfaces;

import ru.kpfu.itis.elvirabatyrova.model.Customer;

import java.util.List;

/**
 * Created by Pc on 25.07.2016.
 */

public interface CustomerDAO {

    List<Customer> findAll();

}
