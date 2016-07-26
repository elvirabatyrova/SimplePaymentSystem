package ru.kpfu.itis.elvirabatyrova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.CustomerDAO;
import ru.kpfu.itis.elvirabatyrova.model.Customer;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.CustomerService;

import java.util.List;

/**
 * Created by Pc on 25.07.2016.
 */

@Controller
public class MainController {

    @Autowired
    CustomerService customerService;
//
//    @Autowired
//    PaymentDAO paymentDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(Model model) {
        return "index";
    }

}
