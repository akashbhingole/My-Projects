package com.invoice.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.invoice.model.Invoice;


public interface InvoiceService {
	
	public boolean isValidUser(String username, String password) throws Exception;
	
	public List<Invoice> getInvoiceList() throws Exception;

	public Invoice getInvoiceDetails(int id) throws Exception;
	
	public Invoice addNewInvoice(HttpServletRequest request) throws Exception;
	
	public void deleteInvoiceDetails(Invoice invoice) throws Exception;
	
	public JSONObject getJsonObjectFromInvoice(Invoice invoice) throws Exception;
}
