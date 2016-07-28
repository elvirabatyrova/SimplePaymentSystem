package ru.kpfu.itis.elvirabatyrova.service.interfaces;

import ru.kpfu.itis.elvirabatyrova.model.Biller;
import ru.kpfu.itis.elvirabatyrova.model.Customer;
import ru.kpfu.itis.elvirabatyrova.model.Payment;

import java.sql.Date;
import java.util.List;

/**
 * Created by Pc on 26.07.2016.
 */
public interface PaymentService {

    List<Payment> findAll();
    Payment create(Customer customer, Biller biller, Long account, double amount, Date paymentDate);
    List<Payment> filterByCustomerAndBiller(Customer customer, Biller biller);

}
