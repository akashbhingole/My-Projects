package com.invoice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invoice.model.Invoice;
import com.invoice.service.InvoiceService;


@Controller
@RequestMapping("/invoice")
public class InvoiceController {
	
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	@Autowired
	private InvoiceService invoiceService;
	
	
	@RequestMapping(value="/invoices", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getInvoiceList() {
		LOGGER.info("Inside getInvoiceList method in InvoiceController");
		String result = "";
		try {
			JSONArray invoiceArr = new JSONArray();
			List<Invoice> invoiceList = invoiceService.getInvoiceList();
			for(Invoice invoice: invoiceList) {
				JSONObject invoiceObj = invoiceService.getJsonObjectFromInvoice(invoice);
				invoiceArr.put(invoiceObj);
			}
			result = invoiceArr.toString();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("Exception in getInvoiceList method in InvoiceController ", e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exit from getInvoiceList method in InvoiceController");
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/details/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getInvoiceDetails(@PathVariable int id) {
		LOGGER.info("Inside getInvoiceDetails method in InvoiceController");
		String result = "";
		try {
			Invoice invoice = invoiceService.getInvoiceDetails(id);
			if(invoice!=null) {
				JSONObject invoiceObj = invoiceService.getJsonObjectFromInvoice(invoice);
				result = invoiceObj.toString();
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("Exception in getInvoiceDetails method in InvoiceController ", e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exit from getInvoiceDetails method in InvoiceController");
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> addNewInvoiceDetails(HttpServletRequest request) {
		LOGGER.info("Inside addNewInvoiceDetails method in InvoiceController");
		String result = "";
		try {
			Invoice invoice = invoiceService.addNewInvoice(request);
			if(invoice!=null) {
				JSONObject invoiceObj = invoiceService.getJsonObjectFromInvoice(invoice);
				result = invoiceObj.toString();
			}
		} 
		catch (Exception e) {
			LOGGER.error("Exception in addNewInvoiceDetails method in InvoiceController ", e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exit from addNewInvoiceDetails method in InvoiceController");
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{invoiceId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<String> deleteInvoiceDetails(@PathVariable int invoiceId) {
		LOGGER.info("Inside deleteInvoiceDetails method in InvoiceController");
		String result = "";
		try {
			Invoice invoice = invoiceService.getInvoiceDetails(invoiceId);
			if(invoice!=null) {
				invoiceService.deleteInvoiceDetails(invoice);
			}
		} 
		catch (Exception e) {
			LOGGER.error("Exception in deleteInvoiceDetails method in InvoiceController ", e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exit from deleteInvoiceDetails method in InvoiceController");
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
}
