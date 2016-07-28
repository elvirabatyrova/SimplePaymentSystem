package ru.kpfu.itis.elvirabatyrova.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.elvirabatyrova.dao.interfaces.CustomerDAO;
import ru.kpfu.itis.elvirabatyrova.model.Biller;
import ru.kpfu.itis.elvirabatyrova.model.Customer;
import ru.kpfu.itis.elvirabatyrova.model.Payment;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.BillerService;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.CustomerService;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.PaymentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pc on 25.07.2016.
 */

@Controller
public class MainController {

    @Autowired
    CustomerService customerService;

    @Autowired
    BillerService billerService;

    @Autowired
    PaymentService paymentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(Model model) {

        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("billers", billerService.findAll());
        model.addAttribute("payments", paymentService.findAll());

        return "index";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getCustomersPage(Model model) {

        model.addAttribute("customers", customerService.findAll());

        return "customers";
    }

    @RequestMapping(value = "/billers", method = RequestMethod.GET)
    public String getBillersPage(Model model) {

        model.addAttribute("billers", billerService.findAll());

        return "billers";
    }

    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
    public String deleteCustomer(Model model, @RequestParam("id") Integer id) {

        customerService.deleteById(id);
        model.addAttribute("customers", customerService.findAll());

        return "customers";
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.GET)
    public String saveCustomerPage(Model model, @RequestParam(value = "id", required = false) Integer id) {

        model.addAttribute("customers", customerService.findAll());
        if (id != null) {
            model.addAttribute("customer", customerService.getById(id));
        }
        return "saveCustomer";

    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public String updateCustomer(Model model, @RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "firstName") String firstName,
                                 @RequestParam(value = "secondName") String secondName, @RequestParam(value = "dateOfBirth")String dateOfBirth,
                                 @RequestParam(value = "address") String address) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date parsedDate;
        Date sqlParsedDate = null;

        try {
            parsedDate = sdf.parse(dateOfBirth);
            sqlParsedDate = new Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (id != null) {
            customerService.update(id, firstName, secondName, sqlParsedDate, address);
        }
        else {
            customerService.create(firstName, secondName, sqlParsedDate, address);
        }

        model.addAttribute("customers", customerService.findAll());

        return "customers";
    }

    @RequestMapping(value = "/deleteBiller", method = RequestMethod.POST)
    public String deleteBiller(Model model, @RequestParam("id") Integer id) {

        billerService.deleteById(id);
        model.addAttribute("billers", billerService.findAll());

        return "billers";
    }

    @RequestMapping(value = "/saveBiller", method = RequestMethod.GET)
    public String saveBillerPage(Model model, @RequestParam(value = "id", required = false) Integer id) {

        model.addAttribute("billers", billerService.findAll());
        if (id != null) {
            model.addAttribute("biller", billerService.getById(id));
        }

        return "saveBiller";
    }

    @RequestMapping(value = "/saveBiller", method = RequestMethod.POST)
    public String saveBiller(Model model, @RequestParam(value = "id", required = false) Integer id,
                                 @RequestParam(value = "companyName") String companyName) {

        if (id != null) {
            billerService.update(id, companyName);
        }
        else {
            billerService.create(companyName);
        }
        model.addAttribute("billers", billerService.findAll());

        return "billers";
    }

    @RequestMapping(value = "/savePayment", method = RequestMethod.GET)
    public String savePaymentPage(Model model) {

        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("billers", billerService.findAll());
        model.addAttribute("payments", paymentService.findAll());

        return "savePayment";
    }

    @RequestMapping(value = "/savePayment", method = RequestMethod.POST)
    public String savePayment(Model model, @RequestParam("customer") String customer,
                              @RequestParam("biller") String biller, @RequestParam(value = "account", required = false)
                              Long account, @RequestParam("amount") double amount) {

        Pattern p = Pattern.compile("\\[(?<id>[0-9])+\\].*");

        Matcher m1 = p.matcher(customer);
        Integer customer_id = 0;
        while (m1.find()) {
            customer_id = Integer.parseInt(m1.group("id"));
        }
        Customer customer1 = customerService.getById(customer_id);

        Matcher m2 = p.matcher(biller);
        Integer biller_id = 0;
        while (m2.find()) {
            biller_id = Integer.parseInt(m2.group("id"));
        }
        Biller biller1 = billerService.getById(biller_id);

        java.util.Date d = new java.util.Date();
        java.sql.Date date = new java.sql.Date(d.getTime());

        paymentService.create(customer1, biller1, account, amount, date);

        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("billers", billerService.findAll());
        model.addAttribute("payments", paymentService.findAll());

        return "index";
    }

    @RequestMapping(value = "/payment_filter", method = RequestMethod.GET)
    public void paymentFilter(Model model, HttpServletResponse response,
                                @RequestParam(value = "q1", required = false) String q1,
                                @RequestParam(value = "q2", required = false) String q2) throws JSONException, IOException {

        Pattern p = Pattern.compile("\\[(?<id>[0-9])+\\].*");

        Customer customer1 = null;
        if (!q1.equals("All Customers")) {
            Matcher m1 = p.matcher(q1);
            Integer customer_id = 0;
            while (m1.find()) {
                customer_id = Integer.parseInt(m1.group("id"));
            }
            customer1 = customerService.getById(customer_id);
        }

        Biller biller1 = null;
        if (!q2.equals("All Billers")) {
            Matcher m2 = p.matcher(q2);
            Integer biller_id = 0;
            while (m2.find()) {
                biller_id = Integer.parseInt(m2.group("id"));
            }
            biller1 = billerService.getById(biller_id);
        }

        List<Payment> payments = paymentService.filterByCustomerAndBiller(customer1, biller1);

        JSONArray ja = new JSONArray();
        for (Payment payment: payments) {
            ja.put(new JSONObject(payment));
        }

        JSONObject jo = new JSONObject();
        jo.put("results", ja);
        model.addAttribute(jo);
        response.getWriter().print(jo);
    }


}
