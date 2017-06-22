package com.invoice.dao;

import java.util.List;

import com.invoice.model.Invoice;


public interface InvoiceDao {
	
	public boolean isValidUser(String username, String password) throws Exception;
	
	public List<Invoice> getInvoiceList() throws Exception;
	
	public Invoice getInvoiceDetails(int id) throws Exception;
	
	public Invoice addNewInvoice(Invoice invoice) throws Exception;
	
	public void deleteInvoiceDetails(Invoice invoice) throws Exception;
}
