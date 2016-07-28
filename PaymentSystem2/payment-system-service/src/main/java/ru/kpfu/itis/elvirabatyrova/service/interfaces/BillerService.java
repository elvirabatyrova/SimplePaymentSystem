package ru.kpfu.itis.elvirabatyrova.service.interfaces;

import ru.kpfu.itis.elvirabatyrova.model.Biller;

import java.util.List;

/**
 * Created by Pc on 27.07.2016.
 */
public interface BillerService {

    List<Biller> findAll();
    Biller create(String companyName);
    void deleteById(Integer id);
    void update(Integer id, String companyName);
    Biller getById(Integer id);

}
