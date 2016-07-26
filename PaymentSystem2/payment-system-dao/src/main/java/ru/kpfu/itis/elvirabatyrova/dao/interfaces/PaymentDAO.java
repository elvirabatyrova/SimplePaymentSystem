package ru.kpfu.itis.elvirabatyrova.dao.interfaces;

import ru.kpfu.itis.elvirabatyrova.model.Payment;

import java.util.List;

/**
 * Created by Pc on 26.07.2016.
 */
public interface PaymentDAO {

    List<Payment> findAll();

}
