package ru.kpfu.itis.elvirabatyrova.service.impls;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.CustomerDAO;
import ru.kpfu.itis.elvirabatyrova.model.Customer;
import ru.kpfu.itis.elvirabatyrova.service.config.TestConfig;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.CustomerService;

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
public class CustomerServiceTest {

    public static CustomerService customerService;
    public static CustomerDAO customerDAO;
    public static Customer customer;
    public static List<Customer> customers;

    @BeforeClass
    public static void prepareData() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        customerService = (CustomerService) ac.getBean("customerService");
        customerDAO = (CustomerDAO) ac.getBean("customerDAO");

        customer = mock(Customer.class);
        when(customer.getId()).thenReturn(1);
        customers = new ArrayList<Customer>();
        customers.add(customer);
    }

    @Test
    public void findAllTest() {
        when(customerDAO.findAll()).thenReturn(customers);
        Assert.assertEquals(customers, customerService.findAll());
    }

    @Test
    public void getById() {
        when(customerDAO.getById(1)).thenReturn(customer);
        Assert.assertEquals(customer, customerService.getById(1));
    }

    @Test
    public void createMethodTest() {
        Customer customer1 = mock(Customer.class);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date parsedDate;
        Date sqlParsedDate = null;

        try {
            parsedDate = sdf.parse("12.02.1999");
            sqlParsedDate = new Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(customer1.getFirstName()).thenReturn("Alina");
        when(customer1.getSecondName()).thenReturn("Garipova");
        when(customer1.getDateOfBirth()).thenReturn(sqlParsedDate);
        when(customer1.getAddress()).thenReturn("Kazan");
        when(customerDAO.create("Alina", "Garipova", sqlParsedDate, "Kazan")).thenReturn(customer1);
        Assert.assertEquals(customer1, customerService.create("Alina", "Garipova", sqlParsedDate, "Kazan"));
    }

    @Test
    public void deleteMethodTest() {
        customerService.deleteById(1);
        verify(customerDAO, times(1)).deleteById(1);
    }

    @Test
    public void updateMethodTest() {
        customerService.update(3, "Alina", "Garipova", new Date(10000L), "Kazan");
        verify(customerDAO, times(1)).update(3, "Alina", "Garipova", new Date(10000L), "Kazan");
    }

}
