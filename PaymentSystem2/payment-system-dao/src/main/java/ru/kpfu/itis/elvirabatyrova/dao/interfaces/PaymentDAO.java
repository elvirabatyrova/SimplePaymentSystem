package ru.kpfu.itis.elvirabatyrova.dao.interfaces;

import ru.kpfu.itis.elvirabatyrova.model.Biller;
import ru.kpfu.itis.elvirabatyrova.model.Customer;
import ru.kpfu.itis.elvirabatyrova.model.Payment;

import java.sql.Date;
import java.util.List;

/**
 * Created by Pc on 26.07.2016.
 */
public interface PaymentDAO {

    List<Payment> findAll();
    Payment create(Customer customer, Biller biller, Long account, double amount, Date paymentDate);

}
