package ru.kpfu.itis.elvirabatyrova.service.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.BillerDAO;
import ru.kpfu.itis.elvirabatyrova.model.Biller;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.BillerService;

import java.util.List;

/**
 * Created by Pc on 27.07.2016.
 */

@Service
public class BillerServiceImpl implements BillerService {

    @Autowired
    BillerDAO billerDAO;

    @Override
    public List<Biller> findAll() {
        return billerDAO.findAll();
    }

    @Override
    public Biller create(String companyName) {
        return billerDAO.create(companyName);
    }

    @Override
    public void deleteById(Integer id) {
        billerDAO.deleteById(id);
    }

    @Override
    public void update(Integer id, String companyName) {
        billerDAO.update(id, companyName);
    }

    @Override
    public Biller getById(Integer id) {
        return billerDAO.getById(id);
    }

}
