package ru.kpfu.itis.elvirabatyrova.service.impls;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.PaymentDAO;
import ru.kpfu.itis.elvirabatyrova.model.Biller;
import ru.kpfu.itis.elvirabatyrova.model.Customer;
import ru.kpfu.itis.elvirabatyrova.model.Payment;
import ru.kpfu.itis.elvirabatyrova.service.config.TestConfig;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.PaymentService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Pc on 28.07.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class PaymentServiceTest {

    public static PaymentService paymentService;
    public static PaymentDAO paymentDAO;

    @BeforeClass
    public static void prepareData() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        paymentService = (PaymentService) ac.getBean("paymentService");
        paymentDAO = (PaymentDAO) ac.getBean("paymentDAO");

    }

    @Test
    public void findAllTest() {
        Payment payment = mock(Payment.class);
        when(payment.getId()).thenReturn(1);
        List<Payment> payments = new ArrayList<Payment>();
        payments.add(payment);
        when(paymentDAO.findAll()).thenReturn(payments);
        Assert.assertEquals(payments, paymentService.findAll());
    }

    @Test
    public void createMethodTest() {
        Payment payment1 = mock(Payment.class);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date parsedDate;
        Date sqlParsedDate = null;

        try {
            parsedDate = sdf.parse("12.02.1999");
            sqlParsedDate = new Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Customer customer = mock(Customer.class);
        when(customer.getId()).thenReturn(5);
        Biller biller = mock(Biller.class);
        when(biller.getId()).thenReturn(3);
        when(payment1.getCustomer()).thenReturn(customer);
        when(payment1.getBiller()).thenReturn(biller);
        when(payment1.getAccount()).thenReturn(123456781234L);
        when(payment1.getAmount()).thenReturn(12.56);
        when(payment1.getPaymentDate()).thenReturn(sqlParsedDate);
        when(paymentDAO.create(customer, biller, 123456781234L, 12.56, sqlParsedDate)).thenReturn(payment1);
        Assert.assertEquals(payment1, paymentService.create(customer, biller, 123456781234L, 12.56, sqlParsedDate));
    }

    @Test
    public void filterByCustomerAndBillerTest() {
        Payment payment1 = mock(Payment.class);
        Customer customer = mock(Customer.class);
        when(customer.getId()).thenReturn(5);
        Biller biller = mock(Biller.class);
        when(biller.getId()).thenReturn(3);
        when(payment1.getCustomer()).thenReturn(customer);
        when(payment1.getBiller()).thenReturn(biller);
        when(payment1.getAccount()).thenReturn(123456781234L);
        when(payment1.getAmount()).thenReturn(12.56);
        when(payment1.getPaymentDate()).thenReturn(new Date(10000L));

        ArrayList<Payment> payments1 = new ArrayList<Payment>();
        payments1.add(payment1);
        when(paymentDAO.findAll()).thenReturn(payments1);
        Assert.assertEquals(payments1, paymentService.filterByCustomerAndBiller(customer, biller));
        Assert.assertEquals(payments1, paymentService.filterByCustomerAndBiller(customer, null));
        Assert.assertEquals(payments1, paymentService.filterByCustomerAndBiller(null, biller));
        Assert.assertEquals(payments1, paymentService.filterByCustomerAndBiller(null, null));

    }


}


