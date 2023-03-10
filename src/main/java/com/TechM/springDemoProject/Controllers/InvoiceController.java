package com.TechM.springDemoProject.Controllers;

import com.TechM.springDemoProject.Models.Customer;
import com.TechM.springDemoProject.Models.Invoice;
import com.TechM.springDemoProject.RequestObject.CustomerRequestForCreateCustomer;
import com.TechM.springDemoProject.RequestObject.InvoiceRequest;
import com.TechM.springDemoProject.Services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "Invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @RequestMapping(value = "Invoice/getAll", method = RequestMethod.GET)
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return invoices;
    }


    @RequestMapping(value = "Invoice/getById", method = RequestMethod.GET)
    public Invoice getInvoiceById(@RequestParam Integer invoiceId) {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        return invoice;
    }

    @RequestMapping(value = "Invoice/getByEmail", method = RequestMethod.GET)
    public Invoice getInvoiceByEmail(@RequestParam String email) {
        Invoice invoice = invoiceService.getInvoiceByEmail(email);
        return invoice;
    }

    @RequestMapping(value = "Invoice/getByFax", method = RequestMethod.GET)
    public Invoice getInvoiceByFax(@RequestParam String fax) {
        Invoice invoice = invoiceService.getInvoiceByFax(fax);
        return invoice;
    }

    @RequestMapping(value = "Invoice/getByWebsite", method = RequestMethod.GET)
    public Invoice getInvoiceByWebsite(@RequestParam String website) {
        Invoice invoice = invoiceService.getInvoiceByWebsite(website);
        return invoice;
    }

    @GetMapping(value = "addInvoice")
    public void addInvoice() {
        invoiceService.addInvoice();
        invoiceService.addInvoice();
    }

    @RequestMapping(value = "/getIsActive", method = RequestMethod.GET)
    public List<Invoice> getAllActiveInvoices() {
        List<Invoice> invoces = invoiceService.getAllActiveInvoices();
        return invoces;
    }

    @RequestMapping(value = "/getInActive", method = RequestMethod.GET)
    public List<Invoice> getAllInActiveInvoices() {
        List<Invoice> invoces = invoiceService.getAllInActiveInvoices();
        return invoces;
    }

    @RequestMapping(value = "/getLatestRow", method = RequestMethod.GET)
    public Invoice findTopByOrderById() {
        Invoice invoice = invoiceService.findTopByOrderById();
        return invoice;
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public void deleteByIdIsActive(Integer id) {
        invoiceService.deleteByIdIsActive(id);
    }

    @RequestMapping(value = "/deleteBySetActiveFalse", method = RequestMethod.GET)
    public void deleteItemByID(Integer id) {
        invoiceService.deleteInvoiceByID(id);
    }

    @RequestMapping(value = "/deleteByEmail", method = RequestMethod.GET)
    public void deleteByInvoiceEmail(String email) {
        invoiceService.deleteInvoiceByEmail(email);
    }

    @RequestMapping(value = "deleteAllInvoices", method = RequestMethod.GET)
    public void deleteAllInvoices() {
       invoiceService.deleteAllInvoices();
    }


    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    public void deleteAll() {
        invoiceService.deleteAll();
    }

    @RequestMapping(value = "/createInvoice", method = RequestMethod.POST)
    public void createInvoice(@RequestBody InvoiceRequest invoiceRequest) throws ParseException {
        invoiceService.createInvoice(invoiceRequest.getEmail(), invoiceRequest.getFax(), invoiceRequest.getWebsite(), invoiceRequest.getCustomerId(), invoiceRequest.getCreatedDate(), invoiceRequest.getIsActive());


    }

    @GetMapping(value = "deleteCustomerByInvoiceId")
    public void  deleteInvoiceByCustomerId(@RequestParam Integer id) {
        invoiceService. deleteInvoiceByCustomerId(id);

    }

    @RequestMapping(value="/deleteByUpdatedDate", method = RequestMethod.GET)
    public void deleteByUpdatedDate(@RequestParam("updatedDate") Date updatedDate) {
        invoiceService.deleteByUpdatedDate(updatedDate);
    }

    @RequestMapping(value="/deleteByCreatedDate", method = RequestMethod.GET)
    public void deleteByCreatedDate(@RequestParam("createdDate") Date createdDate) {
        invoiceService.deleteByCreatedDate(createdDate);
    }
    @RequestMapping(value="/deleteByCreatedAfterDate", method = RequestMethod.GET)
    public void deleteByAfterCreatedDate(@RequestParam("date") Date date) {
        invoiceService.deleteByCreatedAfterDate(date);
    }


    @RequestMapping(value = "/getByCreatedDate", method = RequestMethod.GET)
    public Invoice getInvoiceByUpdatedDate(Date updatedDate){
        Invoice invoice = invoiceService.getInvoiceByUpdatedDate(updatedDate);
        return invoice;
    }

}
