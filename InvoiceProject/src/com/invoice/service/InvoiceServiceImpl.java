package com.invoice.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.invoice.dao.InvoiceDao;
import com.invoice.model.Invoice;


@Service("invoiceService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceDao invoiceDao;
	
	@Override
	public boolean isValidUser(String username, String password) throws Exception {
		return invoiceDao.isValidUser(username, password);
	}

	@Override
	public List<Invoice> getInvoiceList() throws Exception {
		return invoiceDao.getInvoiceList();
	}
	
	@Override
	public Invoice getInvoiceDetails(int id) throws Exception{
		return invoiceDao.getInvoiceDetails(id);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Invoice addNewInvoice(HttpServletRequest request) throws Exception {
		String idStr = request.getParameter("id");
		String customerName = request.getParameter("customerName");
		String customerAddress = request.getParameter("customerAddress");
		String itemName = request.getParameter("itemName");
		String priceStr = request.getParameter("price");
		String quantityStr = request.getParameter("quantity");
		String totalStr = request.getParameter("total");
		
		int id = 0;
		if(idStr!=null && idStr.isEmpty()==false) {
			id = Integer.parseInt(idStr);
		}
		
		double price = 0.0;
		if(priceStr!=null && priceStr.isEmpty()==false) {
			price = Double.parseDouble(priceStr);
		}
		
		int quantity = 0;
		if(quantityStr!=null && quantityStr.isEmpty()==false) {
			quantity = Integer.parseInt(quantityStr);
		}
		
		double total = 0.0;
		if(totalStr!=null && totalStr.isEmpty()==false) {
			total = Double.parseDouble(totalStr);
		}
		
		Invoice invoice = null;
		if(id!=0) {
			invoice = invoiceDao.getInvoiceDetails(id);
		}
		else {
			invoice = new Invoice();
		}
		
		invoice.setCustomerName(customerName);
		invoice.setCustomerAddress(customerAddress);
		invoice.setItemName(itemName);
		invoice.setPrice(price);
		invoice.setQuantity(quantity);
		invoice.setTotal(total);
		
		invoice = invoiceDao.addNewInvoice(invoice);
		return invoice;
	}
	
	
	@Override
	public void deleteInvoiceDetails(Invoice invoice) throws Exception{
		invoiceDao.deleteInvoiceDetails(invoice);
	}

	@Override
	public JSONObject getJsonObjectFromInvoice(Invoice invoice) throws Exception {
		JSONObject invoiceObj = new JSONObject();
		
		invoiceObj.put("id", invoice.getId());
		invoiceObj.put("customerName", invoice.getCustomerName());
		invoiceObj.put("customerAddress", invoice.getCustomerAddress());
		invoiceObj.put("itemName", invoice.getItemName());
		invoiceObj.put("price", invoice.getPrice());
		invoiceObj.put("quantity", invoice.getQuantity());
		invoiceObj.put("total", invoice.getTotal());
		
		return invoiceObj;
	}

}
