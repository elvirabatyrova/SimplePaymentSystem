package ru.kpfu.itis.elvirabatyrova.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.PaymentDAO;
import ru.kpfu.itis.elvirabatyrova.model.Biller;
import ru.kpfu.itis.elvirabatyrova.model.Customer;
import ru.kpfu.itis.elvirabatyrova.model.Payment;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.PaymentService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pc on 26.07.2016.
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDAO paymentDAO;

    public List<Payment> findAll() {
        return paymentDAO.findAll();
    }

    @Override
    public Payment create(Customer customer, Biller biller, Long account, double amount, Date paymentDate) {
        return paymentDAO.create(customer, biller, account, amount, paymentDate);
    }

    @Override
    public List<Payment> filterByCustomerAndBiller(Customer customer, Biller biller) {
        List<Payment> allPayments = paymentDAO.findAll();
        List<Payment> payments = new ArrayList<Payment>();
        if (customer == null && biller == null) {
            payments = allPayments;
        }
        if (customer != null && biller != null) {
            for (Payment payment: allPayments) {
                if (payment.getCustomer().getId() == customer.getId() && payment.getBiller().getId() == biller.getId()) {
                    payments.add(payment);
                }
            }
        }
        if (customer == null && biller != null) {
            for (Payment payment: allPayments) {
                if (payment.getBiller().getId() == biller.getId()) {
                    payments.add(payment);
                }
            }
        }
        if (customer != null && biller == null) {
            for (Payment payment: allPayments) {
                if (payment.getCustomer().getId() == customer.getId()) {
                    payments.add(payment);
                }
            }
        }
        return payments;
    }

}
