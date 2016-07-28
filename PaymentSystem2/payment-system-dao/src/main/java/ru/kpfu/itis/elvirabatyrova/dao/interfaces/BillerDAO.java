package ru.kpfu.itis.elvirabatyrova.dao.interfaces;

import ru.kpfu.itis.elvirabatyrova.model.Biller;

import java.util.List;

/**
 * Created by Pc on 26.07.2016.
 */
public interface BillerDAO {

    List<Biller> findAll();
    Biller create(String companyName);
    void deleteById(Integer id);
    void update(Integer id, String companyName);
    Biller getById(Integer id);

}
