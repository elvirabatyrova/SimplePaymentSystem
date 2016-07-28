package ru.kpfu.itis.elvirabatyrova.service.impls;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.BillerDAO;
import ru.kpfu.itis.elvirabatyrova.model.Biller;
import ru.kpfu.itis.elvirabatyrova.service.config.TestConfig;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.BillerService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by Pc on 28.07.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class BillerServiceTest {

    public static BillerService billerService;
    public static BillerDAO billerDAO;
    public static Biller biller;
    public static List<Biller> billers;

    @BeforeClass
    public static void prepareData() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        billerService = (BillerService) ac.getBean("billerService");
        billerDAO = (BillerDAO)ac.getBean("billerDAO");

        biller = mock(Biller.class);
        when(biller.getId()).thenReturn(1);
        billers = new ArrayList<Biller>();
        billers.add(biller);
    }

    @Test
    public void findAllTest() {
        when(billerDAO.findAll()).thenReturn(billers);
        Assert.assertEquals(billers, billerService.findAll());
    }

    @Test
    public void getById() {
        when(billerDAO.getById(1)).thenReturn(biller);
        Assert.assertEquals(biller, billerService.getById(1));
    }

    @Test
    public void createMethodTest() {
        Biller biller1 = mock(Biller.class);
        when(biller1.getId()).thenReturn(2);
        when(billerDAO.create("Company name")).thenReturn(biller1);
        Assert.assertEquals(biller1.getId(), billerService.create("Company name").getId());
    }

    @Test
    public void deleteMethodTest() {
        billerService.deleteById(1);
        verify(billerDAO, times(1)).deleteById(1);
    }

    @Test
    public void updateMethodTest() {
        billerService.update(1, "Company 1");
        verify(billerDAO, times(1)).update(1, "Company 1");
    }

}
