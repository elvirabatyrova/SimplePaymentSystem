package ru.kpfu.itis.elvirabatyrova.service.interfaces;

import ru.kpfu.itis.elvirabatyrova.model.Payment;

import java.util.List;

/**
 * Created by Pc on 26.07.2016.
 */
public interface PaymentService {

    List<Payment> findAll();

}
