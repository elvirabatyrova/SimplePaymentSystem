package ru.kpfu.itis.elvirabatyrova.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.PaymentDAO;
import ru.kpfu.itis.elvirabatyrova.model.Payment;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.PaymentService;

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

}
