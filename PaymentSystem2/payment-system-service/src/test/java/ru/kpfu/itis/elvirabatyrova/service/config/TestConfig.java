package ru.kpfu.itis.elvirabatyrova.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.elvirabatyrova.dao.impls.BillerDAOImpl;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.BillerDAO;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.CustomerDAO;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.PaymentDAO;
import ru.kpfu.itis.elvirabatyrova.model.Biller;
import ru.kpfu.itis.elvirabatyrova.model.Customer;
import ru.kpfu.itis.elvirabatyrova.service.impls.BillerServiceImpl;
import ru.kpfu.itis.elvirabatyrova.service.impls.CustomerServiceImpl;
import ru.kpfu.itis.elvirabatyrova.service.impls.PaymentServiceImpl;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.BillerService;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.CustomerService;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.PaymentService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;

/**
 * Created by Pc on 28.07.2016.
 */

@Configuration
public class TestConfig {

    @Bean
    public BillerService billerService() {
        return new BillerServiceImpl();
    }

    @Bean
    public BillerDAO billerDAO() {
        return mock(BillerDAO.class);
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerServiceImpl();
    }

    @Bean
    public CustomerDAO customerDAO() {
        return mock(CustomerDAO.class);
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentServiceImpl();
    }

    @Bean
    public PaymentDAO paymentDAO() {
        return mock(PaymentDAO.class);
    }

}
