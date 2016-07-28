package ru.kpfu.itis.elvirabatyrova.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.kpfu.itis.elvirabatyrova.model.Biller;
import ru.kpfu.itis.elvirabatyrova.model.Customer;
import ru.kpfu.itis.elvirabatyrova.model.Payment;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.BillerService;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.CustomerService;
import ru.kpfu.itis.elvirabatyrova.service.interfaces.PaymentService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class FileController {

	@Autowired
	PaymentService paymentService;

	@Autowired
	CustomerService customerService;

	@Autowired
	BillerService billerService;
 
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {
 
		String name = null;
 
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				name = file.getOriginalFilename();

				Pattern p = Pattern.compile(".*.xml");
				Matcher m = p.matcher(name);

				if (!m.matches()) {
					model.addAttribute("error", "Wrong file format");
					return "errorPage";
				}

				String rootPath = "C:\\path\\";
				File dir = new File(rootPath + File.separator + "loadFiles");

				if (!dir.exists()) {
					dir.mkdirs();
				}

				File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
				stream.write(bytes);
				stream.flush();
				stream.close();

				parseFile(uploadedFile);
				model.addAttribute("customers", customerService.findAll());
				model.addAttribute("billers", billerService.findAll());
				model.addAttribute("payments", paymentService.findAll());
				return "index";
//				return "You successfully uploaded file '" + name + "'" + parseFile(uploadedFile);

			} catch (Exception e) {
				model.addAttribute("error", "You failed to upload " + name + " => " + e.getMessage());
				return "errorPage";
			}
		} else {
			model.addAttribute("error", "You failed to upload " + name + " because the file was empty.");
			return "errorPage";
		}
	}

	public void parseFile(File file) {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList payment = doc.getElementsByTagName("payment");

			for (int temp = 0; temp < payment.getLength(); temp++) {

				Node paymentNode = payment.item(temp);

				if (paymentNode.getNodeType() == Node.ELEMENT_NODE) {

					Customer customer1;
					Biller biller1;
					Payment payment1;

					Element paymentElement = (Element) paymentNode;

					NodeList customer = doc.getElementsByTagName("customer");
					Node customerNode = customer.item(temp);
					Element customerElement = (Element)customerNode;

					NodeList biller = doc.getElementsByTagName("biller");
					Node billerNode = biller.item(temp);
					Element billerElement = (Element) billerNode;

					System.out.println("Customer id: " + customerElement.getElementsByTagName("id").item(0).getTextContent());
					System.out.println("Customer First Name: " + customerElement.getElementsByTagName("firstName").item(0).getTextContent());
					System.out.println("Customer Last Name: " + customerElement.getElementsByTagName("lastName").item(0).getTextContent());
					System.out.println("Customer Date Of Birth: " + customerElement.getElementsByTagName("dateOfBirth").item(0).getTextContent());
					System.out.println("Customer Address: " + customerElement.getElementsByTagName("address").item(0).getTextContent());


					Integer customerId = Integer.parseInt(customerElement.getElementsByTagName("id").item(0).getTextContent());
					if (customerService.getById(customerId) != null) {
						customer1 = customerService.getById(customerId);
					}
					else {
						SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
						java.util.Date parsedDate;
						Date sqlParsedDate = null;

						try {
							parsedDate = sdf.parse(customerElement.getElementsByTagName("dateOfBirth").item(0).getTextContent());
							sqlParsedDate = new Date(parsedDate.getTime());
						} catch (ParseException e) {
							e.printStackTrace();
						}

						customer1 = customerService.create(customerElement.getElementsByTagName("firstName").item(0).getTextContent(),
								customerElement.getElementsByTagName("lastName").item(0).getTextContent(), sqlParsedDate,
								customerElement.getElementsByTagName("address").item(0).getTextContent());
					}

					System.out.println("Biller id: " + billerElement.getElementsByTagName("id").item(0).getTextContent());
					System.out.println("Biller Company Name: " + billerElement.getElementsByTagName("companyName").item(0).getTextContent());


					Integer billerId = Integer.parseInt(billerElement.getElementsByTagName("id").item(0).getTextContent());
					if (billerService.getById(billerId) != null) {
						biller1 = billerService.getById(billerId);
					}
					else {
						biller1 = billerService.create(billerElement.getElementsByTagName("companyName").item(0).getTextContent());
					}

					java.util.Date d = new java.util.Date();
					java.sql.Date date = new java.sql.Date(d.getTime());
					payment1 = paymentService.create(customer1, biller1,
							Long.parseLong(paymentElement.getElementsByTagName("account").item(0).getTextContent()),
							Double.parseDouble(paymentElement.getElementsByTagName("amount").item(0).getTextContent()), date);

					System.out.println("Account: " + paymentElement.getElementsByTagName("account").item(0).getTextContent());
					System.out.println("Amount: " + paymentElement.getElementsByTagName("amount").item(0).getTextContent());
					System.out.println("Date: " + paymentElement.getElementsByTagName("paymentDate").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}